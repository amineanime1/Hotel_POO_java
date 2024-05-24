package Main;

import Model.Application;
import Model.ReservationManagement;
import View.LoginPanel;
import View.HomeClientPanel;
import View.ReserveRoomPanel;
import View.CheckReservationsPanel;
import View.PopupReserveDialog;
import Controller.LoginController;
import Controller.DataController;
import Controller.ClientHomeController;
import Controller.ReserveRoomController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class MainGUITest {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);

        Application model = new Application();
        ReservationManagement reservationManagement = new ReservationManagement();

        LoginPanel loginView = new LoginPanel();
        HomeClientPanel homeView = new HomeClientPanel();
        ReserveRoomPanel reserveRoomPanel = new ReserveRoomPanel(frame);
        CheckReservationsPanel checkReservationsPanel = new CheckReservationsPanel(frame);
        PopupReserveDialog reserveDialog = new PopupReserveDialog(frame);

        LoginController loginController = new LoginController(model, loginView);
        DataController dataController = new DataController(model);

        // Load data
        dataController.loadData();
        reservationManagement.loadReservationsFromDatabase();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        try {
            Image icon = ImageIO.read(MainGUITest.class.getResource("images/logov4.png"));
            frame.setIconImage(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }

        cardPanel.add(loginView, "LoginPanel");
        cardPanel.add(homeView, "HomeClientPanel");
        cardPanel.add(reserveRoomPanel, "ReserveRoomPanel");
        cardPanel.add(checkReservationsPanel, "CheckReservationsPanel");

        frame.setContentPane(cardPanel);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dataController.saveData();
                reservationManagement.saveReservationsToDatabase();
                super.windowClosing(e);
            }
        });

        ClientHomeController clientHomeController = new ClientHomeController(frame, homeView, reserveRoomPanel, checkReservationsPanel, loginView);

        loginController.setLoginSuccessListener(username -> {
            clientHomeController.setCurrentUsername(username);
            cardLayout.show(cardPanel, "HomeClientPanel");
        });

        ReserveRoomController reserveRoomController = new ReserveRoomController(reservationManagement, reserveDialog);
        reserveRoomController.setReserveSuccessListener(reservation -> {
            reservationManagement.addReservation(reservation);
            // checkReservationsPanel.updateReservations(reservationManagement.getReservations());
            cardLayout.show(cardPanel, "CheckReservationsPanel");
        });

        // Assuming you have some way to open the reserve dialog, e.g., a button in HomeClientPanel
     
    }
}
