package library;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.time.LocalDate;

public class IssueBookPage {

    public IssueBookPage() {

        JFrame frame = new JFrame("Issue Book");
        frame.setSize(450, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel title = new JLabel("Issue Book");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setBounds(150, 20, 200, 30);
        frame.add(title);

        // ------- Student Dropdown -------
        JLabel lblStudent = new JLabel("Select Student:");
        lblStudent.setBounds(50, 80, 120, 25);
        frame.add(lblStudent);

        JComboBox<String> studentCombo = new JComboBox<>();
        studentCombo.setBounds(180, 80, 200, 25);
        frame.add(studentCombo);

        // ------- Book Dropdown -------
        JLabel lblBook = new JLabel("Select Book:");
        lblBook.setBounds(50, 130, 120, 25);
        frame.add(lblBook);

        JComboBox<String> bookCombo = new JComboBox<>();
        bookCombo.setBounds(180, 130, 200, 25);
        frame.add(bookCombo);

        // ------- Issue Date -------
        JLabel lblDate = new JLabel("Issue Date:");
        lblDate.setBounds(50, 180, 120, 25);
        frame.add(lblDate);

        JTextField txtDate = new JTextField(LocalDate.now().toString());
        txtDate.setBounds(180, 180, 200, 25);
        frame.add(txtDate);

        // ------- Issue Button -------
        JButton btnIssue = new JButton("Issue Book");
        btnIssue.setBounds(160, 250, 120, 35);
        frame.add(btnIssue);

        // ------- Load Students into Dropdown -------
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT student_id, student_name FROM students";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                studentCombo.addItem(rs.getInt("student_id") + " - " + rs.getString("student_name"));
            }

            con.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error loading students: " + ex.getMessage());
        }

        // ------- Load Books into Dropdown -------
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT book_id, title FROM books WHERE quantity > 0";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                bookCombo.addItem(rs.getInt("book_id") + " - " + rs.getString("title"));
            }

            con.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error loading books: " + ex.getMessage());
        }

        // ------- Button Action -------
        btnIssue.addActionListener(e -> {

            try {
                String studentSelected = (String) studentCombo.getSelectedItem();
                String bookSelected = (String) bookCombo.getSelectedItem();

                if (studentSelected == null || bookSelected == null) {
                    JOptionPane.showMessageDialog(frame, "Please select both student and book.");
                    return;
                }

                int studentId = Integer.parseInt(studentSelected.split(" - ")[0]);
                int bookId = Integer.parseInt(bookSelected.split(" - ")[0]);
                String issueDate = txtDate.getText();

                Connection con = DBConnection.getConnection();

                // Insert into issue_records
                String sql1 = "INSERT INTO issue_records (student_id, book_id, issue_date) VALUES (?, ?, ?)";
                PreparedStatement pst1 = con.prepareStatement(sql1);
                pst1.setInt(1, studentId);
                pst1.setInt(2, bookId);
                pst1.setString(3, issueDate);
                pst1.executeUpdate();

                // Decrease book quantity by 1
                String sql2 = "UPDATE books SET quantity = quantity - 1 WHERE book_id = ?";
                PreparedStatement pst2 = con.prepareStatement(sql2);
                pst2.setInt(1, bookId);
                pst2.executeUpdate();

                JOptionPane.showMessageDialog(frame, "Book Issued Successfully!");

                con.close();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Database Error: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new IssueBookPage();
    }
}
