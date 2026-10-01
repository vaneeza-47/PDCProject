# Distributed Computing System Documentation

**Project:** Java Distributed Computing System  
**Author:** [Your Name]  
**Date:** January 2025  

---

## Overview

A distributed computing system where two servers process CSV files and a client verifies consistency between results. Demonstrates parallel processing, network communication, and data encryption.

**Key Features:**
- Two identical servers processing same data
- GUI and console client interfaces  
- AES encryption for secure communication
- Consistency checking for data integrity
- File upload/download management

---

## System Architecture

```
Client → Server1 (8081) → data/server1/
      → Server2 (8082) → data/server2/
```

**Workflow:**
1. Client sends request to both servers
2. Servers calculate sum from CSV file
3. Results encrypted and sent back
4. Client compares results for consistency

---

## Installation

**Requirements:**
- Java JDK 8+
- Windows OS
- Ports 8081, 8082 available

**Setup:**
1. Run `compile.bat`
2. Run `java GenerateData` 
3. Start `run-server1.bat` and `run-server2.bat`
4. Start `run-gui-client.bat`

---

## Usage

### GUI Client
- **Generate Sample Data:** Creates test CSV files
- **Upload CSV File:** Add your own data
- **Request Sum:** Process selected file
- **Delete File:** Remove files from both servers

### Console Client
- Enter filename to process (e.g., "file1")
- Type "quit" to exit

### CSV Format
```
100
200
300
```
One number per line, decimals supported.

---

## Security

- **Encryption:** AES-128 with fixed key "MySecretKey12345"
- **Consistency Check:** Compares results from both servers
- **Input Validation:** Checks CSV format before processing

---

## Troubleshooting

**Common Issues:**
- **"Java not recognized"** → Install Java JDK
- **"Port in use"** → Close other server instances  
- **"Connection refused"** → Start servers before client
- **Inconsistent results** → Regenerate data files

---

## Technical Details

**Code Structure:**
- `client/` - GUI and console clients
- `server/` - Two identical servers
- `shared/` - Encryption, file processing utilities
- `data/` - CSV file storage

**Network Protocol:**
- Request: `GET_SUM:filename`
- Response: `RESULT:encrypted_data`
- Error: `ERROR:message`

---

## Learning Objectives

- Distributed computing concepts
- Network programming (TCP sockets)
- Encryption/decryption implementation
- GUI programming with Swing
- File I/O and data validation
- System reliability and fault tolerance

---

## Extensions

**Beginner:** Add more math operations, file preview, better UI
**Advanced:** Web interface, database storage, cloud deployment

---

## Conclusion

This system demonstrates core distributed computing principles used in real-world applications like Google, Amazon, and banking systems. Students gain practical experience with network programming, security, and system reliability.

**Project Stats:**
- ~20 Java files
- ~1,000 lines of code  
- Supports files with 10,000+ numbers
- Educational focus with professional concepts