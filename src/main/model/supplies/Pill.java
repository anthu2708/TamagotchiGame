package model.supplies;


/**
 * Represents a Pill with attributes such as name, cost, nutrition value, health value, and happiness value.
 */
public class Pill {
    private String name;
    private int cost;
    private int nutritionValue;
    private int healthValue;
    private int happinessValue;

    // Constructor to initialize all values
    public Pill(String name, int cost, int nutritionValue, int healthValue, int happinessValue) {
        this.name = name;
        this.cost = cost;
        this.nutritionValue = nutritionValue;
        this.healthValue = healthValue;
        this.happinessValue = happinessValue;
    }

    // EFFECTS: Returns a string representing the pill's content
    public String getContent() {
        return "Nutrition Value - " + getNutrition() 
            + ", Health Value - " + getHealth() 
            + ", Happiness Value - " + getHappiness();
    }

    // Getters for each attribute
    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getNutrition() {
        return nutritionValue;
    }

    public int getHealth() {
        return healthValue;
    }

    public int getHappiness() {
        return happinessValue;
    }
}
