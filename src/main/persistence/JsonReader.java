package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

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

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads game from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Game readGame() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGame(jsonObject);
    }

    // EFFECTS: parses game from JSON object and returns it
    private Game parseGame(JSONObject jsonObject) {
        House house = parseHouse(jsonObject.getJSONObject("house"));
        Fridge fridge = parseFridge(jsonObject.getJSONObject("fridge"));
        MedicineBox medicineBox = parseMedicineBox(jsonObject.getJSONObject("medicineBox"));
        CoinManager coinManager = parseCoinManager(jsonObject.getJSONObject("coinManager"));
        Store store = new Store(); // Assuming Store does not require complex initialization from JSON

        return new Game(house, fridge, medicineBox, coinManager, store);
    }

    private CoinManager parseCoinManager(JSONObject jsonObject) {
        int coins = jsonObject.getInt("coins");
        CoinManager coinManager = new CoinManager(coins);
        return coinManager;
    }

    // EFFECTS: parses Fridge from JSON object and returns it
    private MedicineBox parseMedicineBox(JSONObject jsonMB) {
        MedicineBox mb = new MedicineBox();
        for (Object jsonPill : jsonMB.getJSONArray("pills")) {
            JSONObject pillObject = (JSONObject) jsonPill;
            Map.Entry<Pill, Integer> pills = parsePill(pillObject);
            int quantity = pills.getValue();
            Pill pill = pills.getKey();
            for (int i = 0; i < quantity; i++) {
                mb.addPill(pill);
            }
        }
        return mb;
    }

    // EFFECTS: parses Food from JSON object and returns it
    private Map.Entry<Pill, Integer> parsePill(JSONObject jsonPill) {
        String name = jsonPill.getString("name");
        int cost = jsonPill.getInt("cost");
        int nutritionalValue = jsonPill.getInt("nutritionValue");
        int healthValue = jsonPill.getInt("healthValue");
        int happinessValue = jsonPill.getInt("happinessValue");
        Pill pill = new Pill(name, cost, nutritionalValue, healthValue, happinessValue);
        int quantity = jsonPill.getInt("quantity");
        return new AbstractMap.SimpleEntry<>(pill, quantity);
    }

    // EFFECTS: parses Fridge from JSON object and returns it
    private Fridge parseFridge(JSONObject jsonFridge) {
        Fridge fridge = new Fridge();
        Object foodItems = jsonFridge.get("foodItems");
        JSONArray jsonArray = (JSONArray) foodItems;
        for (Object jsonFood : jsonArray) {
            JSONObject foodObject = (JSONObject) jsonFood;
            Map.Entry<Food, Integer> foods = parseFood(foodObject);
            int quantity = foods.getValue();
            Food food = foods.getKey();
            for (int i = 0; i < quantity; i++) {
                fridge.addFood(food);
            }
        }
        return fridge;
    }

    // EFFECTS: parses Food from JSON object and returns it
    private Map.Entry<Food, Integer> parseFood(JSONObject jsonFood) {
        String name = jsonFood.getString("name");
        int nutritionalValue = jsonFood.getInt("nutritionValue");
        int cost = jsonFood.getInt("cost");
        Food food = new Food(name, nutritionalValue, cost);
        int quantity = jsonFood.getInt("quantity");
        return new AbstractMap.SimpleEntry<>(food, quantity);
    }

    // EFFECTS: parses House from JSON object and returns it
    private House parseHouse(JSONObject jsonObject) {
        House house = new House();
        for (Object jsonPet : jsonObject.getJSONArray("pets")) {
            JSONObject petObject = (JSONObject) jsonPet;
            Pet pet = parsePet(petObject);
            house.addPet(pet);
        }
        return house;
    }

    // EFFECTS: parses Pet from JSON object and returns it
    private Pet parsePet(JSONObject jsonPet) {
        String type = jsonPet.getString("type");
        Pet pet;

        switch (type) {
            case "Meap":
                pet = new Meap(jsonPet.getString("name"));
                break;
            case "Meomo":
                pet = new Meomo(jsonPet.getString("name"));
                break;
            default:
                pet = new Donukie(jsonPet.getString("name"));
                break;
        }

        pet.setHunger(jsonPet.getInt("hunger"));
        pet.setHappiness(jsonPet.getInt("happiness"));
        pet.setCleanliness(jsonPet.getInt("cleanliness"));
        pet.setHealth(jsonPet.getInt("health"));

        return pet;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }
}
