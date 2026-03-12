package library;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.time.LocalDate;

public class ReturnBookPage {

    public ReturnBookPage() {

        JFrame frame = new JFrame("Return Book");
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel title = new JLabel("Return Book");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setBounds(170, 20, 200, 30);
        frame.add(title);

        // -------- Issued Records Dropdown --------
        JLabel lblIssued = new JLabel("Select Issued Record:");
        lblIssued.setBounds(40, 90, 150, 25);
        frame.add(lblIssued);

        JComboBox<String> issuedCombo = new JComboBox<>();
        issuedCombo.setBounds(200, 90, 260, 25);
        frame.add(issuedCombo);

        // -------- Return Date --------
        JLabel lblDate = new JLabel("Return Date:");
        lblDate.setBounds(40, 150, 150, 25);
        frame.add(lblDate);

        JTextField txtDate = new JTextField(LocalDate.now().toString());
        txtDate.setBounds(200, 150, 260, 25);
        frame.add(txtDate);

        // -------- Return Button --------
        JButton btnReturn = new JButton("Return Book");
        btnReturn.setBounds(180, 230, 150, 40);
        frame.add(btnReturn);

        // -------- Load issued books that are NOT returned --------
        try {
            Connection con = DBConnection.getConnection();
            String sql =
                "SELECT ir.issue_id, ir.book_id, ir.student_id, b.title, s.student_name " +
                "FROM issue_records ir " +
                "JOIN books b ON ir.book_id = b.book_id " +
                "JOIN students s ON ir.student_id = s.student_id " +
                "WHERE ir.return_date IS NULL";

            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String item = rs.getInt("issue_id") + " - " +
                              rs.getString("student_name") + " | " +
                              rs.getString("title");
                issuedCombo.addItem(item);
            }

            con.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error loading issued books: " + ex.getMessage());
        }

        // -------- Button Action --------
        btnReturn.addActionListener(e -> {

            try {
                String selected = (String) issuedCombo.getSelectedItem();
                if (selected == null) {
                    JOptionPane.showMessageDialog(frame, "No issued books to return!");
                    return;
                }

                int issueId = Integer.parseInt(selected.split(" - ")[0]);
                String returnDate = txtDate.getText();

                Connection con = DBConnection.getConnection();

                // Get the book id from this issue record
                String sqlGetBook = "SELECT book_id FROM issue_records WHERE issue_id = ?";
                PreparedStatement pstBook = con.prepareStatement(sqlGetBook);
                pstBook.setInt(1, issueId);
                ResultSet rsBook = pstBook.executeQuery();

                int bookId = 0;
                if (rsBook.next()) {
                    bookId = rsBook.getInt("book_id");
                }

                // Update issue record with return date
                String sqlReturn = "UPDATE issue_records SET return_date = ? WHERE issue_id = ?";
                PreparedStatement pst1 = con.prepareStatement(sqlReturn);
                pst1.setString(1, returnDate);
                pst1.setInt(2, issueId);
                pst1.executeUpdate();

                // Increase the quantity of the book
                String sqlQuantity = "UPDATE books SET quantity = quantity + 1 WHERE book_id = ?";
                PreparedStatement pst2 = con.prepareStatement(sqlQuantity);
                pst2.setInt(1, bookId);
                pst2.executeUpdate();

                JOptionPane.showMessageDialog(frame, "Book Returned Successfully!");

                con.close();
                frame.dispose();
                new ReturnBookPage();  // reload the page to refresh dropdown

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new ReturnBookPage();
    }
}
