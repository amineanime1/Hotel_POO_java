package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class CheckReservationsPanel extends JPanel {

    private JDialog popupDialog;
    private JPanel overlayPanel;
    private JButton modifyReservationButton;
    private JButton backButton;

    public CheckReservationsPanel(JFrame frame) {
        setBackground(Color.WHITE);
        setLayout(null);

        // Add check reservations label
        JLabel checkReservationsLabel = new JLabel("Check reservations", JLabel.LEFT);
        checkReservationsLabel.setFont(new Font("Serif", Font.BOLD, 50));
        checkReservationsLabel.setBounds(50, 30, 500, 60);
        checkReservationsLabel.setForeground(Color.decode("#006281"));
        add(checkReservationsLabel);

        // Add your reservations label
        JLabel yourReservationsLabel = new JLabel("your reservations");
        yourReservationsLabel.setBounds(50, 100, 300, 50);
        yourReservationsLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        yourReservationsLabel.setForeground(Color.decode("#006281"));
        add(yourReservationsLabel);

        // Create table for rooms
        String[] columnNames = { "Number", "Type", "Price", "Date" };
        Object[][] data = {
            { "102", "Dual", "2500 Dzd", "11/05/2024" },
            { "103", "Small", "1000 Dzd", "12/05/2024" },
            { "105", "Solo", "2000 Dzd", "15/05/2024" }
        };

        JTable roomTable = new JTable(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                c.setBackground(row % 2 == 0 ? Color.decode("#00bfff") : Color.decode("#00ccff"));
                ((JComponent) c).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
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
        scrollPane.setBounds(50, 160, 700, 200);
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
        roundedPanel.setBounds(50, 160, 700, 200);
        add(roundedPanel);

        // Add back button
        backButton = new JButton("<- back");
        backButton.setBounds(50, 400, 100, 50);
        backButton.setFont(new Font("SansSerif", Font.PLAIN, 20));
        backButton.setForeground(Color.decode("#00bfff"));
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        add(backButton);

        // Add modify a reservation button
        modifyReservationButton = new JButton("modify a reservation");
        modifyReservationButton.setBounds(600, 400, 200, 50);
        modifyReservationButton.setFont(new Font("SansSerif", Font.PLAIN, 20));
        modifyReservationButton.setForeground(Color.decode("#00bfff"));
        modifyReservationButton.setContentAreaFilled(false);
        modifyReservationButton.setBorderPainted(false);
        add(modifyReservationButton);

        // Add circles to bottom corners
        JPanel leftCircle = new CirclePanel();
        leftCircle.setBounds(-40, 500, 100, 100);
        leftCircle.setBackground(Color.WHITE);
        add(leftCircle);

        JPanel rightCircle = new CirclePanel();
        rightCircle.setBounds(725, 500, 100, 100);
        rightCircle.setBackground(Color.WHITE);
        add(rightCircle);

        // Add action listener to modify reservation button
        modifyReservationButton.addActionListener(e -> showPopup(frame));

        // Initialize PopupDialog and overlay panel
        popupDialog = new PopupCheckReserveDialog(frame);
        popupDialog.setVisible(false);

        overlayPanel = new JPanel();
        overlayPanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        overlayPanel.setBackground(new Color(0, 0, 0, 100));
        overlayPanel.setVisible(false);
        overlayPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                popupDialog.setVisible(false);
                overlayPanel.setVisible(false);
            }
        });

        frame.add(overlayPanel);

        // Add mouse listener to close popup when clicking outside
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (popupDialog.isVisible() && !popupDialog.getBounds().contains(e.getPoint())) {
                    popupDialog.setVisible(false);
                    overlayPanel.setVisible(false);
                }
            }
        });

        // Add action listener to OK button in popup dialog to close popup and hide overlay
        // popupDialog.getOkButton().addActionListener(e -> {
        //     popupDialog.setVisible(false);
        //     overlayPanel.setVisible(false);
        //     frame.requestFocus();
        //     frame.repaint();
        // });
    }

    private void showPopup(JFrame frame) {
        overlayPanel.setVisible(true);
        popupDialog.setLocationRelativeTo(frame);
        popupDialog.setVisible(true);
    }

    class CirclePanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.decode("#006281"));
            g.fillOval(0, 0, 100, 100);
        }
    }

    public void addReserveRoomListener(ActionListener listener) {
        modifyReservationButton.addActionListener(listener);
    }

    public void addBackListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new CheckReservationsPanel(frame));
        frame.setVisible(true);
    }
}
