package ui.GUI.Screens.NavButton;

import javax.swing.*;

import ui.GUI.App;
import ui.GUI.MainApp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Button extends JButton {

    public App app;
    static int BORDER_THICKNESS = 5;
    private Image content;
    private String imgPath;

    public Button(App app, String imgPath) {
        this.app = app;
        this.content = new ImageIcon(imgPath).getImage();
        setPreferredSize(new Dimension(30, 30)); // Set size of the round button
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
            g.setColor(App.SUB_YELLOW); 
        } else {
            g.setColor(App.MAIN_YELLOW); 
        }

        g.fillOval(BORDER_THICKNESS / 2, BORDER_THICKNESS / 2, 
        getWidth() - BORDER_THICKNESS, getHeight() - BORDER_THICKNESS);


        if (content != null) {
            int imageX = (getWidth() - content.getWidth(null)) / 2 ;
            int imageY = (getHeight() - content.getHeight(null)) / 2;
            g.drawImage(content, imageX, imageY, this);
        }
    }

    @Override
    protected void paintBorder(Graphics g) {

        
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the border with rounded edges
        if (getModel().isPressed()) {
            g2.setColor(App.MAIN_YELLOW); 
        } else {
            g2.setColor(App.SUB_YELLOW); 
        }
        g2.setStroke(new BasicStroke(BORDER_THICKNESS));
        g2.drawOval(BORDER_THICKNESS / 2, BORDER_THICKNESS / 2, 
                         getWidth() - BORDER_THICKNESS, getHeight() - BORDER_THICKNESS);
    }

}

