package ui.gui.screens.appscreens;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import ui.gui.App;
import ui.gui.MainApp;
import ui.gui.screens.AppScreen;
import ui.gui.screens.customizedpanel.RoundedButton;
import ui.gui.screens.customizedpanel.RoundedPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuScreen extends AppScreen {
    private MainApp mainApp;

    // Menu Screen to either hatch egg, view pet status or go to each pet
    public MenuScreen(MainApp app) {
        super(app, "");
        mainApp = super.app;

        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setBorder(new EmptyBorder(150, 36, 100, 36));
        getButtonPanel(mainPanel);
        add(mainPanel);

        createHomeButton();
    }

    // EFFECTS: add a new Button Panel to MenuScreen Panel
    // with 3 buttons: Hatch, Status and Pets.
    private void getButtonPanel(JPanel mainPanel) {
        JPanel buttonPanel = new RoundedPanel(48, App.SUB_YELLOW, App.MAIN_YELLOW);
        buttonPanel.setBorder(new EmptyBorder(27, 23, 23, 27));
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));

        JLabel menu = new JLabel("Menu");
        menu.setFont(new Font(menu.getFont().getName(), 1, 32));
        menu.setForeground(App.TEXT_YELLOW); // Set text color to TEXT_YELLOW
        menu.setHorizontalAlignment(SwingConstants.CENTER); // Optional: center the label text
        buttonPanel.add(menu, BorderLayout.CENTER);

        getButton(buttonPanel, "HatchScreen", "Hatch new Egg", App.SUB_PINK, App.MAIN_PINK, App.TEXT_PINK);
        getButton(buttonPanel, "StatusScreen", "Go To Pet", App.SUB_GREEN, App.MAIN_GREEN, App.TEXT_GREEN);
        mainPanel.add(buttonPanel);
    }

    // EFFECTS: add button to navigate to PetsChoosing Screen to selected Panel
    private void getButton(JPanel buttonPanel, String screen, String name, Color sub, Color main, Color text) {
        JButton petChoosingButton = new RoundedButton(28, sub, main, text, name);
        petChoosingButton.setPreferredSize(new Dimension(269, 100));
        petChoosingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainApp.showScreen(screen);
            }
        });
        buttonPanel.add(petChoosingButton);
    }

}
