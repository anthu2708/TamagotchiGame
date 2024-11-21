package ui.GUI.Screens.GameScreens;

import javax.swing.*;
import java.awt.*;

import javax.swing.border.EmptyBorder;

import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import ui.GUI.App;
import ui.GUI.PetGameApp;
import ui.GUI.Screens.GameScreen;
import ui.GUI.Screens.CustomizedPanel.RoundedPanel;

public class MainGameScreen extends GameScreen {

    // Main Game - pet display
    public MainGameScreen(PetGameApp petGameApp) {
        super(petGameApp, "");

        JPanel mainPanel = new JPanel();
        JPanel mainPetPanel = new RoundedPanel(32, App.SUB_YELLOW, App.MAIN_YELLOW);
        JLabel petLabel = new JLabel(getRoundedImageIcon());
        JLabel nameLabel = new JLabel(pet.getName());

        nameLabel.setForeground(App.TEXT_YELLOW); 
        nameLabel.setFont(new Font("Arial", Font.BOLD, 32));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        petLabel.setAlignmentX(Component.CENTER_ALIGNMENT); 

        mainPanel.setOpaque(false);
        mainPetPanel.setLayout(new BoxLayout(mainPetPanel, BoxLayout.Y_AXIS)); 
        mainPetPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        mainPanel.setBorder(new EmptyBorder(100, 30, 50, 30));


        mainPetPanel.add(nameLabel);
        mainPetPanel.add(Box.createVerticalStrut(15)); 
        mainPetPanel.add(petLabel);
        mainPanel.add(mainPetPanel);
        add(mainPanel);

        createMenuButton();
    }

    // EFFECTS: return scaled image
    private ImageIcon getRoundedImageIcon() {
        ImageIcon imageIcon = new ImageIcon(handleImagePath());
        Image originalImage = imageIcon.getImage();

        int WIDTH = 300;
        int HEIGHT = WIDTH / 2 * 3;
        int ARC_WIDTH = 28;

        BufferedImage roundedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = roundedImage.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setClip(new RoundRectangle2D.Float(0, 0, WIDTH, HEIGHT, ARC_WIDTH, ARC_WIDTH));
        g2.drawImage(originalImage, 0, 0, WIDTH, HEIGHT, null);
        g2.dispose();

        return new ImageIcon(roundedImage);
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
        } else if (pet.needsAttention()[0]) {
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
