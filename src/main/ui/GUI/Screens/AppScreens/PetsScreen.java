package ui.GUI.Screens.AppScreens;

import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.GUI.MainApp;
import ui.GUI.Screens.AppScreen;

public class PetsScreen extends AppScreen {
    private MainApp mainApp;

    // Screen to choose Pet Game
    public PetsScreen(MainApp app) {
        super(app, "House");
        this.mainApp = super.app;

        addScrollPane();
        createMenuButton();
    }

    // MODIFIES: this
    // EFFECTS: adding a scroll pane with Pet Button to go to Pet Game
    private void addScrollPane() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: adding Pets button for each pet in house
    //          open Pet Game when clicked
    private void addButton(JPanel jPanel) {
        // stub
    }

}
