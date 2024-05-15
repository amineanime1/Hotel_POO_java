// MainFrame.java
package View;

import javax.swing.*;
import java.awt.*;
import Model.Application;

public class MainFrame extends JFrame {
    private JPanel mainPanel;
    private LoginPanel loginPanel;
    private MenuPanel menuPanel;
    private Application app;

    public MainFrame() {
        setTitle("Hotel Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        app = new Application();

        mainPanel = new JPanel(new CardLayout());
        loginPanel = new LoginPanel(app);
        menuPanel = new MenuPanel();

        mainPanel.add(loginPanel, "loginPanel");
        mainPanel.add(menuPanel, "menuPanel");

        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}