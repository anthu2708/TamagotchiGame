package model;

import java.util.*;

import model.supplies.Food;
import model.supplies.Fridge;
import model.supplies.MedicineBox;
import model.supplies.Pill;

/**
 * The Store class represents a shop where various food items and pills are
 * available for purchase.
 * It provides methods to display available items, purchase food and pills, and
 * manage inventory.
 */

public class Store {
    private List<Food> availableFood;
    private List<Pill> availablePills;

    // Constructor: constructs a Store instance with sample food and pill items.
    public Store() {
        availableFood = new ArrayList<>();
        availablePills = new ArrayList<>();

        // Sample Food Items
        availableFood.add(new Food("Reeze'z", 10, 5));
        availableFood.add(new Food("Tonkatsu", 20, 10));
        availableFood.add(new Food("Banh mi", 30, 10));
        availableFood.add(new Food("Bun bo Hue", 35, 20));

        // Sample Pill Items
        availablePills.add(new Pill("Basic Pill", 30, 5, 20, 0));
        availablePills.add(new Pill("Super Health Pill", 50, 0, 50, 5));
    }

    // EFFECTS: show available items in the store with details
    public String displayAvailItems() {
        StringBuilder sb = new StringBuilder();
        int index = 1;

        sb.append("--- Food --- \n");
        for (Food food : availableFood) {
            sb.append(index++)
                    .append(". ")
                    .append(food.getName())
                    .append(" (Hunger Points: ")
                    .append(food.getNutritionValue())
                    .append(", Cost: ")
                    .append(food.getCost() + ") \n");
        }

        sb.append("\n --- Pills --- \n");
        for (Pill pill : availablePills) {
            sb.append(index++)
                    .append(". ")
                    .append(pill.getName())
                    .append(" (Content: ")
                    .append(pill.getContent())
                    .append(", Cost: ")
                    .append(pill.getCost() + ") \n");
        }
        return sb.toString();
    }

    // MODIFIES: fridge
    // EFFECTS: purchase Food and put it in the specified fridge
    public Food purchaseFood(int index, Fridge fridge, CoinManager coinManager) {
        if (index > 0 && index <= availableFood.size()) {
            Food selectedFood = availableFood.get(index - 1);
            if (coinManager.getValue() >= selectedFood.getCost()) {
                fridge.addFood(selectedFood);
                return selectedFood;
            }
        }
        return null; // Not enough coins or invalid selection
    }

    // MODIFIES: medicineBox
    // EFFECTS: purchase Pill and put it in the medicine box
    public Pill purchasePill(int index, MedicineBox medicineBox, CoinManager coinManager) {
        if (index > 0 && index <= availablePills.size()) {
            Pill selectedPill = availablePills.get(index - 1);
            if (coinManager.getValue() >= selectedPill.getCost()) {
                medicineBox.addPill(selectedPill);
                return selectedPill;
            }
        }
        return null;
    }

    // Getters
    public List<Food> getFood() {
        return availableFood;
    }

    public List<Pill> getPill() {
        return availablePills;
    }

    // EFFECTS: Returns the total number of available items (food and pills) in the
    // store.
    public int getNumItems() {
        return availableFood.size() + availablePills.size();
    }

}
