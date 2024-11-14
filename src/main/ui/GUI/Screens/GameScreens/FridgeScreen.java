package ui.GUI.Screens.GameScreens;

import javax.swing.*;
import java.awt.GridLayout;
import java.util.*;

import model.pet.Pet;
import model.supplies.Food;
import model.supplies.Fridge;
import ui.GUI.PetGameApp;
import ui.GUI.Screens.GameScreen;

public class FridgeScreen extends GameScreen {
    private PetGameApp app;
    private Fridge fridge;
    // Fridge Screen to choose food from
    public FridgeScreen(PetGameApp petGameApp) {
        super(petGameApp, "Fridge");
        this.app = super.app;
        this.fridge = app.getGame().getFridge();

        getFoodPanel();
        createMenuButton();
    }

    // MODIFIES: this
    // EFECTS: Add item Panel with Food Panels
    private void getFoodPanel() {
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new GridLayout(3, 1, 10, 10));

        Map<Food, Integer> foods = fridge.getFood();
        for (Map.Entry<Food, Integer> entry : foods.entrySet()) {
            Food food = entry.getKey();
            int quantity = entry.getValue();
            getFoodButton(itemPanel, food, quantity);
        }
        add(itemPanel);
    }

    // MODIFIES: this 
    // EFFECTS: Add Food Panel for each food to itemPanel
    private void getFoodButton(JPanel itemPanel, Food food, int quantity) {
        JPanel smallPanel = new JPanel();

        String name = food.getName();
        int nutriVal = food.getNutritionValue();

        smallPanel.setLayout(new GridLayout(4, 1, 10, 10));
        smallPanel.add(new JLabel(name));
        smallPanel.add(new JLabel("Hunger Point: " + nutriVal));
        smallPanel.add(new JLabel("Quantity: " + quantity));

        JButton button = new JButton("Feed");
        button.addActionListener(e -> feedPet(food));
        smallPanel.add(button);
        smallPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        itemPanel.add(smallPanel);
    }

    // MODIFIES: this
    // EFFECTS: feed Pet and update game state accordingly
    private void feedPet(Food food) {
        Pet pet = super.app.getPet();
        pet.feed(food);
        fridge.removeFood(food);
        reloadScreens("GameMenuScreen");
    }



}
