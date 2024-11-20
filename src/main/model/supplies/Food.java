package model.supplies;

/**
 * Represents a food item with a name, nutrition value, and cost.
 */

public class Food {
    private String name;
    private int nutritionValue;
    private int cost;

    // Constructor
    public Food(String name, int nutritionValue, int cost) {
        this.name = name;
        this.nutritionValue = nutritionValue;
        this.cost = cost;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getNutritionValue() {
        return nutritionValue;
    }

    public int getCost() {
        return cost;
    }

}
