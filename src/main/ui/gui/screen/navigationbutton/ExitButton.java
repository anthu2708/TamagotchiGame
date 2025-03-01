package ui.gui.screen.navigationbutton;

import javax.swing.*;

import ui.gui.MainApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitButton extends Button {

    public ExitButton(MainApp app) {
        super(app, "data/petimage/bgimage/XIcon.png");
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
                super.app,
                "Do you want to save your progress before exiting?",
                "Exit Confirmation",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            ((MainApp) super.app).saveGame();
            System.exit(0);
        } else if (response == JOptionPane.NO_OPTION) {
            System.exit(0);
        }
    }
}

