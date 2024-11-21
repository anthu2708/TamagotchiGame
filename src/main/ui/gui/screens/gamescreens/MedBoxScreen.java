package ui.gui.screens.gamescreens;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.util.*;

import model.pet.Pet;
import model.supplies.MedicineBox;
import model.supplies.Pill;
import ui.gui.App;
import ui.gui.PetGameApp;
import ui.gui.screens.GameScreen;
import ui.gui.screens.customizedpanel.CustomScrollBarUI;
import ui.gui.screens.customizedpanel.RoundedButton;
import ui.gui.screens.customizedpanel.RoundedPanel;

public class MedBoxScreen extends GameScreen {
    private MedicineBox medicineBox;

    // Medicine Box Screen to choose pill from
    public MedBoxScreen(PetGameApp petGameApp) {
        super(petGameApp, "Medicine Box");
        this.medicineBox = game.getMedicineBox();

        addScrollPane();
        createMenuButton();
    }

    // MODIFIES: this
    // EFFECTS: adding a scroll pane with Pill Pane
    private void addScrollPane() {
        JPanel statusPanel = initPillPanel();
        statusPanel.setBackground(App.BACKGROUND_BLUE);

        JScrollPane scrollPane = new JScrollPane(statusPanel);

        JScrollBar verticalBar = scrollPane.getVerticalScrollBar();
        verticalBar.setPreferredSize(new Dimension(10, 0));
        verticalBar.setUI(new CustomScrollBarUI());
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(scrollPane, BorderLayout.CENTER);
    }

    // EFFECTS: return JPanel with Panel for each Food
    private JPanel initPillPanel() {
        Map<Pill, Integer> pills = medicineBox.getPill();
        int i = (pills.size() <= 3) ? 3 : pills.size();
        JPanel buttonPanel = new JPanel(new GridLayout(i, 1, 10, 10));
        buttonPanel.setBackground(App.BACKGROUND_BLUE);
        buttonPanel.setBorder(new EmptyBorder(24, 24, 24, 24));

        for (Map.Entry<Pill, Integer> entry : pills.entrySet()) {
            Pill pill = entry.getKey();
            int quantity = entry.getValue();
            for (int j = 0; j < quantity; j++) {
                addFoodPanel(buttonPanel, pill);
            }
        }
        return buttonPanel;
    }

    // MODIFIES: this
    // EFFECTS: adding Pills panel with status, and Use button for each Pill
    private void addFoodPanel(JPanel jpanel, Pill pill) {
        JPanel smallPanel = new RoundedPanel(40, App.SUB_YELLOW, App.MAIN_YELLOW);
        smallPanel.setLayout(new GridLayout(3, 1, 10, 0));
        smallPanel.setBorder(new EmptyBorder(20, 40, 20, 40));

        String name = pill.getName();
        int nutriVal = pill.getNutrition();
        int healthVal = pill.getHealth();
        int happinessValue = pill.getHappiness();

        smallPanel.setLayout(new GridLayout(5, 1, 10, 10));
        smallPanel.add(new JLabel(name));
        smallPanel.add(new JLabel("Hunger Point: " + nutriVal));
        smallPanel.add(new JLabel("Health Point: " + healthVal));
        smallPanel.add(new JLabel("Happiness Point: " + happinessValue));

        JButton button = new RoundedButton(28, App.SUB_PINK,
                App.MAIN_PINK, App.TEXT_PINK, "Feed");
        button.setPreferredSize(new Dimension(80, 40));
        button.addActionListener(e -> heal(pill));
        smallPanel.add(button);
        jpanel.add(smallPanel);
    }

    // MODIFIES: this
    // EFFECTS: use pill to modify pet state and reload all Screen to match new
    // state
    private void heal(Pill pill) {
        Pet pet = super.app.getPet();
        pet.usePill(pill);
        JOptionPane.showMessageDialog(this, pet.getName() + " is healed!");
        reloadScreens("GameScreen");

    }

}
