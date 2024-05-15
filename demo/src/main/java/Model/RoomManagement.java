package Model;

import java.util.HashMap;
import java.util.Map;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class RoomManagement {
    private Map<Integer, Room> rooms;

    public RoomManagement() {
        rooms = new HashMap<>();
    }
    public void addRoom(Room room) {
        // Get the highest current ID
        int highestId = 0;
        for (Room existingRoom : rooms.values()) {
            if (existingRoom.getId() > highestId) {
                highestId = existingRoom.getId();
            }
        }
    
        // Set the ID of the new room to be one higher than the highest current ID
        room.setId(highestId + 1);
    
        // Add the room to the map
        rooms.put(room.getNumber(), room);
    }
    public Map<Integer, Room> getRooms() {
        return rooms;
    }
    public void printRooms() {
        System.out.println("Existing Rooms");
        for (Map.Entry<Integer, Room> entry : rooms.entrySet()) {
            Room room = entry.getValue();
            System.out.println("ROOM || ID: " + room.getId() +" Number: " + room.getNumber() + ", Room Type: " + room.getType() + ", Room Price: " + room.getPrice());
        }
    // Other methods to add, remove, or modify rooms...
}
public void loadRoomsFromDatabase() {
    Database db = new Database();
    try {
        // Connect to the database
        Connection connection = db.connect();

        // Execute a SQL query to get all rooms
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM rooms");

        // Iterate over the result set and add each room to the rooms hashmap
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int number = resultSet.getInt("number");
            String type = resultSet.getString("type");
            double price = resultSet.getDouble("price");
            boolean isReserved = resultSet.getBoolean("isReserved");

            Room room = new Room(number, type, price, isReserved);
            rooms.put(room.getNumber(), room);
        }

        // Close the connection
        connection.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
public void saveRoomsToDatabase() {
    Database db = new Database();
    try {
        // Connect to the database
        Connection connection = db.connect();

        // Delete all rooms from the rooms table
        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM rooms");

        // Insert each room from the rooms hashmap into the rooms table
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO rooms (id, number, type, price, isReserved) VALUES (?, ?, ?, ?, ?)");
        for (Room room : rooms.values()) {
            preparedStatement.setInt(1, room.getId());
            preparedStatement.setInt(2, room.getNumber());
            preparedStatement.setString(3, room.getType());
            preparedStatement.setDouble(4, room.getPrice());
            preparedStatement.setBoolean(5, room.isReserved());
            preparedStatement.executeUpdate();
        }

        // Close the connection
        connection.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}
