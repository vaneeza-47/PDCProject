package client;

import shared.EncryptionUtil;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * GUI Client for the Distributed Computing System
 */
public class ClientGUI extends JFrame {
    private static final String SERVER1_HOST = "localhost";
    private static final int SERVER1_PORT = 8081;
    private static final String SERVER2_HOST = "localhost";
    private static final int SERVER2_PORT = 8082;
    
    // GUI Components
    private JComboBox<String> fileSelector;
    private JButton requestButton;
    private JButton uploadButton;
    private JButton generateDataButton;
    private JTextArea resultArea;
    private JTextArea logArea;
    private JLabel statusLabel;
    private JProgressBar progressBar;
    
    public ClientGUI() {
        initializeGUI();
    }
    
    private void initializeGUI() {
        setTitle("Secure Distributed Summation System (SDSS)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Create main panels
        JPanel topPanel = createTopPanel();
        JPanel centerPanel = createCenterPanel();
        JPanel bottomPanel = createBottomPanel();
        
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        
        // Set window properties
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(true);
        
        // Initialize components
        updateFileSelector();
        logMessage("GUI initialized. Ready to connect to servers.");
    }
    
    private JPanel createTopPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new TitledBorder("File Selection & Actions"));
        GridBagConstraints gbc = new GridBagConstraints();
        
        // File selector
        gbc.gridx = 0; gbc.gridy = 0; gbc.insets = new Insets(5, 5, 5, 5);
        panel.add(new JLabel("Select File:"), gbc);
        
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        fileSelector = new JComboBox<>();
        panel.add(fileSelector, gbc);
        
        // Request button
        gbc.gridx = 2; gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0;
        requestButton = new JButton("Request Sum");
        requestButton.addActionListener(e -> requestSum());
        panel.add(requestButton, gbc);
        
        // Second row - Upload and Generate buttons
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        uploadButton = new JButton("Upload CSV File");
        uploadButton.addActionListener(e -> uploadFile());
        panel.add(uploadButton, gbc);
        
        gbc.gridx = 1;
        generateDataButton = new JButton("Generate Sample Data");
        generateDataButton.addActionListener(e -> generateSampleData());
        panel.add(generateDataButton, gbc);
        
        gbc.gridx = 2;
        JButton refreshButton = new JButton("Refresh Files");
        refreshButton.addActionListener(e -> updateFileSelector());
        panel.add(refreshButton, gbc);
        
        // Third row - Delete button
        gbc.gridx = 0; gbc.gridy = 2;
        JButton deleteButton = new JButton("Delete Selected File");
        deleteButton.addActionListener(e -> deleteSelectedFile());
        panel.add(deleteButton, gbc);
        
