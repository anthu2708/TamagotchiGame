package ui.GUI.Screens.CustomizedPanel;

import javax.swing.*;
import java.awt.*;

import javax.swing.*;
import java.awt.*;

public class RoundedPanel extends JPanel {
    private int radius;
    private Color borderColor;
    private Color fillColor;
    private Color textColor;
    private int borderThickness;

    // Constructor that accepts radius, border color, and fill color
    public RoundedPanel(int radius, Color borderColor, Color fillColor) {
        this.radius = radius;
        this.borderColor = borderColor;
        this.fillColor = fillColor;
        this.textColor = textColor;
        this.borderThickness = 5;  // Set border thickness to 5px
        setOpaque(false);  // Make the panel non-opaque to support rounded edges
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fill the background with rounded edges
        g2.setColor(fillColor);
        g2.fillRoundRect(borderThickness / 2, borderThickness / 2, 
                         getWidth() - borderThickness, getHeight() - borderThickness, 
                         radius, radius);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the border with rounded edges
        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(borderThickness));
        g2.drawRoundRect(borderThickness / 2, borderThickness / 2, 
                         getWidth() - borderThickness, getHeight() - borderThickness, 
                         radius, radius);
    }
}
