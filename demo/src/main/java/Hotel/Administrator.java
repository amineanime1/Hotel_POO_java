package Hotel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Administrator extends User {
    private UsersManagement usersManagement;
    private User currentUser;
    private Map<Integer, Room> rooms = new HashMap<>();
    private Map<Integer, Reservation> reservations = new HashMap<>();
    private RoomManagement roomManagement;
    private ReservationManagement reservationManagement;

    public Administrator(String username, String password) {
        super(username, password, true);
        this.usersManagement = new UsersManagement();
        this.roomManagement = new RoomManagement();
        this.reservationManagement = new ReservationManagement();
        this.currentUser = null;
    }

    public Administrator(String username, String password, RoomManagement roomManagement, ReservationManagement reservationManagement) {
        super(username, password, true);
        this.usersManagement = new UsersManagement();
        this.reservationManagement = reservationManagement;
        this.roomManagement = roomManagement;
        this.currentUser = null;
    }

    void authenticate(String username, String password) {
        // Implement authentication logic
    }
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }
    public void createRoom(int number, String type, double price) {
        if (currentUser != null) {
            System.out.println("Current user: " + currentUser.getUsername());
        } else {
            System.out.println("No user is currently logged in.");
            return;
        }

        // Create a new Room object
        Room room = new Room(number, type, price, false);

        // Add the room to the rooms HashMap
        roomManagement.addRoom(room);

        System.out.println("Room created.");
    }

    public void modifyRoom(int number, String newType, double newPrice) {
        if (currentUser != null) {
            System.out.println("Current user: " + currentUser.getUsername());
        } else {
            System.out.println("No user is currently logged in.");
            return;
        }

        // Get the room with the entered number
        Room room = rooms.get(number);
        if (room != null) {
            // Modify the room
            room.setType(newType);
            room.setPrice(newPrice);

            System.out.println("Room modified.");
        } else {
            System.out.println("No room found with the entered number.");
        }
    }

    public void deleteRoom(int number) {
        if (currentUser != null) {
            System.out.println("Current user: " + currentUser.getUsername());
        } else {
            System.out.println("No user is currently logged in.");
            return;
        }

        // Remove the room from the rooms HashMap
        if (rooms.remove(number) != null) {
            System.out.println("Room deleted.");
        } else {
            System.out.println("No room found with the entered number.");
        }
    }
    void manageReservations() {
        // Create a list to store pending reservations
        List<Reservation> pendingReservations = new ArrayList<>();

        // Iterate over the reservations HashMap in the ReservationManagement class
        for (Reservation reservation : reservationManagement.getReservations().values()) {
            if (reservation.getStatus().equals("Pending")) {
                pendingReservations.add(reservation);
                System.out.printf("Reservation details - ID: %d, Username: %s, Room ID: %d, Status: %s%n",
                    reservation.getId(), reservation.getUsername(), reservation.getRoomId(), reservation.getStatus());
            }
        }
        System.out.println("\nManage reservations :\n");
        // Iterate over the list of pending reservations and prompt the administrator to accept or decline each one
        for (Reservation reservation : pendingReservations) {
            System.out.printf("Manage reservation - ID: %d, Username: %s, Room ID: %d, Status: %s%n",
                reservation.getId(), reservation.getUsername(), reservation.getRoomId(), reservation.getStatus());

            System.out.println("Enter 1 to accept, 2 to decline:");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                // Accept the reservation
                reservation.setStatus("Accepted");
                System.out.println("Reservation accepted.");
            } else if (choice == 2) {
                // Decline the reservation
                reservation.setStatus("Declined");
                System.out.println("Reservation declined.");
            } else {
                System.out.println("Invalid choice. Please enter 1 or 2.");
            }
        }
    }
}