        return panel;
    }
    
    private JPanel createCenterPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 10, 0));
        
        // Results panel
        JPanel resultsPanel = new JPanel(new BorderLayout());
        resultsPanel.setBorder(new TitledBorder("Results"));
        
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        resultArea.setBackground(new Color(248, 248, 248));
        JScrollPane resultScroll = new JScrollPane(resultArea);
        resultsPanel.add(resultScroll, BorderLayout.CENTER);
        
        // Log panel
        JPanel logPanel = new JPanel(new BorderLayout());
        logPanel.setBorder(new TitledBorder("Activity Log"));
        
        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 11));
        logArea.setBackground(new Color(240, 240, 240));
        JScrollPane logScroll = new JScrollPane(logArea);
        logPanel.add(logScroll, BorderLayout.CENTER);
        
        panel.add(resultsPanel);
        panel.add(logPanel);
        
        return panel;
    }
    
    private JPanel createBottomPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Status and progress
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusLabel = new JLabel("Ready");
        statusPanel.add(statusLabel);
        
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setString("Ready");
        
        panel.add(statusPanel, BorderLayout.WEST);
        panel.add(progressBar, BorderLayout.CENTER);
        
        return panel;
    }
    
    private void updateFileSelector() {
        fileSelector.removeAllItems();
        
        // Check for existing files
        File server1Dir = new File("data/server1");
        if (server1Dir.exists()) {
            File[] files = server1Dir.listFiles((dir, name) -> name.endsWith(".csv"));
            if (files != null) {
                for (File file : files) {
                    String fileName = file.getName().replace(".csv", "");
                    fileSelector.addItem(fileName);
                }
            }
        }
        
        if (fileSelector.getItemCount() == 0) {
            fileSelector.addItem("No files available");
            requestButton.setEnabled(false);
        } else {
            requestButton.setEnabled(true);
        }
    }
    
    private void uploadFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("CSV Files", "csv"));
        fileChooser.setDialogTitle("Select CSV File to Upload");
        
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            
            // Ask for file name
            String fileName = JOptionPane.showInputDialog(this, 
                "Enter a name for this file (without .csv extension):", 
                selectedFile.getName().replace(".csv", ""));
            
            if (fileName != null && !fileName.trim().isEmpty()) {
                fileName = fileName.trim();
                uploadFileToServers(selectedFile, fileName);
            }
        }
    }
    
    private void uploadFileToServers(File sourceFile, String fileName) {
        SwingWorker<Void, String> worker = new SwingWorker<Void, String>() {
            @Override
            protected Void doInBackground() throws Exception {
                publish("Starting file upload...");
                progressBar.setIndeterminate(true);
                statusLabel.setText("Uploading file...");
                
                // Create directories if they don't exist
                new File("data/server1").mkdirs();
                new File("data/server2").mkdirs();
                
                // Copy file to both server directories
                File server1File = new File("data/server1/" + fileName + ".csv");
                File server2File = new File("data/server2/" + fileName + ".csv");
                
                Files.copy(sourceFile.toPath(), server1File.toPath(), StandardCopyOption.REPLACE_EXISTING);
                publish("Uploaded to Server 1: " + server1File.getAbsolutePath());
                
                Files.copy(sourceFile.toPath(), server2File.toPath(), StandardCopyOption.REPLACE_EXISTING);
                publish("Uploaded to Server 2: " + server2File.getAbsolutePath());
                
                publish("File upload completed successfully!");
                
                return null;
            }
            
            @Override
            protected void process(java.util.List<String> chunks) {
                for (String message : chunks) {
                    logMessage(message);
                }
            }
            
            @Override
            protected void done() {
                progressBar.setIndeterminate(false);
                progressBar.setValue(0);
                statusLabel.setText("Ready");
                updateFileSelector();
                
                try {
                    get(); // Check for exceptions
                    JOptionPane.showMessageDialog(ClientGUI.this, 
                        "File uploaded successfully to both servers!", 
                        "Upload Complete", 
                        JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(ClientGUI.this, 
                        "Error uploading file: " + e.getMessage(), 
                        "Upload Error", 
                        JOptionPane.ERROR_MESSAGE);
                    logMessage("Upload error: " + e.getMessage());
                }
            }
        };
        
        worker.execute();
    }
    
    private void generateSampleData() {
        SwingWorker<Void, String> worker = new SwingWorker<Void, String>() {
            @Override
            protected Void doInBackground() throws Exception {
                publish("Generating sample data files...");
                progressBar.setIndeterminate(true);
                statusLabel.setText("Generating data...");
                
                String[] files = {"file1", "file2", "file3"};
                String[] servers = {"server1", "server2"};
                
                // Create directories
                new File("data/server1").mkdirs();
                new File("data/server2").mkdirs();
                
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
                            publish("Generated: " + filePath);
                        }
                    }
                }
                
                publish("Sample data generation completed!");
                publish("Note: Both servers now have identical files for consistency checking.");
                return null;
            }
            
            @Override
            protected void process(java.util.List<String> chunks) {
                for (String message : chunks) {
                    logMessage(message);
                }
            }
            
            @Override
            protected void done() {
                progressBar.setIndeterminate(false);
                progressBar.setValue(0);
                statusLabel.setText("Ready");
                updateFileSelector();
                
                JOptionPane.showMessageDialog(ClientGUI.this, 
                    "Sample data files generated successfully!\nBoth servers now have identical files.", 
                    "Generation Complete", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
        };
        
        worker.execute();
    }
    
    private void requestSum() {
        String selectedFile = (String) fileSelector.getSelectedItem();
        if (selectedFile == null || selectedFile.equals("No files available")) {
            JOptionPane.showMessageDialog(this, "Please select a valid file first.", "No File Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        SwingWorker<Void, String> worker = new SwingWorker<Void, String>() {
            private double server1Result = -1;
            private double server2Result = -1;
            
            @Override
            protected Void doInBackground() throws Exception {
                publish("Starting distributed sum calculation for: " + selectedFile);
                progressBar.setIndeterminate(true);
                statusLabel.setText("Processing request...");
                
                // Request from both servers simultaneously
                Thread server1Thread = new Thread(() -> {
                    server1Result = requestDataFromServer(SERVER1_HOST, SERVER1_PORT, selectedFile, "Server 1");
                });
                
                Thread server2Thread = new Thread(() -> {
                    server2Result = requestDataFromServer(SERVER2_HOST, SERVER2_PORT, selectedFile, "Server 2");
                });
                
                server1Thread.start();
                server2Thread.start();
                
                server1Thread.join();
                server2Thread.join();
                
                return null;
            }
            
            @Override
            protected void process(java.util.List<String> chunks) {
                for (String message : chunks) {
                    logMessage(message);
                }
            }
            
            @Override
            protected void done() {
                progressBar.setIndeterminate(false);
                progressBar.setValue(100);
                statusLabel.setText("Request completed");
                
                if (server1Result != -1 && server2Result != -1) {
                    // Check consistency between servers
                    String results;
                    if (Math.abs(server1Result - server2Result) < 0.001) { // Allow small floating point differences
                        results = String.format(
                            "=== DISTRIBUTED SUM RESULTS ===\n" +
                            "File: %s\n" +
                            "Server 1 Sum: %.2f\n" +
                            "Server 2 Sum: %.2f\n" +
                            "✓ CONSISTENCY CHECK: PASSED\n" +
                            "Final Verified Sum: %.2f\n" +
                            "================================\n\n",
                            selectedFile, server1Result, server2Result, server1Result
                        );
                        logMessage("✓ Consistency check passed - results match!");
                    } else {
                        results = String.format(
                            "=== DISTRIBUTED SUM RESULTS ===\n" +
                            "File: %s\n" +
                            "Server 1 Sum: %.2f\n" +
                            "Server 2 Sum: %.2f\n" +
                            "❌ CONSISTENCY CHECK: FAILED\n" +
                            "ERROR: Servers returned different results!\n" +
                            "Difference: %.2f\n" +
                            "================================\n\n",
                            selectedFile, server1Result, server2Result, Math.abs(server1Result - server2Result)
                        );
                        logMessage("❌ Consistency check failed - results don't match!");
                    }
                    
                    resultArea.append(results);
                    resultArea.setCaretPosition(resultArea.getDocument().getLength());
                    
                    logMessage("✓ Request completed successfully!");
                } else {
                    resultArea.append("❌ Failed to get results from one or both servers\n\n");
                    logMessage("❌ Request failed - check server connections");
                }
                
                // Reset progress bar after a delay
                Timer timer = new Timer(2000, e -> {
                    progressBar.setValue(0);
                    statusLabel.setText("Ready");
                });
                timer.setRepeats(false);
                timer.start();
            }
            
            private double requestDataFromServer(String host, int port, String fileName, String serverName) {
                try (Socket socket = new Socket(host, port);
                     PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    
                    publish("🔗 Connecting to " + serverName + "...");
                    
                    String request = "GET_SUM:" + fileName;
                    out.println(request);
                    publish("📤 Sent request to " + serverName + ": " + request);
                    
                    String response = in.readLine();
                    publish("📥 Received encrypted response from " + serverName);
                    
                    if (response != null && response.startsWith("RESULT:")) {
                        String encryptedData = response.substring(7);
                        String decryptedData = EncryptionUtil.decrypt(encryptedData);
                        
                        if (decryptedData != null) {
                            double result = Double.parseDouble(decryptedData);
                            publish("🔓 " + serverName + " decrypted result: " + result);
                            return result;
                        } else {
                            publish("❌ Failed to decrypt data from " + serverName);
                        }
                    } else if (response != null && response.startsWith("ERROR:")) {
                        publish("❌ " + serverName + " error: " + response.substring(6));
                    } else {
                        publish("❌ Invalid response from " + serverName);
                    }
                    
                } catch (IOException e) {
                    publish("❌ Connection error with " + serverName + ": " + e.getMessage());
                } catch (NumberFormatException e) {
                    publish("❌ Error parsing result from " + serverName);
                }
                
                return -1;
            }
        };
        
        worker.execute();
    }
    
    private void logMessage(String message) {
        SwingUtilities.invokeLater(() -> {
            String timestamp = java.time.LocalTime.now().format(
                java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss"));
            logArea.append("[" + timestamp + "] " + message + "\n");
            logArea.setCaretPosition(logArea.getDocument().getLength());
        });
    }
    
    private void deleteSelectedFile() {
        String selectedFile = (String) fileSelector.getSelectedItem();
        if (selectedFile == null || selectedFile.equals("No files available")) {
            JOptionPane.showMessageDialog(this, "Please select a valid file to delete.", "No File Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Confirm deletion
        int result = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete '" + selectedFile + "' from both servers?\nThis action cannot be undone.", 
            "Confirm Deletion", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.WARNING_MESSAGE);
        
        if (result == JOptionPane.YES_OPTION) {
            SwingWorker<Void, String> worker = new SwingWorker<Void, String>() {
                @Override
                protected Void doInBackground() throws Exception {
                    publish("Deleting file: " + selectedFile);
                    progressBar.setIndeterminate(true);
                    statusLabel.setText("Deleting file...");
                    
                    // Delete from both servers
                    File server1File = new File("data/server1/" + selectedFile + ".csv");
                    File server2File = new File("data/server2/" + selectedFile + ".csv");
                    
                    boolean success1 = true, success2 = true;
                    
                    if (server1File.exists()) {
                        success1 = server1File.delete();
                        if (success1) {
                            publish("✓ Deleted from Server 1: " + server1File.getAbsolutePath());
                        } else {
                            publish("❌ Failed to delete from Server 1");
                        }
                    } else {
                        publish("⚠ File not found on Server 1");
                    }
                    
                    if (server2File.exists()) {
                        success2 = server2File.delete();
                        if (success2) {
                            publish("✓ Deleted from Server 2: " + server2File.getAbsolutePath());
                        } else {
                            publish("❌ Failed to delete from Server 2");
                        }
                    } else {
                        publish("⚠ File not found on Server 2");
                    }
                    
                    if (success1 && success2) {
                        publish("✓ File deletion completed successfully!");
                    } else {
                        publish("⚠ File deletion completed with some issues");
                    }
                    
                    return null;
                }
                
                @Override
                protected void process(java.util.List<String> chunks) {
                    for (String message : chunks) {
                        logMessage(message);
                    }
                }
                
                @Override
                protected void done() {
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(0);
                    statusLabel.setText("Ready");
                    updateFileSelector();
                    
                    try {
                        get(); // Check for exceptions
                        JOptionPane.showMessageDialog(ClientGUI.this, 
                            "File deleted successfully from both servers!", 
                            "Deletion Complete", 
                            JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(ClientGUI.this, 
                            "Error deleting file: " + e.getMessage(), 
                            "Deletion Error", 
                            JOptionPane.ERROR_MESSAGE);
                        logMessage("Deletion error: " + e.getMessage());
                    }
                }
            };
            
            worker.execute();
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ClientGUI().setVisible(true);
        });
    }
}