package server;

import shared.DataProcessor;
import shared.EncryptionUtil;
import java.io.*;
import java.net.*;

/**
 * Server 2 - Handles client requests and processes data from server2 directory
 */
public class Server2 {
    private static final int PORT = 8082;
    private static final String DATA_DIR = "data/server2/";
    
    public static void main(String[] args) {
        System.out.println("Starting Server 2 on port " + PORT + "...");
        
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server 2 is running and waiting for connections...");
            
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected to Server 2");
                
                // Handle client request in a separate thread
                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (IOException e) {
            System.err.println("Server 2 error: " + e.getMessage());
        }
    }
    
    private static void handleClient(Socket clientSocket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
            
            String request = in.readLine();
            System.out.println("Server 2 received request: " + request);
            
            if (request != null && request.startsWith("GET_SUM:")) {
                String fileName = request.substring(8); // Remove "GET_SUM:" prefix
                String filePath = DATA_DIR + fileName + ".csv";
                
                // Calculate sum from the requested file
                double sum = DataProcessor.calculateSum(filePath);
                System.out.println("Server 2 calculated sum: " + sum + " from " + fileName);
                
                // Encrypt the result
                String encryptedResult = EncryptionUtil.encrypt(String.valueOf(sum));
                
                // Send encrypted result back to client
                out.println("RESULT:" + encryptedResult);
                System.out.println("Server 2 sent encrypted result to client");
                
            } else {
                out.println("ERROR:Invalid request format");
            }
            
        } catch (IOException e) {
            System.err.println("Error handling client in Server 2: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Error closing client socket: " + e.getMessage());
            }
        }
    }
}