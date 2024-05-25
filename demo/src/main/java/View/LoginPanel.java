package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private JButton loginButton;

    public LoginPanel() {
        setBackground(Color.WHITE);
        setLayout(null);

        // Add welcome label
        JLabel welcomeLabel = new JLabel("Welcome", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 80));
        welcomeLabel.setBounds(0, 60, 800, 100);
        welcomeLabel.setForeground(Color.decode("#006281"));
        add(welcomeLabel);

        JLabel usernameLabel = new JLabel("username");
        usernameLabel.setBounds(200, 200, 400, 50);
        usernameLabel.setFont(new Font("", Font.BOLD, 20));
        usernameLabel.setForeground(Color.decode("#006281"));
        add(usernameLabel);

        usernameField = new RoundedTextField();
        usernameField.setBounds(200, 250, 400, 70);
        usernameField.setBackground(new Color(0, 191, 255));
        usernameField.setFont(new Font("", Font.PLAIN, 15));
        usernameField.setForeground(Color.BLACK);
        add(usernameField);

        JLabel passwordLabel = new JLabel("password");
        passwordLabel.setBounds(200, 320, 400, 50);
        passwordLabel.setFont(new Font("", Font.BOLD, 20));
        passwordLabel.setForeground(Color.decode("#006281"));
        add(passwordLabel);

        passwordField = new RoundedPasswordField();
        passwordField.setBounds(200, 370, 400, 70);
        passwordField.setBackground(new Color(0, 191, 255));
        passwordField.setFont(new Font("", Font.PLAIN, 15));
        passwordField.setForeground(Color.BLACK);
        add(passwordField);

        registerButton = new JButton("Register");
        registerButton.setBounds(200, 450, 100, 30);
        registerButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        registerButton.setForeground(Color.decode("#00bfff"));
        registerButton.setBackground(Color.WHITE);
        registerButton.setBorderPainted(false);
        add(registerButton);

        loginButton = new JButton("Log-in");
        loginButton.setBounds(500, 450, 100, 30);
        loginButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        loginButton.setForeground(Color.decode("#00bfff"));
        loginButton.setBackground(Color.WHITE);
        loginButton.setBorderPainted(false);
        add(loginButton);

        JPanel leftCircle = new CirclePanel();
        leftCircle.setBounds(-40, 500, 100, 100);
        leftCircle.setBackground(Color.WHITE);
        add(leftCircle);

        JPanel rightCircle = new CirclePanel();
        rightCircle.setBounds(725, 500, 100, 100);
        rightCircle.setBackground(Color.WHITE);
        add(rightCircle);
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public void addRegisterListener(ActionListener listener) {
        registerButton.addActionListener(listener);
    }

    public void addLoginListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    class RoundedTextField extends JTextField {
        RoundedTextField() {
            setOpaque(false);
            setBorder(new RoundedCornerBorder(20));
        }

        protected void paintComponent(Graphics g) {
            g.setColor(getBackground());
            g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            super.paintComponent(g);
        }
    }

    class RoundedPasswordField extends JPasswordField {
        RoundedPasswordField() {
            setOpaque(false);
            setBorder(new RoundedCornerBorder(20));
        }

        protected void paintComponent(Graphics g) {
            g.setColor(getBackground());
            g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
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
            g.setColor(Color.decode("#006281"));
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
