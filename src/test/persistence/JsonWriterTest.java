package persistence;

import model.CoinManager;
import model.Game;
import model.House;
import model.Store;
import model.pet.Donukie;
import model.pet.Meap;
import model.pet.Meomo;
import model.pet.Pet;
import model.supplies.Food;
import model.supplies.Fridge;
import model.supplies.MedicineBox;
import model.supplies.Pill;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyHouseFridgeMedicineBox() {
        try {
            // Create a new Game with an empty House, empty Fridge, empty Medicine Box, 100
            // coins and a store
            Game game = new Game(new House(), new Fridge(), new MedicineBox(), new CoinManager(100), new Store());
            JsonWriter writer = new JsonWriter("./data/testEmptyGame.json");
            writer.open();
            writer.write(game);
            writer.close();

            JsonReader reader = new JsonReader("./data/testEmptyGame.json");
            Game loadedGame = reader.readGame();

            assertTrue(loadedGame.getHouse().getPets().isEmpty()); // no Pets
            assertTrue(loadedGame.getFridge().getFood().isEmpty()); // no Food
            assertTrue(loadedGame.getMedicineBox().getPill().isEmpty()); // no Pill
            assertEquals(100, loadedGame.getCoinManager().getValue());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


    @Test
    void testWriterGeneralGame() {
        try {
            Game game = initGeneralGame();

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralGame.json");
            writer.open();
            writer.write(game);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralGame.json");
            Game loadedGame = reader.readGame();

            checkGeneralGame(loadedGame);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    Game initGeneralGame() {
        // Create House with pets
        House house = initHouseGeneralGame();
        // Create Fridge with food
        Fridge fridge = new Fridge();
        Food steak = new Food("Steak", 40, 20);
        Food apple = new Food("Apple", 5, 2);
        fridge.addFood(steak);
        fridge.addFood(apple);

        // Create MedicineBox with Pills
        MedicineBox medicineBox = new MedicineBox();
        Pill painkiller = new Pill("PainKiller", 50, 20, 10, 5);
        Pill vitamin = new Pill("Vitamin", 30, 15, 5, 10);
        medicineBox.addPill(painkiller);
        medicineBox.addPill(vitamin);


        CoinManager coinManager = new CoinManager(100);
        Store store = new Store();

        Game game = new Game(house, fridge, medicineBox, coinManager, store);
        return game;
    }

    House initHouseGeneralGame() {
        House house = new House();

        Pet p1 = new Meap("Meap");
        p1.setHunger(80);
        p1.setCleanliness(75);
        p1.setCleanliness(75);
        p1.setHealth(100);

        Pet p2 = new Meomo("Meomo");
        p1.setHunger(60);
        p1.setCleanliness(70);
        p1.setCleanliness(50);
        p1.setHealth(15);

        Pet p3 = new Donukie("Donukie");
        p1.setHunger(50);
        p1.setCleanliness(40);
        p1.setCleanliness(30);
        p1.setHealth(60);

        house.addPet(p1);
        house.addPet(p2);
        house.addPet(p3);
        return house;
    }

    void checkGeneralGame(Game loadedGame) {
        // Check pets in House
        House readHouse = loadedGame.getHouse();
        assertEquals(3, readHouse.getPets().size());
        checkPet("Meap", 80, 90, 75, 100, "Meap", false, readHouse.getPet(0));
        checkPet("Meomo", 60, 70, 50, 15, "Meomo", true, readHouse.getPet(1));
        checkPet("Donukie", 50, 40, 30, 60, "Donukie", false, readHouse.getPet(2));

        // Check food in Fridge
        Fridge readFridge = loadedGame.getFridge();
        assertEquals(2, readFridge.getFood().size());
        checkFood("Steak", 40, 20, readFridge.getFoodByIndex(1));
        checkFood("Apple", 5, 2, readFridge.getFoodByIndex(2));

        // Check Pill in MedicineBox
        MedicineBox readMedicineBox = loadedGame.getMedicineBox();
        assertEquals(2, readMedicineBox.getPill().size());
        checkPill("Vitamin", 10, 5, 15, 10, readMedicineBox.getPillByIndex(1));
        checkPill("Aspirin", 15, 0, 10, 5, readMedicineBox.getPillByIndex(2));

        // Check CoinManager
        assertEquals(100, loadedGame.getCoinManager().getValue());
    }
}