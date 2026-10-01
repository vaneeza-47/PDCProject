import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility to generate sample CSV files with 500 identical values for both servers
 */
public class GenerateData {
    public static void main(String[] args) {
        String[] servers = {"server1", "server2"};
        String[] files = {"file1", "file2", "file3"};
        
        for (String file : files) {
            // Generate data once for each file
            List<Integer> data = generateFileData(500);
            
            // Write the same data to both servers
            for (String server : servers) {
                String filePath = "data/" + server + "/" + file + ".csv";
                
                try (FileWriter writer = new FileWriter(filePath)) {
                    for (Integer value : data) {
                        writer.write(value + "\n");
                    }
                    System.out.println("Generated " + filePath);
                } catch (IOException e) {
                    System.err.println("Error creating " + filePath + ": " + e.getMessage());
                }
            }
        }
        
        System.out.println("Data generation complete!");
        System.out.println("Note: Both servers now have identical files for consistency checking.");
    }
    
    private static List<Integer> generateFileData(int count) {
        List<Integer> data = new ArrayList<>();
        Random random = new Random();
        
        // Use a fixed seed for each file to ensure reproducibility if needed
        // But generate different data for each file (file1, file2, file3)
        for (int i = 0; i < count; i++) {
            int value = random.nextInt(1000) + 1;
            data.add(value);
        }
        
        return data;
    }
}