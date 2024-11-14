package ui.GUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import model.pet.Pet;

public class MainApp extends JFrame {

    // main Application's interface
    public MainApp() {

    }

    // MODIFIES: this
    // EFFECT: reload all screens, and add to mainPanel
    public void initScreens() {
        // TODO
        // stub
    }

    // REQUIRES: screenName has to be created in mainPanel
    // MODIFIES: this
    // EFFECTS: show the specified screen
    public void showScreen(String screenName) {
        // stub
    }

    // EFFECTS: save game to file
    public void saveGame() {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: loads game from file
    public String loadGame() {
        return null; //stub
    }

    // main method to show MainApp screen
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainApp app = new MainApp();
                app.setVisible(true);
            }
        });
    }

}
