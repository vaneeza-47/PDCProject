package shared;

import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * Utility class for managing CSV files in the distributed system
 */
public class FileManager {
    
    /**
     * Lists all available CSV files in a directory
     */
    public static List<String> listAvailableFiles(String directory) {
        List<String> files = new ArrayList<>();
        File dir = new File(directory);
        
        if (dir.exists() && dir.isDirectory()) {
            File[] csvFiles = dir.listFiles((d, name) -> name.endsWith(".csv"));
            if (csvFiles != null) {
                for (File file : csvFiles) {
                    files.add(file.getName().replace(".csv", ""));
                }
            }
        }
        
        Collections.sort(files);
        return files;
    }
    
    /**
     * Checks if a file exists in the specified directory
     */
    public static boolean fileExists(String directory, String fileName) {
        File file = new File(directory + "/" + fileName + ".csv");
        return file.exists() && file.isFile();
    }
    
    /**
     * Gets file information (size, line count, etc.)
     */
    public static FileInfo getFileInfo(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return null;
        }
        
        FileInfo info = new FileInfo();
        info.fileName = file.getName();
        info.filePath = filePath;
        info.fileSize = file.length();
        info.lastModified = new Date(file.lastModified());
        
        // Count lines and calculate basic stats
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int lineCount = 0;
            double sum = 0;
            double min = Double.MAX_VALUE;
            double max = Double.MIN_VALUE;
            
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                
                try {
                    double value = Double.parseDouble(line);
                    lineCount++;
                    sum += value;
                    min = Math.min(min, value);
                    max = Math.max(max, value);
                } catch (NumberFormatException e) {
                    // Skip non-numeric lines
                }
            }
            
            info.lineCount = lineCount;
            info.sum = sum;
            info.average = lineCount > 0 ? sum / lineCount : 0;
            info.min = lineCount > 0 ? min : 0;
            info.max = lineCount > 0 ? max : 0;
            
        } catch (IOException e) {
            System.err.println("Error reading file info: " + e.getMessage());
        }
        
        return info;
    }
    
    /**
     * Validates CSV file format
     */
    public static ValidationResult validateCSVFile(String filePath) {
        ValidationResult result = new ValidationResult();
        result.isValid = true;
        result.errors = new ArrayList<>();
        result.warnings = new ArrayList<>();
        
        File file = new File(filePath);
        if (!file.exists()) {
            result.isValid = false;
            result.errors.add("File does not exist: " + filePath);
            return result;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNumber = 0;
            int validNumbers = 0;
            
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                line = line.trim();
                
                if (line.isEmpty()) {
                    result.warnings.add("Empty line at line " + lineNumber);
                    continue;
                }
                
                try {
                    Double.parseDouble(line);
                    validNumbers++;
                } catch (NumberFormatException e) {
                    result.warnings.add("Non-numeric value at line " + lineNumber + ": " + line);
                }
            }
            
            if (validNumbers == 0) {
                result.isValid = false;
                result.errors.add("No valid numeric values found in file");
            } else if (validNumbers < 10) {
                result.warnings.add("File contains only " + validNumbers + " valid numbers");
            }
            
            result.totalLines = lineNumber;
            result.validNumbers = validNumbers;
            
        } catch (IOException e) {
            result.isValid = false;
            result.errors.add("Error reading file: " + e.getMessage());
        }
        
        return result;
    }
    
    /**
     * Creates a backup of a file
     */
    public static boolean backupFile(String filePath) {
        try {
            Path source = Paths.get(filePath);
            Path backup = Paths.get(filePath + ".backup." + System.currentTimeMillis());
            Files.copy(source, backup, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            System.err.println("Error creating backup: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Deletes a file from both server directories
     */
    public static DeleteResult deleteFile(String fileName) {
        DeleteResult result = new DeleteResult();
        result.fileName = fileName;
        
        String server1Path = "data/server1/" + fileName + ".csv";
        String server2Path = "data/server2/" + fileName + ".csv";
        
        File server1File = new File(server1Path);
        File server2File = new File(server2Path);
        
        result.server1Exists = server1File.exists();
        result.server2Exists = server2File.exists();
        
        if (!result.server1Exists && !result.server2Exists) {
            result.success = false;
            result.message = "File not found on either server";
            return result;
        }
        
        // Create backups before deletion
        if (result.server1Exists) {
            backupFile(server1Path);
        }
        if (result.server2Exists) {
            backupFile(server2Path);
        }
        
        // Perform deletion
        result.server1Deleted = !result.server1Exists || server1File.delete();
        result.server2Deleted = !result.server2Exists || server2File.delete();
        
        result.success = result.server1Deleted && result.server2Deleted;
        
        if (result.success) {
            result.message = "File deleted successfully from both servers";
        } else {
            result.message = "File deletion completed with some issues";
        }
        
        return result;
    }
    
    /**
     * File information container
     */
    public static class FileInfo {
        public String fileName;
        public String filePath;
        public long fileSize;
        public Date lastModified;
        public int lineCount;
        public double sum;
        public double average;
        public double min;
        public double max;
        
        @Override
        public String toString() {
            return String.format(
                "File: %s\nLines: %d\nSum: %.2f\nAverage: %.2f\nMin: %.2f\nMax: %.2f\nSize: %d bytes",
                fileName, lineCount, sum, average, min, max, fileSize
            );
        }
    }
    
    /**
     * Validation result container
     */
    public static class ValidationResult {
        public boolean isValid;
        public List<String> errors;
        public List<String> warnings;
        public int totalLines;
        public int validNumbers;
        
        public String getSummary() {
            StringBuilder sb = new StringBuilder();
            sb.append("Validation ").append(isValid ? "PASSED" : "FAILED").append("\n");
            sb.append("Total lines: ").append(totalLines).append("\n");
            sb.append("Valid numbers: ").append(validNumbers).append("\n");
            
            if (!errors.isEmpty()) {
                sb.append("Errors:\n");
                for (String error : errors) {
                    sb.append("  - ").append(error).append("\n");
                }
            }
            
            if (!warnings.isEmpty()) {
                sb.append("Warnings:\n");
                for (String warning : warnings) {
                    sb.append("  - ").append(warning).append("\n");
                }
            }
            
            return sb.toString();
        }
    }
    
    /**
     * Delete operation result container
     */
    public static class DeleteResult {
        public String fileName;
        public boolean success;
        public String message;
        public boolean server1Exists;
        public boolean server2Exists;
        public boolean server1Deleted;
        public boolean server2Deleted;
        
        public String getSummary() {
            StringBuilder sb = new StringBuilder();
            sb.append("Delete operation for: ").append(fileName).append("\n");
            sb.append("Result: ").append(success ? "SUCCESS" : "PARTIAL/FAILED").append("\n");
            sb.append("Message: ").append(message).append("\n");
            
            if (server1Exists) {
                sb.append("Server 1: ").append(server1Deleted ? "Deleted" : "Failed to delete").append("\n");
            } else {
                sb.append("Server 1: File not found\n");
            }
            
            if (server2Exists) {
                sb.append("Server 2: ").append(server2Deleted ? "Deleted" : "Failed to delete").append("\n");
            } else {
                sb.append("Server 2: File not found\n");
            }
            
            return sb.toString();
        }
    }
}