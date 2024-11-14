package ui.GUI.Screens.GameScreens;

import ui.GUI.PetGameApp;
import ui.GUI.Screens.GameScreen;

public class MainGameScreen extends GameScreen {

    // Fridge Screen to choose food from
    public MainGameScreen(PetGameApp petGameApp) {
        super(petGameApp, petGameApp.getPet().getName());
    }

}
