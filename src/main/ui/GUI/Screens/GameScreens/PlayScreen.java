package ui.GUI.Screens.GameScreens;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import model.CoinManager;
import model.Game;
import model.pet.Pet;
import ui.GUI.PetGameApp;
import ui.GUI.Screens.GameScreen;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayScreen extends GameScreen {
    private CoinManager coinManager;

    static int PLAY_COIN = 7;
    static int PET_COIN = 5;

    // Play Screen where you can either choose to play with or pet your Pet
    public PlayScreen(PetGameApp petGameApp) {
        super(petGameApp, "Play");
        this.coinManager = game.getCoinManager();

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(250, 10, 300, 10));
        buttonPanel.setLayout(new GridLayout(8, 1, 10, 10));

        addButton(buttonPanel, "Play");
        addButton(buttonPanel, "Pet");
        add(buttonPanel);

        createMenuButton();
    }

    // MODIFIES: this
    // EFFECTS: add new Button with Label name to specified JPanel
    // when pressed, play the function specified in name
    private void addButton(JPanel buttonPanel, String name) {
        JButton button = new JButton(name);
        if (name == "Play") {
            button.addActionListener(e -> play());
        } else {
            button.addActionListener(e -> pet());
        }
        buttonPanel.add(button);
    }

    // MODIFIES: this
    // EFFECTS: award PET_COIN, print successfull message and reload screens
    private void pet() {
        pet.pet();
        JOptionPane.showMessageDialog(this,
                pet.getName() 
                + " loves our time together! \n" 
                + "Earned " 
                + PET_COIN 
                + " coins!");
        game.getCoinManager().add(PET_COIN);
        reloadScreens("GameScreen");
    }

    // MODIFIES: this
    // EFFECTS: award PLAY_COIN, print successfull message and reload screens
    private void play() {
        pet.play();
        game.getCoinManager().add(PLAY_COIN);
        JOptionPane.showMessageDialog(this,
                pet.getName() 
                + " loves playing together! \n" 
                + "Earned " 
                + PLAY_COIN 
                + " coins!");
        reloadScreens("GameScreen");
    }

}
