package main_menu;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame{
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel mainPanel = new JPanel(cardLayout);

    public MainMenu(){
        setTitle("Game Hub");
        setSize(700, 750);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); //screen center

        //panel menu
        mainPanel.add(new MenuPanel(this), "menu");

        //menu in main screen
        add(mainPanel);
        setVisible(true);

    }
    public JPanel getMainPanel(){
        return mainPanel;
    }
    public void showScreen(String name){
        cardLayout.show(mainPanel, name);
    }

    public static void main(String[] args) {
        new MainMenu();
    }
}
