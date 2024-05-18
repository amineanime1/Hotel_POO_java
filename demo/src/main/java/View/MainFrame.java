package View;

import Controller.RoomController;
import Model.RoomManagement;
import View.LoginPanel;
import View.ReserveRoomPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private LoginPanel loginPanel;
    private ReserveRoomPanel reserveRoomPanel;
    private RoomManagement roomManagement;
    private RoomController roomController;

    public MainFrame() {
        setTitle("Hotel Ayaram");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new CardLayout());

        // Initialize the room management model
        roomManagement = new RoomManagement();
        roomManagement.loadRoomsFromDatabase(); // Load existing rooms from the database

        // Create the login panel
        loginPanel = new LoginPanel();
        
        // Create the reserve room panel
        reserveRoomPanel = new ReserveRoomPanel(this);
        
        // Initialize the room controller
        roomController = new RoomController(roomManagement, reserveRoomPanel);

        // Add panels to the frame
        add(loginPanel, "login");
        add(reserveRoomPanel, "reserveRoom");

        // Show the login panel first
        switchToPanel("reserveRoom");

        // For demonstration purposes, switch to the reserve room panel after login (you should implement proper login logic)
        // loginPanel.addLoginButtonListener(e -> switchToPanel("reserveRoom"));
    }

    public void switchToPanel(String panelName) {
        CardLayout layout = (CardLayout) getContentPane().getLayout();
        layout.show(getContentPane(), panelName);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
