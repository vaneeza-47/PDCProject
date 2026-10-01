# Distributed Computing System Documentation

**Project:** Java Distributed Computing System  
**Version:** 1.0  
**Date:** January 2025  

---

## 1. Project Overview

### What This System Does

This project demonstrates distributed computing concepts using Java. It consists of:
- **Two servers** that process CSV files containing numbers
- **A client** (GUI or console) that requests calculations from both servers
- **Consistency checking** to verify both servers return the same result
- **AES encryption** for secure data transmission

### Key Features

- **Dual Interface**: Graphical (GUI) and command-line clients
- **File Management**: Upload, generate, and delete CSV files
- **Security**: AES encryption for all data transmission
- **Consistency Verification**: Ensures data integrity across servers
- **Real-time Monitoring**: Activity logs and progress tracking

### Learning Objectives

Students learn:
- Distributed computing principles
- Network programming with TCP sockets
- Encryption/decryption implementation
- GUI programming with Java Swing
- File I/O and data processing
- Error handling and system reliability

---

## 2. System Architecture

### Components

```
Client (GUI/Console)
       ↓
   Network Layer
       ↓
┌─────────────┬─────────────┐
│  Server 1   │  Server 2   │
│ Port 8081   │ Port 8082   │
└─────────────┴─────────────┘
       ↓             ↓
┌─────────────┬─────────────┐
│data/server1/│data/server2/│
│ - file1.csv │ - file1.csv │
│ - file2.csv │ - file2.csv │
│ - file3.csv │ - file3.csv │
└─────────────┴─────────────┘
```

### How It Works

1. **Client Request**: Sends "GET_SUM:filename" to both servers
2. **Parallel Processing**: Both servers calculate sum simultaneously
3. **Encryption**: Each server encrypts its result using AES
4. **Response**: Encrypted results sent back to client
5. **Verification**: Client decrypts and compares results
6. **Output**: Shows final result or consistency error

---

## 3. Installation and Setup

### Requirements

- **Java JDK 8+** (full JDK, not just JRE)
- **Windows OS** (batch files provided)
- **Ports 8081 and 8082** available
- **50 MB** disk space

### Installation Steps

1. **Download** all project files to a folder (e.g., `C:\DistributedComputing`)
2. **Compile** by running `compile.bat`
3. **Generate data** by running `java GenerateData`
4. **Start servers** by running `run-server1.bat` and `run-server2.bat`
5. **Start client** by running `run-gui-client.bat` or `run-client.bat`

### Verification

- Servers show "waiting for connections" message
- GUI client opens without errors
- Sample files (file1, file2, file3) are available
- Requesting a sum returns consistent results

---

## 4. Usage Guide

### GUI Client

**Main Interface:**
- **File Dropdown**: Select CSV files to process
- **Request Sum**: Calculate sum using both servers
- **Upload CSV File**: Add your own data files
- **Generate Sample Data**: Create test files with random numbers
- **Delete Selected File**: Remove files from both servers
- **Results Panel**: Shows calculation results and consistency status
- **Activity Log**: Real-time operation monitoring

**Basic Workflow:**
1. Generate sample data or upload your CSV file
2. Select file from dropdown
3. Click "Request Sum"
4. View results in Results panel

### Console Client

**Basic Commands:**
- Enter filename (e.g., "file1") to request sum
- Type "quit" to exit
- Results show both server responses and consistency check

### File Format

**CSV Requirements:**
- One number per line
- Supports integers and decimals
- Empty lines ignored
- Non-numeric values skipped with warnings

**Example CSV:**
```
100
200.5
300
450.75
```

---

## 5. Security Implementation

### Encryption Details

- **Algorithm**: AES (Advanced Encryption Standard)
- **Key Size**: 128-bit
- **Mode**: ECB (Electronic Codebook)
- **Encoding**: Base64 for transmission
- **Key**: Fixed "MySecretKey12345" (educational purposes)

### Security Features

- **Data Encryption**: All calculation results encrypted before transmission
- **Consistency Checking**: Detects data tampering or corruption
- **Input Validation**: Prevents malicious file uploads
- **Error Handling**: Secure failure modes without information leakage

