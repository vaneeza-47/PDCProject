# Distributed Computing Project - Java

A distributed system with both console and GUI interfaces where a client requests data from two servers, each containing CSV files. The servers process the data, encrypt the results, and send them back to the client for decryption.

## Features

### 🖥️ **Dual Interface Support**
- **Console Client**: Command-line interface for basic operations
- **GUI Client**: User-friendly Swing interface with file upload capabilities

### 📁 **File Management**
- Upload your own CSV files through the GUI
- Generate sample data with 500 random values
- File validation and information display
- Support for multiple file formats

### 🔒 **Security**
- AES encryption for all data transmission
- Secure client-server communication
- Data integrity verification

### ⚡ **Distributed Processing**
- Parallel processing across two servers
- Real-time progress tracking
- Concurrent request handling

## Project Structure
```
distributed-computing-java/
├── client/
│   ├── Client.java          # Console client
│   └── ClientGUI.java       # GUI client with file upload
├── server/
│   ├── Server1.java         # Server 1 (port 8081)
│   └── Server2.java         # Server 2 (port 8082)
├── shared/
│   ├── EncryptionUtil.java  # AES encryption/decryption
│   ├── DataProcessor.java   # CSV data processing
│   └── FileManager.java     # File management utilities
├── data/
│   ├── server1/             # Server 1 data files
│   └── server2/             # Server 2 data files
├── FileUploadUtility.java   # Standalone file management
└── *.bat                    # Windows batch files for easy execution
```

## Quick Start

### 1. Compile Everything
```batch
compile.bat
```

### 2. Start Servers (in separate windows)
```batch
run-server1.bat
run-server2.bat
```

### 3. Choose Your Client
**GUI Client (Recommended):**
```batch
run-gui-client.bat
```

**Console Client:**
```batch
run-client.bat
```

## GUI Features

The GUI client provides:
- **File Selection**: Choose from available CSV files
- **File Upload**: Upload your own CSV files to both servers
- **Sample Data Generation**: Create test files with random data
- **Real-time Logging**: See all operations and server communications
- **Progress Tracking**: Visual progress bars and status updates
- **Results Display**: Formatted results with server breakdowns

## Requirements
- Java 8 or higher
- Windows OS (batch files provided)
- Network ports 8081 and 8082 available