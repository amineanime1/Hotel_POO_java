package Hotel;

import java.util.HashMap;
import java.util.Map;

public class UsersManagement {
    private Map<String, User> users = new HashMap<>();
    private User currentUser;

    public boolean register(String username, String password, boolean isAdmin) {
        if (users.containsKey(username)) {
            return false;
        } else {
            User user;
            if (isAdmin) {
                user = new Administrator(username, password);
            } else {
                user = new Client(username, password);
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
}