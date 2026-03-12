package library;

import java.sql.Connection;
import java.sql.DriverManager;
public class DBConnection {
	private static final String URL = "jdbc:mysql://localhost:3306/library_management";
    private static final String USER = "root";
    private static final String PASSWORD = "admin"; // your MySQL password

    public static Connection getConnection() {
        Connection con = null;
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to MySQL successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void main(String[] args) {
        getConnection();
    }
}

