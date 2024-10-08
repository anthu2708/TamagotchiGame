package model.pet;

/**
 * Represents a Meap pet with specific behaviors for playing, getting injured, and being petted.
 * A Meap has a name, happiness, hunger, cleanliness, and health levels.
 */
public class Meap extends Pet {
    public Meap(String name) {
        super(name, "Meap");
    }

    @Override
    // REQUIRES: 0< happiness and happiness =< 100
    // MODIFIES: this
    // EFFECTS: Increases happiness by 15 when playing, 
    //          decrease hunger by 10 and 
    //          cleanliness bar by 20;
    //          if full stay the same; 
    //          might randomly get injured
    public void play() {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: random get injured with chance of 7% (health drop to 15)
    public void randomlyInjured() {
        // stub
    }

    @Override
    // MODIFIES: this
    // EFFECTS: Increase happiness by 10 when playing; decrease hunger bar by 10;
    //          if full stay the same
    public void pet() {
        //stub
    }
}