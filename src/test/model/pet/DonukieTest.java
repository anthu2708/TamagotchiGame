package model.pet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class DonukieTest {
    
    private Donukie donukie;

    @BeforeEach
    void runBefore() {
        donukie = new Donukie("Fluffy");
    }

    @Test
    void testInitialValues() {
        assertEquals("Fluffy", donukie.getName());
        assertEquals("Donukie", donukie.getType());
        assertEquals(50, donukie.getHunger());
        assertEquals(50, donukie.getHappiness());
        assertEquals(100, donukie.getCleanliness());
        assertEquals(100, donukie.getHealth());
    }

    @Test
    void testPlay() {
        donukie.play();
        assertEquals(55, donukie.getHappiness());
        assertEquals(45, donukie.getHunger());
        assertEquals(95, donukie.getCleanliness()); 
        
        if (donukie.getHealth() == 15) {
            assertEquals(15, donukie.getHealth());  
        } else {
            assertEquals(100, donukie.getHealth()); 
        }
    }

    @Test
    void testPlayHappinessMax() {
        donukie.setHappiness(96);
        donukie.play();
        assertEquals(100, donukie.getHappiness()); 
    }

    @Test
    void testPet() {
        donukie.pet();
        assertEquals(60,donukie.getHappiness());
        assertEquals(40, donukie.getHunger()); 
    }

    @Test
    void testPetHappinessMax() {
        donukie.setHappiness(90);
        donukie.pet();
        assertEquals(100, donukie.getHappiness()); 
    }


    @Test
    void testRandomlyInjured() {
        int consecutiveInjuries = 0;
        int maxConsecutiveInjuries = 0;
        int consecutiveNonInjuries = 0;
        int maxConsecutiveNonInjuries = 0;

        for (int i = 0; i < 100; i++) {
            donukie.randomlyInjured(); // Call the method
            boolean isInjured = (donukie.getHealth() == 15); // Check injured state after

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
