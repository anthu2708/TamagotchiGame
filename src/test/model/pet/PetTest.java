package model.pet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.supplies.Food;
import model.supplies.Pill;

public class PetTest {
    private Meap meap;
    private Meomo meomo;
    private Donukie donukie;

    @BeforeEach
    void runBefore() {
        meap = new Meap("Fluffy");
        meomo = new Meomo("Mittens");
        donukie = new Donukie("Skibidi");
    }

    @Test
    void testMeomoConstructor() {
        assertEquals("Mittens", meomo.getName());
        assertEquals("Meomo", meomo.getType());
        assertEquals(50, meomo.getHunger());
        assertEquals(50, meomo.getHappiness());
        assertEquals(100, meomo.getCleanliness());
        assertEquals(100, meomo.getHealth());
    }

    @Test
    void testDOnukieConstructor() {
        assertEquals("Skibidi", donukie.getName());
        assertEquals("Donukie", donukie.getType());
        assertEquals(50, donukie.getHunger());
        assertEquals(50, donukie.getHappiness());
        assertEquals(100, donukie.getCleanliness());
        assertEquals(100, donukie.getHealth());
    }

    @Test
    void testMeapConstructor() {
        assertEquals("Fluffy", meap.getName());
        assertEquals("Meap", meap.getType());
        assertEquals(50, meap.getHunger());
        assertEquals(50, meap.getHappiness());
        assertEquals(100, meap.getCleanliness());
        assertEquals(100, meap.getHealth());
    }

    @Test
    void testFeedIncreasesHunger() {
        Food food = new Food("Dog Food", 30, 0); // Assuming Food has a constructor and a getNutritionValue method
        meap.feed(food);
        assertEquals(80, meap.getHunger()); // 50 + 30
    }

    @Test
    void testCleanRestoresCleanliness() {
        meap.clean();
        assertEquals(100, meap.getCleanliness());
    }

    @Test
    void testNeedsAttentionHunger() {
        meap.setHunger(15);
        boolean[] attention = meap.needsAttention();
        assertTrue(attention[0]); // Hunger should need attention (set hunger to 10)
        assertFalse(attention[1]); // Cleanliness should not need attention
        assertFalse(attention[2]); // Happiness should not need attention
    }

    @Test
    void testNeedsAttentionCleanliness() {
        meap.setCleanliness(10);
        boolean[] attention = meap.needsAttention();
        assertFalse(attention[0]); // Hunger should need attention (set hunger to 10)
        assertTrue(attention[1]); // Cleanliness should not need attention
        assertFalse(attention[2]); // Happiness should not need attention
    }

    @Test
    void testNeedsAttentionHappiness() {
        meap.setHappiness(10);

        boolean[] attention = meap.needsAttention();
        assertFalse(attention[0]); // Hunger should need attention (set hunger to 10)
        assertFalse(attention[1]); // Cleanliness should not need attention
        assertTrue(attention[2]); // Happiness should not need attention
    }

    @Test
    void testNeedsPill() {
        meap.setHealth(19); 
        assertTrue(meap.needsPill());
    }

    @Test
    void testFeedMax() {
        meap.setHunger(95); 
        meap.feed(new Food("testFood", 30, 0));
        assertEquals(100, meap.getHunger());
    }

    @Test
    void testInjured() {
        meap.injuried();
        assertEquals(15, meap.getHealth());
    }

    @Test
    void testAlreadyInjured() {
        meap.setHealth(10);
        meap.injuried();
        assertEquals(10, meap.getHealth());
    }


    @Test
    void testUsePill() {
        meap.setHunger(30);
        meap.setHealth(40);
        meap.setHappiness(80);
        meap.usePill(new Pill("testPill", 0, 20, 10, 10));
        assertEquals(50, meap.getHunger());
        assertEquals(50, meap.getHealth());
        assertEquals(90, meap.getHappiness());
    }

    @Test
    void testUsePillMax() {
        meap.setHunger(100);
        meap.setHealth(95);
        meap.setHappiness(90);
        meap.usePill(new Pill("testPill", 0, 20, 10, 10));
        assertEquals(100, meap.getHunger());
        assertEquals(100, meap.getHealth());
        assertEquals(100, meap.getHappiness());
    }

    @Test
    void testNeedPillTrue() {
        meap.setHealth(10);
        assertTrue(meap.needsPill());
    }

    @Test
    void testNeedPillFalse() {
        assertFalse(meap.needsPill());
    }

    @Test
    void testGetStatus() {
        String returnString =  "Pet Fluffy - Pet Type: Meap\n"
                    + "Hunger: 50\n"
                    + "Happiness: 50\n"
                    + "Health: 100\n"
                    + "Cleanliness: 100";
        assertEquals(returnString, meap.getStatus());
    }

    @Test
    void testToJson() {
        // Init
        Pet pet = new Meap("Buddy");
        pet.setHunger(30);
        pet.setHappiness(70);
        pet.setHealth(80);
        pet.setCleanliness(90);

        JSONObject json = pet.toJson();

        assertEquals("Buddy", json.getString("name"));
        assertEquals("Meap", json.getString("type"));
        assertEquals(30, json.getInt("hunger"));
        assertEquals(70, json.getInt("happiness"));
        assertEquals(80, json.getInt("health"));
        assertEquals(90, json.getInt("cleanliness"));
        assertEquals(false, json.getBoolean("isInjured"));
    }
}

