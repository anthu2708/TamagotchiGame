package ui.gui.screens.gamescreens;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import ui.gui.App;
import ui.gui.PetGameApp;
import ui.gui.screens.GameScreen;
import ui.gui.screens.customizedpanel.RoundedButton;
import ui.gui.screens.customizedpanel.RoundedPanel;

import java.awt.*;

public class PlayScreen extends GameScreen {

    static int PLAY_COIN = 7;
    static int PET_COIN = 5;

    // Play Screen where you can either choose to play with or pet your Pet
    public PlayScreen(PetGameApp petGameApp) {
        super(petGameApp, "Play");

        JPanel maiPanel = new JPanel();
        maiPanel.setOpaque(false);
        maiPanel.setBorder(new EmptyBorder(200, 20, 250, 20));

        JPanel buttonPanel = new RoundedPanel(40, App.SUB_YELLOW, App.MAIN_YELLOW);
        buttonPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        buttonPanel.setLayout(new GridLayout(2, 1, 10, 10));

        addButton(buttonPanel, "Play");
        addButton(buttonPanel, "Pet");
        maiPanel.add(buttonPanel);
        add(maiPanel);

        createMenuButton();
    }

    // MODIFIES: this
    // EFFECTS: add new Button with Label name to specified JPanel
    // when pressed, play the function specified in name
    private void addButton(JPanel buttonPanel, String name) {
        JButton button = new RoundedButton(32, App.SUB_YELLOW, App.MAIN_YELLOW, App.TEXT_YELLOW, name);
        button.setPreferredSize(new Dimension(250, 35));
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
