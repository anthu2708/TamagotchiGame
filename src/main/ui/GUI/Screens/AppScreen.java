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
import ui.GUI.MainApp;
import ui.GUI.PetGameApp;
import ui.GUI.Screens.NavButton.ExitButton;
import ui.GUI.Screens.NavButton.GoToButton;

public abstract class AppScreen extends JPanel {
    protected MainApp app;
    protected Game game;

    public AppScreen(MainApp app, String name) {
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
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        GoToButton goToButton = new GoToButton(app, "MenuScreen");
        buttonPanel.add(goToButton, BorderLayout.CENTER);

        add(buttonPanel, BorderLayout.SOUTH);
    }

}
