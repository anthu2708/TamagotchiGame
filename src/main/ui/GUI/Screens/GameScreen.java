package ui.GUI.Screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.*;

import model.Game;
import model.pet.Pet;
import model.supplies.Food;
import ui.GUI.PetGameApp;
import ui.GUI.Screens.NavButton.ExitButton;
import ui.GUI.Screens.NavButton.GoToButton;

public class GameScreen extends JPanel {
    protected PetGameApp app;
    protected Game game;

    public GameScreen(PetGameApp app, String name) {
        this.app = app;
        this.game = app.getGame();

        setLayout(new BorderLayout());
        initHeaderPanel(name);
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
        GoToButton goToButton = new GoToButton(app, "GameMenuScreen");
        buttonPanel.add(goToButton, BorderLayout.CENTER);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    // EFFECTS: add Menu Button to return to the Menu Screen when pressed
    public void createHomeButton() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        GoToButton goToButton = new GoToButton(app, "GameScreen");
        buttonPanel.add(goToButton, BorderLayout.CENTER);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: reload all Screen and go to screen
    public void reloadScreens(String screen) {
        app.initScreens();
        app.showScreen(screen);
    }
}
