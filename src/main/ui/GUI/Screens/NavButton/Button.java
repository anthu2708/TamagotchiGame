package ui.GUI.Screens.NavButton;

import javax.swing.*;

import ui.GUI.App;
import ui.GUI.MainApp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Button extends JButton {

    public App app;
    private String content;

    public Button(App app, String content) {
        this.app = app;
        this.content = content;
        setPreferredSize(new Dimension(30, 30)); // Set size of the round button
        setFont(new Font("Arial", Font.BOLD, 16)); // Font for "X"
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false); 

        setContentAreaFilled(false);
    }

    @Override
    // EFFECTS: customize the button looks
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (getModel().isPressed()) {
            g.setColor(new Color(236, 242, 248)); 
        } else {
            g.setColor(new Color(163, 184, 204)); 
        }

        g.fillOval(0, 0, getWidth(), getHeight());


        g.setColor(Color.BLACK);
        FontMetrics fm = g.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(content)) / 2; // Center the "X" horizontally
        int y = (getHeight() + fm.getAscent()) / 2; // Center the "X" vertically
        g.drawString(content, x, y); // Draw X in the center
    }

}

