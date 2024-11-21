package persistence;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import model.Game;
import model.House;
import model.supplies.Fridge;
import model.supplies.MedicineBox;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            reader.readGame();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyGame() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyGame.json");
        try {
            Game game = reader.readGame();
            assertEquals(0, game.getHouse().getPetCount());
            assertEquals(0, game.getFridge().getFood().size());
            assertEquals(0, game.getMedicineBox().getPill().size());
            assertEquals(0, game.getCoinManager().getValue());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralGame() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralGame.json");
        try {
            Game game = reader.readGame();
            House house = game.getHouse();
            Fridge fridge = game.getFridge();
            MedicineBox medicineBox = game.getMedicineBox();

            testHouseReaderGeneralGame(house);
            testFridgeReaderGeneralGame(fridge);
            testMBReaderGeneralGame(medicineBox);
            // Test CoinManager
            assertEquals(100, game.getCoinManager().getValue());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    void testHouseReaderGeneralGame(House house) {
        assertEquals(3, house.getPetCount());
        checkPet("Meap", 80, 90, 75, 100, "Meap", false, house.getPet(0));
        checkPet("Meomo", 60, 70, 50, 15, "Meomo", true, house.getPet(1));
        checkPet("Donukie", 50, 40, 30, 60, "Donukie", false, house.getPet(2));
    }

    void testFridgeReaderGeneralGame(Fridge fridge) {
        assertEquals(2, fridge.getFood().size());
        checkFood("Steak", 40, 20, fridge.getFoodByIndex(1));
        checkFood("Apple", 5, 2, fridge.getFoodByIndex(2));
        assertEquals(1, fridge.getQuantityByIndex(1));
        assertEquals(1, fridge.getQuantityByIndex(2));
    }

    void testMBReaderGeneralGame(MedicineBox medicineBox) {
        assertEquals(2, medicineBox.getPill().size());
        checkPill("Painkiller", 50, 20, 10, 5, medicineBox.getPillByIndex(1));
        checkPill("Vitamin", 30, 15, 5, 10, medicineBox.getPillByIndex(2));
        assertEquals(1, medicineBox.getQuantityByIndex(1));
        assertEquals(1, medicineBox.getQuantityByIndex(2));
    }
}
