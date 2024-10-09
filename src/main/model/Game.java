package model;


import model.supplies.Fridge;
import model.supplies.MedicineBox;

/**
 * The Game class represents the main game environment containing various components
 * like a house, fridge, medicine box, coin manager, and store. 
 * It manages the interactions between these components.
 */

public class Game {
    private House house;
    private Fridge fridge;
    private MedicineBox medicineBox;
    private CoinManager coinManager;
    private Store store;

    /**
     * Constructs a Game instance with the specified components.
     *
     * house        the House object representing the player's house
     * fridge       the Fridge object for storing food items
     * medicineBox  the MedicineBox object for storing pill items
     * coinManager  the CoinManager object to manage the player's coins
     * store        the Store object where items can be purchased
     */
    public Game(House house, Fridge fridge, MedicineBox medicineBox, CoinManager coinManager, Store store) {
        this.house = house;
        this.fridge = fridge;
        this.medicineBox = medicineBox;
        this.coinManager = coinManager;
        this.store = store;
    }

    // getter
    public House getHouse() {
        return this.house;
    }
    
    public Fridge getFridge() {
        return this.fridge;
    }

    public MedicineBox getMedicineBox() {
        return this.medicineBox;
    }

    public CoinManager getCoinManager() {
        return this.coinManager;
    }

    public Store getStore() {
        return this.store;
    }
}
