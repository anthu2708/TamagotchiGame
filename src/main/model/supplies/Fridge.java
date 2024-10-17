package model.supplies;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Represents a fridge that stores various food items.
 * Each food item can have multiple quantities stored.
 */
public class Fridge {
    private Map<Food, Integer> foodItems;

    // Constructor
    public Fridge() {
        foodItems = new LinkedHashMap<>();
    }

    // EFFECTS: check if the fridge is empty
    public boolean isEmpty() {
        return foodItems.isEmpty();
    }

    // MODIFIES: this
    // EFFECTS: add food to the fridge
    public void addFood(Food food) {
        foodItems.put(food, foodItems.getOrDefault(food, 0) + 1);
    }

    // REQUIRES: the fridge is not empty
    // MODIFIES: this
    // EFFECTS: remove specific food with quantity from the fridge
    public void removeFood(Food food) {
        if (foodItems.containsKey(food)) {
            int remaining = foodItems.get(food) - 1;
            if (remaining > 0) {
                foodItems.put(food, remaining);
            } else {
                foodItems.remove(food);
            }
        }
    }

    // REQUIRES: the fridge is not empty
    // EFFECTS: return a list of food in the fridge with index and details
    public String viewFood() {
        StringBuilder sb = new StringBuilder();
        int index = 1;
        for (Map.Entry<Food, Integer> entry : foodItems.entrySet()) {
            Food food = entry.getKey();
            sb.append(index++)
                    .append(". ")
                    .append(food.getName())
                    .append(" (Quantity: ")
                    .append(entry.getValue())
                    .append(", Hunger Points: ")
                    .append(food.getNutritionValue())
                    .append(")\n");
        }
        return sb.toString();
    }

    // REQUIRES: the fridge is not empty
    // EFFECTS: returns a specific food item by index
    public Food getFoodByIndex(int index) {
        int i = 1;
        for (Food food : foodItems.keySet()) {
            if (i == index) {
                return food;
            }
            i++;
        }
        return null;
    }

    // REQUIRES: the fridge is not empty
    // EFFECTS: returns a specific food item's quantity by index
    // return -1 if fail (index not in the list)
    public int getQuantityByIndex(int index) {
        int i = 1;
        for (Map.Entry<Food, Integer> entry : foodItems.entrySet()) {
            if (i == index) {
                return entry.getValue();
            }
            i++;
        }
        return -1;
    }

    // getters
    public Map<Food, Integer> getFood() {
        return foodItems;
    }

    // EFFECTS: return object as a JSON Object
    public JSONObject toJson() {
        JSONObject fridgeJson = new JSONObject();
        JSONArray foods = new JSONArray();
        for (Map.Entry<Food, Integer> entry : foodItems.entrySet()) {
            Food food = entry.getKey();
            int quantity = entry.getValue();
            JSONObject jsonFood = new JSONObject()
                        .put("name", food.getName())
                        .put("nutritionValue", food.getNutritionValue())
                        .put("cost", food.getCost())
                        .put("quantity", quantity);
            foods.put(jsonFood);
        }
        fridgeJson.put("foodItems", foods);
        return fridgeJson;
    }
}
