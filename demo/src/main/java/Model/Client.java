package Model;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class Client extends User {
    private UsersManagement usersManagement;
    private User currentUser;
    private Map<Integer, Reservation> reservations = new HashMap<>();
    private RoomManagement roomManagement;
    private ReservationManagement reservationManagement;

   
    public Client(String username, String password) {
        super(username, password, false);
        this.usersManagement = new UsersManagement();
        this.reservationManagement = new ReservationManagement();
        this.roomManagement = new RoomManagement();
        this.currentUser = null;
    }

    public Client(String username, String password, RoomManagement roomManagement, ReservationManagement reservationManagement) {
        super(username, password, false);
        this.usersManagement = new UsersManagement(roomManagement, reservationManagement);
        this.roomManagement = roomManagement;
        this.reservationManagement = reservationManagement;
        this.currentUser = null;
    }
    void authenticate(String username, String password) {
        this.currentUser = usersManagement.login(username, password);
    }
    public void checkReservation(String username) {
        // Assuming getReservations() returns a HashMap<Integer, Reservation>
        Map<Integer, Reservation> reservations = reservationManagement.getReservations();
    
        for (Reservation res : reservations.values()) {
            if (res.getUsername().equals(username)) {
                System.out.printf("Reservation details - ID: %d, Room: %d, Start date: %s, End date: %s, Status: %s%n",
                    res.getId(), res.getRoomId(), res.getStartDate(), res.getEndDate(), res.getStatus());
            }
        }
        if (reservations.isEmpty()) {
            System.out.println("No reservation found.");
        }
    }

    public void reserveRoom(User user) {
          // Check if the user is logged in
          currentUser = user;
    if (currentUser == null) {
        System.out.println("User not logged in.");
        return;
    }

    // Check if there are available rooms
    if (roomManagement.getRooms().isEmpty()) {
        System.out.println("No available rooms.");
        return;
    }

        // Print available rooms
        System.out.println("Available rooms:");
        for (Room room : roomManagement.getRooms().values()) {
            boolean isReserved = false;
        
            // Check if the room has an accepted reservation
            for (Reservation reservation : reservationManagement.getReservations().values()) {
                if (reservation.getRoomId() == room.getId() && reservation.getStatus().equals("Accepted")) {
                    isReserved = true;
                    break;
                }
            }
        
            // If the room has an accepted reservation, skip it
            if (isReserved) {
                continue;
            }
        
            System.out.println("Number: " + room.getNumber() + ", Type: " + room.getType() + ", Price: " + room.getPrice());
        }

        // Ask the user to enter the ID of the room they want to reserve
        System.out.println("Enter the Number of the room you want to reserve:");
        Scanner scanner = new Scanner(System.in);
        int roomId = scanner.nextInt();
        scanner.nextLine();

        // Check if the room exists
        Room room = roomManagement.getRooms().get(roomId);
        if (room == null) {
            System.out.println("Room not found.");
            return;
        }
        // Create a new Reservation object
        Reservation reservation = new Reservation(currentUser.getId(), currentUser.getUsername(), roomId, "2021-04-21", "2021-05-21", "Pending");
        // Add the reservation to the reservations HashMap
        reservationManagement.addReservation(reservation);
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
