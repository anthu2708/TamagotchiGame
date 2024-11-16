package ui.GUI.Screens.CustomizedPanel;

import javax.swing.*;
import javax.swing.border.AbstractBorder;

import ui.GUI.App;

import java.awt.*;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.AbstractBorder;

public class RoundedTextField extends JTextField {

    private int radius;
    private Color borderColor;
    private Color fillColor;
    private int borderThickness;

    // Constructor for custom rounded text field
    public RoundedTextField(String text) {
        super(text);
        this.radius = 28;
        this.borderColor = App.SUB_YELLOW;
        this.fillColor = Color.WHITE;
        this.borderThickness = 5;
        setOpaque(false); // Make the background transparent
    }

    @Override
    public void paintBorder(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Set the color of the border
        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(borderThickness));
        g2.drawRoundRect(borderThickness / 2, borderThickness / 2,
                getWidth() - borderThickness, getHeight() - borderThickness,
                radius, radius);
    }

}
