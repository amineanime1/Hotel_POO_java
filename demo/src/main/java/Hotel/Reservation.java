package Hotel_POO_java.demo.src.main.java.Hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Reservation {
    int id;
    Room room;
    User client;
    String username;
    int roomId;
    int userId;
    String startDate;
    String endDate;
    String status;
    private static Database database = new Database();

    public Reservation(int userId, String username, int roomId, String startDate, String endDate, String status, Database database) {
        this.userId = userId;
        this.username = username;
        this.roomId = roomId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.database = database;
    }

    void accept() {
        this.status = "Accepted";
    
        String sql = "UPDATE reservations SET status = 'Accepted' WHERE id = ?";
    
        try (Connection conn = database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
    
            pstmt.setInt(1, this.id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    void decline() {
        this.status = "Declined";
    
        String sql = "UPDATE reservations SET status = 'Declined' WHERE id = ?";
    
        try (Connection conn = database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
    
            pstmt.setInt(1, this.id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    static Reservation getReservationByUsername(String username) {
        String sql = "SELECT * FROM reservations WHERE client_username = ?";
    
        try (Connection conn = database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
    
            pstmt.setString(1, username);
    
            ResultSet rs = pstmt.executeQuery();
    
            if (rs.next()) {
                int userId = rs.getInt("user_id");
                int roomId = rs.getInt("room_id");
                String startDate = rs.getString("start_date");
                String endDate = rs.getString("end_date");
                String status = rs.getString("status");
    
                return new Reservation(userId, username, roomId, startDate, endDate, status, database);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    
        return null;
    }
    
    void save() {
        String sql = "INSERT INTO reservations (client_username, room_id, start_date, end_date, status) VALUES (?, ?, ?, ?, ?)";
    
        try (Connection conn = database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
    
            pstmt.setString(1, this.username);
            pstmt.setInt(2, this.roomId);
            pstmt.setString(3, this.startDate);
            pstmt.setString(4, this.endDate);
            pstmt.setString(5, this.status);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}