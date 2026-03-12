package library;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {

    public Dashboard() {

        setTitle("Library Dashboard");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel title = new JLabel("Welcome to Library Management System!");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setBounds(260, 20, 500, 30);
        add(title);

        // ------------ BUTTONS --------------
        JButton btnAddBook = new JButton("Add Book");
        btnAddBook.setBounds(100, 100, 200, 40);
        add(btnAddBook);

        JButton btnViewBooks = new JButton("View Books");
        btnViewBooks.setBounds(100, 160, 200, 40);
        add(btnViewBooks);

        JButton btnAddStudent = new JButton("Add Student");
        btnAddStudent.setBounds(350, 100, 200, 40);
        add(btnAddStudent);

        JButton btnViewStudents = new JButton("View Students");
        btnViewStudents.setBounds(350, 160, 200, 40);
        add(btnViewStudents);

        JButton btnIssueBook = new JButton("Issue Book");
        btnIssueBook.setBounds(600, 100, 200, 40);
        add(btnIssueBook);

        JButton btnReturnBook = new JButton("Return Book");
        btnReturnBook.setBounds(600, 160, 200, 40);
        add(btnReturnBook);

        JButton btnViewIssued = new JButton("View Issued Books");
        btnViewIssued.setBounds(350, 220, 200, 40);
        add(btnViewIssued);

        JButton btnLogout = new JButton("Logout");
        btnLogout.setBounds(350, 300, 200, 40);
        add(btnLogout);

        // ------------ BUTTON ACTIONS ------------

        btnAddBook.addActionListener(e -> new AddBookPage());
        btnViewBooks.addActionListener(e -> new ViewBooksPage());
        btnAddStudent.addActionListener(e -> new AddStudentPage());
        btnViewStudents.addActionListener(e -> new ViewStudentsPage());
        btnIssueBook.addActionListener(e -> new IssueBookPage());
        btnReturnBook.addActionListener(e -> new ReturnBookPage());
        btnViewIssued.addActionListener(e -> new ViewIssuedBooksPage());

        btnLogout.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Logged out!");
            dispose();
            new LoginPage();
        });

        setVisible(true);
    }
}
