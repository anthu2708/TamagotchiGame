
package model.pet;

import model.inventory.Food;
import model.inventory.Pill;

// Represents a Pet that has a name, hunger, happiness, health, and cleanliness levels, along with an alive state.
public abstract class Pet {
    protected String name;           // Pet's name
    protected int hunger;           // Hunger level (0 = full, 100 = starving)
    protected int happiness;        // Happiness level (0 = sad, 100 = very happy)
    protected int health;           // Health level (0 = ill, 100 = healthy)
    protected int cleanliness;      // Cleanliness level (0 = dirty, 100 = clean)
    protected String type;          // Type of pet (e.g., dog, cat)

    // Constructs a Pet with specified name and type.
    // 
    // REQUIRES: name has a non-zero length; type has a non-zero length.
    // EFFECTS: Initializes the pet's name, type, hunger, happiness, cleanliness, and health levels.
    public Pet(String name, String type) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: Increases happiness when playing, decreases hunger and cleanliness; 
    //          if already full, hunger remains the same.
    public abstract void play();

    // MODIFIES: this
    // EFFECTS: Changes happiness when petted; if already full, happiness remains the same.
    public abstract void pet();

    // MODIFIES: this
    // EFFECTS: Randomly injures the pet, setting health to 15 if above that level.
    public abstract void randomlyInjured();

    // MODIFIES: this
    // EFFECTS: Increases the pet's hunger level when fed; if already full, hunger remains the same.
    public void feed(Food food) {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: Injures the pet; health drops to 15 if health is above 15.
    public void injuried() {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: Cleans the pet, setting cleanliness to 100.
    public void clean() {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: Uses a pill, adjusting happiness, health, and hunger levels accordingly.
    public void usePill(Pill pill) {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: Returns an array of boolean values indicating which needs attention.
    //          [0] = hunger, [1] = cleanliness, [2] = happiness.
    public boolean[] needsAttention() {
        return new boolean[]{false, false, false};
    }

    // EFFECTS: Returns true if the pet's health is below 20, indicating it needs a pill.
    public boolean needsPill() {
        return false;
    }

    // MODIFIES: this
    // EFFECTS: Decreases health by 5; health cannot drop below 0.
    public void dropHealth() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: Returns a string summarizing the pet's status 
    // including name, type, hunger, happiness, health, and cleanliness.
    public String getStatus() {
        return "";
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getHunger() {
        return hunger;
    }

    public int getHappiness() {
        return happiness;
    }

    public int getHealth() {
        return health;
    }

    public int getCleanliness() {
        return cleanliness; 
    }

    public String getType() {
        return type;
    }
}
