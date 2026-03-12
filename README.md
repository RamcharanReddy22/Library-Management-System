📚 Library Management System (Java & MySQL)

This is a desktop-based Library Management System built using Java Swing and MySQL.
The project helps manage books, students, and book issue/return records through a simple admin interface connected to a database.

The main goal of this project is to reduce manual work in library management and store records digitally.

📌 Project Overview

This application performs common library tasks such as:

Adding new books

Managing student records

Issuing books

Returning books

Viewing issued book details

The system uses a Java Swing graphical interface for interaction and MySQL database to store all the records permanently.

The project contains:

Java source files for GUI and application logic

SQL files for creating and managing the database

🚀 Features

Admin login authentication

Add new books to the library

View and manage book records

Add student information

View student details

Issue books to students

Return issued books

View issued book history

Data stored securely in MySQL database

Simple and user-friendly interface

🧠 Technologies Used

Java (Core Java)

Java Swing for GUI

JDBC for database connectivity

MySQL database

SQL

🗂️ Project Structure
Library-Management-System
│
├── src
│   └── library
│       ├── LoginPage.java
│       ├── Dashboard.java
│       ├── AddBookPage.java
│       ├── ViewBooksPage.java
│       ├── AddStudentPage.java
│       ├── ViewStudentsPage.java
│       ├── IssueBookPage.java
│       ├── ReturnBookPage.java
│       ├── ViewIssuedBooksPage.java
│       └── DBConnection.java
│
├── Library Management System DATABASE
│   ├── library_management_admin.sql
│   ├── library_management_books.sql
│   ├── library_management_students.sql
│   └── library_management_issue_records.sql
│
└── README.md
🗄️ Database Design

The database used in this project is library_management.

Tables

admin – stores login credentials

books – stores book information and quantity

students – stores student details

issue_records – keeps track of issued and returned books

Relationships

issue_records.student_id → students.student_id

issue_records.book_id → books.book_id

These relationships maintain data consistency using foreign keys.

▶️ How to Run the Project
1️⃣ Setup Database

Open MySQL and create the database:

CREATE DATABASE library_management;

Then run all the .sql files inside the database folder.

2️⃣ Run the Java Application

Open the project in Eclipse / IntelliJ / NetBeans

Update database username and password in:

DBConnection.java

Run the program starting with:

LoginPage.java
🔐 Default Admin Login (Demo)

Username

admin

Password

admin
🎯 What I Learned

While developing this project, I learned:

How to connect Java with MySQL using JDBC

Performing CRUD operations

Designing Java Swing GUI applications

Creating relational databases

Structuring a real-world software project

⚠️ Note

This project is developed only for learning purposes.
Passwords are stored in plain text for demonstration and should be encrypted in real applications.

👤 Author

Ch Ram Charan Reddy

GitHub:
https://github.com/RamcharanReddy22
