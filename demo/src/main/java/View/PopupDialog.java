package View;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class PopupDialog extends JDialog {

    private JButton okButton; // Declare okButton as an instance variable

    public PopupDialog(JFrame frame) {
    super(frame, true);
    setSize(400, 300);
    setUndecorated(true);
    setShape(new RoundRectangle2D.Double(0, 0, 400, 300, 50, 50));
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

    JTextField roomNumberField = new JTextField();
    roomNumberField.setFont(new Font("SansSerif", Font.PLAIN, 20));
    roomNumberField.setBounds(50, 130, 300, 30);
    roomNumberField.setBorder(BorderFactory.createLineBorder(Color.decode("#00bfff"), 2));
    add(roomNumberField);

    JLabel dateLabel = new JLabel("Date");
    dateLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
    dateLabel.setBounds(50, 170, 300, 30);
    add(dateLabel);

    JTextField dateField = new JTextField();
    dateField.setFont(new Font("SansSerif", Font.PLAIN, 20));
    dateField.setBounds(50, 210, 300, 30);
    dateField.setBorder(BorderFactory.createLineBorder(Color.decode("#00bfff"), 2));
    add(dateField);

    okButton = new JButton("ok");
    okButton.setFont(new Font("SansSerif", Font.PLAIN, 20));
    okButton.setBounds(170, 260, 60, 30);
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
}