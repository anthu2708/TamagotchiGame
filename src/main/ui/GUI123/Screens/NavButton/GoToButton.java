package ui.GUI.Screens.NavButton;
import javax.swing.*;

import ui.GUI.App;
import ui.GUI.MainApp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToButton extends Button {

    public GoToButton(App app, String screen) {
        super(app, "src\\main\\ui\\GUI\\PetImage\\BackGround\\HomeIcon.png");
        App mainApp = super.app;
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainApp.showScreen(screen);
            }
        });
    }

}

