package model.pet;

public class Donukie extends Pet {
    public Donukie(String name) {
        super(name, "Donukie");
    }

    @Override
    // REQUIRES: 0< happiness and happiness =< 100
    // MODIFIES: this
    // EFFECTS: Increases happiness by 10 when playing, 
    //          decrease hunger by 5 and 
    //          cleanliness bar by 10;
    //          if full stay the same; 
    //          might randomly get injured
    public void play() {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: random get injured with chance of 5% (health drop to 15)
    public void randomlyInjured() {
        // stub
    }

    @Override
    // MODIFIES: this
    // EFFECTS: Increase happiness by 15 when playing; decrease hunger bar by 10;
    //          if full stay the same
    public void pet() {
        //stub
    }
}
