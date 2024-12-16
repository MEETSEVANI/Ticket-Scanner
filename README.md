# Ticket-Scanner

Overview

The Ticket Scanner Application is a Java-based desktop application that simulates a ticket gate system. It enables users to scan, validate, purchase, and reset tickets. The application uses a tabular data file (codes.txt) to store ticket details and manages ticket status (purchased, entered, or not).

# Feature-
->Ticket Scanning: Validates ticket numbers and grants access if valid.
->Purchase Tickets: Marks tickets as purchased in the system.
->Reset Tickets: Resets all tickets to their initial state.
->File Persistence: Stores ticket data in a tabular format (codes.txt) for persistence.
#UI Components:
Input field for ticket barcode.
Buttons for scanning, purchasing, and resetting tickets.
Feedback labels for user status messages.

#Application Workflow

#Scan Ticket:
Enter a ticket barcode in the input field and click the Scan button.
The application checks:
If the ticket is purchased.
If the ticket is valid.
If the ticket has already been entered.

#Purchase Tickets:
Click the Purchase button to mark all tickets as purchased.
Updates the second column in codes.txt to Y.
#Reset Tickets:
Click the Reset button to reset all tickets to the unentered state.
Updates the third column in codes.txt to N.
#Persistent Updates:
Changes made during runtime are saved back to codes.txt for persistence.

#Requirements
Java Development Kit (JDK): Version 8 or higher.
IDE: IntelliJ IDEA or any Java-compatible IDE (optional).
Operating System: Windows, macOS, or Linux.

#Installation and Setup
Clone the repository or download the project.
git clone <repository-url>
Open the project in your IDE.
Ensure codes.txt exists in the project root and follows the specified format.
Compile and run the TicketScanningApp class.

#Usage
Run the application.
Use the input field to enter a ticket number and interact with the buttons:
Scan: Validate the entered ticket.
Purchase: Mark tickets as purchased.
Reset: Reset all tickets to unentered state.

