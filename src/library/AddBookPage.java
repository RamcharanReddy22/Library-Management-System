package library;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class AddBookPage extends JFrame {

    public AddBookPage() {

        setTitle("Add New Book");
        setSize(450, 500);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel heading = new JLabel("Add Book Details");
        heading.setFont(new Font("Arial", Font.BOLD, 20));
        heading.setBounds(130, 20, 250, 30);
        add(heading);

        // ----------- Form Labels & Inputs -------------
        JLabel lblTitle = new JLabel("Title:");
        lblTitle.setBounds(50, 80, 100, 30);
        add(lblTitle);

        JTextField txtTitle = new JTextField();
        txtTitle.setBounds(180, 80, 200, 30);
        add(txtTitle);

        JLabel lblAuthor = new JLabel("Author:");
        lblAuthor.setBounds(50, 130, 100, 30);
        add(lblAuthor);

        JTextField txtAuthor = new JTextField();
        txtAuthor.setBounds(180, 130, 200, 30);
        add(txtAuthor);

        JLabel lblCategory = new JLabel("Category:");
        lblCategory.setBounds(50, 180, 100, 30);
        add(lblCategory);

        JTextField txtCategory = new JTextField();
        txtCategory.setBounds(180, 180, 200, 30);
        add(txtCategory);

        JLabel lblYear = new JLabel("Publication Year:");
        lblYear.setBounds(50, 230, 130, 30);
        add(lblYear);

        JTextField txtYear = new JTextField();
        txtYear.setBounds(180, 230, 200, 30);
        add(txtYear);

        JLabel lblQuantity = new JLabel("Quantity:");
        lblQuantity.setBounds(50, 280, 100, 30);
        add(lblQuantity);

        JTextField txtQuantity = new JTextField();
        txtQuantity.setBounds(180, 280, 200, 30);
        add(txtQuantity);

        // ------------ Add Book Button ------------
        JButton btnAdd = new JButton("Add Book");
        btnAdd.setBounds(150, 350, 150, 40);
        add(btnAdd);

        // ------------ Button Action Logic ------------
        btnAdd.addActionListener(e -> {

            String title = txtTitle.getText();
            String author = txtAuthor.getText();
            String category = txtCategory.getText();
            String yearStr = txtYear.getText();
            String qtyStr = txtQuantity.getText();

            // ----- Validation -----
            if (title.isEmpty() || author.isEmpty() || qtyStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Title, Author, and Quantity are required!");
                return;
            }

            try {
                int year = yearStr.isEmpty() ? 0 : Integer.parseInt(yearStr);
                int qty = Integer.parseInt(qtyStr);

                Connection con = DBConnection.getConnection();

                String sql = "INSERT INTO books (title, author, category, publication_year, quantity) VALUES (?, ?, ?, ?, ?)";

                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, title);
                pst.setString(2, author);
                pst.setString(3, category);
                pst.setInt(4, year == 0 ? null : year); 
                pst.setInt(5, qty);

                pst.executeUpdate();

                JOptionPane.showMessageDialog(this, "Book Added Successfully!");

                // Clear fields
                txtTitle.setText("");
                txtAuthor.setText("");
                txtCategory.setText("");
                txtYear.setText("");
                txtQuantity.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Year and Quantity must be numbers!");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
            }
        });

        setVisible(true);
    }
}
