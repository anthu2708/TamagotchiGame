package ui;

import java.util.ArrayList;
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

/**
 * GameUI - Represents the user interface for the pet management game.
 * This class manages the main game loop, handles user input, and 
 * interacts with various components such as pets, inventory, and store.
 */

public class GameUI { 
    private List<PetUI> petGames;
    private Game game;
    private Scanner scanner;

    // Constructor
    public GameUI() {
        this.game = new Game(new House(), new Fridge(), new MedicineBox(), new CoinManager(100), new Store());
        this.petGames = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    // EFFECTS: Initiates the main game loop, displaying the main menu and 
    // handling user choices until the game exits.
    public void startGame() {
        boolean isPlaying = true;

        while (isPlaying) {
            showMainMenu();
            int choice = getUserChoice();
            handleUserChoice(choice);

            if (choice == 5) {
                isPlaying = false;
            }
        }
        System.out.println("Exiting game...");
        scanner.close();
    }

    // EFFECTS: Prompts the user to enter their menu choice and validates the input.
    private int getUserChoice() {
        int choice = -1;
        while (choice < 1 || choice > 5) {
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                scanner.nextLine(); 
            }
        }
        return choice;
    }

    // EFFECTS: Displays the main menu options to the user.
    private void showMainMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Go to Pet");
        System.out.println("2. View Status");
        System.out.println("3. Add more Pet");
        System.out.println("4. Delete Pet");
        System.out.println("5. Exit Game");
    }

 
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
        
        int petIndex = getUserChoiceForPet();
        if (petIndex >= 0) {
            managePet(petGames.get(petIndex));
            return;
        }
    }

    // EFFECTS: Prompts the user to select a pet by its index.
    private int getUserChoiceForPet() {
        while (true) { // Keep prompting until a valid input is given
            System.out.print("Enter the pet number: ");
            int choice = scanner.nextInt() - 1; // Adjusting index for zero-based array
    
            if (choice >= 0 && choice < game.getHouse().getPetCount()) {
                scanner.nextLine(); // Consume the newline
                return choice; // Return valid choice
            } else {
                System.out.println("Invalid input. Please enter a valid pet number.");
                scanner.nextLine(); // Clear the invalid input
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
        
        // Display all pets with their indices
        System.out.println("\nPet Statuses:");
        List<Pet> pets = game.getHouse().getPets();
        for (int i = 1; i <= pets.size(); i++) {
            System.out.println(i + ": " + pets.get(i - 1).getName());
        }
    
        // Prompt user to select a pet by index
        System.out.print("Enter the index of the pet to view its status: ");
        int index = scanner.nextInt();
    
        // Validate the index
        if (index <= 0 || index > pets.size()) {
            System.out.println("Invalid index! Please enter a valid index.");
        } else {
            // Get the selected pet and display its status
            Pet selectedPet = pets.get(index - 1);
            System.out.println(selectedPet.getName() + ": " + selectedPet.getStatus());
        }
    }

    // MODIFIES: this
    // EFFECTS: Add a new pet
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
    // EFFECTS: Delete a new pet
    private void deletePet() {
        if (game.getHouse().getPetCount() == 0) {
            System.out.println("You have no pets to delete!");
            return;
        }

        // Show the list of pets
        System.out.println("Select a pet to delete:");
        for (int i = 0; i < game.getHouse().getPetCount(); i++) {
            System.out.println((i + 1) + ". " + game.getHouse().getPet(i).getName());
        }
        
        int petIndex = getUserChoiceForPet();
        if (petIndex >= 0) {
            game.getHouse().removePet(petIndex);
            petGames.remove(petIndex);
            System.out.println("Pet removed successfully.");
        }
    }
}
