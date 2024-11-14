package ui.GUI.Screens.GameScreens;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.GUI.PetGameApp;
import ui.GUI.Screens.GameScreen;

public class GameMenuScreen extends GameScreen {

    // Menu Screen to choose action from
    public GameMenuScreen(PetGameApp petGameApp) {
        super(petGameApp, "Menu");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(0, 10, 20, 10));
        buttonPanel.setLayout(new GridLayout(6, 1, 10, 10));

        getButton(buttonPanel, "View Pet Status", "GameStatusScreen");
        getFeedButton(buttonPanel);
        getPlayButton(buttonPanel);
        getHealButton(buttonPanel);
        getCleanButton(buttonPanel);
        getButton(buttonPanel, "Store", "StoreScreen");
        add(buttonPanel, BorderLayout.CENTER);

        createHomeButton();
    }

    // MODIFIES: this
    // EFFECTS: add new Button with Label name to specified JPanel 
    //          when pressed, go to specified game screen
    private void getButton(JPanel buttonPanel, String name, String screen) {
        JButton button = new JButton(name);
        button.addActionListener(e -> {
            app.showScreen(screen);
        });
        buttonPanel.add(button);
    }

    // MODIFIES: this
    // EFFECTS: add Feed Button with to specified JPanel 
    //          when pressed, if there is food in fridge, go to Fridge
    //          if no food, throw message to buy more
    private void getFeedButton(JPanel buttonPanel) {
        JButton button = new JButton("Feed");
        button.addActionListener(e -> {
            if (app.getGame().getFridge().isEmpty()) {
                JOptionPane.showMessageDialog(this, "There is no food. Please visit store to purchase Food.");
            } else {
                app.showScreen("FridgeScreen");
            }
        });
        buttonPanel.add(button);
    }

    // MODIFIES: this
    // EFFECTS: add Heal Button with to specified JPanel 
    //          when pressed, if pet does not need to be heal, throw message 
    //          else, go to Medicine Box
    private void getHealButton(JPanel buttonPanel) {
        JButton button = new JButton("Heal");
        button.addActionListener(e -> {
            if (!pet.needsPill()) {
                JOptionPane.showMessageDialog(this, pet.getName() + " does not need to be healed!");
            } else if (game.getMedicineBox().getPill().isEmpty()) {
                JOptionPane.showMessageDialog(this, "There is no pill. Please visit store to purchase Pill.");
            } else {
                app.showScreen("MedBoxScreen");
            }
        });
        buttonPanel.add(button);
    }

    // MODIFIES: this
    // EFFECTS: add Heal Button with to specified JPanel 
    //          when pressed, if pet is clean, throw message 
    //          else, clean pet
    private void getCleanButton(JPanel buttonPanel) {
        JButton button = new JButton("Clean");
        button.addActionListener(e -> {
            if (!pet.needsAttention()[1]) {
                JOptionPane.showMessageDialog(this, pet.getName() + " does not want to bathe!");
            } else {
                pet.clean();
                JOptionPane.showMessageDialog(this, pet.getName() + " is now clean!");
                reloadScreens("GameScreen");
            }
        });
        buttonPanel.add(button);
    }

    // MODIFIES: this
    // EFFECTS: add Play Button with to specified JPanel 
    //          when pressed, if pet is sick, throw message need to heal
    //          else, go to Play Screen
    private void getPlayButton(JPanel buttonPanel) {
        JButton button = new JButton("Play");
        button.addActionListener(e -> {
            if (pet.needsAttention()[0] || pet.needsAttention()[1]) {
                JOptionPane.showMessageDialog(this, pet.getName() + " can't play and need attention!");
            } else {
                app.showScreen("PlayScreen");
            }
        });
        buttonPanel.add(button);
    }
}
