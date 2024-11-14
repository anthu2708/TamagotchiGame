package ui.GUI.Screens.GameScreens;

import javax.swing.*;
import java.awt.GridLayout;
import java.util.*;

import model.pet.Pet;
import model.supplies.MedicineBox;
import model.supplies.Pill;
import ui.GUI.PetGameApp;
import ui.GUI.Screens.GameScreen;

public class MedBoxScreen extends GameScreen {
    private MedicineBox medicineBox;

    // Medicine Box Screen to choose pill from
    public MedBoxScreen(PetGameApp petGameApp) {
        super(petGameApp, "Medicine Box");
        this.medicineBox = game.getMedicineBox();

        getPillsPanel();
        createMenuButton();
    }

    // MODIFIES: this 
    // EFFECTS: generate and add Panel with all Pills to choose from
    private void getPillsPanel() {
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new GridLayout(3, 1, 10, 10));

        Map<Pill, Integer> pills = medicineBox.getPill();
        for (Map.Entry<Pill, Integer> entry : pills.entrySet()) {
            Pill pill = entry.getKey();
            int quantity = entry.getValue();
            getPillButton(itemPanel, pill, quantity);
        }
        add(itemPanel);
    }

    // MODIFIES: this 
    // EFFECTS: generate and add button for each pill
    //          when Use button is pressed, use pill to modify pet state
    private void getPillButton(JPanel itemPanel, Pill pill, int quantity) {
        JPanel smallPanel = new JPanel();

        String name = pill.getName();
        int nutriVal = pill.getNutrition();
        int healthVal = pill.getHealth();
        int happinessValue = pill.getHappiness();

        smallPanel.setLayout(new GridLayout(6, 1, 10, 10));
        smallPanel.add(new JLabel(name));
        smallPanel.add(new JLabel("Hunger Point: " + nutriVal));
        smallPanel.add(new JLabel("Health Point: " + healthVal));
        smallPanel.add(new JLabel("Happiness Point: " + happinessValue));
        smallPanel.add(new JLabel("Quantity: " + quantity));
        JButton button = new JButton("Use Pill");
        button.addActionListener(e -> heal(pill));
        smallPanel.add(button);

        itemPanel.add(smallPanel);
    }


    // MODIFIES: this 
    // EFFECTS: use pill to modify pet state and reload all Screen to match new state
    private void heal(Pill pill) {
        Pet pet = super.app.getPet();
        pet.usePill(pill);
        JOptionPane.showMessageDialog(this, pet.getName() + " is healed!");
        reloadScreens("GameScreen");

    }

}
