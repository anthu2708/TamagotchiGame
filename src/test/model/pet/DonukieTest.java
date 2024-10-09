package model.pet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;


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
    void testPlayNotInjured() {
        donukie.play();
        assertEquals(60, donukie.getHappiness());
        assertEquals(45, donukie.getHunger());
        assertEquals(90, donukie.getCleanliness()); 
        
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
        assertEquals(65,donukie.getHappiness());
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
        ArrayList<Boolean> isInjuriedList = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            isInjuriedList.add(donukie.randomlyInjured());
        }

        assertTrue(isInjuriedList.contains(true));
        assertTrue(isInjuriedList.contains(false));
    }

}
