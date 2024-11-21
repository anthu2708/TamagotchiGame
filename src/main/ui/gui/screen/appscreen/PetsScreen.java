package ui.gui.screen.appscreen;

import javax.swing.*;
import javax.swing.border.*;

import model.Game;
import model.pet.Pet;

import java.awt.*;
import java.util.List;

import ui.gui.App;
import ui.gui.MainApp;
import ui.gui.PetGameApp;
import ui.gui.screen.AppScreen;
import ui.gui.screen.customizedcomponent.CustomScrollBarUI;
import ui.gui.screen.customizedcomponent.RoundedButton;

public class PetsScreen extends AppScreen {
    private MainApp mainApp;

    // Screen to choose Pet Game
    public PetsScreen(MainApp app) {
        super(app, "House");
        this.mainApp = super.app;

        addScrollPane();
        createMenuButton();
    }

    // MODIFIES: this
    // EFFECTS: adding a scroll pane with Pet Button to go to Pet Game
    private void addScrollPane() {
        JPanel buttonPanel = initButtonPanel();

        JScrollPane scrollPane = new JScrollPane(buttonPanel); // Set buttonPanel as viewport view
        // Customize the vertical scrollbar
        JScrollBar verticalBar = scrollPane.getVerticalScrollBar();
        verticalBar.setPreferredSize(new Dimension(10, 0));
        verticalBar.setUI(new CustomScrollBarUI());
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(scrollPane, BorderLayout.CENTER);
    }

    // EFFECTS: return a JPanel with buttons for each pet
    private JPanel initButtonPanel() {
        List<Pet> pets = mainApp.getPets();
        JPanel buttonPanel = new JPanel();
        int i = (pets.size() <= 10) ? 10 : pets.size();
        buttonPanel.setLayout(new GridLayout(i, 1, 10, 10));
        buttonPanel.setBackground(App.BACKGROUND_BLUE);
        buttonPanel.setBorder(new EmptyBorder(25, 25, 25, 25));

        for (Pet pet : pets) {
            addButton(buttonPanel, pet);
        }
        return buttonPanel;
    }

    // MODIFIES: this
    // EFFECTS: adding Pets button for each pet in house
    // open Pet Game when clicked
    private void addButton(JPanel jpanel, Pet pet) {
        JButton petButton = new RoundedButton(48, App.SUB_YELLOW, App.MAIN_YELLOW,
                App.TEXT_YELLOW, pet.getName());
        petButton.addActionListener(e -> openPetGameApp(app.getGame(), pet));
        petButton.setFont(new Font("Arial", Font.BOLD, 16));
        petButton.setFocusPainted(false);
        petButton.setPreferredSize(new Dimension(250, 50));
        jpanel.add(petButton);
    }

    private void openPetGameApp(Game game, Pet pet) {
        SwingUtilities.invokeLater(() -> {
            PetGameApp petGameApp = new PetGameApp(game, pet);
            petGameApp.setVisible(true);
        });
    }

}
