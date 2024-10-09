package model.pet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class MeapTest {

    private Meap meap;

    @BeforeEach
    void runBefore() {
        meap = new Meap("Fluffy");
    }

    @Test
    void testConstructor() {
        assertEquals("Fluffy", meap.getName());
        assertEquals("Meap", meap.getType());
        assertEquals(50, meap.getHunger());        // Assuming default hunger starts at 50
        assertEquals(50, meap.getHappiness());     // Assuming default happiness starts at 50
        assertEquals(100, meap.getCleanliness());  // Assuming default cleanliness is 100
        assertEquals(100, meap.getHealth());       // Assuming default health is 100
    }

    @Test
    void testPlayNotInjured() {
        meap.play();
        assertEquals(65, meap.getHappiness()); // 50 + 15 = 65
        assertEquals(40, meap.getHunger());    // 50 - 10 = 40
        assertEquals(80, meap.getCleanliness()); // 100 - 20 = 80
    }

    @Test
    void testPlayHappinessMax() {
        meap.setHappiness(95);
        meap.play();
        assertEquals(100, meap.getHappiness()); 
    }

    @Test
    void testPet() {
        meap.pet();
        assertEquals(70, meap.getHappiness()); // 50 + 20 = 70
        assertEquals(40, meap.getHunger());    // 50 - 10 = 40
        assertEquals(80, meap.getCleanliness()); // 100 - 20 = 80
    }

    @Test
    void testPetHappinessMax() {
        meap.setHappiness(95);
        meap.pet();
        assertEquals(100, meap.getHappiness()); // Happiness should cap at 100
    }

    @Test
    void testRandomlyInjured() {
        ArrayList<Boolean> isInjuriedList = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            isInjuriedList.add(meap.randomlyInjured());
        }

        assertTrue(isInjuriedList.contains(true));
        assertTrue(isInjuriedList.contains(false));
    }

}
