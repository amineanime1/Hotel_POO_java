package View;

import javax.swing.*;
import Controller.CheckReservationsController;
import Model.Reservation;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

public class PopupCheckReserveDialog extends JDialog {
    private JButton okButton;
    private JTextField roomNumberField;
    private JTextField dateField;
    private JTextField newDateField;
    private JTextField usernameField;
    private JRadioButton modifyRadioButton;
    private JRadioButton annulerRadioButton;
    private CheckReservationsController controller;
    private int reservationId;
    private String currentUser;

    public PopupCheckReserveDialog(JFrame frame) {
        super(frame, true);
        setSize(400, 400);
        setUndecorated(true);
        setShape(new RoundRectangle2D.Double(0, 0, 400, 400, 50, 50));
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // Change title and field labels
        JLabel titleLabel = new JLabel("Modify or Cancel", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 30));
        titleLabel.setForeground(Color.decode("#006281"));
        titleLabel.setBounds(0, 20, 400, 50);
        add(titleLabel);

        // Add username field
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        usernameLabel.setBounds(50, 80, 300, 30);
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setFont(new Font("SansSerif", Font.PLAIN, 20));
        usernameField.setBounds(50, 110, 300, 30);
        usernameField.setBorder(BorderFactory.createLineBorder(Color.decode("#00bfff"), 2));
        usernameField.setEditable(false); // Make this field non-editable
        add(usernameField);

        // Add room number and date fields on the same line
        JLabel roomNumberLabel = new JLabel("Number");
        roomNumberLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        roomNumberLabel.setBounds(50, 150, 80, 30);
        add(roomNumberLabel);

        roomNumberField = new JTextField();
        roomNumberField.setFont(new Font("SansSerif", Font.PLAIN, 20));
        roomNumberField.setBounds(140, 150, 80, 30);
        roomNumberField.setBorder(BorderFactory.createLineBorder(Color.decode("#00bfff"), 2));
        roomNumberField.setEditable(false); // Make this field non-editable
        add(roomNumberField);

        JLabel dateLabel = new JLabel("Date");
        dateLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        dateLabel.setBounds(230, 150, 50, 30);
        add(dateLabel);

        dateField = new JTextField();
        dateField.setFont(new Font("SansSerif", Font.PLAIN, 20));
        dateField.setBounds(290, 150, 80, 30);
        dateField.setBorder(BorderFactory.createLineBorder(Color.decode("#00bfff"), 2));
        dateField.setEditable(false); // Make this field non-editable
        add(dateField);

        // Add radio buttons for Modify and Annuler on the same line
        modifyRadioButton = new JRadioButton("Modify");
        modifyRadioButton.setFont(new Font("SansSerif", Font.PLAIN, 20));
        modifyRadioButton.setBounds(50, 190, 100, 30);
        modifyRadioButton.setBackground(Color.WHITE);
        add(modifyRadioButton);

        annulerRadioButton = new JRadioButton("Annuler");
        annulerRadioButton.setFont(new Font("SansSerif", Font.PLAIN, 20));
        annulerRadioButton.setBounds(250, 190, 100, 30);
        annulerRadioButton.setBackground(Color.WHITE);
        add(annulerRadioButton);

        // Group radio buttons
        ButtonGroup group = new ButtonGroup();
        group.add(modifyRadioButton);
        group.add(annulerRadioButton);

        // Add new date field, initially hidden
        JLabel newDateLabel = new JLabel("New Date");
        newDateLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        newDateLabel.setBounds(50, 230, 300, 30);
        newDateLabel.setVisible(false); // Initially hidden
        add(newDateLabel);

        newDateField = new JTextField();
        newDateField.setFont(new Font("SansSerif", Font.PLAIN, 20));
        newDateField.setBounds(150, 230, 220, 30);
        newDateField.setBorder(BorderFactory.createLineBorder(Color.decode("#00bfff"), 2));
        newDateField.setVisible(false); // Initially hidden
        add(newDateField);

        // Add action listener to radio buttons to show/hide the new date field
        modifyRadioButton.addActionListener(e -> {
            newDateLabel.setVisible(true);
            newDateField.setVisible(true);
        });

        annulerRadioButton.addActionListener(e -> {
            newDateLabel.setVisible(false);
            newDateField.setVisible(false);
        });

        // Add OK button
        okButton = new JButton("ok");
        okButton.setFont(new Font("SansSerif", Font.PLAIN, 20));
        okButton.setBounds(170, 300, 60, 30);
        okButton.setForeground(Color.WHITE);
        okButton.setBackground(Color.decode("#00bfff"));
        add(okButton);
    }

    public void setController(CheckReservationsController controller) {
        this.controller = controller;
    }

    public void setReservation(Reservation reservation, String currentUser) {
        this.reservationId = reservation.getId();
        this.currentUser = currentUser;
        roomNumberField.setText(String.valueOf(reservation.getRoomId()));
        dateField.setText(reservation.getStartDate());
        usernameField.setText(currentUser);
        modifyRadioButton.setSelected(true);
        newDateField.setText("");
        newDateField.setVisible(true);
    }

    public Reservation getModifiedReservation() {
        return new Reservation(
            reservationId,
            currentUser, // Assuming username is correct
            Integer.parseInt(roomNumberField.getText()), // Assuming room ID doesn't change
            dateField.getText(),
            newDateField.getText(),
            "Modified"
        );
    }

    public int getReservationId() {
        return reservationId;
    }

    public boolean isModifySelected() {
        return modifyRadioButton.isSelected();
    }

    public void addOkListener(ActionListener listener) {
        okButton.addActionListener(listener);
    }
}
