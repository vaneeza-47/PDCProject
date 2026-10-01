#!/usr/bin/env python3
"""
PowerPoint Presentation Generator for Distributed Computing System Project
Run this script to automatically generate a presentation about your Java distributed computing project.

Requirements: pip install python-pptx
"""

from pptx import Presentation
from pptx.util import Inches, Pt
from pptx.enum.text import PP_ALIGN
from pptx.dml.color import RGBColor

def create_presentation():
    # Create presentation object
    prs = Presentation()
    
    # Slide 1: Title Slide
    slide_layout = prs.slide_layouts[0]  # Title slide layout
    slide = prs.slides.add_slide(slide_layout)
    title = slide.shapes.title
    subtitle = slide.placeholders[1]
    
    title.text = "Distributed Computing System"
    subtitle.text = "Java Implementation with GUI Interface\n\nStudent: [Your Name]\nCourse: [Course Name]\nDate: January 2025"
    
    # Slide 2: Project Overview
    slide_layout = prs.slide_layouts[1]  # Title and content layout
    slide = prs.slides.add_slide(slide_layout)
    title = slide.shapes.title
    content = slide.placeholders[1]
    
    title.text = "Project Overview"
    content.text = """• Distributed computing system with two servers
• Client-server architecture using TCP sockets
• AES encryption for secure data transmission
• Consistency checking for data integrity
• GUI and console interfaces
• File management (upload, process, delete)"""
    
    # Slide 3: System Architecture
    slide_layout = prs.slide_layouts[1]
    slide = prs.slides.add_slide(slide_layout)
    title = slide.shapes.title
    content = slide.placeholders[1]
    
    title.text = "System Architecture"
    content.text = """Client Application
    ↓
Network Layer (TCP Sockets)
    ↓
┌─────────────┬─────────────┐
│  Server 1   │  Server 2   │
│ Port 8081   │ Port 8082   │
└─────────────┴─────────────┘
    ↓             ↓
CSV Data Files (Identical copies)"""
    
    # Slide 4: Key Features
    slide_layout = prs.slide_layouts[1]
    slide = prs.slides.add_slide(slide_layout)
    title = slide.shapes.title
    content = slide.placeholders[1]
    
    title.text = "Key Features"
    content.text = """🖥️ Dual Interface Support
   • Graphical User Interface (Swing)
   • Command-line Interface

🔒 Security Implementation
   • AES-128 encryption
   • Input validation
   • Secure error handling

📊 Data Processing
   • CSV file processing
   • Parallel computation
   • Consistency verification"""
    
    # Slide 5: How It Works
    slide_layout = prs.slide_layouts[1]
    slide = prs.slides.add_slide(slide_layout)
    title = slide.shapes.title
    content = slide.placeholders[1]
    
    title.text = "How It Works"
    content.text = """1. Client sends request to both servers
2. Servers process CSV files simultaneously
3. Each server calculates sum of numbers
4. Results encrypted using AES encryption
5. Encrypted data sent back to client
6. Client decrypts and compares results
7. Consistency check: Pass ✓ or Fail ❌
8. Final result displayed to user"""
    
    # Slide 6: Technologies Used
    slide_layout = prs.slide_layouts[1]
    slide = prs.slides.add_slide(slide_layout)
    title = slide.shapes.title
    content = slide.placeholders[1]
    
    title.text = "Technologies Used"
    content.text = """Programming Language: Java 8+

Core Technologies:
• Java Swing (GUI Framework)
• TCP Sockets (Network Communication)
• AES Encryption (javax.crypto)
• File I/O (CSV Processing)
• Multi-threading (Parallel Processing)

Development Tools:
• Java Development Kit (JDK)
• Windows Batch Scripts
• Command Line Interface"""
    
    # Slide 7: Demo Screenshots
    slide_layout = prs.slide_layouts[1]
    slide = prs.slides.add_slide(slide_layout)
    title = slide.shapes.title
    content = slide.placeholders[1]
    
    title.text = "User Interface Demo"
    content.text = """GUI Features:
• File selection dropdown
• Upload CSV files
• Generate sample data
• Real-time activity log
• Progress tracking
• Results display with consistency check

Console Features:
• Simple text-based interaction
• Direct file processing
• Minimal resource usage"""
    
    # Slide 8: Security Implementation
    slide_layout = prs.slide_layouts[1]
    slide = prs.slides.add_slide(slide_layout)
    title = slide.shapes.title
    content = slide.placeholders[1]
    
    title.text = "Security Implementation"
    content.text = """Encryption Details:
• Algorithm: AES (Advanced Encryption Standard)
• Key Size: 128-bit
• Mode: ECB (Electronic Codebook)
• Encoding: Base64 for transmission

Security Features:
• Data encryption during transmission
• Consistency checking for integrity
• Input validation for CSV files
• Secure error handling"""
    
    # Slide 9: Testing Results
    slide_layout = prs.slide_layouts[1]
    slide = prs.slides.add_slide(slide_layout)
    title = slide.shapes.title
    content = slide.placeholders[1]
    
    title.text = "Testing & Results"
    content.text = """Test Scenarios:
✓ Basic functionality (file upload, processing)
✓ Consistency checking (identical results)
✓ Error handling (server failures, invalid files)
✓ Security (encryption/decryption)
✓ Performance (large files, multiple clients)

Results:
• Successfully processes files with 10,000+ numbers
• Consistent results across both servers
• Secure data transmission verified
• User-friendly error messages"""
    
    # Slide 10: Learning Outcomes
    slide_layout = prs.slide_layouts[1]
    slide = prs.slides.add_slide(slide_layout)
    title = slide.shapes.title
    content = slide.placeholders[1]
    
    title.text = "Learning Outcomes"
    content.text = """Technical Skills Gained:
• Distributed computing concepts
• Network programming (TCP/IP)
• Encryption/decryption implementation
• GUI development with Swing
• Multi-threaded programming
• File I/O and data validation

Concepts Learned:
• Client-server architecture
• Data consistency and integrity
• Fault tolerance and error handling
• Security best practices"""
    
    # Slide 11: Real-World Applications
    slide_layout = prs.slide_layouts[1]
    slide = prs.slides.add_slide(slide_layout)
    title = slide.shapes.title
    content = slide.placeholders[1]
    
    title.text = "Real-World Applications"
    content.text = """This project demonstrates concepts used in:

🌐 Web Services
   • Load balancing across multiple servers
   • Data replication and consistency

🏦 Banking Systems
   • Transaction verification
   • Secure data transmission

☁️ Cloud Computing
   • Distributed processing
   • Fault tolerance

📱 Social Media Platforms
   • Handling millions of concurrent users
   • Data consistency across data centers"""
    
    # Slide 12: Challenges & Solutions
    slide_layout = prs.slide_layouts[1]
    slide = prs.slides.add_slide(slide_layout)
    title = slide.shapes.title
    content = slide.placeholders[1]
    
    title.text = "Challenges & Solutions"
    content.text = """Challenges Faced:
• Ensuring data consistency across servers
• Implementing secure communication
• Handling network failures gracefully
• Creating user-friendly interfaces

Solutions Implemented:
• Consistency checking algorithm
• AES encryption for data security
• Comprehensive error handling
• Dual interface (GUI + Console)
• Automatic backup before file deletion"""
    
    # Slide 13: Future Enhancements
    slide_layout = prs.slide_layouts[1]
    slide = prs.slides.add_slide(slide_layout)
    title = slide.shapes.title
    content = slide.placeholders[1]
    
    title.text = "Future Enhancements"
    content.text = """Possible Improvements:
• Web-based interface
• Database integration for result storage
• Support for multiple file formats (Excel, JSON)
• Dynamic server discovery
• Load balancing algorithms
• Cloud deployment (AWS, Azure)
• Machine learning integration
• Real-time data streaming"""
    
    # Slide 14: Conclusion
    slide_layout = prs.slide_layouts[1]
    slide = prs.slides.add_slide(slide_layout)
    title = slide.shapes.title
    content = slide.placeholders[1]
    
    title.text = "Conclusion"
    content.text = """Project Achievements:
✓ Successfully implemented distributed computing system
✓ Demonstrated parallel processing concepts
✓ Implemented secure data transmission
✓ Created user-friendly interfaces
✓ Achieved data consistency verification

Key Takeaways:
• Understanding of distributed systems
• Practical network programming experience
• Security implementation knowledge
• GUI development skills
• Problem-solving and debugging abilities"""
    
    # Slide 15: Thank You
    slide_layout = prs.slide_layouts[0]  # Title slide layout
    slide = prs.slides.add_slide(slide_layout)
    title = slide.shapes.title
    subtitle = slide.placeholders[1]
    
    title.text = "Thank You"
    subtitle.text = "Questions & Discussion\n\nDistributed Computing System\nJava Implementation\n\n[Your Name]\n[Your Email]"
    
    # Save presentation
    prs.save('Distributed_Computing_System_Presentation.pptx')
    print("✓ Presentation created successfully!")
    print("✓ File saved as: Distributed_Computing_System_Presentation.pptx")
    print("✓ Total slides: 15")
    print("\nPresentation includes:")
    print("  • Title slide")
    print("  • Project overview and architecture")
    print("  • Key features and technologies")
    print("  • Security implementation")
    print("  • Testing results")
    print("  • Learning outcomes")
    print("  • Real-world applications")
    print("  • Future enhancements")
    print("  • Conclusion and Q&A")

if __name__ == "__main__":
    try:
        create_presentation()
    except ImportError:
        print("❌ Error: python-pptx library not found")
        print("📦 Please install it using: pip install python-pptx")
        print("🔄 Then run this script again")
    except Exception as e:
        print(f"❌ Error creating presentation: {e}")
        print("🔍 Please check if you have write permissions in this directory")