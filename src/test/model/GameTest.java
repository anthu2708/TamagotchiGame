package model;

import model.CoinManager;
import model.Game;
import model.House;
import model.Store;
import model.supplies.Fridge;
import model.supplies.MedicineBox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Game game;
    private House house;
    private Fridge fridge;
    private MedicineBox medicineBox;
    private CoinManager coinManager;
    private Store store;

    @BeforeEach
    void setUp() {
        house = new House();
        fridge = new Fridge();
        medicineBox = new MedicineBox();
        coinManager = new CoinManager(100); // Assuming initial coins are set to 100
        store = new Store();
        
        game = new Game(house, fridge, medicineBox, coinManager, store);
    }

    @Test
    void testConstructor() {
        assertEquals(house, game.getHouse());
        assertEquals(fridge, game.getFridge());
        assertEquals(medicineBox, game.getMedicineBox());
        assertEquals(coinManager, game.getCoinManager());
        assertEquals(store, game.getStore());
    }
}
