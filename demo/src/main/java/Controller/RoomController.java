package Controller;

import Model.RoomManagement;
import Model.Room;
import View.ReserveRoomPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoomController {
    private RoomManagement model;
    private ReserveRoomPanel view;

    public RoomController(RoomManagement model, ReserveRoomPanel view) {
        this.model = model;
        this.view = view;

        // Add action listeners to the view components
        this.view.addReserveRoomListener(new ReserveRoomListener());
        this.view.addBackListener(new BackListener());
    }

    class ReserveRoomListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String roomNumberStr = view.getRoomNumber();
            String dateStr = view.getDate();

            // Validate inputs
            if (roomNumberStr.isEmpty() || dateStr.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please enter all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int roomNumber = Integer.parseInt(roomNumberStr);
            Room room = model.getRooms().get(roomNumber);

            if (room == null) {
                JOptionPane.showMessageDialog(view, "Room not found.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Here you can add further validation for the date if necessary
            if (!validateDate(dateStr)) {
                JOptionPane.showMessageDialog(view, "Invalid date format. Please use dd/MM/yyyy.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Reserve the room
            room.setReserved(true);
            model.saveRoomsToDatabase();

            JOptionPane.showMessageDialog(view, "Room reserved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    class BackListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Logic to go back to the previous screen
        }
    }

    private boolean validateDate(String dateStr) {
        // Basic date validation logic here
        // You can use the same logic as in the view for consistency
        return true;
    }
}

