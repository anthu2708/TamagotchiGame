package ui.GUI.Screens.NavButton;

import javax.swing.*;

import ui.GUI.MainApp;
import ui.GUI.PetGameApp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameExitButton extends Button {
    private PetGameApp app;

    public GameExitButton(PetGameApp app) {
        super(app, "X");
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    app.setVisible(false);
                });
            }
        });
    }

}

