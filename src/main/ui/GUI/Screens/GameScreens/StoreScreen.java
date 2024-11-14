package ui.GUI.Screens.GameScreens;

import model.CoinManager;
import model.Store;
import model.supplies.Food;
import model.supplies.Pill;
import ui.GUI.PetGameApp;
import ui.GUI.Screens.GameScreen;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StoreScreen extends GameScreen {
    private Store store;
    private CoinManager coinManager;

    // Fridge Screen to choose food from
    public StoreScreen(PetGameApp petGameApp) {
        super(petGameApp, "Store");

        this.store = new Store();
        this.coinManager = game.getCoinManager();

        addScrollPane();
        createMenuButton();
    }

    // MODIFIES: this
    // EFFECTS: adding a scroll pane with Pet Button to go to Pet Game
    private void addScrollPane() {
        JPanel storePanel = getStorePanel();

        JScrollPane scrollPane = new JScrollPane(storePanel); // Set buttonPanel as viewport view
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);
    }

    // MODIFIES: this 
    // EFFECTS: generate and add Store Panel with coin 
    //          and PillPanel all Pills to choose from
    private JPanel getStorePanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        List<Food> foods = store.getFood();
        List<Pill> pills = store.getPill();

        JPanel coinPanel = new JPanel();
        coinPanel.add(new JLabel("Coin: " + coinManager.getValue()));
        mainPanel.add(coinPanel);

        JPanel itemPanel = new JPanel();
        itemPanel.setBorder(new EmptyBorder(20, 20, 0, 20));
        itemPanel.setLayout(new GridLayout(foods.size() + pills.size(), 1, 0, 0));

        for (Food food : foods) {
            getFoodButton(itemPanel, food);
        }

        for (Pill pill : pills) {
            getPillButton(itemPanel, pill);
        }

        mainPanel.add(itemPanel);
        add(mainPanel);
        return itemPanel;
    }

    // MODIFIES: this 
    // EFFECTS: generate and add a Food Panel
    //          when purchase Food button is pressed,
    //          if not enough coin, throw message
    //          else, add to Fridge and go back to menu
    private void getFoodButton(JPanel mainPanel, Food food) {
        JPanel smallPanel = new JPanel();

        String name = food.getName();
        int nutriVal = food.getNutritionValue();
        int cost = food.getCost();

        smallPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        smallPanel.setLayout(new GridLayout(4, 1, 0, 0));
        smallPanel.add(new JLabel(name));
        smallPanel.add(new JLabel("Hunger Point: " + nutriVal));
        smallPanel.add(new JLabel("Cost: " + cost));

        JButton button = new JButton("Purchase");
        button.addActionListener(e -> handlePurchase(food, cost, "Food"));
        smallPanel.add(button);
        mainPanel.add(smallPanel);
    }

    // MODIFIES: this 
    // EFFECTS: generate and add a Pill Panel
    //          when purchase Pill button is pressed,
    //          if not enough coin, throw message
    //          else, add to MedBox and go back to menu
    private void getPillButton(JPanel mainPanel, Pill pill) {
        JPanel smallPanel = new JPanel();
        String name = pill.getName();
        int nutriVal = pill.getNutrition();
        int healthVal = pill.getHealth();
        int happinessValue = pill.getHappiness();
        int cost = pill.getCost();

        smallPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        smallPanel.setLayout(new GridLayout(6, 1, 10, 0));
        smallPanel.add(new JLabel(name));
        smallPanel.add(new JLabel("Hunger Point: " + nutriVal));
        smallPanel.add(new JLabel("Health Point: " + healthVal));
        smallPanel.add(new JLabel("Happiness Point: " + happinessValue));
        smallPanel.add(new JLabel("Cost: " + cost));

        JButton button = new JButton("Purchase");
        button.addActionListener(e -> handlePurchase(pill, cost, "Pill"));
        smallPanel.add(button);
        mainPanel.add(smallPanel);
    }

    // MODIFIES: this 
    // EFFECTS: handle purchase:
    //          if not enough coin, throw message
    //          else, add to MedBox and go back to menu
    private void handlePurchase(Object item, int cost, String itemType) {
        int coin = coinManager.getValue();
        if (coin < cost) {
            JOptionPane.showMessageDialog(this, "Insufficient funds!");
        } else {
            if (itemType.equals("Food")) {
                game.getFridge().addFood((Food) item);
            } else if (itemType.equals("Pill")) {
                game.getMedicineBox().addPill((Pill) item);
            }

            coinManager.subtract(cost);
            super.app.initScreens();
            JOptionPane.showMessageDialog(this,
                    "Purchased 1 " + ((item instanceof Food) ? ((Food) item).getName() : ((Pill) item).getName())
                            + ". Remaining coins: " + coinManager.getValue());
            super.app.showScreen("GameMenuScreen");
        }
    }


    

}
