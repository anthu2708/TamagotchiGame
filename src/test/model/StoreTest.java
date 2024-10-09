package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import model.supplies.Food;
import model.supplies.Fridge;
import model.supplies.MedicineBox;
import model.supplies.Pill;

import static org.junit.jupiter.api.Assertions.*;

class StoreTest {
    private Store store;
    private Fridge fridge;
    private MedicineBox medicineBox;
    private CoinManager coinManager;

    @BeforeEach
    void setUp() {
        store = new Store();
        fridge = new Fridge();
        medicineBox = new MedicineBox();
        coinManager = new CoinManager(100);
    }

    @Test
    void testDisplayAvailableItems() {
        String displayedItems = store.displayAvailItems();

        assertTrue(displayedItems.contains("Reeze'z"));
        assertTrue(displayedItems.contains("Tonkatsu"));
        assertTrue(displayedItems.contains("Banh mi"));
        assertTrue(displayedItems.contains("Bun bo Hue"));
        assertTrue(displayedItems.contains("Basic Pill"));
        assertTrue(displayedItems.contains("Super Health Pill"));
    }

    @Test
    void testPurchaseFoodSuccessful() {
        Food purchasedFood = store.purchaseFood(1, fridge, coinManager);
        assertEquals("Reeze'z", purchasedFood.getName());
        assertEquals(1, fridge.getFood().size());
    }

    @Test
    void testPurchaseFoodNotEnoughCoins() {
        coinManager.subtract(100); 
        store.purchaseFood(1, fridge, coinManager); 
        assertEquals(0, fridge.getFood().size());
    }

    @Test
    void testPurchasePillSuccessful() {
        Pill purchasedPill = store.purchasePill(1, medicineBox, coinManager); 
        assertEquals("Basic Pill", purchasedPill.getName());
        assertEquals(1, medicineBox.getPill().size());
    }

    @Test
    void testPurchasePillNotEnoughCoins() {
        coinManager.subtract(100); 
        store.purchasePill(1, medicineBox, coinManager);
        assertEquals(0, medicineBox.getPill().size());
    }

    @Test
    void testPurchaseFoodInvalidIndex() {
        store.purchaseFood(5, fridge, coinManager); 
        assertEquals(0, fridge.getFood().size());
        store.purchaseFood(-1, fridge, coinManager); 
        assertEquals(0, fridge.getFood().size());
    }


    @Test
    void testPurchasePillInvalidIndex() {
        store.purchasePill(3, medicineBox, coinManager); 
        assertEquals(0, medicineBox.getPill().size());
        store.purchasePill(-1, medicineBox, coinManager); 
        assertEquals(0, medicineBox.getPill().size());
    }

    @Test
    void testGetNumItems() {
        int numItems = store.getNumItems();
        assertEquals(6, numItems);
    }

    @Test
    void testConstructorFoodList() {
        List<Food> foodList = store.getFood();
        assertEquals(4, foodList.size());
        
        assertEquals("Reeze'z", foodList.get(0).getName());
        assertEquals(10, foodList.get(0).getNutritionValue());
        assertEquals(5, foodList.get(0).getCost());
    
        assertEquals("Tonkatsu", foodList.get(1).getName());
        assertEquals(20, foodList.get(1).getNutritionValue());
        assertEquals(10, foodList.get(1).getCost());
    
        assertEquals("Banh mi", foodList.get(2).getName());
        assertEquals(30, foodList.get(2).getNutritionValue());
        assertEquals(10, foodList.get(2).getCost());
    
        assertEquals("Bun bo Hue", foodList.get(3).getName());
        assertEquals(35, foodList.get(3).getNutritionValue());
        assertEquals(20, foodList.get(3).getCost());
    }
    
    @Test
    void testConstructorPillList() {
        List<Pill> pillList = store.getPill();
        assertEquals(2, pillList.size());
    
        assertEquals("Basic Pill", pillList.get(0).getName());
        assertEquals(30, pillList.get(0).getCost());
    
        assertEquals("Super Health Pill", pillList.get(1).getName());
        assertEquals(50, pillList.get(1).getCost());
    }
    
}
