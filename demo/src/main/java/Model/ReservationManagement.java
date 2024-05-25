package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.SynchronousQueue;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReservationManagement {
    private Map<Integer, Reservation> reservations;
    private Map<Integer, Room> rooms;

    public ReservationManagement() {
        reservations = new HashMap<>();
    }
    public ReservationManagement(RoomManagement roomManagement) {
        reservations = new HashMap<>();
        rooms = roomManagement.getRooms();
    }
    public Map<Integer, Reservation> getReservations() {
        return reservations;
    }

    public void printReservations() {
        System.out.println("Existing Reservations");
        for (Map.Entry<Integer, Reservation> entry : reservations.entrySet()) {
            Reservation reservation = entry.getValue();
            System.out.println("Reservation || ID: " + reservation.getId() + ", Room Number: " + reservation.getRoomId() + ", User: " + reservation.getUsername() + ", Status: " + reservation.getStatus());
        }
    }

    public void addReservation(Reservation reservation) {
        reservations.put(reservation.getId(), reservation);
    }
    public List<Reservation> getAllReservations() {
        return new ArrayList<>(reservations.values());
    }
    public List<Reservation> getReservationsByUser(String username) {
        List<Reservation> userReservations = new ArrayList<>();
        for (Reservation reservation : reservations.values()) {
            if (reservation.getUsername().equals(username)) {
                userReservations.add(reservation);
            }
        }
        return userReservations;
    }
    public Reservation getReservationById(int id) {
        return reservations.get(id);
    }

    public void removeReservation(int id) {
        reservations.remove(id);
    }

    public void updateReservation(int id, Reservation newReservation) {
        reservations.put(id, newReservation);
    }

    public void loadReservationsFromDatabase() {
        Database db = new Database();
        try {
            // Connect to the database
            Connection connection = db.connect();
    
            // Execute a SQL query to get all reservations
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM reservations");
    
            // Iterate over the result set and add each reservation to the reservations hashmap
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                int roomId = resultSet.getInt("roomId");
                String startDate = resultSet.getString("startDate");
                String endDate = resultSet.getString("endDate");
                String status = resultSet.getString("status");
    
                Reservation reservation = new Reservation(id, username, roomId, startDate, endDate, status);
                reservations.put(reservation.getId(), reservation);
            }
    
            // Close the connection
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void saveReservationsToDatabase() {
    Database db = new Database();
    try {
        // Connect to the database
        Connection connection = db.connect();

        // Delete all reservations from the reservations table
        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM reservations");

        // Insert each reservation from the reservations hashmap into the reservations table
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO reservations (id, username, roomId, startDate, endDate, status) VALUES (?, ?, ?, ?, ?, ?)");
        for (Reservation reservation : reservations.values()) {
            preparedStatement.setInt(1, reservation.getId());
            preparedStatement.setString(2, reservation.getUsername());
            preparedStatement.setInt(3, reservation.getRoomId());
            preparedStatement.setString(4, reservation.getStartDate());
            preparedStatement.setString(5, reservation.getEndDate());
            preparedStatement.setString(6, reservation.getStatus());
            preparedStatement.executeUpdate();
        }

        // Close the connection
        connection.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
public Object[][] getReservationsData() {
     Object[][] data = new Object[reservations.size()][4];
    int i = 0;
    for (Reservation reservations : reservations.values()) {
        data[i][0] = reservations.getId();
        data[i][1] = rooms.get(data[i][0]).getType();
        data[i][2] = rooms.get(data[i][0]).getPrice() + " Dzd";
        data[i][3] = reservations.getStartDate() + " - " + reservations.getEndDate();
        i++;
    }

    // Print room data for debugging
    System.out.println("Reservations data for table:");
    for (Object[] row : data) {
        System.out.println("a");
        System.out.println(Arrays.toString(row));
    }

    return data;
}
}