package ui.GUI.Screens.AppScreens;

import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ui.GUI.MainApp;
import ui.GUI.Screens.AppScreen;

public class HomeScreen extends AppScreen {

    // Home Screen to either load game or start game
    public HomeScreen(MainApp app) {
        super(app, "Tamagotchi Game");

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout()); 

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 10, 10));
     
        getStartButton(app, buttonPanel);
        getLoadButton(app, buttonPanel);

        mainPanel.add(buttonPanel);
        add(mainPanel);
    }

    // MODIFIES: this
    // EFFECTS: add Load game Button to load saved Game from file
    private void getLoadButton(MainApp app, JPanel buttonPanel) {
        JButton loadButton = new JButton("Load Game");
        loadButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, app.loadGame());
        });
        // styleButton(loadButton, new Color(255, 182, 193)); // Light pink
        buttonPanel.add(loadButton);
    }

    // MODIFIES: this
    // EFFECTS: start game by going to the main menu
    private void getStartButton(MainApp app, JPanel buttonPanel) {
        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(e -> app.showScreen("MenuScreen"));
        // styleButton(startButton, new Color(173, 216, 230)); // Light blue
        buttonPanel.add(startButton);
    }

}
