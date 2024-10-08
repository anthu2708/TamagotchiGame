package model.pet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MeapTest {

    private Meap meap;

    @BeforeEach
    void runBefore() {
        meap = new Meap("Fluffy");
    }

    @Test
    void testInitialValues() {
        assertEquals("Fluffy", meap.getName());
        assertEquals("Meap", meap.getType());
        assertEquals(50, meap.getHunger());        // Assuming default hunger starts at 50
        assertEquals(50, meap.getHappiness());     // Assuming default happiness starts at 50
        assertEquals(100, meap.getCleanliness());  // Assuming default cleanliness is 100
        assertEquals(100, meap.getHealth());       // Assuming default health is 100
    }

    @Test
    void testPlay() {
        meap.play();
        assertEquals(65, meap.getHappiness()); // 50 + 15 = 65
        assertEquals(40, meap.getHunger());    // 50 - 10 = 40
        assertEquals(80, meap.getCleanliness()); // 100 - 20 = 80

        if (meap.getHealth() == 15) {
            assertEquals(15, meap.getHealth());  
        } else {
            assertEquals(100, meap.getHealth()); 
        }
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
        int consecutiveInjuries = 0;
        int maxConsecutiveInjuries = 0;
        int consecutiveNonInjuries = 0;
        int maxConsecutiveNonInjuries = 0;

        for (int i = 0; i < 100; i++) {
            meap.randomlyInjured(); // Call the method
            boolean isInjured = (meap.getHealth() == 15); // Check injured state after

            if (isInjured) {
                consecutiveInjuries++;
                maxConsecutiveInjuries = Math.max(maxConsecutiveInjuries, consecutiveInjuries);
                consecutiveNonInjuries = 0;
            } else {
                consecutiveNonInjuries++;
                maxConsecutiveNonInjuries = Math.max(maxConsecutiveNonInjuries, consecutiveNonInjuries);
                consecutiveInjuries = 0;
            }
        }

        assertTrue(maxConsecutiveInjuries < 10);
        assertTrue(maxConsecutiveNonInjuries < 10);
    }

}
