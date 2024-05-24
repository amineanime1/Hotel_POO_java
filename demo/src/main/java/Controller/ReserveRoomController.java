package Controller;

import Model.Reservation;
import Model.ReservationManagement;
import View.HomeClientPanel;
import View.PopupReserveDialog;
import View.ReserveRoomPanel;

import javax.swing.*;

import java.awt.CardLayout;
import java.util.function.Consumer;

public class ReserveRoomController {
    private JFrame mainFrame;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private HomeClientPanel homeClientPanel;
    private ReserveRoomPanel reserveRoomPanel;
    private ReservationManagement reservationManagement;
    private PopupReserveDialog reserveDialog;
    private Consumer<Reservation> reserveSuccessListener;

    public ReserveRoomController(JFrame mainFrame, ReservationManagement reservationManagement, PopupReserveDialog reserveDialog, ReserveRoomPanel reserveRoomPanel ,HomeClientPanel homeClientPanel) {
        this.mainFrame = mainFrame;
        this.cardPanel = (JPanel) mainFrame.getContentPane();
        this.cardLayout = (CardLayout) cardPanel.getLayout();
        this.reservationManagement = reservationManagement;
        this.reserveDialog = reserveDialog;
        this.reserveDialog.setReserveRoomController(this);
        this.homeClientPanel = homeClientPanel;
        this.reserveRoomPanel = reserveRoomPanel;
    }

    public void setReserveSuccessListener(Consumer<Reservation> listener) {
        this.reserveSuccessListener = listener;
    }

    public void handleReservation() {
        System.out.println("handleReservation called"); // Debugging message

        String roomNumber = reserveDialog.getRoomNumber();
        String date = reserveDialog.getDate();
        String username = reserveDialog.getUsername();

        if (roomNumber.isEmpty() || date.isEmpty()) {
            JOptionPane.showMessageDialog(reserveDialog, "All fields must be filled", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int roomNumberInt = Integer.parseInt(roomNumber);

            // Create a new Reservation object (generate an ID or use a better mechanism for ID generation)
            int id = reservationManagement.getReservations().size() + 1;
            Reservation reservation = new Reservation(id, username, roomNumberInt, date, date, "Reserved");

            // Add the reservation to the model
            reservationManagement.addReservation(reservation);

            // Save to database (optional, if needed immediately)
            reservationManagement.saveReservationsToDatabase();

            if (reserveSuccessListener != null) {
                reserveSuccessListener.accept(reservation);
            }
            // Close the dialog
            reserveDialog.setVisible(false);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(reserveDialog, "Room number must be a valid integer", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
