# Demo Walkthrough

## What You'll See

### 1. Server 1 Output
```
Starting Server 1 on port 8081...
Server 1 is running and waiting for connections...
Client connected to Server 1
Server 1 received request: GET_SUM:file1
Processed 500 values from data/server1/file1.csv
Server 1 calculated sum: 125250.0 from file1
Server 1 sent encrypted result to client
```

### 2. Server 2 Output
```
Starting Server 2 on port 8082...
Server 2 is running and waiting for connections...
Client connected to Server 2
Server 2 received request: GET_SUM:file1
Processed 500 values from data/server2/file1.csv
Server 2 calculated sum: 249750.0 from file1
Server 2 sent encrypted result to client
```

### 3. Client Output
```
=== Distributed Computing Client ===
Available files: file1, file2, file3

Enter file name to request sum (or 'quit' to exit): file1
Connecting to Server 1...
Sent request to Server 1: GET_SUM:file1
Received encrypted response from Server 1
Server 1 decrypted result: 125250.0
Connecting to Server 2...
Sent request to Server 2: GET_SUM:file2
Received encrypted response from Server 2
Server 2 decrypted result: 249750.0

=== RESULTS ===
Server 1 sum: 125250.0
Server 2 sum: 249750.0
Total combined sum: 375000.0
================
```

## Key Concepts Demonstrated

1. **Distributed Processing**: Two separate servers process the same request independently
2. **Parallel Execution**: Both servers work simultaneously, not sequentially
3. **Secure Communication**: Data is encrypted before transmission
4. **Client Coordination**: Client manages communication with multiple servers
5. **Data Aggregation**: Final result combines outputs from both servers