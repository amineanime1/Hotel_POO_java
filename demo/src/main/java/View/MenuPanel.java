// MenuPanel.java
package View;

import javax.swing.*;

public class MenuPanel extends JPanel {
    public MenuPanel() {
        // Add components for the menu panel here
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            // Switch to login panel
            // This will need to be handled differently, as MenuPanel does not have direct access to MainFrame
        });
        add(logoutButton);

        // Add the rest of the components and action listeners...
    }
}