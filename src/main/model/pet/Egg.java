package model.pet;

import java.util.Random;

/**
 * Represents an Egg that can hatch into a pet.
 * The Egg will randomly hatch into either Meap, Meomo, or Donukie.
 */

public class Egg {
    private String name;

    public Egg(String name) {
        this.name = name;
    }

    // REQUIRES: petName must not be null
    // EFFECTS: randomly select a Pet type (Meap, Meomo, Donukie)
    //          return an instance of the selected Pet type, initialize with petName
    public Pet hatch() {
        Random rand = new Random();
        int randomPet = rand.nextInt(3);

        switch (randomPet) {
            case 0: return new Meap(this.name);
            case 1: return new Meomo(this.name);
            default: return new Donukie(this.name);
        }
    }

    public String getName() {
        return name;
    }
}

