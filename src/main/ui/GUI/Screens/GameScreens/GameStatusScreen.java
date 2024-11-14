package ui.GUI.Screens.GameScreens;

import ui.GUI.PetGameApp;
import ui.GUI.Screens.GameScreen;

import javax.swing.*;
import javax.swing.border.*;

import model.pet.Pet;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

public class GameStatusScreen extends GameScreen {

    // View Pet Status
    public GameStatusScreen(PetGameApp petGameApp) {
        super(petGameApp, "Pet Status");
        getPetStatusPanel(pet);
        createMenuButton();
    }

    // MODIFIES: this
    // EFFECTS: crfeate and add Panel showing all pet status
    private void getPetStatusPanel(Pet p) {
        List<String> status = getStatus(p);
        JPanel smallPanel = new JPanel();
        smallPanel.setBorder(new EmptyBorder(300, 40, 300, 40));
        smallPanel.setLayout(new GridLayout(status.size(), 1, 10, 10));

        for (String s : status) {
            smallPanel.add(new JLabel(s));
        }
        add(smallPanel);
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
