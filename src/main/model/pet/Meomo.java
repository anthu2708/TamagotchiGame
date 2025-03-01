package model.pet;

import java.util.Random;

/**
 * Represents a Meomo pet with specific behaviors for playing, getting injured, and being petted.
 * A Meomo has a name, happiness, hunger, cleanliness, and health levels.
 */
public class Meomo extends Pet {
    public Meomo(String name) {
        super(name, "Meomo");
    }

    @Override
    // REQUIRES: dont need attention or pills
    // MODIFIES: this
    // EFFECTS: Increases happiness by 5 when playing, 
    //          decrease hunger by 5 and 
    //          cleanliness bar by 5;
    //          if full stay the same; 
    //          might randomly get injured
    public void play() {
        this.happiness = (this.happiness + 5 < 100) ? (this.happiness + 15) : 100;        
        this.hunger = this.hunger - 5;
        this.cleanliness = this.cleanliness - 5;
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
    // REQUIRES: dont need attention or pills
    // MODIFIES: this
    // EFFECTS: Increase happiness by 10 when playing; decrease hunger bar by 10;
    //          if full stay the same
    public void pet() {
        this.happiness = (this.happiness + 10 < 100) ? (this.happiness + 10) : 100;
        this.hunger = this.hunger - 10;
    }
}

