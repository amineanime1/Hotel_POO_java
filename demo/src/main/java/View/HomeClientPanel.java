package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
public class HomeClientPanel extends JPanel {
    private JButton reserveButton;
    private JButton checkButton;
    private JButton logoutLabel;
    private JPanel roundedRectangle;

    public HomeClientPanel() {
        setBackground(Color.WHITE);
        setLayout(null);

        // Add welcome label
        JLabel welcomeLabel = new JLabel("Hey username", JLabel.LEFT);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 50)); // Set font size
        welcomeLabel.setBounds(50, 60, 400, 60); // Adjust bounds
        welcomeLabel.setForeground(Color.decode("#006281")); // Change text color
        add(welcomeLabel);

        // Add reserve a room link
        reserveButton = new JButton("Reserve a room");
        reserveButton.setBounds(50, 230, 300, 50); // Adjust bounds
        reserveButton.setFont(new Font("SansSerif", Font.PLAIN, 20)); // Set font size
        reserveButton.setForeground(Color.decode("#006281")); // Change text color
        reserveButton.setOpaque(false); // Make background transparent
        reserveButton.setContentAreaFilled(false); // Remove default button styling
        reserveButton.setBorderPainted(false); // Remove border
        reserveButton.setHorizontalAlignment(SwingConstants.LEFT); // Align text to the left
        add(reserveButton);
        
        // Add check reservations link
        checkButton = new JButton("Check reservations");
        checkButton.setBounds(50, 280, 300, 50); // Adjust bounds
        checkButton.setFont(new Font("SansSerif", Font.PLAIN, 20)); // Set font size
        checkButton.setForeground(Color.decode("#006281")); // Change text color
        checkButton.setOpaque(false); // Make background transparent
        checkButton.setContentAreaFilled(false); // Remove default button styling
        checkButton.setBorderPainted(false); // Remove border
        checkButton.setHorizontalAlignment(SwingConstants.LEFT); // Align text to the left
        add(checkButton);

        // Add log-out link
        logoutLabel = new JButton("Log-out");
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
    public void addReserveButtonListener(ActionListener listener) {
        reserveButton.addActionListener(listener);
    }

    public void addCheckButtonListener(ActionListener listener) {
        checkButton.addActionListener(listener);
    }

    public void addLogoutButtonListener(ActionListener listener) {
        logoutLabel.addActionListener(listener);
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
}
