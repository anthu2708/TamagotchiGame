package model.supplies;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

class FoodTest {
    private Food food;

    @BeforeEach
    void runBefore() {
        food = new Food("Apple", 95, 1);
    }

    @Test
    void testFoodConstructor() {
        assertEquals("Apple", food.getName());
        assertEquals(95, food.getNutritionValue());
        assertEquals(1, food.getCost());
    }
}