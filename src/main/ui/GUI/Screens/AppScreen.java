package ui.GUI.Screens;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import model.Game;
import ui.GUI.MainApp;
import ui.GUI.PetGameApp;

public abstract class AppScreen extends JPanel {
    protected MainApp app;
    protected Game game;

    public AppScreen(MainApp app, String name) {
        this.app = app;
        this.game = app.getGame();

        setLayout(new BorderLayout());
        add(initHeaderPanel(name));
        addExitButton();
    }


    // EFFECTS: add Exit Button with a choice to Save game Progress or not
    public void addExitButton() {
        // stub
    }

    // EFFECTS: create header with specified name for
    public JPanel initHeaderPanel(String title) {
        return new JPanel(); 
    }

    // EFFECTS: create Menu Button to return to the Menu Screen when pressed
    public JPanel createMenuButton() {
        return new JPanel();
    }

}
