package Controller;

import Model.Reservation;
import Model.ReservationManagement;
import View.CheckReservationsPanel;
import View.PopupCheckReserveDialog;

import java.util.List;
import javax.swing.*;
import java.awt.CardLayout;
import java.util.function.Consumer;

public class CheckReservationsController {
    private JFrame mainFrame;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private CheckReservationsPanel checkReservePanel;
    private ReservationManagement reservationManagement;
    private PopupCheckReserveDialog checkDialog;
    private Consumer<Reservation> reserveSuccessListener;
    private String currentUser;

    public CheckReservationsController(JFrame mainFrame, ReservationManagement reservationManagement, PopupCheckReserveDialog checkDialog, CheckReservationsPanel checkReservePanel, JPanel cardPanel, CardLayout cardLayout, String currentUser) {
        this.mainFrame = mainFrame;
        this.cardPanel = cardPanel;
        this.cardLayout = cardLayout;
        this.reservationManagement = reservationManagement;
        this.checkDialog = checkDialog;
        this.checkDialog.setController(this);
        this.checkReservePanel = checkReservePanel;
        this.currentUser = currentUser;

        this.checkReservePanel.addBackListener(e -> goBack());
        this.checkReservePanel.addModifyReservationListener(e -> showModifyReservationDialog());

        this.checkDialog.addOkListener(e -> handleDialogOk());

        // Load the reservations for the current user and update the table
        List<Reservation> userReservations = reservationManagement.getReservationsByUser(currentUser);
        checkReservePanel.updateTableData(userReservations);
    }

    private void goBack() {
        cardLayout.show(cardPanel, "HomeClientPanel");
    }

    private void showModifyReservationDialog() {
        int selectedRow = checkReservePanel.getSelectedRow();
        if (selectedRow >= 0) {
            int reservationId = checkReservePanel.getReservationId(selectedRow);
            Reservation reservation = reservationManagement.getReservationById(reservationId);
            if (reservation != null) {
                checkDialog.setReservation(reservation, currentUser);
                checkDialog.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Reservation not found", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(mainFrame, "Please select a reservation to modify", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void handleDialogOk() {
        if (checkDialog.isModifySelected()) {
            Reservation modifiedReservation = checkDialog.getModifiedReservation();
            reservationManagement.updateReservation(modifiedReservation.getId(), modifiedReservation);
        } else {
            int reservationId = checkDialog.getReservationId();
            reservationManagement.removeReservation(reservationId);
        }
        checkDialog.setVisible(false);
        List<Reservation> userReservations = reservationManagement.getReservationsByUser(currentUser);
        checkReservePanel.updateTableData(userReservations);
    }
}
