package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.pet.Donukie;
import model.pet.Meap;
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

    @Test
    void testToJson() {
        House house = initHouse();
        JSONObject actualJson = house.toJson();
        JSONArray jsonPets = actualJson.getJSONArray("pets");
        assertEquals(3, jsonPets.length());

        JSONObject meapJson = jsonPets.getJSONObject(0);
        JSONObject meomoJson = jsonPets.getJSONObject(1);
        JSONObject donukieJson = jsonPets.getJSONObject(2);

        checkMeap(meapJson);
        checkMeomo(meomoJson);
        checkDonukie(donukieJson);

    }

    void checkMeap(JSONObject meapJson) {
        assertEquals("Meap", meapJson.getString("name")); 
        assertEquals("Meap", meapJson.getString("type"));
        assertEquals(80, meapJson.getInt("hunger"));
        assertEquals(90, meapJson.getInt("happiness"));
        assertEquals(75, meapJson.getInt("cleanliness"));
        assertEquals(100, meapJson.getInt("health"));
        assertFalse(meapJson.getBoolean("isInjured"));
    }

    void checkMeomo(JSONObject meomoJson) {
        assertEquals("Meomo", meomoJson.getString("name")); 
        assertEquals("Meomo", meomoJson.getString("type"));
        assertEquals(60, meomoJson.getInt("hunger"));
        assertEquals(70, meomoJson.getInt("happiness"));
        assertEquals(50, meomoJson.getInt("cleanliness"));
        assertEquals(15, meomoJson.getInt("health"));
        assertTrue(meomoJson.getBoolean("isInjured"));
    }

    void checkDonukie(JSONObject donukieJson) {
        assertEquals("Donukie", donukieJson.getString("name")); 
        assertEquals("Donukie", donukieJson.getString("type"));
        assertEquals(50, donukieJson.getInt("hunger"));
        assertEquals(40, donukieJson.getInt("happiness"));
        assertEquals(30, donukieJson.getInt("cleanliness"));
        assertEquals(60, donukieJson.getInt("health"));
        assertFalse(donukieJson.getBoolean("isInjured"));
    }

    House initHouse() {
        House house = new House();

        Pet p1 = new Meap("Meap");
        p1.setHunger(80);
        p1.setHappiness(90);
        p1.setCleanliness(75);
        p1.setHealth(100);
        p1.setInjured();

        Pet p2 = new Meomo("Meomo");
        p2.setHunger(60);
        p2.setHappiness(70);
        p2.setCleanliness(50);
        p2.setHealth(15);
        p2.setInjured();

        Pet p3 = new Donukie("Donukie");
        p3.setHunger(50);
        p3.setHappiness(40);
        p3.setCleanliness(30);
        p3.setHealth(60);
        p3.setInjured();

        house.addPet(p1);
        house.addPet(p2);
        house.addPet(p3);
        return house;
    }
}
