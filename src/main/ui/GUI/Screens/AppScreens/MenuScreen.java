package ui.GUI.Screens.AppScreens;

import java.awt.GridLayout;
import java.awt.BorderLayout;


import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.border.EmptyBorder;

import ui.GUI.MainApp;
import ui.GUI.Screens.AppScreen;

public class MenuScreen extends AppScreen {

    // Menu Screen to either hatch egg, view pet status or go to each pet
    public MenuScreen(MainApp app) {
        super(app, "Menu");
        JPanel buttonPanel = new JPanel();
    buttonPanel.setBorder(new EmptyBorder(0,10,20,10));
    buttonPanel.setLayout(new GridLayout(3,1,10,10));

    getHatchButton(app, buttonPanel);
    getPetChoosingButton(app, buttonPanel);
    getPetStatusButton(app, buttonPanel);
    add(buttonPanel, BorderLayout.CENTER);
    }

    // EFFECTS: button to navigate to PetsChoosing Screen
    private void getPetChoosingButton(MainApp app, JPanel buttonPanel) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPetChoosingButton'");
    }

    // EFFECTS: button to navigate to Status Screen
    private void getPetStatusButton(MainApp app, JPanel buttonPanel) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPetStatusButton'");
    }

    // EFFECTS: button to navigate to hatch Screen
    private void getHatchButton(MainApp app, JPanel buttonPanel) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getHatchButton'");
    }

}
