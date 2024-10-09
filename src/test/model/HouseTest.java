package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.pet.Donukie;
import model.pet.Meomo;
import model.pet.Pet;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class HouseTest {
    private House house;
    private Pet pet1;
    private Pet pet2;

    @BeforeEach
    void setUp() {
        // Initialize a House and some Pet instances
        house = new House();
        pet1 = new Meomo("Buddy"); 
        pet2 = new Donukie("Max");
    }

    @Test
    void testAddPet() {
        house.addPet(pet1);
        house.addPet(pet2);
        assertEquals(2, house.getPetCount());
    }

    @Test
    void testRemovePet() {
        // Arrange
        house.addPet(pet1);
        house.addPet(pet2);
        house.removePet(0);
        assertEquals(1, house.getPetCount(), "House should have 1 pet after removal.");
        assertEquals(pet2, house.getPet(0), "The remaining pet should be Max.");
    }

    @Test
    void testRemovePetInvalidIndex() {
        house.addPet(pet1);
        house.removePet(1); 
        assertEquals(1, house.getPetCount());
        house.removePet(-1); 
        assertEquals(1, house.getPetCount());
    }

    @Test
    void testGetPets() {
        // Arrange
        house.addPet(pet1);
        house.addPet(pet2);

        // Act
        List<Pet> petsInHouse = house.getPets();

        // Assert
        assertEquals(2, petsInHouse.size(), "There should be 2 pets in the house.");
        assertTrue(petsInHouse.contains(pet1), "Pet1 should be in the house.");
        assertTrue(petsInHouse.contains(pet2), "Pet2 should be in the house.");
    }

    @Test
    void testGetPet() {
        // Arrange
        house.addPet(pet1);
        house.addPet(pet2);

        // Act
        Pet retrievedPet = house.getPet(1); // Get the second pet (pet2)

        // Assert
        assertEquals(pet2, retrievedPet, "Retrieved pet should be Max.");
    }

    @Test
    void testGetPetInvalidIndex() {
        house.addPet(pet1);
        Pet retrievedPet = house.getPet(1); // Attempt to get pet at an invalid index
        assertNull(retrievedPet);
    }

    @Test
    void testGetPetCountInitially() {
        // Assert
        assertEquals(0, house.getPetCount(), "House should initially have 0 pets.");
    }
}
