package ui.GUI.Screens.NavButton;

import javax.swing.*;

import ui.GUI.MainApp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitButton extends Button {
    private MainApp app;

    public ExitButton(MainApp app) {
        super(app);
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleExitAction();
            }
        });
    }


    // MODIFIES: app
    // EFFECTS: handle exit action: save game progress or not
    private void handleExitAction() {
        int response = JOptionPane.showConfirmDialog(
                app,
                "Do you want to save your progress before exiting?",
                "Exit Confirmation",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            app.saveGame(); // You will need to define the saveGame method
            System.exit(0);
        } else if (response == JOptionPane.NO_OPTION) {
            System.exit(0);
        }
    }
}

