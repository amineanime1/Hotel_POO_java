package Hotel_POO_java.demo.src.main.java.Hotel;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Administrator extends User {

    private Database database;
    private UsersManagement usersManagement;
    private User currentUser;

    public Administrator(String username, String password, boolean isAdmin, Database database, UsersManagement usersManagement, User currentUser) {
        super(username, password, isAdmin);
        this.database = database;
        this.usersManagement = usersManagement;
        this.currentUser = currentUser;
    }



        void authenticate(String username, String password) {
            // Implement authentication logic
        }
    
        public void createRoom(int number, String type, double price) {
            if (currentUser != null) {
                System.out.println("Current user: " + currentUser.getUsername());
            } else {
                System.out.println("No user is currently logged in.");
                return;
            }
        
            // Create a new Room object
            Room room = new Room(number, type, price,false, database);
        
            // Save the room to the database
            room.save();
        
            System.out.println("Room created.");
        }

    public void modifyRoom(int number, String newType, double newPrice) {
        if (currentUser != null) {
            System.out.println("Current user: " + currentUser.getUsername());
        } else {
            System.out.println("No user is currently logged in.");
            return;
        }
    
        // Get the room with the entered number
        Room room = Room.getRoomByNumber(number);
    if (room != null) {
        // Modify the room
        room.setType(newType);
        room.setPrice(newPrice);

        // Save the changes to the database
        room.update();

        System.out.println("Room modified.");
    } else {
        System.out.println("No room found with the entered number.");
    }
    }
    
    public void deleteRoom(int number) {
        if (currentUser != null) {
            System.out.println("Current user: " + currentUser.getUsername());
        } else {
            System.out.println("No user is currently logged in.");
            return;
        }
    
        // Get the room with the entered number
        Room room = Room.getRoomByNumber(number);
        if (room != null) {
            // Delete the room
            room.delete();
    
            System.out.println("Room deleted.");
        } else {
            System.out.println("No room found with the entered number.");
        }
    }
    void manageReservations() {
        String sql = "SELECT * FROM reservations WHERE status = 'Pending'";
    
        try (Connection conn = database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
    
            ResultSet rs = pstmt.executeQuery();
    
            while (rs.next()) {
                int reservationId = rs.getInt("id");
                String username = rs.getString("client_username");
                int roomId = rs.getInt("room_id");
                String status = rs.getString("status");
                int userId = rs.getInt("user_id");
    
                System.out.println("Reservation ID: " + reservationId);
                System.out.println("Username: " + username);
                System.out.println("Username ID: " + userId);
                System.out.println("Room ID: " + roomId);
                System.out.println("Status: " + status);
    
                System.out.println("Enter 1 to accept, 2 to decline:");
                Scanner scanner = new Scanner(System.in);
                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == 1) {
                    // Accept the reservation
                    String updateSql = "UPDATE reservations SET status = 'Accepted' WHERE id = ?";
                    try (PreparedStatement updatePstmt = conn.prepareStatement(updateSql)) {
                        updatePstmt.setInt(1, reservationId);
                        updatePstmt.executeUpdate();
                        System.out.println("Reservation accepted.");
                    }
                } else if (choice == 2) {
                    // Decline the reservation
                    String updateSql = "UPDATE reservations SET status = 'Declined' WHERE id = ?";
                    try (PreparedStatement updatePstmt = conn.prepareStatement(updateSql)) {
                        updatePstmt.setInt(1, reservationId);
                        updatePstmt.executeUpdate();
                        System.out.println("Reservation declined.");
                    }
                } else {
                    System.out.println("Invalid choice. Please enter 1 or 2.");
                }
 
                

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }
}