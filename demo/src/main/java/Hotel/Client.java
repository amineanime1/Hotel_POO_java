package Hotel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class Client extends User {
    private UsersManagement usersManagement;
    private User currentUser;
    private Map<Integer, Reservation> reservations = new HashMap<>();

    public Client(String username, String password) {
        super(username, password, false);
        this.usersManagement = new UsersManagement();
        this.currentUser = null;
    }

    void authenticate(String username, String password) {
        // Implement authentication logic
    }

    public void checkReservation(String username) {
        for (Reservation reservation : reservations.values()) {
            if (reservation.getUsername().equals(username)) {
                System.out.println("Reservation details:");
                System.out.println("ID: " + reservation.getId());
                System.out.println("Room: " + reservation.getRoomId());
                System.out.println("Start date: " + reservation.getStartDate());
                System.out.println("End date: " + reservation.getEndDate());
                System.out.println("Status: " + reservation.getStatus());
                return;
            }
        }
        System.out.println("No reservation found.");
    }

    public void reserveRoom(String username) {
        // Check if the user exists
        User user = User.getUsers().get(username);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }
    
        // Check if there are available rooms
        if (Room.getRooms().isEmpty()) {
            System.out.println("No available rooms.");
            return;
        }
    
        // Print available rooms
        System.out.println("Available rooms:");
        for (Room room : Room.getRooms().values()) {
            System.out.println("ID: " + room.getId() + ", Number: " + room.getNumber() + ", Type: " + room.getType() + ", Price: " + room.getPrice());
        }
    
        // Ask the user to enter the ID of the room they want to reserve
        System.out.println("Enter the Number of the room you want to reserve:");
        Scanner scanner = new Scanner(System.in);
        int roomId = scanner.nextInt();
        scanner.nextLine();
    
        // Check if the room exists
        Room room = Room.getRooms().get(roomId);
        if (room == null) {
            System.out.println("Room not found.");
            return;
        }
    
        // Create a new Reservation object
        Reservation reservation = new Reservation(user.getId(), username, roomId, "2021-04-21", "2021-05-21", "Pending");
    
        // Add the reservation to the reservations HashMap
        reservations.put(reservation.getId(), reservation);
    
        System.out.println("Reservation created.");
    }
    @Override
public String toString() {
    return "Client{" +
        "username='" + getUsername() + '\'' +
        ", password='" + getPassword() + '\'' +
        ", isAdmin=" + isAdmin() +
    '}';
}
}
