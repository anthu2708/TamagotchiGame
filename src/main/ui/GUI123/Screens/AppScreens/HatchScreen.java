package ui.GUI.Screens.AppScreens;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.border.*;
import javax.swing.*;

import model.pet.Egg;
import model.pet.Pet;
import ui.GUI.App;
import ui.GUI.MainApp;
import ui.GUI.Screens.AppScreen;
import ui.GUI.Screens.CustomizedPanel.RoundedButton;
import ui.GUI.Screens.CustomizedPanel.RoundedPanel;
import ui.GUI.Screens.CustomizedPanel.RoundedTextField;

public class HatchScreen extends AppScreen {
    private MainApp mainApp;

    // Hatching new Pet Screen
    public HatchScreen(MainApp app) {
        super(app, "");
        this.mainApp = super.app;

        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setBorder(new EmptyBorder(150, 0, 0, 0));

        addMainPanel(mainPanel);
        add(mainPanel)
        ;
        createMenuButton();
    }

    // MODIFIES: this
    // EFFECTS: create a Panel for creating a new Pet and add to main Panel
    private void addMainPanel(JPanel panel) {
        JPanel buttonPanel = new RoundedPanel(48, App.SUB_YELLOW, App.MAIN_YELLOW);
        buttonPanel.setBorder(new EmptyBorder(23, 23, 23, 23));
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));

        JLabel menu = new JLabel("Name Your Pet");
        menu.setFont(new Font(menu.getFont().getName(), 1, 32));
        menu.setForeground(App.TEXT_YELLOW); 
        menu.setHorizontalAlignment(SwingConstants.CENTER); 
        menu.setBorder(new EmptyBorder(0, 0, 20, 0));
        buttonPanel.add(menu, BorderLayout.CENTER);

        JTextField nameField = addEntryField(buttonPanel);
        addSaveButton(buttonPanel, nameField);
        panel.add(buttonPanel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: add Save Button, which 
    //          on Pressed, creates new Pet with given name in EntryField
    //                      throw error message if field is empty
    private void addSaveButton(JPanel mainPanel, JTextField nameField) {
        JButton saveButton = new RoundedButton(28, App.SUB_GREEN, App.MAIN_GREEN, App.TEXT_GREEN,"Hatch");
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
        JTextField nameField = new RoundedTextField("");
        nameField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(nameField);
        return nameField;
    }

}
