package model.pet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.List;

class EggTest {
    private Egg egg;

    @BeforeEach
    void runBefore() {
        egg = new Egg("Fluffy");
    }

    @Test
    void testConstructor() {
        assertEquals("Fluffy", egg.getName());
    }
    @Test
    void testHatchReturnsRandomPetTypes() {
        List<String> petTypes = new ArrayList<>();
        int iterations = 100; // Number of times to hatch
        for (int i = 0; i < iterations; i++) {
            Egg newEgg = new Egg("Tep");
            Pet pet = newEgg.hatch();
            petTypes.add(pet.getType());
        }

        // Ensure we have all three pet types
        assertTrue(petTypes.contains("Meap"));
        assertTrue(petTypes.contains("Meomo"));
        assertTrue(petTypes.contains("Donukie"));
    }

}
