package client;

import shared.EncryptionUtil;
import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * Client - Connects to both servers, requests data, and decrypts results
 */
public class Client {
    private static final String SERVER1_HOST = "localhost";
    private static final int SERVER1_PORT = 8081;
    private static final String SERVER2_HOST = "localhost";
    private static final int SERVER2_PORT = 8082;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Distributed Computing Client ===");
        System.out.println("Available files: file1, file2, file3");
        
        while (true) {
            System.out.print("\nEnter file name to request sum (or 'quit' to exit): ");
            String fileName = scanner.nextLine().trim();
            
            if ("quit".equalsIgnoreCase(fileName)) {
                System.out.println("Goodbye!");
                break;
            }
            
            if (!fileName.matches("file[1-3]")) {
                System.out.println("Invalid file name. Please enter file1, file2, or file3");
                continue;
            }
            
            // Request data from both servers
            double server1Result = requestDataFromServer(SERVER1_HOST, SERVER1_PORT, fileName, "Server 1");
            double server2Result = requestDataFromServer(SERVER2_HOST, SERVER2_PORT, fileName, "Server 2");
            
            if (server1Result != -1 && server2Result != -1) {
                System.out.println("\n=== RESULTS ===");
                System.out.println("Server 1 sum: " + server1Result);
                System.out.println("Server 2 sum: " + server2Result);
                
                // Check consistency between servers
                if (Math.abs(server1Result - server2Result) < 0.001) { // Allow small floating point differences
                    System.out.println("✓ CONSISTENCY CHECK PASSED");
                    System.out.println("Final verified sum: " + server1Result);
                } else {
                    System.out.println("❌ CONSISTENCY CHECK FAILED");
                    System.out.println("ERROR: Servers returned different results!");
                    System.out.println("Difference: " + Math.abs(server1Result - server2Result));
                }
                System.out.println("================");
            } else {
                System.out.println("Failed to get results from one or both servers");
            }
        }
        
        scanner.close();
    }
    
    private static double requestDataFromServer(String host, int port, String fileName, String serverName) {
        try (Socket socket = new Socket(host, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            
            System.out.println("Connecting to " + serverName + "...");
            
            // Send request
            String request = "GET_SUM:" + fileName;
            out.println(request);
            System.out.println("Sent request to " + serverName + ": " + request);
            
            // Receive response
            String response = in.readLine();
            System.out.println("Received encrypted response from " + serverName);
            
            if (response != null && response.startsWith("RESULT:")) {
                String encryptedData = response.substring(7); // Remove "RESULT:" prefix
                
                // Decrypt the result
                String decryptedData = EncryptionUtil.decrypt(encryptedData);
                if (decryptedData != null) {
                    double result = Double.parseDouble(decryptedData);
                    System.out.println(serverName + " decrypted result: " + result);
                    return result;
                } else {
                    System.err.println("Failed to decrypt data from " + serverName);
                }
            } else if (response != null && response.startsWith("ERROR:")) {
                System.err.println(serverName + " error: " + response.substring(6));
            } else {
                System.err.println("Invalid response from " + serverName + ": " + response);
            }
            
        } catch (IOException e) {
            System.err.println("Error connecting to " + serverName + ": " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing result from " + serverName + ": " + e.getMessage());
        }
        
        return -1; // Error indicator
    }
}