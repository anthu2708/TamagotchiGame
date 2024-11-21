package ui.gui.screen.customizedcomponent;

import javax.swing.*;
import java.awt.*;

public class RoundedButton extends JButton {
    private int radius;
    private Color borderColor;
    private Color fillColor;
    private Color textColor;
    private int borderThickness;

    // Constructor that accepts radius, border color, fill color, text color, and
    // button name
    public RoundedButton(int radius, Color borderColor, Color fillColor, Color textColor, String name) {
        super(name);
        this.radius = radius;
        this.borderColor = borderColor;
        this.fillColor = fillColor;
        this.textColor = textColor;
        this.borderThickness = 5; // Set border thickness to 5px
        setOpaque(false); // Make the panel non-opaque to support rounded edges
        setContentAreaFilled(false); // Ensures only custom paint is used
        setFocusPainted(false); // Remove the default focus painting
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (!getModel().isPressed()) {
            g2.setColor(fillColor);
        } else {
            g2.setColor(borderColor);
        }
        g2.fillRoundRect(borderThickness / 2, borderThickness / 2,
                getWidth() - borderThickness, getHeight() - borderThickness,
                radius, radius);

        // Set the text color and draw the text
        g2.setColor(textColor);
        FontMetrics fm = g2.getFontMetrics();
        int textWidth = fm.stringWidth(getText());
        int textHeight = fm.getAscent();
        int x = (getWidth() - textWidth) / 2;
        int y = (getHeight() + textHeight) / 2 - 2;
        g2.drawString(getText(), x, y);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (getModel().isPressed()) {
            g2.setColor(fillColor);
        } else {
            g2.setColor(borderColor);
        }
        g2.setStroke(new BasicStroke(borderThickness));
        g2.drawRoundRect(borderThickness / 2, borderThickness / 2,
                getWidth() - borderThickness, getHeight() - borderThickness,
                radius, radius);
    }
}
