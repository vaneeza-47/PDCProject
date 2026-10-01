# Complete Demo Guide - GUI Version

## Overview
This guide walks you through all the features of the enhanced distributed computing system with GUI interface and file upload capabilities.

## Demo Scenario

### Phase 1: Setup and Initial Testing

1. **Compile the Project**
   ```
   compile.bat
   ```

2. **Start Both Servers**
   - Open Command Prompt 1: `run-server1.bat`
   - Open Command Prompt 2: `run-server2.bat`
   - Wait for "Server is running and waiting for connections..." message

3. **Launch GUI Client**
   ```
   run-gui-client.bat
   ```

### Phase 2: Generate Sample Data

1. **Click "Generate Sample Data"**
   - Creates file1.csv, file2.csv, file3.csv for both servers
   - Each file has 500 random numbers (1-1000)
   - Watch the Activity Log for progress

2. **Test Basic Functionality**
   - Select "file1" from dropdown
   - Click "Request Sum"
   - Observe the parallel processing in both Results and Activity Log panels

### Phase 3: File Upload Demo

1. **Prepare a Test CSV File**
   Create a simple CSV file (e.g., `test.csv`) with content:
   ```
   10
   20
   30
   40
   50
   ```

2. **Upload the File**
   - Click "Upload CSV File"
   - Select your test.csv file
   - Enter name: "mytest"
   - Watch file validation and upload process

3. **Test Uploaded File**
   - Click "Refresh Files"
   - Select "mytest" from dropdown
   - Click "Request Sum"
   - Expected result: Server 1: 150, Server 2: 150, Total: 300

### Phase 4: Advanced File Management

1. **Use File Utility**
   ```
   run-file-utility.bat
   ```

2. **Explore Options:**
   - Option 2: List existing files
   - Option 3: Validate a file
   - Option 4: View detailed file information

## Expected GUI Behavior

### Results Panel Output:
```
=== DISTRIBUTED SUM RESULTS ===
File: mytest
Server 1 Sum: 150.00
Server 2 Sum: 150.00
Total Combined Sum: 300.00
================================

=== DISTRIBUTED SUM RESULTS ===
File: file1
Server 1 Sum: 249,750.00
Server 2 Sum: 251,250.00
Total Combined Sum: 501,000.00
================================
```

### Activity Log Output:
```
[15:30:45] GUI initialized. Ready to connect to servers.
[15:31:02] Starting file upload...
[15:31:02] Uploaded to Server 1: E:\PDCProject\data\server1\mytest.csv
[15:31:02] Uploaded to Server 2: E:\PDCProject\data\server2\mytest.csv
[15:31:02] File upload completed successfully!
[15:31:15] Starting distributed sum calculation for: mytest
[15:31:15] 🔗 Connecting to Server 1...
[15:31:15] 📤 Sent request to Server 1: GET_SUM:mytest
[15:31:15] 📥 Received encrypted response from Server 1
[15:31:15] 🔓 Server 1 decrypted result: 150.0
[15:31:15] 🔗 Connecting to Server 2...
[15:31:15] 📤 Sent request to Server 2: GET_SUM:mytest
[15:31:15] 📥 Received encrypted response from Server 2
[15:31:15] 🔓 Server 2 decrypted result: 150.0
[15:31:15] ✓ Request completed successfully!
```

## Key Features Demonstrated

### 1. **User-Friendly Interface**
- Intuitive button layout
- Real-time progress tracking
- Clear status messages
- Organized result display

### 2. **File Management**
- Drag-and-drop style file selection
- Automatic file validation
- Duplicate file handling
- Cross-server synchronization

### 3. **Distributed Processing**
- Parallel server communication
- Encrypted data transmission
- Real-time activity logging
- Error handling and recovery

### 4. **Data Visualization**
- Formatted results with server breakdown
- Historical result tracking
- Progress bars and status indicators
- Comprehensive activity logging

## Testing Different Scenarios

### Scenario 1: Large Numbers
Create a CSV with large values:
```
1000000
2000000
3000000
```

### Scenario 2: Decimal Numbers
Test with decimal precision:
```
123.456
789.012
345.678
```

### Scenario 3: Mixed Data
Include some invalid data to test validation:
```
100
abc
200
300
xyz
```

## Performance Observations

- **Parallel Processing**: Both servers process simultaneously
- **Network Efficiency**: Minimal data transfer (only results)
- **Security**: All data encrypted during transmission
- **Scalability**: Easy to add more servers or files
- **User Experience**: Responsive GUI with progress feedback

## Educational Value

This demo showcases:
1. **GUI Programming**: Event-driven interface design
2. **File I/O**: CSV processing and validation
3. **Network Programming**: Client-server architecture
4. **Parallel Computing**: Simultaneous processing
5. **Security**: Encryption/decryption implementation
6. **Error Handling**: Graceful failure management
7. **User Experience**: Progress tracking and feedback