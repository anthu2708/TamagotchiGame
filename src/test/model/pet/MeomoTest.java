package model.pet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

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
    void testPlayNotInjured() {
        meomo.play();
        assertEquals(65, meomo.getHappiness());
        assertEquals(45, meomo.getHunger());
        assertEquals(95, meomo.getCleanliness()); 
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
        assertEquals(40, meomo.getHunger());
    }

    @Test
    void testPetHappinessMax() {
        meomo.setHappiness(90);
        meomo.pet();
        assertEquals(100, meomo.getHappiness()); // Happiness should cap at 100
    }

    @Test
    void testRandomlyInjured() {
        ArrayList<Boolean> isInjuriedList = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            isInjuriedList.add(meomo.randomlyInjured());
        }

        assertTrue(isInjuriedList.contains(true));
        assertTrue(isInjuriedList.contains(false));
    }
}



