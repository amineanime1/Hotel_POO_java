package View;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class PopupCheckReserveDialog extends JDialog {

    private JButton okButton; // Declare okButton as an instance variable
    private JTextField roomNumberField;
    private JTextField dateField;
    private JTextField newDateField;
    private JRadioButton modifyRadioButton;
    private JRadioButton annulerRadioButton;

    public PopupCheckReserveDialog(JFrame frame) {
        super(frame, true);
        setSize(400, 300);
        setUndecorated(true);
        setShape(new RoundRectangle2D.Double(0, 0, 400, 300, 50, 50));
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // Change title and field labels
        JLabel titleLabel = new JLabel("Modify or Cancel", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 30));
        titleLabel.setForeground(Color.decode("#006281"));
        titleLabel.setBounds(0, 20, 400, 50);
        add(titleLabel);

        // Add room number and date fields on the same line
        JLabel roomNumberLabel = new JLabel("Number");
        roomNumberLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        roomNumberLabel.setBounds(50, 80, 80, 30);
        add(roomNumberLabel);

        roomNumberField = new JTextField();
        roomNumberField.setFont(new Font("SansSerif", Font.PLAIN, 20));
        roomNumberField.setBounds(140, 80, 80, 30);
        roomNumberField.setBorder(BorderFactory.createLineBorder(Color.decode("#00bfff"), 2));
        add(roomNumberField);

        JLabel dateLabel = new JLabel("Date");
        dateLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        dateLabel.setBounds(230, 80, 50, 30);
        add(dateLabel);

        dateField = new JTextField();
        dateField.setFont(new Font("SansSerif", Font.PLAIN, 20));
        dateField.setBounds(290, 80, 80, 30);
        dateField.setBorder(BorderFactory.createLineBorder(Color.decode("#00bfff"), 2));
        add(dateField);

        // Add radio buttons for Modify and Annuler on the same line
        modifyRadioButton = new JRadioButton("Modify");
        modifyRadioButton.setFont(new Font("SansSerif", Font.PLAIN, 20));
        modifyRadioButton.setBounds(50, 130, 100, 30);
        modifyRadioButton.setBackground(Color.WHITE);
        add(modifyRadioButton);

        annulerRadioButton = new JRadioButton("Annuler");
        annulerRadioButton.setFont(new Font("SansSerif", Font.PLAIN, 20));
        annulerRadioButton.setBounds(250, 130, 100, 30);
        annulerRadioButton.setBackground(Color.WHITE);
        add(annulerRadioButton);

        // Group radio buttons
        ButtonGroup group = new ButtonGroup();
        group.add(modifyRadioButton);
        group.add(annulerRadioButton);

        // Add new date field, initially hidden
        JLabel newDateLabel = new JLabel("New Date");
        newDateLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        newDateLabel.setBounds(50, 180, 300, 30);
        newDateLabel.setVisible(false); // Initially hidden
        add(newDateLabel);

        newDateField = new JTextField();
        newDateField.setFont(new Font("SansSerif", Font.PLAIN, 20));
        newDateField.setBounds(150, 180, 220, 30);
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
        okButton.setBounds(170, 240, 60, 30);
        okButton.setForeground(Color.WHITE);
        okButton.setBackground(Color.decode("#00bfff"));
        okButton.addActionListener(e -> {
            setVisible(false);
            // overlayPanel.setVisible(false); // You need to handle this outside this class
        });

        add(okButton);
    }

    public JButton getOkButton() {
        return okButton;
    }

    public JTextField getRoomNumberField() {
        return roomNumberField;
    }

    public JTextField getDateField() {
        return dateField;
    }

    public JTextField getNewDateField() {
        return newDateField;
    }

    public JRadioButton getModifyRadioButton() {
        return modifyRadioButton;
    }

    public JRadioButton getAnnulerRadioButton() {
        return annulerRadioButton;
    }
}
