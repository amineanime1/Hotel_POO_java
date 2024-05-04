package Hotel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ReservationManagement {
    private Map<Integer, Reservation> reservations;

    public ReservationManagement() {
        reservations = new HashMap<>();
    }

    public Map<Integer, Reservation> getReservations() {
        return reservations;
    }
    public void printReservations() {
        for (Map.Entry<Integer, Reservation> entry : reservations.entrySet()) {
            Reservation reservation = entry.getValue();
            System.out.println("Reservation ID: " + reservation.getId() + ", Room Number: " + reservation.getRoomId() + ", User: " + reservation.getClient());
        }
    }
        public void addReservation(Reservation reservation) {
            reservations.put(reservation.getId(), reservation);
        }
    
        public Reservation getReservationById(int id) {
            return reservations.get(id);
        }

    
        // Other methods to remove or update reservations...
}
