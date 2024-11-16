package ui.GUI.Screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.*;

import javax.swing.*;

import model.Game;
import ui.GUI.App;
import ui.GUI.MainApp;
import ui.GUI.PetGameApp;
import ui.GUI.Screens.NavButton.ExitButton;
import ui.GUI.Screens.NavButton.GoToButton;

public abstract class AppScreen extends JPanel {
    protected String BACKGROUND_PATH = "src\\main\\ui\\GUI\\PetImage\\BackGround\\BackGroundScreen.png";
    protected MainApp app;
    protected Game game;

    public AppScreen(MainApp app, String name) {
        this.app = app;
        this.game = app.getGame();

        setLayout(new BorderLayout());
        initHeaderPanel(name);
    }

    // EFFECTS: customize background
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image backgroundImage = new ImageIcon(BACKGROUND_PATH).getImage();
        // Draw the image as the background, scaled to the size of the panel
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    // EFFECTS: add header with specified name for
    public void initHeaderPanel(String title) {
        JLabel headerLabel = new JLabel(title, JLabel.CENTER);
        headerLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(headerLabel, BorderLayout.NORTH);
    }

    // EFFECTS: add Menu Button to return to the Menu Screen when pressed
    public void createMenuButton() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        GoToButton goToButton = new GoToButton(app, "MenuScreen");
        buttonPanel.add(goToButton, BorderLayout.CENTER);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    // EFFECTS: add Menu Button to return to the Menu Screen when pressed
    public void createHomeButton() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        GoToButton goToButton = new GoToButton(app, "HomeScreen");
        buttonPanel.add(goToButton, BorderLayout.CENTER);

        add(buttonPanel, BorderLayout.SOUTH);
    }

}
