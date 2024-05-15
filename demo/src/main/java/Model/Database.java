package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/mydatabase";
    private static final String USER = "root";
    private static final String PASSWORD = "222231575415";
    public Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void testConnection() {
        try {
            // Try to establish a connection with the database
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // If the connection is successful, print a success message
            System.out.println("Successfully connected to the database.");

            // Close the connection
            connection.close();
        } catch (SQLException e) {
            // If the connection fails, print an error message
            System.out.println("Failed to connect to the database.");
            e.printStackTrace();
        }
    }
}