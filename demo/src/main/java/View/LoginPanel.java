// LoginPanel.java
package View;

import javax.swing.*;
import java.awt.*;
import Model.Application;
import Model.User;

public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private Application app;
    private LoginListener loginListener;

    public LoginPanel(Application app) {
        JPanel panel = new JPanel(new GridBagLayout());
        this.app = app;
        this.loginListener = loginListener;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel usernameLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(usernameLabel, gbc);

        usernameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(passwordField, gbc);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = String.valueOf(passwordField.getPassword());

            User user = app.login(username, password);
            if (user != null) {
                loginListener.onLogin(user);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(loginButton, gbc);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = String.valueOf(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(MainFrame.this, "Username and password cannot be empty.", "Registration Failed", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String[] options = {"Regular User", "Administrator"};
            int choice = JOptionPane.showOptionDialog(MainFrame.this, "Select your role:", "Role Selection", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            boolean isAdmin = (choice == 1);

            boolean isRegistered = app.register(username, password, isAdmin);
            if (isRegistered) {
                JOptionPane.showMessageDialog(MainFrame.this, "Registration successful!");
            } else {
                JOptionPane.showMessageDialog(MainFrame.this, "Username already exists. Please enter another username.", "Registration Failed", JOptionPane.ERROR_MESSAGE);
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(registerButton, gbc);

        return panel;
    }
}