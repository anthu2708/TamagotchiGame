package model.pet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.supplies.Food;

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
    void testPlayIncreasesHappiness() {
        int initialHappiness = meap.getHappiness();
        meap.play();
        assertTrue(meap.getHappiness() > initialHappiness);
        assertEquals(40, meap.getHunger()); // 50 - 10
        assertTrue(meap.getCleanliness() < 100); // Cleanliness should decrease
    }

    @Test
    void testPetIncreasesHappiness() {
        int initialHappiness = meap.getHappiness();
        meap.pet();
        assertTrue(meap.getHappiness() > initialHappiness);
        assertEquals(40, meap.getHunger()); // 50 - 10
        assertTrue(meap.getCleanliness() < 100); // Cleanliness should decrease
    }

    @Test
    void testFeedIncreasesHunger() {
        Food food = new Food("Dog Food", 30, 0); // Assuming Food has a constructor and a getNutritionValue method
        meap.feed(food);
        assertEquals(80, meap.getHunger()); // 50 + 30
    }

    @Test
    void testInjuredHealthDecrease() {
        meap.dropHealth(); // Should drop health to 95
        meap.injuried();   // Should drop health to 15 if it's above 15
        assertEquals(15, meap.getHealth());
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
        assertTrue(attention[1]); // Cleanliness should not need attention
        assertFalse(attention[2]); // Happiness should not need attention
    }

    @Test
    void testNeedsPill() {
        meap.dropHealth(); // Decrease health
        meap.dropHealth(); // Decrease health
        assertTrue(meap.needsPill());
    }
}
