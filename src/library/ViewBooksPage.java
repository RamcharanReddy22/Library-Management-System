package library;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ViewBooksPage {

    public ViewBooksPage() {

        JFrame frame = new JFrame("View All Books");
        frame.setSize(900, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // ---------------- Table Model ----------------
        DefaultTableModel model = new DefaultTableModel();

        JTable table = new JTable(model);
        table.setRowHeight(25);

        // Columns of the table
        model.addColumn("Book ID");
        model.addColumn("Title");
        model.addColumn("Author");
        model.addColumn("Category");
        model.addColumn("Genre");
        model.addColumn("Publication Year");
        model.addColumn("Quantity");

        // ---------------- Load Data From DB ----------------
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM books";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("category"),
                        rs.getString("genre"),
                        rs.getInt("publication_year"),
                        rs.getInt("quantity")
                });
            }

            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error loading books: " + ex.getMessage());
        }

        // Add table inside scrollpane
        JScrollPane sp = new JScrollPane(table);
        frame.add(sp, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new ViewBooksPage();
    }
}
