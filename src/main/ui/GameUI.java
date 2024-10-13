package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import model.CoinManager;
import model.Game;
import model.pet.Egg;
import model.pet.Pet;
import model.supplies.Fridge;
import model.supplies.MedicineBox;
import model.House;
import model.Store; 
import persistence.JsonReader;
import persistence.JsonWriter;

/**
 * GameUI - Represents the user interface for the pet management game.
 * This class manages the main game loop, handles user input, and 
 * interacts with various components such as pets, inventory, and store.
 */

public class GameUI { 
    private static final String JSON_STORE = "./data/workroom.json";
    private List<PetUI> petGames;
    private Game game;
    private Scanner scanner;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // Constructor
    public GameUI() {
        this.game = new Game(new House(), new Fridge(), new MedicineBox(), new CoinManager(100), new Store());
        this.petGames = new ArrayList<>();
        this.scanner = new Scanner(System.in);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // EFFECTS: Initiates the main game loop, displaying the main menu and 
    // handling user choices until the game exits.
    public void startGame() {
        boolean isPlaying = true;

        while (isPlaying) {
            showMainMenu();
            int choice = getUserChoice();
            handleUserChoice(choice);

            if (choice == 6) {
                isPlaying = quitGame();
            }
        }
        System.out.println("Exiting game...");
        scanner.close();
    }

    // EFFECTS: ask if want to save game progress and take action;
    //          quitting game
    private boolean quitGame() {
        System.out.println("Do you want to save your progress before quitting? (y/n)");
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("y")) {
            saveGame();
        }
        return false;
    }

    // EFFECTS: saves the game progress to file
    private void saveGame() {
        try {
            jsonWriter.open();
            jsonWriter.write(game);
            jsonWriter.close();
            System.out.println("Game saved successfully!");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save the game.");
        }
    }

    // EFFECTS: Prompts the user to enter their menu choice and validates the input.
    private int getUserChoice() {
        int choice = -1;
        while (choice < 1 || choice > 6) {
            try {
                System.out.println("Choose your action: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
                scanner.nextLine();
            }
        }
        return choice;
    }

    // EFFECTS: Displays the main menu options to the user.
    private void showMainMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Go to Pet");
        System.out.println("2. View Pet");
        System.out.println("3. Add more Pet");
        System.out.println("4. Delete Pet");
        System.out.println("5. Load Game");
        System.out.println("6. Exit Game");
    }

 
    // REQUIRES: choice can only be 1, 2, 3 or 4
    // EFFECTS: Handles the user's choice from the main menu.
    private void handleUserChoice(int choice) {
        switch (choice) {
            case 1:
                goToPet(); // Navigate to pet management
                break;
            case 2:
                viewStatus(); // View status of all pets
                break;
            case 3:
                addPet(); // Add a new pet
                break;
            case 4:
                deletePet(); // Delete an existing pet
                break;
            case 5:
                loadGame();
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadGame() {
        try {
            List<Pet> petList;
            List<PetUI> petsUI = new ArrayList<>();
            game = jsonReader.readGame();
            petList = jsonReader.readPet();

            for (Pet p: petList) {
                PetUI petUI = new PetUI(p, game, scanner);
                petsUI.add(petUI);
            }
            petGames = petsUI;
            System.out.println("Loaded game from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: Navigates to a specific pet for management. If no pets exist, 
    //          prompts the user to add a pet first.
    private void goToPet() {
        if (game.getHouse().getPetCount() == 0) {
            System.out.println("You have no pets! Please add a pet first.");
            return;
        }
        System.out.println("\nSelect a pet:");
        for (int i = 0; i < game.getHouse().getPetCount(); i++) {
            System.out.println((i + 1) + ". " + game.getHouse().getPet(i).getName());
        }
        
        while (true) {
            try {
                int petIndex = getUserChoiceForPet();
                if (petIndex >= 0) {
                    managePet(petGames.get(petIndex));
                    return;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid choice.");
                scanner.nextLine();
            }
        }
    }

    // EFFECTS: Prompts the user to select a pet by its index.
    private int getUserChoiceForPet() {
        while (true) {
            System.out.print("Enter the pet number: ");
            try {
                int choice = scanner.nextInt() - 1;
                if (choice >= 0 && choice < game.getHouse().getPetCount()) {
                    return choice; 
                } else {
                    System.out.println("Invalid index! Please enter a valid index.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid index! Please enter a number.");
                scanner.nextLine();
            }
            
        }
    }

    // EFFECTS: Manages a specific pet, starting its associated game loop.
    private void managePet(PetUI petGame) {
        petGame.startGame();
    }

    // EFFECTS: Displays the status of all pets currently in the house.
    //          If no pets exist, notifies the user.
    private void viewStatus() {
        if (game.getHouse().getPetCount() == 0) {
            System.out.println("You have no pets!");
            return;
        }
        List<Pet> pets = game.getHouse().getPets();
        printPetList(pets);
    
        while (true) {
            System.out.print("Enter the index of the pet to view its status: ");
            try {
                int index = scanner.nextInt();
                scanner.nextLine(); 
                if (index <= 0 || index > pets.size()) {
                    System.out.println("Invalid choice! Please enter a valid index.");
                } else {
                    Pet selectedPet = pets.get(index - 1);
                    System.out.println(selectedPet.getName() + ": " + selectedPet.getStatus());
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid choice! Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    // EFFECTS: print the given list of pets
    private void printPetList(List<Pet> pets) {
        System.out.println("\nPet Statuses:");
        for (int i = 1; i <= pets.size(); i++) {
            System.out.println(i + ": " + pets.get(i - 1).getName());
        }
    }

    // MODIFIES: this
    // EFFECTS: Hatch a Pet, named it and put it to house
    private void addPet() {
        System.out.println("Hatching a new egg! What will your pet's name be?");
        String petName = scanner.nextLine();
        Egg newEgg = new Egg(petName);
        Pet newPet = newEgg.hatch();
        game.getHouse().addPet(newPet);
        petGames.add(new PetUI(newPet, game, scanner));
        System.out.println("You have added a " + newPet.getType() + " named " + newPet.getName());
    }

    // MODIFIES: this
    // EFFECTS: Delete a pet Loop;
    //          If there is no Pet, print out No Pet message
    //          If there is Pet, get input index and delete the Pet
    //          If the input is invalid, print out invalid choice message
    private void deletePet() {
        if (game.getHouse().getPetCount() == 0) {
            System.out.println("You have no pets to delete!");
            return;
        }

        System.out.println("Select a pet to delete:");
        for (int i = 0; i < game.getHouse().getPetCount(); i++) {
            System.out.println((i + 1) + ". " + game.getHouse().getPet(i).getName());
        }

        while (true) {
            try {
                int petIndex = getUserChoiceForPet();
                deletePetWithIndex(petIndex, game);
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid Choice. Please try again.");
                scanner.nextLine();
            }

        }
        
    }

    // MODIFIES: this
    // EFFECTS: Delete a pet with given index from the house and their pet game
    private void deletePetWithIndex(int petIndex, Game game) {
        House house = game.getHouse();
        int petNums = house.getPetCount();
        if (petIndex >= 0 && petIndex < petNums) {
            house.removePet(petIndex);
            petGames.remove(petIndex);
            System.out.println("Pet removed successfully.");
        } else {
            System.out.println("Invalid Choice. Please try again.");
        }
        
    }
}
