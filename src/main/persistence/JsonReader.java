package persistence;

import java.io.IOException;
import java.util.List;

import model.Game;
import model.pet.Pet;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads game from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Game readGame() throws IOException {
        return null;
    }

    // EFFECTS: reads all pets from file and returns it;
    // throws IOException if an error occurs reading data from file
    public List<Pet> readPet() throws IOException {
        return null;
    }
}
