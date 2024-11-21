package ui.GUI.Screens.AppScreens;

import javax.swing.*;
import javax.swing.border.*;

import model.Game;
import model.pet.Pet;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

import ui.GUI.App;
import ui.GUI.MainApp;
import ui.GUI.PetGameApp;
import ui.GUI.Screens.AppScreen;
import ui.GUI.Screens.CustomizedPanel.CustomScrollBarUI;
import ui.GUI.Screens.CustomizedPanel.RoundedButton;
import ui.GUI.Screens.CustomizedPanel.RoundedPanel;

public class StatusScreen extends AppScreen {
    private MainApp mainApp;
    private List<Pet> pets;

    // View Pets status and Delete Pet
    public StatusScreen(MainApp app) {
        super(app, "Pets Status");
        this.mainApp = super.app;
        this.pets = mainApp.getPets();

        addScrollPane();
        createMenuButton();
    }

    // MODIFIES: this
    // EFFECTS: adding a scroll pane with Pet Panel displaying Pet Status
    private void addScrollPane() {
        JPanel statusPanel = initStatusPanel();
        statusPanel.setBackground(App.BACKGROUND_BLUE);

        JScrollPane scrollPane = new JScrollPane(statusPanel);

        JScrollBar verticalBar = scrollPane.getVerticalScrollBar();
        verticalBar.setPreferredSize(new Dimension(10, 0));
        verticalBar.setUI(new CustomScrollBarUI()); 
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(scrollPane, BorderLayout.CENTER);
    }

    // EFFECTS: return JPanel with status Panel for each pet
    private JPanel initStatusPanel() {
        List<Pet> pets = mainApp.getPets();
        int i = (pets.size() <= 3) ? 3 : pets.size();
        JPanel buttonPanel = new JPanel(new GridLayout(i, 1, 10, 10));
        buttonPanel.setBackground(App.BACKGROUND_BLUE);
        buttonPanel.setBorder(new EmptyBorder(24, 24, 24, 24));

        for (Pet pet : pets) {
            addStatusPanel(buttonPanel, pet);
        }
        return buttonPanel;
    }

    // MODIFIES: this
    // EFFECTS: adding Pets panel with status, and delete button for each pet in
    // house
    private void addStatusPanel(JPanel jPanel, Pet pet) {
        List<String> status = getStatus(pet);
        JPanel smallPanel = new RoundedPanel(40, App.SUB_YELLOW, App.MAIN_YELLOW);
        smallPanel.setLayout(new GridLayout(8, 1, 10, 0));
        smallPanel.setBorder(new EmptyBorder(20, 40, 20, 40));

        for (String s : status) {
            JLabel lab = new JLabel(s);
            lab.setForeground(App.TEXT_YELLOW);
            smallPanel.add(lab);
        }

        String s = getAttention(pet);
        JLabel lab = new JLabel(s);
        lab.setForeground(App.TEXT_PINK);
        smallPanel.add(lab);

        JButton goToPetButton = new RoundedButton(28, App.SUB_GREEN,
                App.MAIN_GREEN, App.TEXT_GREEN, "Pet Game");
        goToPetButton.setPreferredSize(new Dimension(80, 40));
        goToPetButton.addActionListener(e -> openPetGameApp(app.getGame(), pet));
        smallPanel.add(goToPetButton);

        JButton deletePetButton = new RoundedButton(28, App.SUB_PINK,
                App.MAIN_PINK, App.TEXT_PINK, "Delete Pet");
        deletePetButton.setPreferredSize(new Dimension(80, 40));
        deletePetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRemovePet(pet);
            }

        });
        smallPanel.add(deletePetButton);
        jPanel.add(smallPanel);
    }

    // MODIFIES : this
    // EFFECTS: open add pet game screen
    private void openPetGameApp(Game game, Pet pet) {
        SwingUtilities.invokeLater(() -> {
            PetGameApp petGameApp = new PetGameApp(game, pet);
            petGameApp.setVisible(true);
        });
    }

    // MODIFIES : this
    // EFFECTS: handle remove pet function
    // when clicked, ask if sure to remove pet
    // if yes, remove
    // else, keep
    private void handleRemovePet(Pet pet) {

        int response = JOptionPane.showConfirmDialog(
                super.app,
                "You sure you want to remove this pet?",
                "Remove Pet",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            pets.remove(pet);
            app.initScreens();
            app.showScreen("StatusScreen");
        }
    }

    // MODIFIES: this
    // EFFECTS: Returns a string summarizing the pet's status
    // including name, type, hunger, happiness, health, and cleanliness.
    private List<String> getStatus(Pet pet) {
        String name = pet.getName();
        String type = pet.getType();
        int hunger = pet.getHunger();
        int happiness = pet.getHappiness();
        int health = pet.getHealth();
        int cleanliness = pet.getCleanliness();

        List<String> status = new ArrayList<>();
        status.add(name.toUpperCase() + " - " + type);
        status.add("Hunger: " + hunger + "\n");
        status.add("Happiness: " + happiness + "\n");
        status.add("Health: " + health + "\n");
        status.add("Cleanliness: " + cleanliness + "\n");
        return status;
    }

    // EFFECTS: return Attention String
    private String getAttention(Pet pet) {
        String attention = "";
        if (pet.needsPill()) {
            attention += "Need Pill!";
        }

        if (pet.needsAttention()[0]) {
            attention += "Need Food!";
        }

        if (pet.needsAttention()[1]) {
            attention += "Dirty!";
        }

        if (pet.needsAttention()[2]) {
            attention += "Sad!";
        }

        return attention;
    }

}
