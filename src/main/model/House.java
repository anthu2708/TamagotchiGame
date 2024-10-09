package model;

import java.util.ArrayList;
import java.util.List;

import model.pet.Pet;

/**
 * The House class represents a collection of pets within a household.
 * It provides methods to add, remove, and retrieve pets, as well as to get 
 * the total number of pets in the house.
 */

public class House {
    private List<Pet> pets;

    // EFFECTS: Constructs a House instance with an empty list of pets.
    public House() {
        this.pets = new ArrayList<>();
    }

    // REQUIRES: pet is not null
    // MODIFIES: this
    // EFFECTS: Adds a pet to the house
    public void addPet(Pet pet) {
        pets.add(pet);
    }

    // MODIFIES: this
    // EFFECTS: Removes a pet from the house by index
    public void removePet(int index) {
        if (index >= 0 && index < pets.size()) {
            pets.remove(index);
        }
    }

    // Getter
    public List<Pet> getPets() {
        return pets;
    }

    // MREQUIRES: house is not empty
    // EFFECTS: Returns a pet by index
    public Pet getPet(int index) {
        if (index < pets.size()) {
            return pets.get(index);
        }
        return null;
    }

    // EFFECTS: Returns the number of pets
    public int getPetCount() {
        return pets.size();
    }
}
