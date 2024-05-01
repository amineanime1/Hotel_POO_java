package Hotel_POO_java.demo.src.main.java.Hotel;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Client extends User {
    
    private Database database;
    private UsersManagement usersManagement;
    private User currentUser;

    public Client(String username, String password, boolean isAdmin, Database database, UsersManagement usersManagement, User currentUser) {
        super(username, password, isAdmin);
        this.database = database;
        this.usersManagement = usersManagement;
        this.currentUser = currentUser;
        System.out.println("Current user: " + currentUser.getUsername());
    }

    void authenticate(String username, String password) {
        // Implement authentication logic
    }

    public void checkReservation(String username) {
        String sql = "SELECT * FROM reservations WHERE client_username = ?";
    
        try (Connection conn = database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
    
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
    
            if (rs.next()) {
                System.out.println("Reservation details:");
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Room: " + rs.getInt("room_id"));
                System.out.println("Start date: " + rs.getString("start_date"));
                System.out.println("End date: " + rs.getString("end_date"));
                System.out.println("Status: " + rs.getString("status"));
            } else {
                System.out.println("No reservation found.");
            }
    
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void reserveRoom(String username) {
        String sql = "SELECT * FROM rooms WHERE is_reserved = false";
        String sql2 = "SELECT idusers FROM users WHERE username = ?";
    
       
    
        try (Connection conn = database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             PreparedStatement pstmt2 = conn.prepareStatement(sql2)) {
    
            // Execute the first query
            ResultSet rs = pstmt.executeQuery();
    
            if (rs.next()) {
                System.out.println("Available rooms:");
                do {
                    System.out.println("ID: " + rs.getInt("id") + ", Number: " + rs.getInt("number") + ", Type: " + rs.getString("type") + ", Price: " + rs.getDouble("price"));
                } while (rs.next());
    
                System.out.println("Enter the ID of the room you want to reserve:");
                Scanner scanner = new Scanner(System.in);
                int roomId = scanner.nextInt();
                scanner.nextLine();
                
                // Execute the second query to get the user's idusers
                pstmt2.setString(1, username);
                ResultSet rs2 = pstmt2.executeQuery();
                rs2.next();
                int userId = rs2.getInt("idusers");
                
                // Create a new Reservation object
                Reservation reservation = new Reservation(userId, username, roomId, "2021-04-21", "2021-05-21", "Pending", database);
                
                // Save the reservation to the database
                reservation.save();
                
                System.out.println("Reservation created.");
            } else {
                System.out.println("No available rooms.");
            }
    
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
