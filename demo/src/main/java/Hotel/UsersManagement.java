package Hotel_POO_java.demo.src.main.java.Hotel;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersManagement {
    private Map<String, User> users = new HashMap<>();
    private Database database = new Database();
    private User currentUser;

    public boolean register(String username, String password, boolean isAdmin) {
        String checkSql = "SELECT * FROM users WHERE username = ?";
        String insertSql = "INSERT INTO users (username, password, is_admin) VALUES (?, ?, ?)";
    
        try (Connection conn = database.connect();
             PreparedStatement checkPstmt = conn.prepareStatement(checkSql);
             PreparedStatement insertPstmt = conn.prepareStatement(insertSql)) {
    
            // Check if the username already exists
            checkPstmt.setString(1, username);
            ResultSet rs = checkPstmt.executeQuery();
    
            if (rs.next()) {
                return false;
            } else {
                // Insert the new user
                insertPstmt.setString(1, username);
                insertPstmt.setString(2, password);
                insertPstmt.setBoolean(3, isAdmin);
                insertPstmt.executeUpdate();
    
                System.out.println("User registered.");
                this.login(username, password);
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public User login(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection conn = database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("Login successful.");
                boolean isAdmin = rs.getBoolean("is_admin");
                if (isAdmin) {
                    User user = new User(username, password, isAdmin);
                    currentUser = new Administrator(username, password, isAdmin, database, this, user);
                } else {
                    User user = new User(username, password, isAdmin);
                    currentUser = new Client(username, password, isAdmin, database, this, user);
                }
                return currentUser;
            } else {
                System.out.println("Invalid username or password.");
                return null;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
