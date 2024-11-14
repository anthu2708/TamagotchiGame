package ui.GUI.Screens.AppScreens;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.border.*;
import javax.swing.*;

import model.pet.Egg;
import model.pet.Pet;
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
        JPanel mainPanel = new JPanel();
        Dimension fixedSize = new Dimension(200, 150);
        mainPanel.setPreferredSize(fixedSize);
        mainPanel.setMinimumSize(fixedSize);
        mainPanel.setMaximumSize(fixedSize);
        

        mainPanel.setBorder(new EmptyBorder(300, 10, 330, 10));
        mainPanel.setLayout(new GridLayout(3, 1, 10, 10)); 
        mainPanel.add(new JLabel("Enter your new pet's name:"));
        JTextField nameField = addEntryField(mainPanel);
        addSaveButton(mainPanel, nameField);
        add(mainPanel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: add Save Button, which 
    //          on Pressed, creates new Pet with given name in EntryField
    //                      throw error message if field is empty
    private void addSaveButton(JPanel mainPanel, JTextField nameField) {
        JButton saveButton = new JButton("Hatch");
        saveButton.addActionListener(e -> {
            String petName = nameField.getText();
            if (petName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter Pet name.");
            } else {
                Egg newEgg = new Egg(petName);
                Pet newPet = newEgg.hatch();
                game.getHouse().addPet(newPet);
                nameField.removeAll();
                app.initScreens();
                JOptionPane.showMessageDialog(this, "You got a " + newPet.getType() + " named " + petName);
                app.showScreen("MenuScreen");
            }
        });
        mainPanel.add(saveButton);
    }


    // MODIFIES: this
    // EFFECTS: add EntryField, which display the typed name
    private JTextField addEntryField(JPanel mainPanel) {
        JTextField nameField = new JTextField(1);
        nameField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(nameField);
        return nameField;
    }

}
