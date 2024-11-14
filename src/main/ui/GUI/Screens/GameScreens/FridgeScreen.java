package ui.GUI.Screens.GameScreens;

import javax.swing.*;
import java.util.*;

import model.pet.Pet;
import model.supplies.Food;
import ui.GUI.PetGameApp;
import ui.GUI.Screens.GameScreen;

public class FridgeScreen extends GameScreen {
    // Fridge Screen to choose food from
    public FridgeScreen(PetGameApp petGameApp) {
        super(petGameApp, "Fridge");
        createMenuButton();
    }

    // MODIFIES: this
    // EFECTS: Add item Panel with Food Panels
    private void getFoodPanel(Map<Food, Integer> foods) {
        //stub
    }

    // MODIFIES: this 
    // EFFECTS: Add Food Panel for each food to itemPanel
    private void getFoodButton(JPanel itemPanel, Food food, int quantity) {
        //stub
    }



}
