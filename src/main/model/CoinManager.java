package model;

/**
 * The CoinManager class represents a simple model for managing a coin's value.
 * It provides methods to add and subtract amounts from the current value of the coin.
 */

public class CoinManager {
    private int value;

    // Constructor
    public CoinManager(int value) {
        this.value = value;
    }

    // Getter
    public int getValue() {
        return value;
    }

    // REQUIRES: amount is not neg
    // MODIFIES: this
    // EFFECTS: add amount to current value
    public void add(int amount) {
        this.value += amount;
    }

    // REQUIRES: amount is not neg and amount is less than this value
    // MODIFIES: this
    // EFFECTS: subtract amount to current value
    public void subtract(int amount) {
        this.value -= amount;
    }
}
