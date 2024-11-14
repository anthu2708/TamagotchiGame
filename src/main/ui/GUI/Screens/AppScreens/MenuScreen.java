package ui.GUI.Screens.AppScreens;

import java.awt.GridLayout;
import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.GUI.MainApp;
import ui.GUI.Screens.AppScreen;

public class MenuScreen extends AppScreen {
    private MainApp mainApp;

    // Menu Screen to either hatch egg, view pet status or go to each pet
    public MenuScreen(MainApp app) {
        super(app, "Menu");
        mainApp = super.app;
        getButtonPanel();

        createHomeButton();
    }

    // EFFECTS: add a new Button Panel to MenuScreen Panel
    //          with 3 buttons: Hatch, Status and Pets.
    private void getButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(0, 10, 20, 10));
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));
        getButton(buttonPanel, "HatchScreen", "Hatch new Egg");
        getButton(buttonPanel, "StatusScreen", "View Pet Status");
        getButton(buttonPanel, "PetsScreen", "Play With Pet");
        add(buttonPanel, BorderLayout.CENTER);
    }

    // EFFECTS: add button to navigate to PetsChoosing Screen to selected Panel
    private void getButton(JPanel buttonPanel, String screen, String name) {
        JButton petChoosingButton = new JButton(name);
        petChoosingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainApp.showScreen(screen);
            }
        });
        buttonPanel.add(petChoosingButton);
    }

}
