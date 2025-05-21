package main_menu;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    public MenuPanel(main_menu.MainMenu app) {
        setLayout(new GridLayout(4, 1, 10, 10));

        JButton dsB = new JButton("\uD83D\uDD25Dark Souls mini");
        JButton snB = new JButton("\uD83D\uDC0DSnake");
        JButton ticB = new JButton("❌Tic Tac Toe⭕\uFE0F");
        JButton guessB = new JButton("\uD83E\uDDE0Guess Color");
        JButton exitBtn = new JButton("Exit");

        dsB.addActionListener(e -> {
            app.getMainPanel().add(new DarkSoulsPanel(app), "darksouls");
            app.showScreen("darksouls");
            snB.addActionListener(e1 -> {
                app.getMainPanel().add(new SnakePanel(app), "snake");
                app.showScreen("snake");
                ticB.addActionListener(e2 -> {


                    //guessB.addActionListener(e -> app.showScreen("guess"));
                    //exitBtn.addActionListener(e -> System.exit(0));

                    //add(snB);
                    //add(ticB);
                    //add(guessB);
                    //add(exitBtn);
                });
            });
        });
        add(dsB);
        add(snB);
        add(ticB);
    }
}
