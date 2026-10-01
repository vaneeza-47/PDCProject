# Distributed Computing System - Complete Project Documentation

**Version:** 1.0  
**Date:** January 2025  
**Programming Language:** Java  
**Project Type:** Educational Distributed Computing System  
**Author:** [Your Name]  

---

## Table of Contents

1. [What is This Project About?](#1-what-is-this-project-about)
2. [How the System Works](#2-how-the-system-works)
3. [What Can This System Do?](#3-what-can-this-system-do)
4. [How We Built This System](#4-how-we-built-this-system)
5. [How to Install and Run](#5-how-to-install-and-run)
6. [How to Use the System](#6-how-to-use-the-system)
7. [For Programmers: How to Modify](#7-for-programmers-how-to-modify)
8. [Security: How We Keep Data Safe](#8-security-how-we-keep-data-safe)
9. [Testing: Making Sure Everything Works](#9-testing-making-sure-everything-works)
10. [When Things Go Wrong: Fixing Problems](#10-when-things-go-wrong-fixing-problems)
11. [What You Will Learn](#11-what-you-will-learn)
12. [Ideas for Making It Better](#12-ideas-for-making-it-better)
13. [Step-by-Step Examples](#13-step-by-step-examples)
14. [Understanding the Code](#14-understanding-the-code)
15. [Real-World Applications](#15-real-world-applications)

---

## 1. What is This Project About?

### 1.1 Simple Explanation

Imagine you have a very big math problem that takes a long time to solve. Instead of using just one computer to solve it, you can use multiple computers working together. This is called "distributed computing" - spreading the work across many computers.

Our project creates a simple version of this idea. We have:
- **Two servers** (think of them as two separate computers)
- **One client** (the person who asks for the work to be done)
- **Data files** (CSV files with numbers that need to be added up)

The cool part is that both servers work on the same problem at the same time, and then we check if they got the same answer. If they did, we know the answer is correct. If they didn't, something went wrong.

### 1.2 Why Did We Build This?

We built this system to help students learn about:

**Distributed Computing:** This is how big companies like Google, Facebook, and Amazon handle millions of users. They don't use just one computer - they use thousands of computers working together.

**Network Programming:** This teaches you how computers talk to each other over the internet or local networks.

**Security:** We show how to keep data safe when sending it between computers using encryption (scrambling the data so only the right person can read it).

**User Interface Design:** We created both a simple text interface and a fancy graphical interface with buttons and windows.

**File Management:** The system can handle uploading, processing, and deleting files safely.

### 1.3 What Makes This Project Special?

**Easy to Understand:** We wrote everything in simple language and provided lots of examples.

**Two Ways to Use It:** You can use either a simple text interface (like old computers) or a modern graphical interface with buttons and menus.

**Real Security:** We use actual encryption methods that real companies use to protect data.

**Handles Mistakes:** The system is smart enough to handle errors and tell you what went wrong in plain English.

**Complete File Management:** You can upload your own files, create test files, and delete files you don't need anymore.

**Consistency Checking:** This is the most important part - the system makes sure both servers give the same answer, which is how real distributed systems work.

### 1.4 Who Should Use This Project?

**Students Learning Programming:** Great for understanding how multiple programs work together.

**Computer Science Students:** Perfect for learning distributed systems concepts.

**Anyone Curious About How Big Websites Work:** This shows the basic ideas behind how sites like Google handle millions of users.

**Teachers:** Can use this to demonstrate distributed computing concepts in class.

### 1.5 What You Need to Know Before Starting

**Basic Computer Skills:** You should know how to open programs and navigate folders on your computer.

**Some Java Knowledge:** It helps if you've written simple Java programs before, but it's not required to just use the system.

**Understanding of Numbers:** The system works with CSV files (like Excel files) containing numbers.

**Patience:** Distributed systems can seem complex at first, but we'll explain everything step by step.

---

## 2. How the System Works

### 2.1 The Big Picture

Think of our system like a restaurant with two kitchens:

**The Customer (Client):** This is you! You want to know the sum of numbers in a file. You place an order by asking both kitchens to calculate the same thing.

**Kitchen 1 and Kitchen 2 (Servers):** These are two separate programs running on different ports (like different phone numbers). Each kitchen has the same recipe book (identical data files).

**The Order (Request):** You send a message saying "Please add up all numbers in file1.csv"

**The Cooking (Processing):** Both kitchens work on your order at the same time, independently.

**The Result (Response):** Each kitchen sends back their answer, but it's encrypted (scrambled) for security.

**Quality Check (Consistency Check):** You compare both answers. If they match, you know the answer is correct. If they don't match, something went wrong.

### 2.2 Step-by-Step Process

Let's follow what happens when you ask for the sum of numbers in a file:

**Step 1: You Make a Request**
- You select a file (like "file1") from the dropdown menu
- You click the "Request Sum" button
- The client program prepares to contact both servers

**Step 2: Connecting to Servers**
- The client opens a connection to Server 1 (running on port 8081)
- At the same time, it opens another connection to Server 2 (running on port 8082)
- This happens in parallel, so both connections happen simultaneously

**Step 3: Sending the Request**
- The client sends a message to both servers: "GET_SUM:file1"
- This message tells each server which file to process
- Both servers receive this message at almost the same time

**Step 4: Servers Process the Data**
- Server 1 opens its copy of file1.csv and reads all the numbers
- Server 2 opens its copy of file1.csv and reads all the numbers
- Each server adds up all the numbers in the file
- This happens simultaneously - both servers work at the same time

**Step 5: Encryption for Security**
- Each server takes its calculated sum (like 125,250)
- They encrypt this number using AES encryption (scrambling it for security)
- The encrypted result looks like random letters and numbers
- This prevents anyone from reading the result if they intercept the message

**Step 6: Sending Results Back**
- Server 1 sends its encrypted result back to the client
- Server 2 sends its encrypted result back to the client
- The client receives both encrypted messages

**Step 7: Decryption and Verification**
- The client decrypts both messages to get the actual numbers
- It compares the two results
- If they match (like both saying 125,250), the consistency check passes
- If they don't match, there's an error somewhere

**Step 8: Displaying Results**
- If consistent: Shows "✓ CONSISTENCY CHECK PASSED" and the verified sum
- If inconsistent: Shows "❌ CONSISTENCY CHECK FAILED" and the difference
- All this information appears in the Results panel

### 2.3 Why This Design is Smart

**Fault Tolerance:** If one server crashes or gives a wrong answer, you'll know because the results won't match.

**Security:** All data is encrypted when traveling between computers, so hackers can't read it.

**Speed:** Both servers work at the same time instead of one after the other, making it faster.

**Reliability:** By checking that both servers give the same answer, we can be confident the result is correct.

**Scalability:** We could easily add more servers (Server 3, Server 4, etc.) using the same pattern.

### 2.4 Real-World Comparison

This is similar to how:

**Banks Process Transactions:** Multiple systems verify each transaction to prevent errors.

**Google Search:** Multiple servers process your search query and compare results.

**Online Shopping:** Multiple servers handle your order to ensure it doesn't get lost.

**Streaming Services:** Multiple servers store the same movie so if one fails, others can serve it.

### 2.5 What Makes Our System Different from Simple Programs

**Normal Program:** One computer does all the work. If it breaks, everything stops.

**Our Distributed System:** Multiple computers do the same work. If one breaks, we can detect the problem and still get reliable results.

**Normal File Processing:** Read file, calculate sum, done.

**Our Secure Processing:** Read file, calculate sum, encrypt result, send securely, verify consistency, then display result.

---

## 3. What Can This System Do?

### 3.1 Main Features Explained Simply

#### 3.1.1 Two Ways to Use the System

**Graphical Interface (GUI) - The Easy Way:**
This looks like a regular computer program with buttons, menus, and windows. It's perfect for beginners because:
- You can click buttons instead of typing commands
- You see progress bars showing what's happening
- Error messages appear in easy-to-read pop-up windows
- You can drag and drop files
- Everything is visual and intuitive

**Command Line Interface - The Text Way:**
This looks like the old-style computer interfaces where you type commands. It's useful because:
- It uses less computer memory
- It's faster for people who know the commands
- It can be automated with scripts
- It works on any computer, even very old ones

#### 3.1.2 File Management Made Easy

**Upload Your Own Files:**
- Click a button to browse for files on your computer
- The system automatically copies your file to both servers
- It checks if your file is in the right format (CSV with numbers)
- If there are problems, it tells you exactly what's wrong
- Your file becomes available immediately for processing

**Generate Test Files:**
- Click one button to create sample files automatically
- The system creates three files (file1, file2, file3) with 500 random numbers each
- Both servers get identical copies, ensuring consistency
- Perfect for testing the system without needing your own data
- Numbers are between 1 and 1000, making results easy to verify

**Delete Files Safely:**
- Select any file and click delete
- The system asks "Are you sure?" to prevent accidents
- It removes the file from both servers simultaneously
- It creates backup copies before deleting (safety feature)
- Shows exactly what was deleted and from where

**View File Information:**
- See how many numbers are in each file
- View the sum, average, minimum, and maximum values
- Check file size and when it was last modified
- Verify that both servers have identical files
- Useful for understanding your data before processing

#### 3.1.3 Processing and Results

**Request Sum Calculation:**
- Choose any available file from a dropdown menu
- Click "Request Sum" to start processing
- Watch real-time progress in the activity log
- See exactly what's happening: connecting, sending, receiving, decrypting
- Get results in an easy-to-read format

**Consistency Checking:**
- The system automatically compares results from both servers
- If they match: Shows green checkmark and "CONSISTENCY CHECK PASSED"
- If they don't match: Shows red X and "CONSISTENCY CHECK FAILED"
- Displays the exact difference when results don't match
- This is the most important feature - it ensures data reliability

**Real-Time Monitoring:**
- Activity log shows every step with timestamps
- Progress bars indicate how much work is done
- Status messages keep you informed
- Error messages explain problems in plain English
- You can see the encrypted data being sent (educational value)

### 3.2 Advanced Features

#### 3.2.1 Security Features

**Data Encryption:**
- All results are scrambled before being sent over the network
- Uses AES encryption - the same method banks use
- Even if someone intercepts the data, they can't read it
- Automatic encryption and decryption - you don't need to do anything
- Educational value: you can see the encrypted data in the logs

**Input Validation:**
- Checks that uploaded files are in the correct format
- Warns about non-numeric values in files
- Prevents processing of corrupted files
- Gives helpful error messages when something's wrong
- Protects the system from bad data

**Safe File Operations:**
- Creates backups before deleting files
- Confirms dangerous operations (like deleting files)
- Validates file permissions before operations
- Handles network errors gracefully
- Never leaves the system in a broken state

#### 3.2.2 User-Friendly Features

**Progress Tracking:**
- Visual progress bars for long operations
- Percentage completion for file uploads
- Real-time status updates
- Estimated time remaining for large files
- Cancel button for operations that take too long

**Error Handling:**
- Clear, non-technical error messages
- Suggestions for fixing problems
- Automatic retry for temporary network issues
- Graceful handling of server crashes
- Help text for common problems

**Customization Options:**
- Choose between different file formats
- Adjust timeout settings for slow networks
- Select which servers to use
- Configure encryption settings (for advanced users)
- Save preferences for future use

### 3.3 What File Types Can You Use?

#### 3.3.1 CSV File Format

**What is CSV?**
CSV stands for "Comma Separated Values" but in our case, we use one number per line. It's like a simple text file with numbers:

```
100
200
300
400
500
```

**What Numbers Work?**
- Whole numbers: 1, 2, 100, 1000
- Decimal numbers: 1.5, 99.99, 123.456
- Negative numbers: -10, -5.5
- Very large numbers: 1000000, 999999.99

**What Doesn't Work?**
- Text: "hello", "abc"
- Mixed content: "100 dollars"
- Dates: "January 1, 2025"
- Empty lines (these are ignored, not errors)

#### 3.3.2 File Size Limits

**Small Files (1-100 numbers):** Process instantly
**Medium Files (100-1,000 numbers):** Process in 1-2 seconds
**Large Files (1,000-10,000 numbers):** Process in 5-10 seconds
**Very Large Files (10,000+ numbers):** May take longer but still work

The system can handle files with hundreds of thousands of numbers, but for learning purposes, smaller files are better.

### 3.4 Network and Communication Features

#### 3.4.1 How Computers Talk to Each Other

**TCP Sockets:**
This is like a telephone connection between computers. Once connected, they can send messages back and forth reliably.

**Port Numbers:**
Think of these like apartment numbers in a building. Server 1 lives at "apartment 8081" and Server 2 lives at "apartment 8082" on your computer.

**Message Format:**
The computers send simple text messages like:
- "GET_SUM:file1" (client asking for sum of file1)
- "RESULT:encrypted_data" (server sending back encrypted result)
- "ERROR:file not found" (server reporting a problem)

#### 3.4.2 Parallel Processing

**What is Parallel Processing?**
Instead of asking Server 1 first, then Server 2, we ask both at the same time. It's like having two people solve the same math problem simultaneously instead of one person solving it twice.

**Why is This Better?**
- **Faster:** Takes half the time since both work simultaneously
- **More Reliable:** If one server has a problem, we know immediately
- **More Realistic:** This is how real distributed systems work

**How We Achieve This:**
The client program creates two separate "threads" (think of them as two workers) that contact the servers at the same time.

---

## 4. How We Built This System

### 4.1 Programming Language and Tools

#### 4.1.1 Why We Chose Java

**Easy to Learn:** Java uses English-like words and is designed to be readable.

**Cross-Platform:** Java programs run on Windows, Mac, and Linux without changes.

**Built-in Networking:** Java has excellent tools for network programming built right in.

**Security Features:** Java includes encryption and security tools we need.

**GUI Support:** Java Swing makes it easy to create windows, buttons, and menus.

**Large Community:** Lots of help and examples available online.

#### 4.1.2 Libraries and Tools We Used

**Java Standard Library:** This comes with Java and includes:
- Socket programming for network communication
- File I/O for reading and writing files
- Encryption tools for security
- Threading for parallel processing

**Swing GUI Framework:** For creating the graphical interface:
- JFrame for windows
- JButton for clickable buttons
- JTextArea for displaying text
- JProgressBar for showing progress
- JFileChooser for selecting files

**No External Dependencies:** Everything we used comes with Java, so you don't need to download extra libraries.

### 4.2 System Architecture in Simple Terms

#### 4.2.1 How the Parts Fit Together

Think of our system like a restaurant chain:

**Client Programs (The Customers):**
- ClientGUI.java: The fancy restaurant with waiters and menus
- Client.java: The simple fast-food counter
- FileUploadUtility.java: The catering service for special orders

**Server Programs (The Kitchens):**
- Server1.java: Kitchen #1 at location 8081
- Server2.java: Kitchen #2 at location 8082
- Both kitchens have the same menu and recipes

**Shared Utilities (The Recipe Books):**
- EncryptionUtil.java: How to scramble and unscramble secret messages
- DataProcessor.java: How to read files and add up numbers
- FileManager.java: How to organize and manage files

**Data Storage (The Pantry):**
- data/server1/: Kitchen #1's ingredient storage
- data/server2/: Kitchen #2's ingredient storage
- Both pantries have identical ingredients

#### 4.2.2 Communication Flow

```
Customer (Client)
       |
   Places Order
       |
   ┌───▼───┐    ┌───────┐
   │Kitchen│    │Kitchen│
   │   1   │    │   2   │
   │ 8081  │    │ 8082  │
   └───┬───┘    └───┬───┘
       │            │
   Cooks Food   Cooks Food
       │            │
   ┌───▼───┐    ┌───▼───┐
   │Result │    │Result │
   │  #1   │    │  #2   │
   └───┬───┘    └───┬───┘
       │            │
       └─────┬──────┘
             │
        Compare Results
             │
      ┌──────▼──────┐
      │Final Answer │
      └─────────────┘
```

### 4.3 How Each Component Works

#### 4.3.1 Client Components Explained

**ClientGUI.java - The Graphical Interface:**

This is like a restaurant with a nice menu and friendly waiters. It has:

- **Buttons:** Easy to click for different actions
- **Dropdown Menu:** Shows available files to choose from
- **Progress Bars:** Shows how much work is done
- **Text Areas:** Displays results and activity logs
- **File Dialogs:** Lets you browse for files on your computer

The GUI uses "event-driven programming" - it waits for you to click buttons or select files, then responds to those events.

**Client.java - The Simple Text Interface:**

This is like a fast-food counter where you just say what you want:

- Type the name of the file you want processed
- See the results printed as text
- Good for people who prefer typing to clicking
- Uses less computer memory than the GUI

**FileUploadUtility.java - The File Manager:**

This is like a catering service that handles special file operations:

- Upload files from anywhere on your computer
- Check if files are in the right format
- View detailed information about files
- Delete files safely with confirmation
- Generate test data for experimenting

#### 4.3.2 Server Components Explained

**Server1.java and Server2.java - The Processing Engines:**

These are like two identical kitchens that:

- **Listen for Orders:** Wait for clients to connect and send requests
- **Process Requests:** Read the specified file and calculate the sum
- **Encrypt Results:** Scramble the answer for security
- **Send Responses:** Return the encrypted result to the client
- **Handle Multiple Customers:** Can serve many clients at the same time

Both servers are nearly identical - they just use different port numbers (8081 vs 8082) and read from different directories.

**How Servers Handle Requests:**

1. **Start Up:** Server begins listening on its port
2. **Wait for Connection:** Server waits for a client to connect
3. **Accept Connection:** When client connects, server creates a new thread to handle it
4. **Read Request:** Server reads the message from client
5. **Parse Request:** Server understands what file is being requested
6. **Process File:** Server reads the CSV file and calculates sum
7. **Encrypt Result:** Server scrambles the result for security
8. **Send Response:** Server sends encrypted result back to client
9. **Close Connection:** Server closes the connection and waits for next client

#### 4.3.3 Shared Utilities Explained

**EncryptionUtil.java - The Security Guard:**

This handles all the security stuff:

- **Encrypt:** Takes a normal number like "125250" and scrambles it into something like "aB3xY9mP2qR7"
- **Decrypt:** Takes scrambled text and turns it back into the original number
- **Uses AES:** This is a very secure method used by banks and governments
- **Automatic:** You don't need to understand how it works - just use it

**DataProcessor.java - The Number Cruncher:**

This reads files and does math:

- **Open File:** Opens a CSV file for reading
- **Read Line by Line:** Goes through each line in the file
- **Parse Numbers:** Converts text like "123.45" into actual numbers
- **Skip Bad Data:** Ignores lines that aren't numbers
- **Calculate Sum:** Adds up all the valid numbers
- **Return Result:** Gives back the final sum

**FileManager.java - The File Organizer:**

This manages all file operations:

- **List Files:** Shows what files are available
- **Validate Files:** Checks if files are in the right format
- **Get File Info:** Shows file size, number of lines, etc.
- **Delete Files:** Safely removes files with backups
- **Handle Errors:** Deals with problems like missing files

### 4.4 Data Storage and Management

#### 4.4.1 Directory Structure

Our system organizes files like this:

```
Project Folder/
├── client/              (Customer service)
│   ├── ClientGUI.java   (Fancy interface)
│   └── Client.java      (Simple interface)
├── server/              (The kitchens)
│   ├── Server1.java     (Kitchen #1)
│   └── Server2.java     (Kitchen #2)
├── shared/              (Recipe books)
│   ├── EncryptionUtil.java
│   ├── DataProcessor.java
│   └── FileManager.java
├── data/                (Food storage)
│   ├── server1/         (Kitchen #1's pantry)
│   │   ├── file1.csv
│   │   ├── file2.csv
│   │   └── file3.csv
│   └── server2/         (Kitchen #2's pantry)
│       ├── file1.csv    (Same as server1's)
│       ├── file2.csv    (Same as server1's)
│       └── file3.csv    (Same as server1's)
└── *.bat files          (Easy startup scripts)
```

#### 4.4.2 Why This Organization Makes Sense

**Separation of Concerns:** Each folder has a specific purpose, making it easy to find things.

**Identical Data:** Both servers have the same files, ensuring consistency.

**Easy Maintenance:** If you need to change something, you know exactly where to look.

**Scalability:** Easy to add more servers by creating more data directories.

### 4.5 Network Communication Details

#### 4.5.1 How Messages Are Sent

**TCP Sockets:** Think of this like a telephone call between computers. Once connected, they can talk back and forth reliably.

**Message Format:** We use simple text messages:
- Request: "GET_SUM:file1"
- Success Response: "RESULT:encrypted_data_here"
- Error Response: "ERROR:file not found"

**Port Numbers:** Like apartment numbers in a building:
- Server 1 lives at port 8081
- Server 2 lives at port 8082
- Client can connect to either port

#### 4.5.2 Error Handling in Network Communication

**Connection Timeouts:** If a server doesn't respond in 5 seconds, give up and show an error.

**Network Interruptions:** If the connection breaks, try to reconnect automatically.

**Server Crashes:** If a server stops working, tell the user clearly what happened.

**Invalid Responses:** If a server sends garbage data, detect it and show an error.

---

## 5. How to Install and Run

### 5.1 What You Need Before Starting

#### 5.1.1 Computer Requirements

**Operating System:** 
- Windows 7, 8, 10, or 11 (our batch files are made for Windows)
- The Java code itself works on Mac and Linux too, but you'd need to create different startup scripts

**Memory (RAM):**
- Minimum: 2 GB (the system uses very little memory)
- Recommended: 4 GB or more for smooth operation

**Hard Drive Space:**
- About 50 MB for the program files
- Extra space for your CSV files (usually very small)

**Network:**
- The system works entirely on your local computer
- No internet connection required
- Ports 8081 and 8082 must be available (not used by other programs)

#### 5.1.2 Software Requirements

**Java Development Kit (JDK):**
- Version 8 or newer (we recommend version 11 or 17)
- Must be the full JDK, not just JRE (Java Runtime Environment)
- You need the JDK to compile the Java source code

**How to Check if You Have Java:**
1. Open Command Prompt (type "cmd" in Windows search)
2. Type: `java -version`
3. Type: `javac -version`
4. If both commands work and show version 8 or higher, you're ready!

**If You Don't Have Java:**
1. Go to oracle.com/java/technologies/downloads/
2. Download the latest JDK for Windows
3. Install it (just click Next, Next, Finish)
4. Restart your computer

### 5.2 Step-by-Step Installation

#### 5.2.1 Getting the Files

**Option 1: Download from Source**
1. Download all the project files
2. Create a new folder like `C:\DistributedComputing`
3. Extract all files into this folder
4. Make sure you have all the .java files and .bat files

**Option 2: Copy from USB/Email**
1. Copy the entire project folder to your computer
2. Make sure the folder structure is preserved
3. Don't rename any files or folders

#### 5.2.2 Compiling the Code

**What is Compiling?**
Java source code (the .java files) needs to be converted into bytecode (the .class files) that the computer can run. This process is called compiling.

**Easy Way - Use Our Batch File:**
1. Open the project folder in File Explorer
2. Double-click on `compile.bat`
3. A black window will appear and show the compilation progress
4. If successful, you'll see "Compilation complete!"
5. Press any key to close the window

**Manual Way - If Batch File Doesn't Work:**
1. Open Command Prompt
2. Navigate to your project folder: `cd C:\DistributedComputing`
3. Compile shared utilities: `javac shared\*.java`
4. Compile servers: `javac -cp . server\*.java`
5. Compile clients: `javac -cp . client\*.java`
6. Compile utilities: `javac -cp . *.java`

**How to Know if Compilation Worked:**
- Look for .class files next to each .java file
- No error messages during compilation
- The compile.bat file says "Compilation complete!"

#### 5.2.3 First Test Run

**Step 1: Start the Servers**
1. Open Command Prompt #1
2. Navigate to project folder: `cd C:\DistributedComputing`
3. Run: `run-server1.bat`
4. You should see: "Server 1 is running and waiting for connections..."

5. Open Command Prompt #2 (keep the first one open!)
6. Navigate to project folder: `cd C:\DistributedComputing`
7. Run: `run-server2.bat`
8. You should see: "Server 2 is running and waiting for connections..."

**Step 2: Generate Test Data**
1. Open Command Prompt #3 (keep the other two open!)
2. Navigate to project folder: `cd C:\DistributedComputing`
3. Run: `java GenerateData`
4. You should see messages about creating files

**Step 3: Test the GUI Client**
1. Open Command Prompt #4 (or just double-click the batch file)
2. Run: `run-gui-client.bat`
3. A window should appear with buttons and menus
4. Try selecting "file1" and clicking "Request Sum"
5. You should see results appear in the Results panel

### 5.3 Understanding the Startup Files

#### 5.3.1 Batch Files Explained

**compile.bat:** Converts all .java files into .class files that can be run.

**run-server1.bat:** Starts the first server on port 8081.

**run-server2.bat:** Starts the second server on port 8082.

**run-gui-client.bat:** Starts the graphical user interface client.

**run-client.bat:** Starts the simple text-based client.

**run-file-utility.bat:** Starts the file management utility.

#### 5.3.2 What Each Batch File Does

**compile.bat:**
```batch
@echo off                          # Don't show each command
echo Compiling Java files...       # Tell user what's happening
javac shared\*.java               # Compile shared utilities first
javac -cp . server\*.java         # Compile servers
javac -cp . client\*.java         # Compile clients
javac -cp . *.java                # Compile remaining files
echo Compilation complete!         # Success message
pause                             # Wait for user to press a key
```

**run-server1.bat:**
```batch
@echo off                          # Don't show commands
echo Starting Server 1...          # Tell user what's happening
java -cp . server.Server1          # Run the Server1 class
pause                             # Keep window open after program ends
```

### 5.4 Troubleshooting Installation

#### 5.4.1 Common Problems and Solutions

**Problem: "javac is not recognized as an internal or external command"**

This means Java isn't installed properly or isn't in your PATH.

**Solution:**
1. Make sure JDK is installed (not just JRE)
2. Find where Java is installed (usually `C:\Program Files\Java\jdk-XX\bin`)
3. Add this location to your Windows PATH environment variable
4. Restart Command Prompt and try again

**Problem: "Port already in use" when starting servers**

This means another program is using ports 8081 or 8082.

**Solution:**
1. Close any other instances of the servers
2. Check what's using the ports: `netstat -an | findstr 8081`
3. Kill any programs using those ports
4. Try starting the servers again

**Problem: Compilation errors about missing classes**

This usually means files are missing or in the wrong location.

**Solution:**
1. Make sure all .java files are present
2. Check that the folder structure is correct
3. Make sure you're running commands from the right directory
4. Try deleting all .class files and recompiling

**Problem: GUI doesn't appear when running client**

This could be a Java Swing issue or display problem.

**Solution:**
1. Try the console client first: `run-client.bat`
2. Make sure you have a full JDK, not just JRE
3. Check if any error messages appear in the command window
4. Try running from Command Prompt instead of double-clicking

#### 5.4.2 Verification Steps

**After Installation, You Should Have:**
- All .java files in their proper folders
- Corresponding .class files next to each .java file
- data/server1/ and data/server2/ directories
- Sample CSV files in both data directories

**Test Each Component:**
1. **Servers:** Both should start without errors and show "waiting for connections"
2. **Data Generation:** Should create 6 CSV files (3 in each server directory)
3. **GUI Client:** Should open a window with buttons and dropdown menus
4. **Console Client:** Should show a text prompt asking for file names
5. **File Utility:** Should show a menu with 7 options

**Signs Everything is Working:**
- No error messages during compilation
- Servers start and show waiting messages
- GUI opens without errors
- You can successfully request a sum and get consistent results
- File operations (upload, delete) work properly

---

## 6. How to Use the System

### 6.1 Starting Up Everything

#### 6.1.1 The Right Order to Start Things

**Step 1: Compile First (Only Once)**
- Double-click `compile.bat`
- Wait for "Compilation complete!" message
- You only need to do this once, unless you change the code

**Step 2: Start Both Servers**
- Open first Command Prompt window
- Double-click `run-server1.bat` OR type the command manually
- Wait for "Server 1 is running and waiting for connections..."
- Open second Command Prompt window (keep first one open!)
- Double-click `run-server2.bat`
- Wait for "Server 2 is running and waiting for connections..."

**Important:** Both servers must be running before you start the client. Think of it like calling a restaurant - the restaurant needs to be open before you can place an order.

**Step 3: Start the Client**
- Double-click `run-gui-client.bat` for the graphical interface
- OR double-click `run-client.bat` for the text interface
- The client will try to connect to both servers

#### 6.1.2 What You Should See

**Server 1 Window:**
```
Starting Server 1...
Server 1 is running and waiting for connections...
```

**Server 2 Window:**
```
Starting Server 2...
Server 2 is running and waiting for connections...
```

**GUI Client Window:**
A window with buttons, dropdown menus, and text areas should appear.

### 6.2 Using the Graphical Interface (GUI)

#### 6.2.1 Understanding the GUI Layout

**Top Section - File Selection and Actions:**
- **Dropdown Menu:** Shows available files (file1, file2, file3, etc.)
- **Request Sum Button:** Starts the calculation process
- **Upload CSV File Button:** Lets you add your own files
- **Generate Sample Data Button:** Creates test files automatically
- **Delete Selected File Button:** Removes files safely
- **Refresh Files Button:** Updates the dropdown menu

**Middle Section - Results and Activity:**
- **Results Panel (Left):** Shows calculation results in a nice format
- **Activity Log (Right):** Shows what's happening step-by-step with timestamps

**Bottom Section - Status:**
- **Status Label:** Shows current operation (Ready, Processing, etc.)
- **Progress Bar:** Shows how much work is completed

#### 6.2.2 Your First Calculation

**Step 1: Make Sure You Have Data**
If the dropdown shows "No files available":
1. Click "Generate Sample Data"
2. Wait for the success message
3. Click "Refresh Files"
4. You should now see file1, file2, file3 in the dropdown

**Step 2: Request a Sum**
1. Select "file1" from the dropdown
2. Click "Request Sum"
3. Watch the Activity Log - you'll see messages like:
   - "Starting distributed sum calculation for: file1"
   - "🔗 Connecting to Server 1..."
   - "📤 Sent request to Server 1: GET_SUM:file1"
   - "📥 Received encrypted response from Server 1"
   - "🔓 Server 1 decrypted result: 125250.0"
   - Similar messages for Server 2

**Step 3: View Results**
In the Results Panel, you'll see something like:
```
=== DISTRIBUTED SUM RESULTS ===
File: file1
Server 1 Sum: 125250.00
Server 2 Sum: 125250.00
✓ CONSISTENCY CHECK: PASSED
Final Verified Sum: 125250.00
================================
```

#### 6.2.3 Uploading Your Own Files

**Step 1: Prepare Your CSV File**
Create a text file with numbers, one per line:
```
100
200
300
400
500
```
Save it as something like "mydata.csv"

**Step 2: Upload the File**
1. Click "Upload CSV File"
2. Browse to find your CSV file
3. Select it and click Open
4. Enter a name for the file (like "mydata" - don't include .csv)
5. Click OK

**Step 3: Watch the Upload Process**
The Activity Log will show:
- "Starting file upload..."
- "Uploaded to Server 1: [path]"
- "Uploaded to Server 2: [path]"
- "File upload completed successfully!"

**Step 4: Use Your Uploaded File**
1. Click "Refresh Files"
2. Your file should appear in the dropdown
3. Select it and click "Request Sum"
4. For the example above, you should get 1500 as the result

#### 6.2.4 Managing Files

**Viewing Available Files:**
The dropdown menu shows all files currently available. If you don't see a file you expect, click "Refresh Files".

**Deleting Files:**
1. Select the file you want to delete from the dropdown
2. Click "Delete Selected File"
3. A confirmation dialog will ask "Are you sure?"
4. Click "Yes" to delete or "No" to cancel
5. The file will be removed from both servers
6. A backup copy is automatically created before deletion

**Generating Test Data:**
1. Click "Generate Sample Data"
2. The system creates three files with 500 random numbers each
3. Both servers get identical copies
4. Perfect for testing without needing your own data

### 6.3 Using the Text Interface (Console)

#### 6.3.1 Starting the Console Client

1. Make sure both servers are running
2. Double-click `run-client.bat`
3. You'll see a text interface like:

```
=== Distributed Computing Client ===
Available files: file1, file2, file3

Enter file name to request sum (or 'quit' to exit): 
```

#### 6.3.2 Using the Console Client

**Making Requests:**
1. Type the name of a file (like "file1")
2. Press Enter
3. Watch the progress messages
4. See the results

**Example Session:**
```
Enter file name to request sum (or 'quit' to exit): file1
Connecting to Server 1...
Sent request to Server 1: GET_SUM:file1
Received encrypted response from Server 1
Server 1 decrypted result: 125250.0
Connecting to Server 2...
Sent request to Server 2: GET_SUM:file1
Received encrypted response from Server 2
Server 2 decrypted result: 125250.0

=== RESULTS ===
Server 1 sum: 125250.0
Server 2 sum: 125250.0
✓ CONSISTENCY CHECK PASSED
Final verified sum: 125250.0
================

Enter file name to request sum (or 'quit' to exit): 
```

**Exiting:**
Type "quit" and press Enter to exit the program.

### 6.4 Using the File Management Utility

#### 6.4.1 Starting the File Utility

1. Double-click `run-file-utility.bat`
2. You'll see a menu with options:

```
=== File Upload Utility ===
This utility helps you manage CSV files for the distributed system.

Options:
1. Upload a CSV file
2. List existing files
3. Validate a file
4. View file information
5. Generate sample data
6. Delete a file
7. Exit
Choose an option (1-7): 
```

#### 6.4.2 File Utility Options Explained

**Option 1: Upload a CSV file**
- Enter the full path to your CSV file
- Enter a name for the file in the system
- The utility copies it to both servers
- Validates the file format automatically

**Option 2: List existing files**
- Shows all files available on both servers
- Helps you see what's currently available
- Useful for checking if uploads worked

**Option 3: Validate a file**
- Checks if a file is in the correct format
- Reports any problems with the data
- Shows how many valid numbers were found
- Gives warnings about non-numeric data

**Option 4: View file information**
- Shows detailed statistics about a file
- Number of lines, sum, average, min, max values
- File size and last modified date
- Compares both servers to ensure consistency

**Option 5: Generate sample data**
- Creates test files automatically
- Same as the GUI "Generate Sample Data" button
- Creates file1, file2, file3 with random numbers

**Option 6: Delete a file**
- Safely removes files from both servers
- Asks for confirmation before deleting
- Creates backup copies automatically
- Shows exactly what was deleted

**Option 7: Exit**
- Closes the file utility program

### 6.5 Understanding the Results

#### 6.5.1 Successful Results

When everything works correctly, you'll see:

**GUI Results Panel:**
```
=== DISTRIBUTED SUM RESULTS ===
File: file1
Server 1 Sum: 125,250.00
Server 2 Sum: 125,250.00
✓ CONSISTENCY CHECK: PASSED
Final Verified Sum: 125,250.00
================================
```

**Console Results:**
```
=== RESULTS ===
Server 1 sum: 125250.0
Server 2 sum: 125250.0
✓ CONSISTENCY CHECK PASSED
Final verified sum: 125250.0
================
```

**What This Means:**
- Both servers processed the same file
- Both got exactly the same answer
- The data is consistent and reliable
- The final answer is trustworthy

#### 6.5.2 Error Results

**Inconsistent Data:**
```
=== DISTRIBUTED SUM RESULTS ===
File: file1
Server 1 Sum: 125,250.00
Server 2 Sum: 125,300.00
❌ CONSISTENCY CHECK: FAILED
ERROR: Servers returned different results!
Difference: 50.00
================================
```

**What This Means:**
- The servers have different data files
- There might be a problem with one of the files
- The result is not reliable
- You should check the data files

**Connection Errors:**
```
❌ Failed to get results from one or both servers
```

**What This Means:**
- One or both servers are not running
- Network connection problem
- Server crashed during processing
- Check that both servers are running

#### 6.5.3 Activity Log Messages

**Normal Operation Messages:**
- 🔗 "Connecting to Server 1..." - Establishing connection
- 📤 "Sent request to Server 1: GET_SUM:file1" - Request sent
- 📥 "Received encrypted response from Server 1" - Got encrypted result
- 🔓 "Server 1 decrypted result: 125250.0" - Decrypted successfully
- ✓ "Consistency check passed - results match!" - Everything good

**Error Messages:**
- ❌ "Connection error with Server 1: Connection refused" - Server not running
- ❌ "Failed to decrypt data from Server 1" - Encryption problem
- ❌ "Invalid response from Server 1" - Communication problem
- ❌ "Consistency check failed - results don't match!" - Data inconsistency

### 6.6 Best Practices for Using the System

#### 6.6.1 File Management Best Practices

**Keep Files Small:** Start with files containing 10-100 numbers for testing, then try larger files.

**Use Simple Numbers:** Stick to whole numbers or simple decimals (like 123.45) to avoid confusion.

**Name Files Clearly:** Use descriptive names like "sales_data" or "test_numbers" instead of "file1".

**Check Your Data:** Use the file validation feature to make sure your CSV files are formatted correctly.

**Make Backups:** The system creates automatic backups when deleting, but keep your own copies of important data.

#### 6.6.2 Testing Best Practices

**Start Simple:** Begin with the generated sample data before using your own files.

**Test Both Interfaces:** Try both the GUI and console clients to see which you prefer.

**Verify Results:** For small files, manually calculate the sum to verify the system is working correctly.

**Test Error Conditions:** Try uploading invalid files or stopping servers to see how the system handles errors.

**Monitor the Logs:** Watch the Activity Log to understand what's happening behind the scenes.

#### 6.6.3 Troubleshooting Tips

**If Results Don't Match:**
1. Check that both servers have identical files
2. Restart both servers
3. Re-upload the file to ensure both servers get the same data
4. Check for file corruption

**If Connections Fail:**
1. Make sure both servers are running and show "waiting for connections"
2. Check that no other programs are using ports 8081 and 8082
3. Try restarting the servers
4. Check Windows Firewall settings

**If GUI Doesn't Respond:**
1. Check the Command Prompt window for error messages
2. Try the console client instead
3. Make sure Java is properly installed
4. Restart the client program

---