package Hotel;

import java.util.Map;

public class Application {
    private User currentUser;
    private UsersManagement usersManagement;
    private RoomManagement roomManagement;
    private ReservationManagement reservationManagement;

   
    public Application() {
               this.roomManagement = new RoomManagement();
        this.reservationManagement = new ReservationManagement();
        this.usersManagement = new UsersManagement(roomManagement, reservationManagement);
    }

    public boolean register(String username, String password, boolean isAdmin) {
        boolean isRegistered = usersManagement.register(username, password, isAdmin);
        if (isRegistered) {
            System.out.println("Registration successful.");
        } else {
            System.out.println("Username already exists. Please enter another username.");
        }
        return isRegistered;
    }
    public User login(String username, String password) {
        User user = usersManagement.login(username, password);
        if (user != null) {
            this.currentUser = user;
            if (user instanceof Administrator) {
                ((Administrator) user).setCurrentUser(user);
            }
            System.out.println("Login successful. Current user: " + currentUser.getUsername());
        } else {
            System.out.println("Invalid username or password.");
        }
        return user;
    }

    public void createRoom(int number, String type, double price) {
        if (currentUser instanceof Administrator) {
            ((Administrator) currentUser).createRoom(number, type, price);
        } else {
            System.out.println("No user is currently logged in or you don't have permission to perform this action.");
        }
    }

    public UsersManagement getUsersManagement() {
        return usersManagement;
    }
    public ReservationManagement getReservationManagement() {
        return reservationManagement;
    }

    public RoomManagement getRoomsManagement() {
        return roomManagement;
    }


//     public void printRooms() {
//     Map<Integer, Room> rooms = roomManagement.getRooms();
//     System.out.println("Rooms: " + rooms);
// }

// public void printReservations() {
//     Map<Integer, Reservation> reservations = reservationManagement.getReservations();
//     System.out.println("Reservations: " + reservations);
// }
}
