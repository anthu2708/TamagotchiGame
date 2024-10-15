package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import model.pet.Pet;
import model.supplies.Food;
import model.supplies.Pill;

public abstract class JsonTest {

    // EFFECTS: compare Pill objects
    protected void checkPill(String name, int cost, int nutritionValue, 
            int healthValue, int happinessValue, Pill pill) {
        assertEquals(name, pill.getName());
        assertEquals(cost, pill.getCost());
        assertEquals(nutritionValue, pill.getNutrition());
        assertEquals(healthValue, pill.getHealth());
        assertEquals(happinessValue, pill.getHappiness());
    }

    // EFFECTS: compare FOOD objects
    protected void checkFood(String name, int nutritionValue, int cost, Food food) {
        assertEquals(name, food.getName());
        assertEquals(nutritionValue, food.getNutritionValue());
        assertEquals(cost, food.getCost());
    }

    // EFFECTS: compare Pet objects
    protected void checkPet(String name, int hunger, int happiness, 
            int cleanliness, int health, String type, boolean isInjured, Pet pet) {
        assertEquals(name, pet.getName());
        assertEquals(hunger, pet.getHunger());
        assertEquals(happiness, pet.getHappiness());
        assertEquals(cleanliness, pet.getCleanliness());
        assertEquals(health, pet.getHealth());
        assertEquals(type, pet.getType());
        assertEquals(isInjured, pet.needsPill());
    }
}
