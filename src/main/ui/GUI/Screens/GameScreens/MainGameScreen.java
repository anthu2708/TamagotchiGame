package ui.GUI.Screens.GameScreens;

import javax.swing.*;
import java.awt.*;

import ui.GUI.PetGameApp;
import ui.GUI.Screens.GameScreen;

public class MainGameScreen extends GameScreen {

    // Main Game - pet display
    public MainGameScreen(PetGameApp petGameApp) {
        super(petGameApp, "Pet Room");

        ImageIcon imageIcon = new ImageIcon(handleImagePath()); // Replace with your image
                                                                                          // path
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(480, 720, Image.SCALE_SMOOTH);

        JLabel petLabel = new JLabel(new ImageIcon(scaledImage));
        add(petLabel, BorderLayout.CENTER);

        createMenuButton();
    }


    // EFFECTS: return the correct image based on the Pet's Status
    private String handleImagePath() {
        String source = "src\\main\\ui\\GUI\\PetImage\\";
        String petType = pet.getType();
        String petStatus = "";
        if (pet.needsPill()) {
            petStatus = "Injuried";
        } else if (pet.needsAttention()[0] && pet.needsAttention()[1]) {
            petStatus = "HungryDirty";
        } else if (pet.needsAttention()[0] ) {
            petStatus = "Hungry";
        } else if (pet.needsAttention()[1]) {
            petStatus = "Dirty";
        } else if (pet.needsAttention()[2]) {
            petStatus = "Sad";
        }

        if (petStatus == "") {
            return source + petType + ".png";
        } else {
            return source + petStatus + "\\" + petType + ".png";
        }
    }

}
