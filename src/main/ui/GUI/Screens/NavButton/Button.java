package ui.GUI.Screens.NavButton;

import javax.swing.*;

import ui.GUI.MainApp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Button extends JButton {

    public MainApp app;

    public Button(MainApp app) {
        this.app = app;
        setPreferredSize(new Dimension(30, 30)); // Set size of the round button
        setFont(new Font("Arial", Font.BOLD, 16)); // Font for "X"
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false); 

        setContentAreaFilled(false);
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleExitAction();
            }
        });
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


        // Set color for "X" text
        g.setColor(Color.BLACK);
        FontMetrics fm = g.getFontMetrics();
        int x = (getWidth() - fm.stringWidth("X")) / 2; // Center the "X" horizontally
        int y = (getHeight() + fm.getAscent()) / 2; // Center the "X" vertically
        g.drawString("X", x, y); // Draw X in the center
    }

    // MODIFIES: app
    // EFFECTS: handle exit action: save game progress or not
    private void handleExitAction() {
        int response = JOptionPane.showConfirmDialog(
                app,
                "Do you want to save your progress before exiting?",
                "Exit Confirmation",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            app.saveGame(); // You will need to define the saveGame method
            System.exit(0);
        } else if (response == JOptionPane.NO_OPTION) {
            System.exit(0);
        }
    }
}

