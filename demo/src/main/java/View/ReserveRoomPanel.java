package View;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import Controller.ReserveRoomController;
import Model.ReservationManagement;
import Model.RoomManagement;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class ReserveRoomPanel extends JPanel {

    private JDialog popupDialog;
    private JPanel overlayPanel;
    private JButton reserveRoomButton;
    private JButton backButton;
    private PopupReserveDialog popupReserveDialog;
    private String currentUsername;
    private ReserveRoomController reserveRoomController;
    private JTable roomTable;
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public ReserveRoomPanel(JFrame frame, ReservationManagement reservationManagement, RoomManagement roomManagement, CardLayout cardLayout, JPanel cardPanel) {
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;

        setBackground(Color.WHITE);
        setLayout(null);

        // Add reserve a room label
        JLabel reserveLabel = new JLabel("Reserve a room", JLabel.LEFT);
        reserveLabel.setFont(new Font("Serif", Font.BOLD, 50)); // Set font size
        reserveLabel.setBounds(50, 30, 400, 60); // Adjust bounds
        reserveLabel.setForeground(Color.decode("#006281")); // Change text color
        add(reserveLabel);

        // Add available rooms label
        JLabel availableRoomsLabel = new JLabel("Available rooms");
        availableRoomsLabel.setBounds(50, 100, 300, 50); // Adjust bounds
        availableRoomsLabel.setFont(new Font("SansSerif", Font.PLAIN, 20)); // Set font size
        availableRoomsLabel.setForeground(Color.decode("#006281")); // Change text color
        add(availableRoomsLabel);

        // Create table for rooms
        String[] columnNames = { "Number", "Type", "Price" };
        Object[][] data = roomManagement.getRoomData();

        // Print table data for debugging
        System.out.println("Table data:");
        for (Object[] row : data) {
            System.out.println(Arrays.toString(row));
        }

        roomTable = new JTable(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                c.setBackground(row % 2 == 0 ? Color.decode("#00bfff") : Color.decode("#00ccff")); // Alternating row colors
                ((JComponent) c).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
                return c;
            }
        };

        roomTable.setFont(new Font("SansSerif", Font.PLAIN, 18));
        roomTable.setRowHeight(50);

        // Set header style
        JTableHeader header = roomTable.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 20));
        header.setBackground(Color.decode("#006281"));
        header.setForeground(Color.WHITE);

        // Remove grid lines
        roomTable.setShowGrid(false);

        // Add scroll pane
        JScrollPane scrollPane = new JScrollPane(roomTable);
        scrollPane.setBounds(50, 160, 700, 200); // Adjust bounds for fixed size
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);

        // Add rounded corners to scroll pane
        JPanel roundedPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.decode("#006281"));
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 50, 50);
            }
        };
        roundedPanel.setLayout(new BorderLayout());
        roundedPanel.add(scrollPane);
        roundedPanel.setBounds(50, 160, 700, 200); // Adjust bounds for fixed size
        add(roundedPanel);

        // Add back button
        backButton = new JButton("<- back");
        backButton.setBounds(50, 400, 100, 50); // Adjust bounds
        backButton.setFont(new Font("SansSerif", Font.PLAIN, 20)); // Set font size
        backButton.setForeground(Color.decode("#00bfff")); // Change text color
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        add(backButton);

        // Add reserve a room button
        reserveRoomButton = new JButton("reserve a room");
        reserveRoomButton.setBounds(600, 400, 200, 50); // Adjust bounds
        reserveRoomButton.setFont(new Font("SansSerif", Font.PLAIN, 20)); // Set font size
        reserveRoomButton.setForeground(Color.decode("#00bfff")); // Change text color
        reserveRoomButton.setContentAreaFilled(false);
        reserveRoomButton.setBorderPainted(false);
        add(reserveRoomButton);

        // Add circles to bottom corners
        JPanel leftCircle = new CirclePanel();
        leftCircle.setBounds(-40, 500, 100, 100);
        leftCircle.setBackground(Color.WHITE); // Change background color to white
        add(leftCircle);

        JPanel rightCircle = new CirclePanel();
        rightCircle.setBounds(725, 500, 100, 100);
        rightCircle.setBackground(Color.WHITE); // Change background color to white
        add(rightCircle);

        // Initialize the popup dialog
        popupReserveDialog = new PopupReserveDialog(frame);
        popupReserveDialog.setVisible(false);

        // Initialize the controller and set it in the dialog
        reserveRoomController = new ReserveRoomController(frame, reservationManagement, popupReserveDialog, this, cardPanel, cardLayout);
        // Create overlay panel
        overlayPanel = new JPanel();
        overlayPanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        overlayPanel.setBackground(new Color(0, 0, 0, 100));
        overlayPanel.setVisible(false);
        overlayPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                popupReserveDialog.setVisible(false);
                overlayPanel.setVisible(false);
            }
        });

        frame.add(overlayPanel);

        // Add mouse listener to close popup when clicking outside
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (popupReserveDialog.isVisible() && !popupReserveDialog.getBounds().contains(e.getPoint())) {
                    popupReserveDialog.setVisible(false);
                    overlayPanel.setVisible(false);
                }
            }
        });

        // Gestion des événements
        reserveRoomButton.addActionListener(e -> {
            overlayPanel.setVisible(true);
            popupReserveDialog.setLocationRelativeTo(frame);
            popupReserveDialog.setUsername(currentUsername);
            popupReserveDialog.setVisible(true);
        });

        // Add action listener to backButton to navigate back to HomeClientPanel
        backButton.addActionListener(e -> {
            cardLayout.show(cardPanel, "HomeClientPanel");
        });
    }

    class CirclePanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.decode("#006281"));
            g.fillOval(0, 0, 100, 100);
        }
    }

    public void addReserveRoomListener(ActionListener listener) {
        reserveRoomButton.addActionListener(listener);
    }

    public void addBackListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }

    public void setCurrentUsername(String username) {
        this.currentUsername = username;
    }
}
