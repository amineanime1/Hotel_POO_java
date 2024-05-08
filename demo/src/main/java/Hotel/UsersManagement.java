package Hotel;

import java.util.HashMap;
import java.util.Map;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager; 
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsersManagement {
    private Map<String, User> users = new HashMap<>();
    private User currentUser;
private RoomManagement roomManagement;
private ReservationManagement reservationManagement;

public UsersManagement() {
    this.roomManagement = new RoomManagement();
}
    public UsersManagement(RoomManagement roomManagement, ReservationManagement reservationManagement) {
        this.roomManagement = roomManagement;
        this.reservationManagement = reservationManagement;
    }

    public Map<String, User> getUsers() {
        return users;
    }
    public User getUser(String username) {
        return users.get(username);
    }

    
    public boolean register(String username, String password, boolean isAdmin) {
        if (users.containsKey(username)) {
            return false;
        } else {
            User user;
            if (isAdmin) {
                user = new Administrator(username, password, roomManagement, reservationManagement);
            } else {
                user = new Client(username, password, roomManagement, reservationManagement);
            }
            users.put(username, user);
            return true;
        }
    }

    public User login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Login successful.");
            currentUser = user;
            System.out.println(currentUser);
            return currentUser;
        } else {
            System.out.println("Invalid username or password.");
            return null;
        }
    }
    public void printUsers() {
        System.out.println("Existing Users");
        for (Map.Entry<String, User> entry : users.entrySet()) {
            User user = entry.getValue();
            System.out.println("USER || ID: " + user.getId() +" Username: " + user.getUsername() + ", Password: " + user.getPassword() + ", Is Admin: " + user.isAdmin());
        }
    }
    public void loadUsersFromDatabase() {
        Database db = new Database();
        try {
            // Connect to the database
            Connection connection = db.connect();
    
            // Execute a SQL query to get all users
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
    
            // Iterate over the result set and add each user to the users hashmap
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                boolean isAdmin = resultSet.getBoolean("isAdmin");
    
                User user;
                if (isAdmin) {
                    user = new Administrator(username, password, roomManagement, reservationManagement);
                } else {
                    user = new Client(username, password, roomManagement, reservationManagement);
                }
                users.put(username, user);
            }
    
            // Close the connection
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void saveUsersToDatabase() {
        Database db = new Database();
        Connection connection = null; // Define connection outside of the try block
        try {
            // Connect to the database
            connection = db.connect();
    
            // Disable auto-commit mode
            connection.setAutoCommit(false);
    
            // Delete all users from the users table
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM users");
    
            // Insert each user from the users hashmap into the users table
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (id, username, password, isAdmin) VALUES (?, ?, ?, ?)");
            for (User user : users.values()) {
                preparedStatement.setInt(1, user.getId());
                preparedStatement.setString(2, user.getUsername());
                preparedStatement.setString(3, user.getPassword());
                preparedStatement.setBoolean(4, user instanceof Administrator);
                preparedStatement.executeUpdate();
            }
    
            // Commit the transaction
            connection.commit();
    
            // Re-enable auto-commit mode
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                // Roll back the transaction in case of an error
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                // Close the connection
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}