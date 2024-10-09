package model.pet;

import java.util.Random;

/**
 * Represents a Meap pet with specific behaviors for playing, getting injured, and being petted.
 * A Meap has a name, happiness, hunger, cleanliness, and health levels.
 */
public class Meap extends Pet {
    public Meap(String name) {
        super(name, "Meap");
    }

    @Override
    // REQUIRES: not needs Attention or injured
    // MODIFIES: this
    // EFFECTS: Increases happiness by 15 when playing, 
    //          decrease hunger by 10 and 
    //          cleanliness bar by 20;
    //          if full stay the same; 
    //          might randomly get injured
    public void play() {
        this.happiness = (this.happiness + 15 < 100) ? (this.happiness + 15) : 100;        
        this.hunger = this.hunger - 10;
        this.cleanliness = this.cleanliness - 20;
        randomlyInjured();
    }

    // MODIFIES: this
    // EFFECTS: random get injured with chance of 7% (health drop to 15)
    public boolean randomlyInjured() {
        Random rand = new Random();
        if (rand.nextInt(100) <= 7) { 
            injuried();
            return true;
        }
        return false;
    }

    @Override
    // REQUIRES: not needs Attention or injured
    // MODIFIES: this
    // EFFECTS: Increase happiness by 10 when petting; decrease hunger bar by 10; cleanliness by 20
    //          if full stay the same
    public void pet() {
        this.happiness = (this.happiness + 20 < 100) ? (this.happiness + 20) : 100;
        this.hunger = this.hunger - 10;
        this.cleanliness = this.cleanliness - 20;
    }
}