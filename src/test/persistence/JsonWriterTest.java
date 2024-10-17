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
            House house = new House();  
            Fridge fridge = new Fridge();
            MedicineBox medicineBox = new MedicineBox();
            CoinManager coinManager = new CoinManager(100);
            Store store = new Store();
            Game game = new Game(house, fridge, medicineBox, coinManager, store);            
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
        p1.setHappiness(90);
        p1.setCleanliness(75);
        p1.setHealth(100);

        Pet p2 = new Meomo("Meomo");
        p2.setHunger(60);
        p2.setHappiness(70);
        p2.setCleanliness(50);
        p2.setHealth(15);

        Pet p3 = new Donukie("Donukie");
        p3.setHunger(50);
        p3.setHappiness(40);
        p3.setCleanliness(30);
        p3.setHealth(60);

        house.addPet(p1);
        house.addPet(p2);
        house.addPet(p3);
        return house;
    }
    

    void checkGeneralGame(Game loadedGame) {
        House readHouse = loadedGame.getHouse();
        testHouseReaderGeneralGame(readHouse);

        Fridge readFridge = loadedGame.getFridge();
        testFridgeReaderGeneralGame(readFridge);

        MedicineBox readMedicineBox = loadedGame.getMedicineBox();
        testMBReaderGeneralGame(readMedicineBox);
        
        // Check CoinManager
        assertEquals(100, loadedGame.getCoinManager().getValue());
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
        checkPill("Painkiller", 50, 20, 10, 5, medicineBox.getPillByIndex(0));
        checkPill("Vitamin", 30, 15, 5, 10, medicineBox.getPillByIndex(1));
        assertEquals(1, medicineBox.getQuantityByIndex(1));
        assertEquals(1, medicineBox.getQuantityByIndex(2));
    }
}