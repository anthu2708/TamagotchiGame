package model.pet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MeomoTest {
    
    private Meomo meomo;

    @BeforeEach
    void runBefore() {
        meomo = new Meomo("Mittens");
    }

    @Test
    void testInitialValues() {
        assertEquals("Mittens", meomo.getName());
        assertEquals("Meomo", meomo.getType());
        assertEquals(50, meomo.getHunger());
        assertEquals(50, meomo.getHappiness());
        assertEquals(100, meomo.getCleanliness());
        assertEquals(100, meomo.getHealth());
    }

    @Test
    void testPlay() {
        meomo.play();
        assertEquals(55, meomo.getHappiness());
        assertEquals(45, meomo.getHunger()); // 50 - 5
        assertEquals(95, meomo.getCleanliness()); // 100 - 5

        if (meomo.getHealth() == 15) {
            assertEquals(15, meomo.getHealth());  
        } else {
            assertEquals(100, meomo.getHealth()); 
        }
    }

    @Test
    void testPlayHappinessMax() {
        meomo.setHappiness(99);
        meomo.play();
        assertEquals(100, meomo.getHappiness()); 
    }

    @Test
    void testPet() {
        meomo.pet();
        assertEquals(60,meomo.getHappiness());
        assertEquals(40, meomo.getHunger()); // 50 - 10
    }

    @Test
    void testPetHappinessMax() {
        meomo.setHappiness(90);
        meomo.pet();
        assertEquals(100, meomo.getHappiness()); // Happiness should cap at 100
    }

    @Test
    void testRandomlyInjured() {
        int consecutiveInjuries = 0;
        int maxConsecutiveInjuries = 0;
        int consecutiveNonInjuries = 0;
        int maxConsecutiveNonInjuries = 0;

        for (int i = 0; i < 100; i++) {
            meomo.randomlyInjured(); // Call the method
            boolean isInjured = (meomo.getHealth() == 15); // Check injured state after

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



