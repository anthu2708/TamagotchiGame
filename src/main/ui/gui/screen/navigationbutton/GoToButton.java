package ui.gui.screen.navigationbutton;

import ui.gui.App;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToButton extends Button {

    public GoToButton(App app, String screen) {
        super(app, "data/petimage/bgimage/HomeIcon.png");
        App mainApp = super.app;
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainApp.showScreen(screen);
            }
        });
    }

}

