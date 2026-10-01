# How to Run the Distributed Computing Project

## Quick Start Guide

### Step 1: Compile the Project
Double-click `compile.bat` or run in command prompt:
```
compile.bat
```

### Step 2: Start the Servers
Open **two separate command prompt windows**:

**Window 1 - Start Server 1:**
```
run-server1.bat
```

**Window 2 - Start Server 2:**
```
run-server2.bat
```

Wait for both servers to show "Server is running and waiting for connections..."

### Step 3: Choose Your Client Interface

#### Option A: GUI Client (Recommended for Beginners)
Open a **third command prompt window**:
```
run-gui-client.bat
```

#### Option B: Console Client
```
run-client.bat
```

## Using the GUI Client

### Main Features:
1. **File Selection Dropdown**: Choose from available CSV files
2. **Request Sum Button**: Calculate distributed sum from both servers
3. **Upload CSV File**: Add your own data files to the system
4. **Generate Sample Data**: Create test files with 500 random values
5. **Results Panel**: View formatted results and statistics
6. **Activity Log**: Monitor all operations and server communications

### File Upload Process:
1. Click "Upload CSV File"
2. Select your CSV file (should contain one number per line)
3. Enter a name for the file (without .csv extension)
4. File will be copied to both server directories automatically
5. Use "Refresh Files" to update the dropdown

### Sample Data Generation:
1. Click "Generate Sample Data"
2. Creates file1.csv, file2.csv, and file3.csv for both servers
3. Each file contains 500 random numbers between 1-1000

## File Management Utility

For advanced file operations, use the standalone utility:
```
run-file-utility.bat
```

This provides:
- File upload with validation
- File information and statistics
- CSV format validation
- Batch file operations

## What Happens Behind the Scenes

1. **Client Request**: Client sends "GET_SUM:filename" to both servers
2. **Parallel Processing**: Both servers process simultaneously
3. **Data Reading**: Each server reads their CSV file and calculates sum
4. **Encryption**: Results encrypted using AES encryption
5. **Response**: Encrypted results sent back to client
6. **Decryption**: Client decrypts and combines results
7. **Display**: Final sum shown with server breakdown

## Sample GUI Output

### Results Panel:
```
=== DISTRIBUTED SUM RESULTS ===
File: file1
Server 1 Sum: 125,250.00
Server 2 Sum: 249,750.00
Total Combined Sum: 375,000.00
================================
```

### Activity Log:
```
[14:30:15] Starting distributed sum calculation for: file1
[14:30:15] 🔗 Connecting to Server 1...
[14:30:15] 📤 Sent request to Server 1: GET_SUM:file1
[14:30:16] 📥 Received encrypted response from Server 1
[14:30:16] 🔓 Server 1 decrypted result: 125250.0
[14:30:16] ✓ Request completed successfully!
```

## CSV File Format

Your CSV files should contain:
- One number per line
- No headers (or they will be skipped if non-numeric)
- Decimal numbers are supported
- Empty lines are ignored

Example CSV content:
```
123.45
67.89
234.56
...
```

## Troubleshooting

**GUI doesn't start:**
- Make sure Java Swing is available (included in standard JDK)
- Try running from command prompt to see error messages

**File upload fails:**
- Check file permissions in the data/ directory
- Ensure CSV file is properly formatted
- Verify file path is correct

**"Port already in use" error:**
- Close any previously running server instances
- Check no other programs are using ports 8081 or 8082

**"Connection refused" error:**
- Ensure both servers are running before starting client
- Check servers show "waiting for connections" message
- Verify firewall isn't blocking the ports

**File not found errors:**
- Use "Generate Sample Data" to create test files
- Check data/server1/ and data/server2/ directories exist
- Verify file names match exactly (case-sensitive)

## Learning Objectives

This project demonstrates:
- **GUI Programming**: Swing interface with event handling
- **File I/O Operations**: CSV reading, file upload, validation
- **Distributed Computing**: Multiple servers working together
- **Parallel Processing**: Simultaneous request handling
- **Network Programming**: TCP socket communication
- **Encryption/Decryption**: Secure data transmission
- **Multi-threading**: Background operations and progress tracking
- **Error Handling**: Graceful failure management