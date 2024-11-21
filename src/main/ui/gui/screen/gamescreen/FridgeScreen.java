package ui.gui.screen.gamescreen;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.*;

import model.pet.Pet;
import model.supplies.Food;
import model.supplies.Fridge;
import ui.gui.App;
import ui.gui.PetGameApp;
import ui.gui.screen.GameScreen;
import ui.gui.screen.customizedcomponent.CustomScrollBarUI;
import ui.gui.screen.customizedcomponent.RoundedButton;
import ui.gui.screen.customizedcomponent.RoundedPanel;

public class FridgeScreen extends GameScreen {
    private Fridge fridge;

    // Fridge Screen to choose food from
    public FridgeScreen(PetGameApp petGameApp) {
        super(petGameApp, "Fridge");
        this.fridge = app.getGame().getFridge();

        addScrollPane();
        createMenuButton();
    }

    // MODIFIES: this
    // EFFECTS: adding a scroll pane with Food Pane
    private void addScrollPane() {
        JPanel statusPanel = initFoodPanel();
        statusPanel.setBackground(App.BACKGROUND_BLUE);

        JScrollPane scrollPane = new JScrollPane(statusPanel);

        JScrollBar verticalBar = scrollPane.getVerticalScrollBar();
        verticalBar.setPreferredSize(new Dimension(10, 0));
        verticalBar.setUI(new CustomScrollBarUI());
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(scrollPane, BorderLayout.CENTER);
    }

    // EFFECTS: return JPanel with Panel for each Food
    private JPanel initFoodPanel() {
        Map<Food, Integer> foods = fridge.getFood();
        int i = (foods.size() <= 5) ? 5 : foods.size();
        JPanel buttonPanel = new JPanel(new GridLayout(i, 1, 10, 10));
        buttonPanel.setBackground(App.BACKGROUND_BLUE);
        buttonPanel.setBorder(new EmptyBorder(24, 24, 24, 24));

        for (Map.Entry<Food, Integer> entry : foods.entrySet()) {
            Food food = entry.getKey();
            int quantity = entry.getValue();
            for (int j = 0; j < quantity; j++) {
                addFoodPanel(buttonPanel, food);
            }
        }
        return buttonPanel;
    }

    // MODIFIES: this
    // EFFECTS: adding Foods panel with status, and Feed button for each Fod in
    // fridge
    private void addFoodPanel(JPanel jpanel, Food food) {
        JPanel smallPanel = new RoundedPanel(40, App.SUB_YELLOW, App.MAIN_YELLOW);
        smallPanel.setLayout(new GridLayout(3, 1, 10, 0));
        smallPanel.setBorder(new EmptyBorder(20, 40, 20, 40));

        String name = food.getName();
        int nutriVal = food.getNutritionValue();

        smallPanel.add(new JLabel(name));
        smallPanel.add(new JLabel("Hunger Point: " + nutriVal));

        JButton button = new RoundedButton(28, App.SUB_PINK,
                App.MAIN_PINK, App.TEXT_PINK, "Feed");
        button.setPreferredSize(new Dimension(80, 40));
        button.addActionListener(e -> feedPet(food));
        smallPanel.add(button);
        jpanel.add(smallPanel);
    }

    // MODIFIES: this
    // EFFECTS: feed Pet and update game state accordingly
    private void feedPet(Food food) {
        Pet pet = super.app.getPet();
        pet.feed(food);
        fridge.removeFood(food);
        JOptionPane.showMessageDialog(this, pet.getName() + " loves " + food.getName());
        reloadScreens("GameMenuScreen");
    }

}
