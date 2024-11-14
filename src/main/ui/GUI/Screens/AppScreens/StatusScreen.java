package ui.GUI.Screens.AppScreens;

import javax.swing.*;
import javax.swing.border.*;

import model.Game;
import model.pet.Pet;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

import ui.GUI.MainApp;
import ui.GUI.PetGameApp;
import ui.GUI.Screens.AppScreen;

public class StatusScreen extends AppScreen {
    private MainApp mainApp;
    private List<Pet> pets;

    // View Pets status and Delete Pet
    public StatusScreen(MainApp app) {
        super(app, "Pets Status");
        this.mainApp = super.app;
        this.pets = mainApp.getPets();

        addScrollPane();
        createMenuButton();
    }

    // MODIFIES: this
    // EFFECTS: adding a scroll pane with Pet Panel displaying Pet Status
    private void addScrollPane() {
        JPanel statusPanel = initStatusPanel();

        JScrollPane scrollPane = new JScrollPane(statusPanel); 
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);
    }

    // EFFECTS: return JPanel with status Panel for each pet
    private JPanel initStatusPanel() {
        List<Pet> pets = mainApp.getPets();
        JPanel buttonPanel = new JPanel(new GridLayout(pets.size(), 1, 10, 10)); 
        buttonPanel.setBorder(new EmptyBorder(10, 10, 20, 10));

        for (Pet pet : pets) {
            addStatusPanel(buttonPanel, pet);
        }
        return buttonPanel;
    }


    // MODIFIES: this
    // EFFECTS: adding Pets panel with status, and delete button for each pet in house
    private void addStatusPanel(JPanel jPanel, Pet pet) {
        List<String> status = getStatus(pet);
        JPanel smallPanel = new JPanel();
        smallPanel.setLayout(new GridLayout(status.size() + 1, 1, 10, 10));
        smallPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
        smallPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        
        for (String s : status) {
            smallPanel.add(new JLabel(s));
        }
        JButton deletePetButton = new JButton("Delete Button");
        deletePetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pets.remove(pet);
                app.initScreens();
                app.showScreen("StatusScreen");
            }
        });
        smallPanel.add(deletePetButton);
        jPanel.add(smallPanel);
    }

    // MODIFIES: this
    // EFFECTS: Returns a string summarizing the pet's status
    // including name, type, hunger, happiness, health, and cleanliness.
    private List<String> getStatus(Pet pet) {
        String name = pet.getName();
        String type = pet.getType();
        int hunger = pet.getHunger();
        int happiness = pet.getHappiness();
        int health = pet.getHealth();
        int cleanliness = pet.getCleanliness();


        List<String> status = new ArrayList<>();
        status.add("Pet " + name + " - Pet Type: " + type + "\n");
        status.add("Hunger: " + hunger + "\n");
        status.add("Happiness: " + happiness + "\n");
        status.add("Health: " + health + "\n");
        status.add("Cleanliness: " + cleanliness + "\n");
        if (getAttention(pet) != "") {
            status.add(getAttention(pet));
        }
        return status;
    }

    // EFFECTS: return Attention String
    private String getAttention(Pet pet) {
        String attention = "";
        if (pet.needsPill()) {
            attention += "Need Pill!";
        }

        if (pet.needsAttention()[0]) {
            attention += "Need Food!";
        }

        if (pet.needsAttention()[1]) {
            attention += "Dirty!";
        }

        if (pet.needsAttention()[2]) {
            attention += "Sad!";
        }

        return attention;
    }



}
