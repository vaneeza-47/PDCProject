import shared.FileManager;
import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

/**
 * Standalone utility for uploading and managing CSV files
 */
public class FileUploadUtility {
    private static final String SERVER1_DIR = "data/server1/";
    private static final String SERVER2_DIR = "data/server2/";
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== File Upload Utility ===");
        System.out.println("This utility helps you manage CSV files for the distributed system.");
        
        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Upload a CSV file");
            System.out.println("2. List existing files");
            System.out.println("3. Validate a file");
            System.out.println("4. View file information");
            System.out.println("5. Generate sample data");
            System.out.println("6. Delete a file");
            System.out.println("7. Exit");
            System.out.print("Choose an option (1-7): ");
            
            String choice = scanner.nextLine().trim();
            
            switch (choice) {
                case "1":
                    uploadFile(scanner);
                    break;
                case "2":
                    listFiles();
                    break;
                case "3":
                    validateFile(scanner);
                    break;
                case "4":
                    viewFileInfo(scanner);
                    break;
                case "5":
                    generateSampleData();
                    break;
                case "6":
                    deleteFile(scanner);
                    break;
                case "7":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    
    private static void uploadFile(Scanner scanner) {
        System.out.print("Enter the full path to your CSV file: ");
        String filePath = scanner.nextLine().trim();
        
        File sourceFile = new File(filePath);
        if (!sourceFile.exists()) {
            System.out.println("File not found: " + filePath);
            return;
        }
        
        System.out.print("Enter a name for this file (without .csv extension): ");
        String fileName = scanner.nextLine().trim();
        
        if (fileName.isEmpty()) {
            System.out.println("File name cannot be empty.");
            return;
        }
        
        try {
            // Create directories if they don't exist
            new File(SERVER1_DIR).mkdirs();
            new File(SERVER2_DIR).mkdirs();
            
            // Validate file first
            FileManager.ValidationResult validation = FileManager.validateCSVFile(filePath);
            System.out.println("\nFile Validation:");
            System.out.println(validation.getSummary());
            
            if (!validation.isValid) {
                System.out.print("File validation failed. Continue anyway? (y/n): ");
                if (!scanner.nextLine().trim().toLowerCase().startsWith("y")) {
                    System.out.println("Upload cancelled.");
                    return;
                }
            }
            
            // Copy to both server directories
            Path source = sourceFile.toPath();
            Path server1Target = Paths.get(SERVER1_DIR + fileName + ".csv");
            Path server2Target = Paths.get(SERVER2_DIR + fileName + ".csv");
            
            Files.copy(source, server1Target, StandardCopyOption.REPLACE_EXISTING);
            Files.copy(source, server2Target, StandardCopyOption.REPLACE_EXISTING);
            
            System.out.println("✓ File uploaded successfully!");
            System.out.println("  Server 1: " + server1Target.toAbsolutePath());
            System.out.println("  Server 2: " + server2Target.toAbsolutePath());
            
        } catch (IOException e) {
            System.out.println("Error uploading file: " + e.getMessage());
        }
    }
    
    private static void listFiles() {
        System.out.println("\n=== Available Files ===");
        
        System.out.println("Server 1 files:");
        var server1Files = FileManager.listAvailableFiles(SERVER1_DIR);
        if (server1Files.isEmpty()) {
            System.out.println("  No files found");
        } else {
            for (String file : server1Files) {
                System.out.println("  - " + file);
            }
        }
        
        System.out.println("Server 2 files:");
        var server2Files = FileManager.listAvailableFiles(SERVER2_DIR);
        if (server2Files.isEmpty()) {
            System.out.println("  No files found");
        } else {
            for (String file : server2Files) {
                System.out.println("  - " + file);
            }
        }
    }
    
    private static void validateFile(Scanner scanner) {
        System.out.print("Enter file name to validate (without .csv): ");
        String fileName = scanner.nextLine().trim();
        
        String server1Path = SERVER1_DIR + fileName + ".csv";
        String server2Path = SERVER2_DIR + fileName + ".csv";
        
        System.out.println("\nValidating Server 1 file:");
        if (new File(server1Path).exists()) {
            FileManager.ValidationResult result = FileManager.validateCSVFile(server1Path);
            System.out.println(result.getSummary());
        } else {
            System.out.println("File not found: " + server1Path);
        }
        
        System.out.println("\nValidating Server 2 file:");
        if (new File(server2Path).exists()) {
            FileManager.ValidationResult result = FileManager.validateCSVFile(server2Path);
            System.out.println(result.getSummary());
        } else {
            System.out.println("File not found: " + server2Path);
        }
    }
    
    private static void viewFileInfo(Scanner scanner) {
        System.out.print("Enter file name to analyze (without .csv): ");
        String fileName = scanner.nextLine().trim();
        
        String server1Path = SERVER1_DIR + fileName + ".csv";
        String server2Path = SERVER2_DIR + fileName + ".csv";
        
        System.out.println("\n=== File Information ===");
        
        System.out.println("Server 1:");
        FileManager.FileInfo info1 = FileManager.getFileInfo(server1Path);
        if (info1 != null) {
            System.out.println(info1.toString());
        } else {
            System.out.println("File not found or error reading file");
        }
        
        System.out.println("\nServer 2:");
        FileManager.FileInfo info2 = FileManager.getFileInfo(server2Path);
        if (info2 != null) {
            System.out.println(info2.toString());
        } else {
            System.out.println("File not found or error reading file");
        }
        
        if (info1 != null && info2 != null) {
            System.out.println("\nConsistency Check:");
            if (Math.abs(info1.sum - info2.sum) < 0.001) {
                System.out.println("✓ PASSED - Both servers have consistent data");
                System.out.println("Verified sum: " + info1.sum);
            } else {
                System.out.println("❌ FAILED - Servers have different data!");
                System.out.println("Server 1 sum: " + info1.sum);
                System.out.println("Server 2 sum: " + info2.sum);
                System.out.println("Difference: " + Math.abs(info1.sum - info2.sum));
            }
        }
    }
    
    private static void generateSampleData() {
        System.out.println("Generating sample data files...");
        
        try {
            // Create directories
            new File(SERVER1_DIR).mkdirs();
            new File(SERVER2_DIR).mkdirs();
            
            String[] files = {"file1", "file2", "file3"};
            String[] servers = {"server1", "server2"};
            java.util.Random random = new java.util.Random();
            
            for (String file : files) {
                // Generate data once for each file
                java.util.List<Integer> data = new java.util.ArrayList<>();
                for (int i = 0; i < 500; i++) {
                    int value = random.nextInt(1000) + 1;
                    data.add(value);
                }
                
                // Write the same data to both servers
                for (String server : servers) {
                    String filePath = "data/" + server + "/" + file + ".csv";
                    
                    try (FileWriter writer = new FileWriter(filePath)) {
                        for (Integer value : data) {
                            writer.write(value + "\n");
                        }
                        System.out.println("Generated: " + filePath);
                    }
                }
            }
            
            System.out.println("✓ Sample data generation completed!");
            System.out.println("Note: Both servers now have identical files for consistency checking.");
            
        } catch (IOException e) {
            System.out.println("Error generating sample data: " + e.getMessage());
        }
    }
    
    private static void deleteFile(Scanner scanner) {
        System.out.print("Enter file name to delete (without .csv): ");
        String fileName = scanner.nextLine().trim();
        
        if (fileName.isEmpty()) {
            System.out.println("File name cannot be empty.");
            return;
        }
        
        String server1Path = SERVER1_DIR + fileName + ".csv";
        String server2Path = SERVER2_DIR + fileName + ".csv";
        
        File server1File = new File(server1Path);
        File server2File = new File(server2Path);
        
        // Check if files exist
        boolean server1Exists = server1File.exists();
        boolean server2Exists = server2File.exists();
        
        if (!server1Exists && !server2Exists) {
            System.out.println("File '" + fileName + "' not found on either server.");
            return;
        }
        
        // Show what will be deleted
        System.out.println("\nFiles to be deleted:");
        if (server1Exists) {
            System.out.println("  Server 1: " + server1Path);
        }
        if (server2Exists) {
            System.out.println("  Server 2: " + server2Path);
        }
        
        // Confirm deletion
        System.out.print("\nAre you sure you want to delete these files? (y/n): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();
        
        if (!confirmation.startsWith("y")) {
            System.out.println("Deletion cancelled.");
            return;
        }
        
        // Perform deletion
        boolean success1 = true, success2 = true;
        
        if (server1Exists) {
            success1 = server1File.delete();
            if (success1) {
                System.out.println("✓ Deleted from Server 1");
            } else {
                System.out.println("❌ Failed to delete from Server 1");
            }
        }
        
        if (server2Exists) {
            success2 = server2File.delete();
            if (success2) {
                System.out.println("✓ Deleted from Server 2");
            } else {
                System.out.println("❌ Failed to delete from Server 2");
            }
        }
        
        if (success1 && success2) {
            System.out.println("✓ File deletion completed successfully!");
        } else {
            System.out.println("⚠ File deletion completed with some issues.");
        }
    }
}