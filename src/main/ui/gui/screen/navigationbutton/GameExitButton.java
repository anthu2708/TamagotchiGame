package ui.gui.screen.navigationbutton;

import javax.swing.*;

import ui.gui.PetGameApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameExitButton extends Button {

    public GameExitButton(PetGameApp app) {
        super(app, "data/petimage/bgimage/XIcon.png");
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

