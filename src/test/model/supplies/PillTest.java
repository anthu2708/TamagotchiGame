package model.supplies;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PillTest {
    private Pill pill;

    @BeforeEach
    void setUp() {
        // Arrange: Initialize a Pill object before each test
        pill = new Pill("Health Pill", 10, 5, 20, 15);
    }

    @Test
    void testPillConstructor() {
        // Act & Assert
        assertEquals("Health Pill", pill.getName());
        assertEquals(10, pill.getCost());
        assertEquals(5, pill.getNutrition());
        assertEquals(20, pill.getHealth());
        assertEquals(15, pill.getHappiness());
    }

    @Test
    void testGetContent() {
        assertEquals("Nutrition Value - 5, Health Value - 20, Happiness Value - 15", pill.getContent());
    }
}