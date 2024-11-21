package ui.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

import java.awt.geom.RoundRectangle2D;


public abstract class App extends JFrame {
    public static final Color BACKGROUND_BLUE = new Color(218, 244, 255);
    public static final Color SUB_BLUE = new Color(191, 236, 255);
    public static final Color TEXT_BLUE = new Color(171, 230, 255);


    public static final Color MAIN_YELLOW = new Color(255, 246, 227);
    public static final Color SUB_YELLOW = new Color(255, 225, 161);
    public static final Color TEXT_YELLOW = new Color(255, 173, 0);

    public static final Color MAIN_PURPLE = new Color(205, 193, 255);
    public static final Color SUB_PURPLE = new Color(234, 229, 255);
    public static final Color TEXT_PURPLE = new Color(125, 94, 255);

    public static final Color MAIN_PINK = new Color(255, 204, 234);
    public static final Color SUB_PINK = new Color(255, 233, 246);
    public static final Color TEXT_PINK = new Color(255, 105, 193);

    public static final Color MAIN_GREEN = new Color(195, 255, 191);
    public static final Color SUB_GREEN = new Color(221, 255, 219);
    public static final Color TEXT_GREEN = new Color(91, 201, 83);

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
    abstract void initScreens();


    // EFFECTS: add Exit Button with a choice to Save game Progress or not
    abstract void addExitButton();


    // REQUIRES: screenName has to be created in mainPanel
    // MODIFIES: this
    // EFFECTS: show the specified screen
    public abstract void showScreen(String screenName);


}
