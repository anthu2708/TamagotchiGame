
package model.pet;

import model.supplies.Food;
import model.supplies.Pill;

// Represents a Pet that has a name, hunger, happiness, health, and cleanliness levels, along with an alive state.
public abstract class Pet {
    protected String name;           // Pet's name
    protected int hunger;           // Hunger level (0 = full, 100 = starving)
    protected int happiness;        // Happiness level (0 = sad, 100 = very happy)
    protected int health;           // Health level (0 = ill, 100 = healthy)
    protected int cleanliness;      // Cleanliness level (0 = dirty, 100 = clean)
    protected String type;          // Type of pet (e.g., dog, cat)
    protected boolean isInjuried;   // Injury Status

    // Constructs a Pet with specified name and type.
    // 
    // REQUIRES: name has a non-zero length; type has a non-zero length.
    // EFFECTS: Initializes the pet's name, type, hunger, happiness, cleanliness, and health levels.
    public Pet(String name, String type) {
        this.name = name;
        this.hunger = 50;
        this.happiness = 50;
        this.cleanliness = 100;
        this.health = 100;
        this.type = type;
        this.isInjuried = false;
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
    public abstract boolean randomlyInjured();

    // MODIFIES: this
    // EFFECTS: Increases the pet's hunger level when fed; if already full, hunger remains the same.
    public void feed(Food food) {
        int nutritionValue = food.getNutritionValue();
        this.hunger = (this.hunger + nutritionValue < 100) ? (this.hunger + nutritionValue) : 100;
    }

    // MODIFIES: this
    // EFFECTS: Injures the pet; health drops to 15 if health is above 15.
    // update injury status
    public void injuried() {
        if (this.health >= 15) {
            this.health = 15;
        }
        setInjured();
    }

    // MODIFIES: this
    // EFFECTS: Cleans the pet, setting cleanliness to 100.
    public void clean() {
        this.cleanliness = 100;
    }

    // MODIFIES: this
    // EFFECTS: Uses a pill, adjusting happiness, health, and hunger levels, injury status accordingly.
    public void usePill(Pill pill) {
        this.happiness = (this.happiness + pill.getHappiness() < 100) ? (this.happiness + pill.getHappiness()) : 100;
        this.health = (this.health + pill.getHealth() < 100) ? (this.health + pill.getHealth()) : 100;
        this.hunger = (this.hunger + pill.getNutrition() < 100) ? (this.hunger + pill.getNutrition()) : 100;
        setInjured();
    }

    // MODIFIES: this
    // EFFECTS: Returns an array of boolean values indicating which needs attention.
    //          [0] = hunger, [1] = cleanliness, [2] = happiness.
    public boolean[] needsAttention() {
        boolean[] status = new boolean[]{false, false, false};
        if (hunger <= 20) {
            status[0] = true; // Needs food
        }
        if (cleanliness <= 20) {
            status[1] = true; // Needs cleaning
        }
        if (happiness <= 20) {
            status[2] = true; // Needs attention
        }
        return status;
    }

    // MODIFIES: this
    // EFFECTS: Returns a string summarizing the pet's status 
    // including name, type, hunger, happiness, health, and cleanliness.
    public String getStatus() {
        return "Pet " + name + " - Pet Type: " + type + "\n"
                + "Hunger: " + hunger + "\n"
                + "Happiness: " + happiness + "\n"
                + "Health: " + health + "\n"
                + "Cleanliness: " + cleanliness;
    }

    // EFFECTS: Returns true if the pet's health is below 20, indicating it needs a pill.
    public boolean needsPill() {
        return this.health < 20;
    }

    // MODIFIES: this
    // EFFECTS: Set injury status to true if need pill
    public void setInjured(){
        if (needsPill()){
            this.isInjuried = true;
        } else {
            this.isInjuried = false;
        }
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

    // Setters
    public void setCleanliness(int cleanliness) {
        this.cleanliness = cleanliness;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
