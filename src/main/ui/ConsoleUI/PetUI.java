package ui.ConsoleUI;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.CoinManager;
import model.Game;
import model.Store;
import model.pet.Pet;
import model.supplies.Food;
import model.supplies.Fridge;
import model.supplies.MedicineBox;
import model.supplies.Pill;

/**
 * PetUI - Represents the user interface for managing a pet in the game.
 * This class provides methods for interacting with the pet, such as feeding,
 * playing, healing, and cleaning. It also manages user input and game state.
 */

public class PetUI {
    private Pet pet;
    private Fridge fridge;
    private MedicineBox medicineBox;
    private CoinManager coinManager;
    private Store store;
    private Scanner scanner;

    /**
     * Constructs a PetUI with the given pet and game state.
     *
     * pet      The pet to manage
     * game     The current game instance containing game components
     * scanner  The scanner for reading user input
     */
    public PetUI(Pet pet, Game game, Scanner scanner) {
        this.pet = pet;
        this.scanner = scanner;
        this.fridge = game.getFridge();
        this.medicineBox = game.getMedicineBox();
        this.store = game.getStore();
        this.coinManager = game.getCoinManager();          
    }
    

    // EFFECTS: Main Pet game loop
    public void startGame() {
        boolean isPlaying = true;

        while (isPlaying) {
            showMainMenu();
            int choice = getUserChoice();
            handleUserChoice(choice);

            if (choice == 9) {
                isPlaying = false;
            }
            checkStatusNotifications();
        }
        System.out.println("Exiting game...");
    }

    // EFFECTS: Displays the main menu options to the user.
    private void showMainMenu() {
        System.out.println("\n--- " + pet.getName() + " ---");
        System.out.println("1. View Pet Status");
        System.out.println("2. Feed Pet");
        System.out.println("3. Play with Pet");
        System.out.println("4. Heal Pet");
        System.out.println("5. Clean Pet");
        System.out.println("6. Visit Store");
        System.out.println("7. View Fridge");
        System.out.println("8. View Medicine Box");
        System.out.println("9. Exit Game");
    }

    // EFFECTS: Gets the user's choice from the menu.
    private int getUserChoice() {
        int choice = -1;
        while (choice < 1 || choice > 9) {
            try {
                System.out.println("Choose your action: ");
                choice = scanner.nextInt();
                scanner.nextLine(); 
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 9.");
                scanner.nextLine();
            }
        }
        return choice;
    }

    @SuppressWarnings("methodlength")
    // MODIFIES: this
    // EFFECTS: Handles the user's choice from the main menu.
    private void handleUserChoice(int choice) {
        switch (choice) {
            case 1:
                viewPetStatus();
                break;
            case 2:
                feedPet();
                break;
            case 3:
                playWithPet();
                break;
            case 4:
                healPet();
                break;
            case 5:
                cleanPet();
                break;
            case 6:
                visitStore();
                break;
            case 7:
                viewFridge();
                break;
            case 8:
                viewMedicineBox();
                break;
        }
    }

    // EFFECTS: View the pet's status
    private void viewPetStatus() {
        System.out.println("\nPet Status:");
        System.out.println(pet.getStatus());
        System.out.println("Type 'm' to return to the main menu.");
        while (!scanner.nextLine().toLowerCase().equals("m")) {
            System.out.println("Invalid input. Type 'm' to return to the main menu.");
        }
    }


    // MODIFIES: this
    // EFFECTS: Feed the pet Loop
    //          if the index is valid, perform the action
    //          otherwise print invalid input message
    private void feedPet() {
        if (fridge.isEmpty()) {
            System.out.println("There is no food in the fridge. Please visit the store to purchase some.");
        } else {
            viewFridge();
            System.out.println("Enter the index of the food to feed:");
            try {
                int foodChoice = scanner.nextInt();
                scanner.nextLine(); 
                handleFeedPet(foodChoice);
            } catch (InputMismatchException  e) {
                System.out.println("Invalid choice. ");
            }
        }               
    }

    // MODIFIES: this
    // EFFECTS:get Food from the fridge by the given index
    //         if successful (there's food w/ given index), feed the pet, remove food from fridge and print out messages
    //         if not, print invalid message
    public void handleFeedPet(int foodChoice) {
        Food food = fridge.getFoodByIndex(foodChoice);
        if (food != null) {
            pet.feed(food);
            fridge.removeFood(food);
            System.out.println("You feed " + pet.getName() + " " + food.getName());
            this.coinManager.add(5);
            System.out.println("You earned 5 coins! Total coins: " + this.coinManager.getValue());
        } else {
            System.out.println("Invalid choice.");
        }
    } 


