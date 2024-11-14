package ui.GUI.Screens.AppScreens;

import javax.swing.*;
import javax.swing.border.*;

import model.Game;
import model.pet.Pet;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import ui.GUI.MainApp;
import ui.GUI.PetGameApp;
import ui.GUI.Screens.AppScreen;

public class StatusScreen extends AppScreen {

    // View Pets status and Delete Pet
    public StatusScreen(MainApp app) {
        super(app, "Pets Status");

        addScrollPane();
        createMenuButton();
    }

    // MODIFIES: this
    // EFFECTS: adding a scroll pane with Pet Panel displaying Pet Status
    private void addScrollPane() {
        // stub
    }

    // EFFECTS: return JPanel with status Panel for each pet
    private JPanel initStatusPanel() {
        return new JPanel();
    }


    // MODIFIES: this
    // EFFECTS: adding Pets panel with status, and delete button for each pet in house
    private void addStatusPanel(JPanel jPanel, Pet pet) {
        // stub
    }



}
