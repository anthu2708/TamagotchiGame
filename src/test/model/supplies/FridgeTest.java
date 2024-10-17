package model.supplies;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

class FridgeTest {
    private Fridge fridge;
    private Food apple;
    private Food banana;

    @BeforeEach
    void setUp() {
        fridge = new Fridge();
        apple = new Food("Apple", 10, 2);
        banana = new Food("Banana", 8, 1);
    }

    @Test
    void testConstructor() {
        assertTrue(fridge.isEmpty(), "Fridge should be empty initially.");
    }

    @Test
    void testAddFood() {
        fridge.addFood(apple);
        assertEquals("1. Apple (Quantity: 1, Hunger Points: 10)\n", fridge.viewFood());
    }

    @Test
    void testAddFoodMultipleTimes() {
        fridge.addFood(apple);
        fridge.addFood(apple); 
        fridge.addFood(banana);
        Map<Food, Integer> answer = new LinkedHashMap<>();
        answer.put(apple,2);
        answer.put(banana,1);
        assertEquals(answer, fridge.getFood());
        
    }

    @Test
    void testRemoveFood() {
        fridge.addFood(apple);
        fridge.addFood(banana);
        fridge.removeFood(apple);
        Map<Food, Integer> answer = new LinkedHashMap<>();
        answer.put(banana,1);
        assertEquals(answer, fridge.getFood());
    }

    @Test
    void testRemoveFoodNotContain() {
        fridge.addFood(banana);
        fridge.removeFood(apple);
        Map<Food, Integer> answer = new LinkedHashMap<>();
        answer.put(banana,1);
        assertEquals(answer, fridge.getFood());
    }

    @Test
    void testRemoveFoodQuantity() {
        fridge.addFood(apple);
        fridge.addFood(apple);
        fridge.removeFood(apple); 
        Map<Food, Integer> answer = new LinkedHashMap<>();
        answer.put(apple,1);
        assertEquals(answer, fridge.getFood());
    }

    @Test
    void testGetFoodByIndex() {
        fridge.addFood(apple);
        fridge.addFood(banana);
        assertEquals(apple, fridge.getFoodByIndex(1));
        assertEquals(banana, fridge.getFoodByIndex(2));
        assertEquals(null, fridge.getFoodByIndex(3));
    }

    @Test
    void testGetQuantityByIndex() {
        fridge.addFood(apple);
        fridge.addFood(apple);
        fridge.addFood(banana);
        assertEquals(2, fridge.getQuantityByIndex(1));
        assertEquals(1, fridge.getQuantityByIndex(2));
        assertEquals(-1, fridge.getQuantityByIndex(3));
    }

    @Test
    void testIsEmpty() {
        assertTrue(fridge.isEmpty());
        fridge.addFood(apple);
        assertFalse(fridge.isEmpty());
    }

    @Test
    void testFoodGetter() {
        fridge.addFood(apple);
        fridge.addFood(apple);
        Map<Food, Integer> answer = new HashMap<>();
        answer.put(apple,2);
        assertEquals(answer, fridge.getFood());

    }

    @Test
    void testToJson() {
        Food steak = new Food("Steak", 40, 20);
        Food apple = new Food("Apple", 5, 2);
        fridge.addFood(steak);
        fridge.addFood(apple);
        fridge.addFood(steak);
        JSONObject expectedJson = new JSONObject();
        JSONArray foodItemsArray = new JSONArray();
        foodItemsArray.put(new JSONObject()
                .put("name", "Steak")
                .put("nutritionValue", 40)
                .put("cost", 20)
                .put("quantity", 2));
        foodItemsArray.put(new JSONObject()
                .put("name", "Apple")
                .put("nutritionValue", 5)
                .put("cost", 2)
                .put("quantity", 1));
        expectedJson.put("foodItems", foodItemsArray);

        JSONObject actualJson = fridge.toJson();
        assertEquals(expectedJson.toString(), actualJson.toString());
    }


}