    // MODIFIES: this
    // EFFECTS: handle the whole loop for play with pet;
    //          if input is valid, perform play or pet action
    //          if input is invalid, print error message
    public void playWithPet() {
        if (pet.needsPill()) {
            System.out.println(pet.getName() + " is sick and needs a pill. Heal your pet before playing.");
        } else {
            System.out.println("1. Play \n2. Pet ");
            System.out.println("Choose your next action: ");
            try {
                int playChoice = scanner.nextInt();
                handlePlayChoice(playChoice);
            } catch (InputMismatchException e) {
                System.out.println("Invalid choice.");
                scanner.nextLine();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Play with the Pet: if the choice is 1, play with the pet
    //                             if the choice is 2, pet the pet
    //                             otherwise print invalid message
    public void handlePlayChoice(int playChoice) {
        if (playChoice == 1) {
            pet.play();
            coinManager.add(7);
            
            System.out.println("You earned 7 coins from playing with " + pet.getName() + "!");
            System.out.println("Total coins: " + coinManager.getValue());
        } else if (playChoice == 2) {
            pet.pet();
            coinManager.add(5);
            System.out.println("You earned 5 coins from playing with " + pet.getName() + "!");
            System.out.println("Total coins: " + coinManager.getValue());
        } else {
            System.out.println("Invalid choice.");
        }
    }


    // MODIFIES: this
    // EFFECTS: Heal the pet Loop; go to the medicine box and give medicine choice
    //          if input is valid, heal the pet using pill
    //          otherwise, print invalid input message
    private void healPet() {
        if (pet.needsPill()) {
            if (medicineBox.isEmpty()) {
                System.out.println("There is no pills. Please visit store to purchase some.");
            } else {
                viewMedicineBox();
                System.out.println("Enter the index of the pill to use:");
                try {
                    int pillChoice = scanner.nextInt();
                    handlePillChoice(pillChoice);
                } catch (InputMismatchException e) {
                    System.out.println("Invalid pill choice.");
                    scanner.nextLine();
                }
                
            }
           
        } else {
            System.out.println("Your pet is not injured.");
        }
    }

    // MODIFIES: this
    // EFFECTS:get Pill from the MedicineBox by the given index
    //         if successful (there's pill w/ given index), use on pet, remove pill from box and print out messages
    //         if not, print invalid message
    public void handlePillChoice(int pillChoice) {
        Pill selectedPill = medicineBox.getPillByIndex(pillChoice);

        if (selectedPill != null && medicineBox.usePill(selectedPill)) {
            pet.usePill(selectedPill);
            coinManager.add(10);
            System.out.println("You earned 10 coins! Total coins: " + coinManager.getValue());
        } else {
            System.out.println("Invalid pill choice.");
        }
    }
    

    // MODIFIES: this
    // EFFECTS: Clean the pet if cleanliness drop above 40
    private void cleanPet() {
        if (pet.getCleanliness() < 40) {
            pet.clean();
            coinManager.add(1);
            System.out.println(pet.getName() + " is now clean!");
            System.out.println("You earned 1 coins! Total coins: " + coinManager.getValue());
        } else {
            System.out.println(pet.getName() + "don't want to bathe");
        }
    }

    // MODIFIES: this
    // EFFECTS: Visit the store to purchase Loop: display all items in store;
    //          take in input and purchase the item
    //          if input is valid (a number within the list), perform the action
    //          if not, print invalid choid message
    private void visitStore() {
        System.out.println("\n=== Store === \n" + store.displayAvailItems());
        System.out.println("Enter the number of the item you want to purchase: ");
        try {
            int itemChoice = scanner.nextInt();
            purchaseItem(itemChoice);
        } catch (InputMismatchException e) {
            System.out.println("Invalid choice.");
            scanner.nextLine();
        }
       
    }

    // MODIFIES: this
    // EFFECTS:get item from the store by the given index
    //         if successful (there's item w/ given index), buy it and store in the right place, subtract coin
    //         if not, print invalid message
    private void purchaseItem(int itemChoice) {
        if (itemChoice > 0 && itemChoice <= store.getNumItems()) {
            if (itemChoice <= store.getFood().size()) { // Check for Food
                Food purchasedFood = store.purchaseFood(itemChoice, fridge, coinManager);
                if (purchasedFood != null) {
                    coinManager.subtract(purchasedFood.getCost()); 
                    System.out.println("You purchased " + purchasedFood.getName() + ". ");
                    System.out.println("Remaining coins: " + coinManager.getValue());
                }
            } else { 
                Pill purchasedPill = store.purchasePill(itemChoice - store.getFood().size(), medicineBox, coinManager);
                if (purchasedPill != null) {
                    coinManager.subtract(purchasedPill.getCost()); 
                    System.out.println("You purchased " + purchasedPill.getName() + ". ");
                    System.out.println("Remaining coins: " + coinManager.getValue());
                }
            }
        } else {
            System.out.println("Invalid choice.");
        }
    }

    // EFFECTS: View food in the fridge
    private void viewFridge() {
        System.out.println("=== Fridge ===");
        System.out.println(fridge.viewFood());
    }

    // EFFECTS: View pills in the medicine box
    private void viewMedicineBox() {
        System.out.println("=== Medicine Box ===");
        System.out.println(medicineBox.viewPills());
    }

    // EFFECTS: Check pet status notifications (e.g., poop, injury)
    private void checkStatusNotifications() {
        if (pet.needsPill()) {
            System.out.println("Your pet need pills");
            System.out.println("Your pet health is:" + pet.getHealth());
        } else if (pet.needsAttention()[0]) {
            System.out.println("Your pet is hungry!");
        } else if (pet.needsAttention()[1]) {
            System.out.println("Your pet is dirty! ");
        } else if (pet.needsAttention()[2]) {
            System.out.println("Your pet is sad! Go play with " + pet.getName());
        }
    }

}
