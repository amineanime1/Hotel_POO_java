package Controller;

import Model.Date;
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
    private ReserveRoomPanel reserveRoomPanel;
    private ReservationManagement reservationManagement;
    private PopupReserveDialog reserveDialog;
    private Consumer<Reservation> reserveSuccessListener;

    public ReserveRoomController(JFrame mainFrame, ReservationManagement reservationManagement, PopupReserveDialog reserveDialog, ReserveRoomPanel reserveRoomPanel, JPanel cardPanel, CardLayout cardLayout) {
        this.mainFrame = mainFrame;
        this.cardPanel = cardPanel;
        this.cardLayout = cardLayout;
        this.reservationManagement = reservationManagement;
        this.reserveDialog = reserveDialog;
        this.reserveDialog.setReserveRoomController(this);
        this.reserveRoomPanel = reserveRoomPanel;
    }

    public void setReserveSuccessListener(Consumer<Reservation> listener) {
        this.reserveSuccessListener = listener;
    }

    public void handleReservation() {
        System.out.println("handleReservation called");

        String roomNumber = reserveDialog.getRoomNumber();
        String startDateStr = reserveDialog.getStartDate();
        String endDateStr = reserveDialog.getEndDate();
        String username = reserveDialog.getUsername();

        if (roomNumber.isEmpty() || startDateStr.isEmpty() || endDateStr.isEmpty()) {
            JOptionPane.showMessageDialog(reserveDialog, "All fields must be filled", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int roomNumberInt = Integer.parseInt(roomNumber);

            Date startDate = Date.fromString(startDateStr);
            Date endDate = Date.fromString(endDateStr);

            if (startDate.isBefore(endDate)) {
                int id = reservationManagement.getReservations().size() + 1;
                Reservation reservation = new Reservation(id, username, roomNumberInt, startDateStr, endDateStr, "Reserved");

                reservationManagement.addReservation(reservation);
                reservationManagement.saveReservationsToDatabase();

                if (reserveSuccessListener != null) {
                    reserveSuccessListener.accept(reservation);
                }
                reserveDialog.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(reserveDialog, "End date must be after start date.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(reserveDialog, "Room number must be a valid integer", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(reserveDialog, "Invalid date: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
