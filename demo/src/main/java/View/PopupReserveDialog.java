package View;

import javax.swing.*;
import Controller.ReserveRoomController;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class PopupReserveDialog extends JDialog {

    private JTextField roomNumberField;
    private JTextField dateField;
    private JTextField usernameField;
    public JButton okButton;
    private ReserveRoomController reserveRoomController;

    public PopupReserveDialog(JFrame frame) {
        super(frame, true);
        setSize(400, 500);
        setUndecorated(true);
        setShape(new RoundRectangle2D.Double(0, 0, 400, 500, 50, 50));
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

        JLabel dateLabel = new JLabel("Date");
        dateLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        dateLabel.setBounds(50, 250, 300, 30);
        add(dateLabel);

        dateField = new JTextField();
        dateField.setFont(new Font("SansSerif", Font.PLAIN, 20));
        dateField.setBounds(50, 290, 300, 30);
        dateField.setBorder(BorderFactory.createLineBorder(Color.decode("#00bfff"), 2));
        add(dateField);

        okButton = new JButton("ok");
        okButton.setFont(new Font("SansSerif", Font.PLAIN, 20));
        okButton.setBounds(170, 340, 60, 30);
        okButton.setForeground(Color.WHITE);
        okButton.setBackground(Color.decode("#00bfff"));
        add(okButton);
    }

    public String getRoomNumber() {
        return roomNumberField.getText();
    }

    public String getDate() {
        return dateField.getText();
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public JButton getOkButton() {
        return okButton;
    }

    public void setUsername(String username) {
        usernameField.setText(username);
        usernameField.setEditable(false); // Rendre le champ non modifiable
    }

    public void setReserveRoomController(ReserveRoomController controller) {
        this.reserveRoomController = controller;
        System.out.println("Setting ReserveRoomController: " + controller); // Debug message
        okButton.addActionListener(e -> {
            System.out.println("OK button clicked in setReserveRoomController"); // Debugging message
            reserveRoomController.handleReservation();
        });
    }
}
