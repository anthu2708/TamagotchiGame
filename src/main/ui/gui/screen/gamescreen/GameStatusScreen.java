package ui.gui.screen.gamescreen;

import ui.gui.App;
import ui.gui.PetGameApp;
import ui.gui.screen.GameScreen;
import ui.gui.screen.customizedcomponent.RoundedPanel;

import javax.swing.*;
import javax.swing.border.*;

import model.pet.Pet;

import java.awt.*;
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
        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setBorder(new EmptyBorder(250, 40, 200, 40));

        
        addStatusPanel(mainPanel, pet);
        add(mainPanel);
    }

    // MODIFIES: this
    // EFFECTS: adding Pets panel with status, and delete button for each pet in
    // house
    private void addStatusPanel(JPanel jpanel, Pet pet) {
        List<String> status = pet.getStatusList();
        JPanel smallPanel = new RoundedPanel(40, App.SUB_YELLOW, App.MAIN_YELLOW);
        smallPanel.setLayout(new GridLayout(6, 1, 10, 0));
        smallPanel.setBorder(new EmptyBorder(20, 40, 20, 40));

        for (String s : status) {
            JLabel lab = new JLabel(s);
            lab.setForeground(App.TEXT_YELLOW);
            smallPanel.add(lab);
        }

        String s = getAttention(pet);
        JLabel lab = new JLabel(s);
        lab.setForeground(App.TEXT_PINK);
        smallPanel.add(lab);
        jpanel.add(smallPanel);
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
