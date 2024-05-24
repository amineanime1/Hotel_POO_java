package Controller;

import View.HomeClientPanel;
import View.ReserveRoomPanel;
import View.CheckReservationsPanel;
import View.LoginPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientHomeController {
    private JFrame mainFrame;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private HomeClientPanel homeClientPanel;
    private ReserveRoomPanel reserveRoomPanel;
    private CheckReservationsPanel checkReservationsPanel;
    private LoginPanel loginPanel;
    private String currentUsername;

    public ClientHomeController(JFrame mainFrame, HomeClientPanel homeClientPanel, ReserveRoomPanel reserveRoomPanel, CheckReservationsPanel checkReservationsPanel, LoginPanel loginPanel) {
        this.mainFrame = mainFrame;
        this.cardPanel = (JPanel) mainFrame.getContentPane();
        this.cardLayout = (CardLayout) cardPanel.getLayout();
        this.homeClientPanel = homeClientPanel;
        this.reserveRoomPanel = reserveRoomPanel;
        this.checkReservationsPanel = checkReservationsPanel;
        this.loginPanel = loginPanel;

        // Ajouter les listeners aux boutons de HomeClientPanel
        this.homeClientPanel.addReserveButtonListener(new ReserveButtonListener());
        this.homeClientPanel.addCheckButtonListener(new CheckButtonListener());
        this.homeClientPanel.addLogoutButtonListener(new LogoutButtonListener());
    }

    public void setCurrentUsername(String username) {
        this.currentUsername = username;
        reserveRoomPanel.setCurrentUsername(username);
    }

    class ReserveButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            cardLayout.show(cardPanel, "ReserveRoomPanel");
        }
    }

    class CheckButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            cardLayout.show(cardPanel, "CheckReservationsPanel");
        }
    }

    class LogoutButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            cardLayout.show(cardPanel, "LoginPanel");
        }
    }
}
