package shared;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for processing CSV data files
 * Reads CSV files and calculates sum of values
 */
public class DataProcessor {
    
    /**
     * Reads a CSV file and returns the sum of all numeric values in the first column
     */
    public static double calculateSum(String filePath) {
        double sum = 0.0;
        int count = 0;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            
            // Skip header if exists
            boolean firstLine = true;
            
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                
                // Skip header row (assuming first line might be header)
                if (firstLine && !isNumeric(line)) {
                    firstLine = false;
                    continue;
                }
                firstLine = false;
                
                try {
                    double value = Double.parseDouble(line);
                    sum += value;
                    count++;
                } catch (NumberFormatException e) {
                    System.err.println("Skipping non-numeric value: " + line);
                }
            }
            
            System.out.println("Processed " + count + " values from " + filePath);
            
        } catch (IOException e) {
            System.err.println("Error reading file " + filePath + ": " + e.getMessage());
        }
        
        return sum;
    }
    
    /**
     * Helper method to check if a string is numeric
     */
    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    /**
     * Generates sample data for testing
     */
    public static List<Double> generateSampleData(int count) {
        List<Double> data = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            data.add((double) i);
        }
        return data;
    }
}