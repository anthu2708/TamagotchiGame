package ui.GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Game;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.geom.RoundRectangle2D;


public abstract class App extends JFrame {
    protected CardLayout cardLayout;
    protected JPanel mainPanel;

    // main Application's interface
    public App() {

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        setScreenLayout();
    }


    public void setScreenLayout() {
        
        setTitle("Tama");
        setSize(390, 844);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setShape(new RoundRectangle2D.Double(0, 0, 390, 844, 40, 40));
    }

    // MODIFIES: this
    // EFFECT: reload all screens, and add to mainPanel
    abstract public void initScreens();


    // EFFECTS: add Exit Button with a choice to Save game Progress or not
    abstract public void addExitButton();


    // REQUIRES: screenName has to be created in mainPanel
    // MODIFIES: this
    // EFFECTS: show the specified screen
    abstract public void showScreen(String screenName);


}
