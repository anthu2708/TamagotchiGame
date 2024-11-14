package ui.GUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.geom.RoundRectangle2D;

import model.CoinManager;
import model.Game;
import model.House;
import model.Store;
import model.pet.Pet;
import model.supplies.Fridge;
import model.supplies.MedicineBox;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.GUI.Screens.AppScreens.HatchScreen;
import ui.GUI.Screens.AppScreens.HomeScreen;
import ui.GUI.Screens.AppScreens.MenuScreen;
import ui.GUI.Screens.AppScreens.PetsScreen;
import ui.GUI.Screens.AppScreens.StatusScreen;

public class MainApp extends JFrame {
    private static final String JSON_STORE = "./data/petgame.json";

    private HomeScreen homeScreen;
    private MenuScreen menuScreen;
    private HatchScreen hatchScreen;
    private PetsScreen petsScreen;
    private StatusScreen statusScreen;

    private Game game;
    private List<PetGameApp> petGames;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // main Application's interface
    public MainApp() {
        this.game = new Game(
                new House(),
                new Fridge(),
                new MedicineBox(),
                new CoinManager(100),
                new Store());
        this.petGames = new ArrayList<>();
        this.jsonWriter = new JsonWriter(JSON_STORE);
        this.jsonReader = new JsonReader(JSON_STORE);
        this.cardLayout = new CardLayout();
        this.mainPanel = new JPanel(cardLayout);

        setScreenLayout();
        initScreens();
        showScreen("HomeScreen");

        add(mainPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
    }

    private void setScreenLayout() {
        setTitle("Tama");
        setSize(390, 844);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setShape(new RoundRectangle2D.Double(0, 0, 390, 844, 40, 40));
    }

    // MODIFIES: this
    // EFFECT: reload all screens, and add to mainPanel
    public void initScreens() {
        homeScreen = new HomeScreen(this);
        menuScreen = new MenuScreen(this);
        hatchScreen = new HatchScreen(this);
        petsScreen = new PetsScreen(this);
        statusScreen = new StatusScreen(this);

        // Remove existing screens from the main panel if they are already there
        mainPanel.remove(homeScreen);
        mainPanel.remove(menuScreen);
        mainPanel.remove(hatchScreen);
        mainPanel.remove(petsScreen);
        mainPanel.remove(statusScreen);

        // Add screens to the main panel with consistent identifiers
        mainPanel.add(homeScreen, "HomeScreen");
        mainPanel.add(menuScreen, "MenuScreen");
        mainPanel.add(hatchScreen, "HatchScreen");
        mainPanel.add(petsScreen, "PetsScreen");
        mainPanel.add(statusScreen, "StatusScreen");
    }

    // REQUIRES: screenName has to be created in mainPanel
    // MODIFIES: this
    // EFFECTS: show the specified screen
    public void showScreen(String screenName) {
        cardLayout.show(mainPanel, screenName);
    }

    // EFFECTS: save game to file
    public void saveGame() {
        try {
            jsonWriter.open();
            jsonWriter.write(game);
            jsonWriter.close();
        } catch (Exception e) {
            System.out.println("Unable to save the game.");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads game from file
    public String loadGame() {
        try {
            List<Pet> petList;
            List<PetGameApp> petGameApps = new ArrayList<>();
            game = jsonReader.readGame();
            petList = game.getHouse().getPets();

            for (Pet p : petList) {
                PetGameApp petUI = new PetGameApp(game, p);
                petGameApps.add(petUI);
            }
            petGames = petGameApps;

            initScreens();
            showScreen("HomeScreen");
            
            return "Game Loaded";
        } catch (IOException e) {
            return ("Unable to read from file: " + JSON_STORE);
        }
    }

    // return App's game
    public Game getGame() {
        return game;
    }

    // main method to show MainApp screen
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainApp app = new MainApp();
                app.setVisible(true);
            }
        });
    }

}
