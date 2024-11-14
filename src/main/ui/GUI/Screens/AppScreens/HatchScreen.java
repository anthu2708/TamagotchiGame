package ui.GUI.Screens.AppScreens;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.*;

import ui.GUI.MainApp;
import ui.GUI.Screens.AppScreen;

public class HatchScreen extends AppScreen {
    private MainApp mainApp;

    // Hatching new Pet Screen
    public HatchScreen(MainApp app) {
        super(app, "Pet Hatching");
        this.mainApp = super.app;

        addMainPanel();
        createMenuButton();
    }

    // MODIFIES: this
    // EFFECTS: create a main Panel for creating a new Pet
    private void addMainPanel() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: add Save Button, which 
    //          on Pressed, creates new Pet with given name in EntryField
    //                      throw error message if field is empty
    private void addSaveButton(JPanel mainPanel) {
        // stub
    }


    // MODIFIES: this
    // EFFECTS: add EntryField, which display the typed name
    private void addEntryField(JPanel mainPanel) {
        // stub
    }

}
