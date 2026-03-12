package library;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ViewIssuedBooksPage {

    public ViewIssuedBooksPage() {

        JFrame frame = new JFrame("Issued Books");
        frame.setSize(950, 500);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // ----------- Table Model -----------
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        table.setRowHeight(25);

        // ----------- Columns -----------
        model.addColumn("Issue ID");
        model.addColumn("Student ID");
        model.addColumn("Student Name");
        model.addColumn("Book ID");
        model.addColumn("Book Title");
        model.addColumn("Issue Date");
        model.addColumn("Return Date");

        // ----------- Fetch Data -----------
        try {
            Connection con = DBConnection.getConnection();

            String sql = 
                "SELECT ir.issue_id, ir.student_id, ir.book_id, ir.issue_date, ir.return_date, " +
                "s.student_name, b.title " +
                "FROM issue_records ir " +
                "JOIN students s ON ir.student_id = s.student_id " +
                "JOIN books b ON ir.book_id = b.book_id";

            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String returnDate = rs.getString("return_date");
                if (returnDate == null) returnDate = "Not Returned Yet";

                model.addRow(new Object[]{
                        rs.getInt("issue_id"),
                        rs.getInt("student_id"),
                        rs.getString("student_name"),
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("issue_date"),
                        returnDate
                });
            }

            con.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error loading issued books: " + ex.getMessage());
            ex.printStackTrace();
        }

        // Add scrollpane
        JScrollPane sp = new JScrollPane(table);
        frame.add(sp, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new ViewIssuedBooksPage();
    }
}
