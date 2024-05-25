package View;

import javax.swing.*;
import Controller.ReserveRoomController;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class PopupReserveDialog extends JDialog {

    private JTextField roomNumberField;
    private JTextField startDateField;
    private JTextField endDateField;
    private JTextField usernameField;
    public JButton okButton;
    private ReserveRoomController reserveRoomController;

    public PopupReserveDialog(JFrame frame) {
        super(frame, true);
        setSize(400, 600); // Adjusted size to accommodate new fields
        setUndecorated(true);
        setShape(new RoundRectangle2D.Double(0, 0, 400, 600, 50, 50));
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel titleLabel = new JLabel("Reserve your room", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 30));
        titleLabel.setForeground(Color.decode("#006281"));
        titleLabel.setBounds(0, 20, 400, 50);
        add(titleLabel);

        JLabel roomNumberLabel = new JLabel("Number of the room");
        roomNumberLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        roomNumberLabel.setBounds(50, 90, 300, 30);
        add(roomNumberLabel);

        roomNumberField = new JTextField();
        roomNumberField.setFont(new Font("SansSerif", Font.PLAIN, 20));
        roomNumberField.setBounds(50, 130, 300, 30);
        roomNumberField.setBorder(BorderFactory.createLineBorder(Color.decode("#00bfff"), 2));
        add(roomNumberField);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        usernameLabel.setBounds(50, 170, 300, 30);
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setFont(new Font("SansSerif", Font.PLAIN, 20));
        usernameField.setBounds(50, 210, 300, 30);
        usernameField.setBorder(BorderFactory.createLineBorder(Color.decode("#00bfff"), 2));
        add(usernameField);

        JLabel startDateLabel = new JLabel("Start Date (dd/MM/yyyy)");
        startDateLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        startDateLabel.setBounds(50, 250, 300, 30);
        add(startDateLabel);

        startDateField = new JTextField();
        startDateField.setFont(new Font("SansSerif", Font.PLAIN, 20));
        startDateField.setBounds(50, 290, 300, 30);
        startDateField.setBorder(BorderFactory.createLineBorder(Color.decode("#00bfff"), 2));
        add(startDateField);

        JLabel endDateLabel = new JLabel("End Date (dd/MM/yyyy)");
        endDateLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        endDateLabel.setBounds(50, 330, 300, 30);
        add(endDateLabel);

        endDateField = new JTextField();
        endDateField.setFont(new Font("SansSerif", Font.PLAIN, 20));
        endDateField.setBounds(50, 370, 300, 30);
        endDateField.setBorder(BorderFactory.createLineBorder(Color.decode("#00bfff"), 2));
        add(endDateField);

        okButton = new JButton("ok");
        okButton.setFont(new Font("SansSerif", Font.PLAIN, 20));
        okButton.setBounds(170, 420, 60, 30);
        okButton.setForeground(Color.WHITE);
        okButton.setBackground(Color.decode("#00bfff"));
        add(okButton);
    }

    public String getRoomNumber() {
        return roomNumberField.getText();
    }

    public String getStartDate() {
        return startDateField.getText();
    }

    public String getEndDate() {
        return endDateField.getText();
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public JButton getOkButton() {
        return okButton;
    }

    public void setUsername(String username) {
        usernameField.setText(username);
        usernameField.setEditable(false);
    }

    public void setReserveRoomController(ReserveRoomController controller) {
        this.reserveRoomController = controller;
        System.out.println("Setting ReserveRoomController: " + controller);
        okButton.addActionListener(e -> {
            System.out.println("OK button clicked in setReserveRoomController");
            reserveRoomController.handleReservation();
        });
    }
}
