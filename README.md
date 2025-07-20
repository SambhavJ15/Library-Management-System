📚 Library Management System (Java Swing)
The Library Management System is a robust, user-friendly desktop application designed for managing books, users, and borrowing transactions. Built using Java and Java Swing, this project focuses on applying Object-Oriented Programming (OOP) principles to deliver a responsive and intuitive GUI-based experience.

🚀 Features
🧑‍💼 User Management
Add new users to the system easily.

Each user maintains a record of books borrowed.

Displays the number of books borrowed next to each user.

📚 Book Management
Add new books with a title and author.

Real-time status updates (Available / Borrowed).

Integrated search functionality to filter books by title or author.

🔄 Borrowing & Returning
Users can borrow available books with a single click.

Returning functionality updates the status and user record instantly.

Ensures that only borrowed books can be returned.

💾 Data Persistence
Save all book and user data to a file (library_data.dat) using Java Serialization.

Load previous session data for seamless user experience.

Clear all records with a click.

🔍 Search Functionality
Built-in live search bar allows users to find books by title or author.

Dynamic filtering of the book list as the user types.

🖥 GUI Highlights
Built using Java Swing for responsive and cross-platform UI.

Organized layout with separate panels for Books and Users.

Clean button controls for every action: Add, Borrow, Return, Save, Load, Clear.

🛠 Technologies Used
Java SE 8+

Swing (for GUI development)

Object Serialization (for file saving/loading)

AWT (for layout and event handling)

Maven/IDE (for project development - optional)

📂 Project Structure
Book – Serializable class to store book info and status.

User – Serializable class to store user name and list of borrowed books.

LibraryManagementSystem – Main class that builds the UI and implements all logic and actions.

⚙ How to Use
Compile and run the LibraryManagementSystem.java file.

Add books and users using the input fields.

Select a book and user, then click "Borrow Book".

To return, select the borrowed book and user, and click "Return Book".

Save records to preserve data between sessions.

Load records any time to restore the previous state.

📌 Future Enhancements
Implement user authentication and roles (Admin vs User).

Export data to Excel or CSV files.

Due date tracking and overdue alerts.

Database integration (MySQL or SQLite) for larger scale applications.

UI improvements with modern look and feel (e.g., JavaFX).

👨‍💻 Author
Sambhav Jain
Tech Stack: Java, Swing, AWT, OOP
Passionate about building real-world software projects using Java and GUI frameworks.
Open to collaboration and feedback!

# Library-Management-System
