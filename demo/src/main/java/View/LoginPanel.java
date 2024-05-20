package View;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    public LoginPanel() {
        setBackground(Color.WHITE);
        setLayout(null);

        // Add welcome label
        JLabel welcomeLabel = new JLabel("Welcome", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 80)); // Increase font size
        welcomeLabel.setBounds(0, 60, 800, 100); // Adjust bounds
        welcomeLabel.setForeground(Color.decode("#006281")); // Change text color
        add(welcomeLabel);

        JLabel usernameLabel = new JLabel("username");
        usernameLabel.setBounds(200, 200, 400, 50); // Adjust bounds
        usernameLabel.setFont(new Font("", Font.BOLD, 20)); // Increase font size
        usernameLabel.setForeground(Color.decode("#006281")); // Change text color
        add(usernameLabel);

        RoundedTextField usernameField = new RoundedTextField();
        usernameField.setBounds(200, 250, 400, 70); // Increase height
        usernameField.setBackground(new Color(0, 191, 255)); // Change background color
        usernameField.setFont(new Font("", Font.PLAIN, 15)); // Increase font size to make it bolder
        usernameField.setForeground(Color.BLACK); // Change text color
        add(usernameField);

        // Add password label and text field
        JLabel passwordLabel = new JLabel("password");
        passwordLabel.setBounds(200, 320, 400, 50); // Adjust bounds
        passwordLabel.setFont(new Font("", Font.BOLD, 20)); // Increase font size
        passwordLabel.setForeground(Color.decode("#006281")); // Change text color
        add(passwordLabel);

        RoundedPasswordField passwordField = new RoundedPasswordField();
        passwordField.setBounds(200, 370, 400, 70); // Increase height
        passwordField.setBackground(new Color(0, 191, 255)); // Change background color
        passwordField.setFont(new Font("", Font.PLAIN, 15)); // Increase font size to make it bolder
        passwordField.setForeground(Color.BLACK); // Change text color
        add(passwordField);

        // Add register and login buttons
        JButton registerButton = new JButton("Register");
        registerButton.setBounds(200, 450, 100, 30);
        registerButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        registerButton.setForeground(Color.decode("#00bfff"));
        registerButton.setBackground(Color.WHITE);
        registerButton.setBorderPainted(false);
        add(registerButton);

        JButton loginButton = new JButton("Log-in");
        loginButton.setBounds(500, 450, 100, 30);
        loginButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        loginButton.setForeground(Color.decode("#00bfff"));
        loginButton.setBackground(Color.WHITE);
        loginButton.setBorderPainted(false);
        add(loginButton);

        // Add circles to bottom corners
        JPanel leftCircle = new CirclePanel();
        leftCircle.setBounds(-40, 500, 100, 100);
        leftCircle.setBackground(Color.WHITE); // Change background color to white
        add(leftCircle);

        JPanel rightCircle = new CirclePanel();
        rightCircle.setBounds(725, 500, 100, 100);
        rightCircle.setBackground(Color.WHITE); // Change background color to white
        add(rightCircle);
    }

    class RoundedTextField extends JTextField {
        RoundedTextField() {
            setOpaque(false);
            setBorder(new RoundedCornerBorder(20)); // Increase corner radius
        }

        protected void paintComponent(Graphics g) {
            g.setColor(getBackground());
            g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // Increase corner radius
            super.paintComponent(g);
        }
    }

    class RoundedPasswordField extends JPasswordField {
        RoundedPasswordField() {
            setOpaque(false);
            setBorder(new RoundedCornerBorder(20)); // Increase corner radius to 20
        }

        protected void paintComponent(Graphics g) {
            g.setColor(getBackground());
            g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // Increase corner radius to 20
            super.paintComponent(g);
        }
    }

    class CirclePanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.decode("#008CB8"));
            g.fillOval(0, 0, 100, 100);
        }
    }

    class RoundedCornerBorder extends javax.swing.border.AbstractBorder {
        private int radius;

        RoundedCornerBorder(int radius) {
            this.radius = radius;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(Color.decode("#006281")); // Change border color
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(radius + 1, radius + 1, radius + 1, radius + 1);
        }

        public Insets getBorderInsets(Component c, Insets insets) {
            insets.left = insets.top = insets.right = insets.bottom = radius + 1;
            return insets;
        }
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new LoginPanel());
        frame.setVisible(true);
    }
}
