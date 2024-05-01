package Hotel_POO_java.demo.src.main.java.Hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Room {
    int id;
    int number;
    String type;
    double price;
    boolean isReserved;
    private static Database database = new Database();

    Room(int number, String type, double price,Boolean isReserved, Database database) {
        this.number = number;
        this.type = type;
        this.price = price;
        this.isReserved = isReserved;
        this.database = database;
    }

    void setType(String type) {
        this.type = type;
    }
    void setPrice(double price) {
        this.price = price;
    }
    static Room getRoomByNumber(int number) {
        String sql = "SELECT * FROM rooms WHERE number = ?";
    
        try (Connection conn = database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
    
            pstmt.setInt(1, number);
    
            ResultSet rs = pstmt.executeQuery();
    
            if (rs.next()) {
                String type = rs.getString("type");
                double price = rs.getDouble("price");
                boolean isReserved = rs.getBoolean("is_reserved");
    
                return new Room(number, type, price, isReserved,database);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    
        return null;
    }
    
    void save() {
        String sql = "INSERT INTO rooms (number, type, price, is_reserved) VALUES (?, ?, ?, ?)";
    
        try (Connection conn = database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
    
            pstmt.setInt(1, this.number);
            pstmt.setString(2, this.type);
            pstmt.setDouble(3, this.price);
            pstmt.setBoolean(4, this.isReserved);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

void update() {
    String sql = "UPDATE rooms SET type = ?, price = ? WHERE number = ?";

    try (Connection conn = database.connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, this.type);
        pstmt.setDouble(2, this.price);
        pstmt.setInt(3, this.number);
        pstmt.executeUpdate();
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
}

    void delete() {
        String sql = "DELETE FROM rooms WHERE number = ?";
    
        try (Connection conn = database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
    
            pstmt.setInt(1, this.number);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}