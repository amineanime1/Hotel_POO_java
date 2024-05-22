package Main;

import Model.Application;
import View.LoginPanel;
import View.HomeClientPanel;
import Controller.LoginController;
import Controller.DataController;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.mysql.cj.log.Log;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.awt.Image;
import java.awt.*;

public class MainGUITest {
    public static void main(String[] args) {
    CardLayout cardLayout = new CardLayout();
    JPanel cardPanel = new JPanel(cardLayout);

    Application model = new Application();
    LoginPanel loginView = new LoginPanel();
    HomeClientPanel homeView = new HomeClientPanel();
    LoginController loginController = new LoginController(model, loginView);

    // Create and use the DataController to load data
    JFrame frame = new JFrame(); // Declare and initialize the frame variable

    DataController dataController = new DataController(model);
    dataController.loadData();

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

    frame.add(cardPanel);
    frame.setVisible(true);

    // Add a listener to save data when the application closes
    frame.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
        dataController.saveData();
        super.windowClosing(e);
        }
    });

    // Assuming the LoginController has a method to set a successful login listener
    loginController.setLoginSuccessListener(() -> {
        cardLayout.show(cardPanel, "HomeClientPanel");
    });
    }
}