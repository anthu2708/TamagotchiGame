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
        JPanel buttonPanel = initButtonPanel();

        JScrollPane scrollPane = new JScrollPane(buttonPanel); // Set buttonPanel as viewport view
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);
    }

    // EFFECTS: return a JPanel with buttons for each pet
    private JPanel initButtonPanel() {
        List<Pet> pets = mainApp.getPets();
        JPanel buttonPanel = new JPanel(new GridLayout(pets.size(), 1, 10, 10)); 
        buttonPanel.setBorder(new EmptyBorder(10, 10, 20, 10));

        for (Pet pet : pets) {
            addButton(buttonPanel, pet);
        }
        return buttonPanel;
    }

    // MODIFIES: this
    // EFFECTS: adding Pets button for each pet in house
    //          open Pet Game when clicked
    private void addButton(JPanel jPanel, Pet pet) {
        JButton petButton = new JButton(pet.getName());
        petButton.addActionListener(e -> openPetGameApp(app.getGame(), pet));
        petButton.setFont(new Font("Arial", Font.BOLD, 16));
        petButton.setFocusPainted(false);
        petButton.setPreferredSize(new Dimension(250, 200)); 
        jPanel.add(petButton);
    }

     private void openPetGameApp(Game game, Pet pet) {
        SwingUtilities.invokeLater(() -> {
            PetGameApp petGameApp = new PetGameApp(game, pet);
            petGameApp.setVisible(true);
        });
    }

}
