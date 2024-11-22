package ui.gui.screen;

import java.awt.*;

import javax.swing.*;

import model.Game;
import ui.gui.MainApp;
import ui.gui.screen.navigationbutton.GoToButton;

public abstract class AppScreen extends JPanel {
    protected String backgroundPath = "data/petimage/bgimage/BackGroundScreen.png";
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
        Image backgroundImage = new ImageIcon(backgroundPath).getImage();
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
