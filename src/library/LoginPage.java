package library;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LoginPage {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Library Login");
        frame.setSize(350, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel lblUser = new JLabel("Username:");
        lblUser.setBounds(40, 40, 100, 30);
        frame.add(lblUser);

        JTextField txtUser = new JTextField();
        txtUser.setBounds(150, 40, 120, 30);
        frame.add(txtUser);

        JLabel lblPass = new JLabel("Password:");
        lblPass.setBounds(40, 90, 100, 30);
        frame.add(lblPass);

        JPasswordField txtPass = new JPasswordField();
        txtPass.setBounds(150, 90, 120, 30);
        frame.add(txtPass);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(120, 140, 100, 30);
        frame.add(btnLogin);

        // ---------- LOGIN BUTTON LOGIC ----------
        btnLogin.addActionListener(e -> {

            String username = txtUser.getText();
            String password = new String(txtPass.getPassword());

            // empty check
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter username & password!");
                return;
            }

            try {
                Connection con = DBConnection.getConnection();

                String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
                PreparedStatement pst = con.prepareStatement(sql);

                pst.setString(1, username);
                pst.setString(2, password);

                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(frame, "Login Successful!");

                    // open dashboard
                    new Dashboard();

                    // close login window
                    frame.dispose();

                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid Credentials!");
                }


            } catch (Exception ex) {
                ex.printStackTrace(); // keep this for console debugging
                JOptionPane.showMessageDialog(frame, "Database Error!");
            }



        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
