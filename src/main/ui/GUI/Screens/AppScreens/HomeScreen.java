package ui.GUI.Screens.AppScreens;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ui.GUI.App;
import ui.GUI.MainApp;
import ui.GUI.Screens.CustomizedPanel.*;
import ui.GUI.Screens.AppScreen;

public class HomeScreen extends AppScreen {

    // Home Screen to either load game or start game
    public HomeScreen(MainApp app) {
        super(app, "Tamagotchi Game");

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setOpaque(false);

        JPanel buttonPanel = new RoundedPanel(48, App.SUB_YELLOW, App.MAIN_YELLOW);
        buttonPanel.setLayout(new GridLayout(2, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(58, 34, 58, 34));

        getStartButton(app, buttonPanel);
        getLoadButton(app, buttonPanel);

        mainPanel.add(buttonPanel);
        add(mainPanel);
    }

    // MODIFIES: this
    // EFFECTS: add Load game Button to load saved Game from file
    private void getLoadButton(MainApp app, JPanel buttonPanel) {
        JButton loadButton = new RoundedButton(20, App.SUB_PURPLE, App.MAIN_PURPLE, App.TEXT_PURPLE, "Load Game");
        loadButton.setPreferredSize(new Dimension(202, 51));
        loadButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, app.loadGame());
        });
        // styleButton(loadButton, new Color(255, 182, 193)); // Light pink
        buttonPanel.add(loadButton);
    }

    // MODIFIES: this
    // EFFECTS: start game by going to the main menu
    private void getStartButton(MainApp app, JPanel buttonPanel) {
        JButton startButton = new RoundedButton(20, App.SUB_PINK, App.MAIN_PINK, App.TEXT_PINK, "Start Game");
        startButton.addActionListener(e -> app.showScreen("MenuScreen"));
        // styleButton(startButton, new Color(173, 216, 230)); // Light blue
        buttonPanel.add(startButton);
    }

}
