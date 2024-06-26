package View;

import javax.swing.*;
import java.awt.*;

public class HomeAdminPanel extends JPanel {

    private JPanel roundedRectangle;

    public HomeAdminPanel() {
        setBackground(Color.WHITE);
        setLayout(null);

        // Add welcome label
        JLabel welcomeLabel = new JLabel("Hey username", JLabel.LEFT);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 50)); // Set font size
        welcomeLabel.setBounds(50, 60, 400, 60); // Adjust bounds
        welcomeLabel.setForeground(Color.decode("#006281")); // Change text color
        add(welcomeLabel);

        // Add manage rooms link
        JButton manageRoomsButton = new JButton("Manage rooms");
        manageRoomsButton.setBounds(50, 230, 300, 50); // Adjust bounds
        manageRoomsButton.setFont(new Font("SansSerif", Font.PLAIN, 20)); // Set font size
        manageRoomsButton.setForeground(Color.decode("#006281")); // Change text color
        manageRoomsButton.setOpaque(false); // Make background transparent
        manageRoomsButton.setContentAreaFilled(false); // Remove default button styling
        manageRoomsButton.setBorderPainted(false); // Remove border
        manageRoomsButton.setHorizontalAlignment(SwingConstants.LEFT); // Align text to the left
        add(manageRoomsButton);
        
        // Add manage reservations requests link
        JButton reservationsRequestsButton = new JButton("Manage reservations requests");
        reservationsRequestsButton.setBounds(50, 280, 300, 50); // Adjust bounds
        reservationsRequestsButton.setFont(new Font("SansSerif", Font.PLAIN, 20)); // Set font size
        reservationsRequestsButton.setForeground(Color.decode("#006281")); // Change text color
        reservationsRequestsButton.setOpaque(false); // Make background transparent
        reservationsRequestsButton.setContentAreaFilled(false); // Remove default button styling
        reservationsRequestsButton.setBorderPainted(false); // Remove border
        reservationsRequestsButton.setHorizontalAlignment(SwingConstants.LEFT); // Align text to the left
        add(reservationsRequestsButton);

        // Add log-out link
        JButton logoutLabel = new JButton("Log-out");
        logoutLabel.setBounds(40, 400, 300, 50); // Adjust bounds
        logoutLabel.setFont(new Font("SansSerif", Font.PLAIN, 20)); // Set font size
        logoutLabel.setForeground(Color.decode("#00bfff")); // Change text color
        logoutLabel.setOpaque(false); // Make background transparent
        logoutLabel.setContentAreaFilled(false); // Remove default button styling
        logoutLabel.setBorderPainted(false); // Remove border
        logoutLabel.setHorizontalAlignment(SwingConstants.LEFT); // Align text to the left
        add(logoutLabel);

        // Add circles to bottom corners
        JPanel leftCircle = new CirclePanel();
        leftCircle.setBounds(-40, 500, 100, 100);
        leftCircle.setOpaque(false); // Make background transparent
        add(leftCircle);

        JPanel rightCircle = new CirclePanel();
        rightCircle.setBounds(725, 500, 100, 100);
        rightCircle.setOpaque(false); // Make background transparent
        add(rightCircle);

        // Add large rounded rectangle on the right
        roundedRectangle = new RoundedRectanglePanel();
        roundedRectangle.setBounds(500, 250, 300, 300);
        roundedRectangle.setBackground(Color.decode("#00bfff")); // Set background color
        add(roundedRectangle);
    }

    class CirclePanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.decode("#008CB8"));
            g.fillOval(0, 0, 100, 100);
        }
    }

    class RoundedRectanglePanel extends JPanel {
        RoundedRectanglePanel() {
            setOpaque(false);
            setBorder(new RoundedCornerBorder(20)); // Increase corner radius
        }
        protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.decode("#00bfff"));
    
        int arcRadius = 50; // Set corner radius
    
        // Draw the rectangle parts
        g.fillRect(arcRadius, 0, getWidth() - arcRadius, getHeight()); // Top
        g.fillRect(0, arcRadius, getWidth(), getHeight() - arcRadius); // Left
    
        // Draw the arc for the top left corner
        g.fillArc(0, 0, 2 * arcRadius, 2 * arcRadius, 90, 90); // Top left
        }
    }

    class RoundedCornerBorder extends javax.swing.border.AbstractBorder {
        private int radius;

        RoundedCornerBorder(int radius) {
            this.radius = radius;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(Color.WHITE); // Change border color
        
            // Draw lines for the sides of the rectangle
            g.drawLine(x + radius, y, x + width - 1, y); // Top
            g.drawLine(x, y + radius, x, y + height - 1); // Left
            g.drawLine(x + radius, y + height - 1, x + width - 1, y + height - 1); // Bottom
            g.drawLine(x + width - 1, y, x + width - 1, y + height - 1); // Right
        
            // Draw arcs for the corners
            g.drawArc(x, y, 2 * radius, 2 * radius, 90, 90); // Top left
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(radius + 1, radius + 1, radius + 1, radius + 1);
        }

        public Insets getBorderInsets(Component c, Insets insets) {
            insets.left = insets.top = insets.right = insets.bottom = radius + 1;
            return insets;
        }
    }
    @Override
    public void doLayout() {
        super.doLayout();

        // Adjust the y-coordinate of roundedRectangle based on the height of the window
        int windowHeight = getHeight();
        int rectangleHeight = roundedRectangle.getHeight();
        roundedRectangle.setLocation(roundedRectangle.getX(), windowHeight - rectangleHeight);
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new HomeAdminPanel());
        frame.setVisible(true);
    }
}
