package model.pet;

import java.util.Random;

/**
 * Represents a Donukie pet with specific behaviors for playing, getting injured, and being petted.
 * A Donukie has a name, happiness, hunger, cleanliness, and health levels.
 */
public class Donukie extends Pet {
    public Donukie(String name) {
        super(name, "Donukie");
    }

    @Override
    // REQUIRES: not needs Attention or injured
    // MODIFIES: this
    // EFFECTS: Increases happiness by 10 when playing, 
    //          decrease hunger by 5 and 
    //          cleanliness bar by 10;
    //          if full stay the same; 
    //          might randomly get injured
    public void play() {
        this.happiness = (this.happiness + 10 < 100) ? (this.happiness + 10) : 100;        
        this.hunger = this.hunger - 5;
        this.cleanliness = this.cleanliness - 10;
        randomlyInjured();
    }

    // MODIFIES: this
    // EFFECTS: random get injured with chance of 5% (health drop to 15)
    public boolean randomlyInjured() {
        Random rand = new Random();
        if (rand.nextInt(100) <= 5) { 
            injuried();
            return true;
        }
        return false;
    }

    @Override
    // REQUIRES: not needs Attention or injured
    // MODIFIES: this
    // EFFECTS: Increase happiness by 15 when petting; decrease hunger bar by 10;
    //          if full stay the same
    public void pet() {
        this.happiness = (this.happiness + 15 < 100) ? (this.happiness + 15) : 100;
        this.hunger = this.hunger - 10;
    }
}
