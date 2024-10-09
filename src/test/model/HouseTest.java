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
        house = new House();
        pet1 = new Meomo("Buddy"); 
        pet2 = new Donukie("Max");
    }

    @Test
    void testConstructor() {
        assertEquals(0, house.getPetCount());
    }

    @Test
    void testAddPet() {
        house.addPet(pet1);
        house.addPet(pet2);
        assertEquals(2, house.getPetCount());
    }

    @Test
    void testRemovePet() {
        house.addPet(pet1);
        house.addPet(pet2);
        house.removePet(0);
        assertEquals(1, house.getPetCount());
        assertEquals(pet2, house.getPet(0));
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
        house.addPet(pet1);
        house.addPet(pet2);
        List<Pet> petsInHouse = house.getPets();
        assertEquals(2, petsInHouse.size());
        assertTrue(petsInHouse.contains(pet1));
        assertTrue(petsInHouse.contains(pet2));
    }

    @Test
    void testGetPet() {
        house.addPet(pet1);
        house.addPet(pet2);
        Pet retrievedPet = house.getPet(1);

        // Assert
        assertEquals(pet2, retrievedPet);
    }

    @Test
    void testGetPetInvalidIndex() {
        house.addPet(pet1);
        house.getPet(1); // Attempt to get pet at an invalid index
        assertEquals(1, house.getPetCount());
    }

    @Test
    void testGetPetCoint() {
        house.addPet(pet1);
        house.addPet(pet1);
        house.addPet(pet2);
        assertEquals(3, house.getPetCount());
    }
}
