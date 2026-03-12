package library;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ViewStudentsPage {

    public ViewStudentsPage() {

        JFrame frame = new JFrame("View Students");
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // ----------- Table Model -----------
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        table.setRowHeight(25);

        // ----------- Table Columns -----------
        model.addColumn("Student ID");
        model.addColumn("Student Name");
        model.addColumn("Department");
        model.addColumn("Contact");

        // ----------- Load Data from DB -----------
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM students";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("student_id"),
                        rs.getString("student_name"),
                        rs.getString("department"),
                        rs.getString("contact")
                });
            }

            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error loading students: " + ex.getMessage());
        }

        JScrollPane sp = new JScrollPane(table);
        frame.add(sp, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new ViewStudentsPage();
    }
}
