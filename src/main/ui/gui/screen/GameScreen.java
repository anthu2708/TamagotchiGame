package ui.gui.screen;

import java.awt.*;

import javax.swing.*;

import model.Game;
import model.pet.Pet;
import ui.gui.PetGameApp;
import ui.gui.screen.navigationbutton.GoToButton;

public class GameScreen extends JPanel {
    protected String backgroundPath = "data/petimage/bgimage/BackGroundScreen.png";
    protected PetGameApp app;
    protected Game game;
    protected Pet pet;

    public GameScreen(PetGameApp app, String name) {
        this.app = app;
        this.game = app.getGame();
        this.pet = app.getPet();

        setLayout(new BorderLayout());
        initHeaderPanel(name);
    }

    // EFFECTS: add header with specified name for
    public void initHeaderPanel(String title) {
        JLabel headerLabel = new JLabel(title, JLabel.CENTER);
        headerLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(headerLabel, BorderLayout.NORTH);
    }

    // EFFECTS: customize background
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image backgroundImage = new ImageIcon(backgroundPath).getImage();
        // Draw the image as the background, scaled to the size of the panel
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    // EFFECTS: add Menu Button to return to the Menu Screen when pressed
    public void createMenuButton() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        GoToButton goToButton = new GoToButton(app, "GameMenuScreen");
        buttonPanel.add(goToButton, BorderLayout.CENTER);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    // EFFECTS: display coin add Menu Button to return to the Menu Screen when
    // pressed
    public void createStoreCoinButton() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1));
        mainPanel.setOpaque(false);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        GoToButton goToButton = new GoToButton(app, "GameMenuScreen");
        goToButton.setBounds(40, 10, 30, 30);
        buttonPanel.add(goToButton);

        JPanel coinPanel = new JPanel();
        coinPanel.setOpaque(false);
        coinPanel.add(new JLabel("Coin: " + game.getCoinManager().getValue()));
        mainPanel.add(coinPanel);

        mainPanel.add(buttonPanel);
        add(mainPanel, BorderLayout.SOUTH);
    }

    // EFFECTS: add Menu Button to return to the Menu Screen when pressed
    public void createStoreMenuButton() {
        GoToButton goToButton = new GoToButton(app, "GameMenuScreen");
        goToButton.setBounds(40, 10, 30, 30);
        add(goToButton);
    }

    // EFFECTS: add Menu Button to return to the Menu Screen when pressed
    public void createHomeButton() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        GoToButton goToButton = new GoToButton(app, "GameScreen");
        buttonPanel.add(goToButton, BorderLayout.CENTER);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: reload all Screen and go to screen
    public void reloadScreens(String screen) {
        app.initScreens();
        app.showScreen(screen);
    }
}