---

## 6. Troubleshooting

### Common Issues

**"Java not recognized"**
- Install Java JDK from oracle.com
- Restart computer after installation

**"Port already in use"**
- Close any running server instances
- Restart computer to clear network connections

**"Connection refused"**
- Ensure both servers are running before starting client
- Check servers show "waiting for connections" message

**Inconsistent results**
- Verify both server directories have identical files
- Regenerate sample data or re-upload files
- Restart both servers

### File Issues

**"File not found"**
- Check data/server1/ and data/server2/ directories exist
- Generate sample data using GUI or `java GenerateData`
- Verify file names match exactly (case-sensitive)

**Upload failures**
- Ensure CSV file contains only numbers
- Check file permissions in data directories
- Try with smaller test file first

---

## 7. Technical Details

### Code Structure

```
├── client/              # Client applications
│   ├── ClientGUI.java   # Graphical interface
│   └── Client.java      # Console interface
├── server/              # Server applications
│   ├── Server1.java     # First server (port 8081)
│   └── Server2.java     # Second server (port 8082)
├── shared/              # Shared utilities
│   ├── EncryptionUtil.java    # AES encryption/decryption
│   ├── DataProcessor.java     # CSV file processing
│   └── FileManager.java       # File operations
└── data/                # Data storage
    ├── server1/         # Server 1 data files
    └── server2/         # Server 2 data files
```

### Key Classes

**EncryptionUtil**: Handles AES encryption and decryption
**DataProcessor**: Reads CSV files and calculates sums
**FileManager**: Manages file operations, validation, and deletion
**ClientGUI**: Swing-based graphical user interface
**Server1/Server2**: TCP socket servers for processing requests

### Network Protocol

**Request Format**: `GET_SUM:<filename>`
**Success Response**: `RESULT:<encrypted_sum>`
**Error Response**: `ERROR:<error_message>`

---

## 8. Extension Ideas

### Beginner Extensions

- Add more mathematical operations (average, median, max, min)
- Implement file preview before processing
- Add keyboard shortcuts to GUI
- Improve error messages with helpful suggestions

### Intermediate Extensions

- Add user authentication and session management
- Implement database storage for results history
- Create web-based interface
- Add support for Excel files

### Advanced Extensions

- Implement dynamic server discovery
- Add load balancing across multiple servers
- Create monitoring dashboard
- Deploy to cloud platforms (AWS, Azure)

---

## 9. Educational Value

### Distributed Computing Concepts

- **Parallel Processing**: Multiple servers working simultaneously
- **Data Consistency**: Ensuring identical results across nodes
- **Fault Tolerance**: Handling server failures gracefully
- **Load Distribution**: Spreading work across multiple machines

### Programming Skills

- **Network Programming**: TCP socket communication
- **GUI Development**: Event-driven programming with Swing
- **Security Implementation**: Encryption and secure communication
- **File Processing**: Reading and validating CSV data
- **Error Handling**: Graceful failure management

### Real-World Applications

This system demonstrates principles used in:
- **Web Services**: Client-server architecture
- **Database Systems**: Data replication and consistency
- **Cloud Computing**: Distributed processing and fault tolerance
- **Financial Systems**: Secure transaction processing

---

## 10. Conclusion

This distributed computing system provides hands-on experience with fundamental concepts used in modern distributed systems. The combination of practical implementation, security features, and user-friendly interfaces makes it an excellent learning tool for understanding how large-scale systems handle reliability, consistency, and security challenges.

The project successfully demonstrates that distributed computing principles can be understood and implemented at an educational level while maintaining the core concepts that power real-world systems serving millions of users.

---

**Files Included:**
- Source code (.java files)
- Compilation scripts (.bat files)
- Sample data files (.csv files)
- Documentation (README.md, INSTRUCTIONS.md)

**Total Project Size:** ~50 MB  
**Estimated Learning Time:** 4-6 hours  
**Difficulty Level:** Beginner to Intermediate