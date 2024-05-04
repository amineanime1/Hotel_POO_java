package Hotel;

import java.util.HashMap;
import java.util.Map;

public class User {
    private static HashMap<String, User> users = new HashMap<>();
    private static int idCounter = 0;
    private int id;
    private String username;
    private String password;
    private boolean isAdmin;


    public User(String username, String password, boolean isAdmin) {
        this.id = idCounter++;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        users.put(username, this);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    public int getId() {
        return id;
    }
    public boolean isAdmin() {
        return isAdmin;
    }

    public static HashMap<String, User> getUsers() {
        return users;
    }
    public static void printUsers() {
        for (Map.Entry<String, User> entry : users.entrySet()) {
            User user = entry.getValue();
            System.out.println("Username: " + user.getUsername() + ", Password: " + user.getPassword() + ", Is Admin: " + user.isAdmin());
        }
    }
}