package View;

import java.awt.*;
import javax.swing.border.AbstractBorder;

public class RoundedCornerBorder extends AbstractBorder {
    private int radius;

    RoundedCornerBorder(int radius) {
        this.radius = radius;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
    }

    public Insets getBorderInsets(Component c, Insets insets) {
        insets.left = insets.right = insets.top = insets.bottom = this.radius + 1;
        return insets;
    }

    public boolean isBorderOpaque() {
        return true;
    }
}