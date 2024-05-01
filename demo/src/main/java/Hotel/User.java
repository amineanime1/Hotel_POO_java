package Hotel_POO_java.demo.src.main.java.Hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private String username;
    private String password;
    private boolean isAdmin;
    private Database database;

    public User(String username, String password, boolean isAdmin, Database database) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.database = database;
    }
    public User(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }
    public String getUsername() {
        return this.username;
    }
    public boolean isAdmin() {
        return this.isAdmin;
    }
    
    void authenticate(String username, String password)
    {
        if (this.username.equals(username) && this.password.equals(password)) {
            System.out.println("Authentication successful");
        } else {
            System.out.println("Authentication failed");
        }
    };
}
