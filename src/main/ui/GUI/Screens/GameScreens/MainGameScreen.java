package ui.GUI.Screens.GameScreens;

import javax.swing.*;
import java.awt.*;

import ui.GUI.PetGameApp;
import ui.GUI.Screens.GameScreen;

public class MainGameScreen extends GameScreen {

    // Main Game - pet display
    public MainGameScreen(PetGameApp petGameApp) {
        super(petGameApp, "Pet Room");

        JLabel petLabel = new JLabel(app.getPet().getName());
        add(petLabel, BorderLayout.CENTER);

        createMenuButton();
    }

}
