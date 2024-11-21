package ui.gui;

import javax.swing.JLayeredPane;

import java.awt.BorderLayout;

import model.Game;
import model.pet.Pet;
import ui.gui.screen.gamescreen.FridgeScreen;
import ui.gui.screen.gamescreen.GameMenuScreen;
import ui.gui.screen.gamescreen.GameStatusScreen;
import ui.gui.screen.gamescreen.MainGameScreen;
import ui.gui.screen.gamescreen.MedBoxScreen;
import ui.gui.screen.gamescreen.PlayScreen;
import ui.gui.screen.gamescreen.StoreScreen;
import ui.gui.screen.navigationbutton.GameExitButton;

public class PetGameApp extends App {

    private FridgeScreen fridgeScreen;
    private GameMenuScreen gameMenuScreen;
    private GameStatusScreen gameStatusScreen;
    private MainGameScreen mainGameScreen;
    private MedBoxScreen medBoxScreen;
    private StoreScreen storeScreen;
    private PlayScreen playScreen;

    private Game game;
    private Pet pet;

    // main Application's interface
    public PetGameApp(Game game, Pet pet) {
        super();
        this.game = game;
        this.pet = pet;

        init();
    }

    // MODIFIES: this
    // EFFECT: reload all screens, and add to mainPanel
    public void initScreens() {
        playScreen = new PlayScreen(this);
        fridgeScreen = new FridgeScreen(this);
        gameMenuScreen = new GameMenuScreen(this);
        gameStatusScreen = new GameStatusScreen(this);
        mainGameScreen = new MainGameScreen(this);
        medBoxScreen = new MedBoxScreen(this);
        storeScreen = new StoreScreen(this);

        // Remove existing screens from the main panel if they are already there
        mainPanel.remove(fridgeScreen);
        mainPanel.remove(gameMenuScreen);
        mainPanel.remove(gameStatusScreen);
        mainPanel.remove(mainGameScreen);
        mainPanel.remove(medBoxScreen);
        mainPanel.remove(storeScreen);
        mainPanel.remove(playScreen);

        // Add screens to the main panel with consistent identifiers
        mainPanel.add(fridgeScreen, "FridgeScreen");
        mainPanel.add(gameMenuScreen, "GameMenuScreen");
        mainPanel.add(gameStatusScreen, "GameStatusScreen");
        mainPanel.add(mainGameScreen, "GameScreen");
        mainPanel.add(medBoxScreen, "MedBoxScreen");
        mainPanel.add(storeScreen, "StoreScreen");
        mainPanel.add(playScreen, "PlayScreen");
    }

    // EFFECTS: add Exit Button with a choice to Save game Progress or not
    public void addExitButton() {
        GameExitButton exitButton = new GameExitButton(this);
        JLayeredPane layeredPane = getLayeredPane();
        layeredPane.add(exitButton);
        exitButton.setBounds(getWidth() - 40, 10, 30, 30);
    }

    // REQUIRES: screenName has to be created in mainPanel
    // MODIFIES: this
    // EFFECTS: show the specified screen
    public void showScreen(String screenName) {
        cardLayout.show(mainPanel, screenName);
    }

    // return App's game
    public Game getGame() {
        return game;
    }

    // return App's pet
    public Pet getPet() {
        return pet;
    }

    // MODIFIES: this
    // EFFECTS: init Game
    public void init() {
        initScreens();
        showScreen("GameScreen");
        addExitButton();
        add(mainPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
    }

}
