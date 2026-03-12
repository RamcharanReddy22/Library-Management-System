package library;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddStudentPage {

    public AddStudentPage() {

        JFrame frame = new JFrame("Add Student");
        frame.setSize(400, 350);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel lblTitle = new JLabel("Add Student Details");
        lblTitle.setBounds(110, 20, 200, 30);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        frame.add(lblTitle);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(50, 80, 100, 25);
        frame.add(lblName);

        JTextField txtName = new JTextField();
        txtName.setBounds(150, 80, 180, 25);
        frame.add(txtName);

        JLabel lblDept = new JLabel("Department:");
        lblDept.setBounds(50, 130, 100, 25);
        frame.add(lblDept);

        // Dropdown for Department
        String[] departments = {"CSE", "CSD", "ECE", "EEE", "IT"};
        JComboBox<String> deptCombo = new JComboBox<>(departments);
        deptCombo.setBounds(150, 130, 180, 25);
        frame.add(deptCombo);

        JLabel lblContact = new JLabel("Contact:");
        lblContact.setBounds(50, 180, 100, 25);
        frame.add(lblContact);

        JTextField txtContact = new JTextField();
        txtContact.setBounds(150, 180, 180, 25);
        frame.add(txtContact);

        JButton btnAdd = new JButton("Add Student");
        btnAdd.setBounds(130, 240, 130, 35);
        frame.add(btnAdd);

        // Button Logic
        btnAdd.addActionListener(e -> {
            String name = txtName.getText();
            String dept = deptCombo.getSelectedItem().toString();
            String contact = txtContact.getText();

            if (name.isEmpty() || contact.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill all fields!");
                return;
            }

            try {
                Connection con = DBConnection.getConnection();
                String sql = "INSERT INTO students (student_name, department, contact) VALUES (?, ?, ?)";
                PreparedStatement pst = con.prepareStatement(sql);

                pst.setString(1, name);
                pst.setString(2, dept);
                pst.setString(3, contact);

                pst.executeUpdate();
                JOptionPane.showMessageDialog(frame, "Student Added Successfully!");
                con.close();

                txtName.setText("");
                txtContact.setText("");

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Database Error: " + ex.getMessage());
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new AddStudentPage();
    }
}
