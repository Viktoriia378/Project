package main_menu;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    public MenuPanel(main_menu.MainMenu app) {
        setLayout(new GridLayout(6, 1, 10, 10));

        JButton dsB = new JButton("\uD83D\uDD25Dark Souls mini");
        JButton snB = new JButton("\uD83D\uDC0DSnake");
        JButton ticB = new JButton("❌Tic Tac Toe⭕");
        JButton guessB = new JButton("\uD83C\uDFA8Guess Color");
        JButton exitBtn = new JButton("❌Exit");

        dsB.addActionListener(e -> {
            app.getMainPanel().add(new DarkSoulsPanel(app), "darksouls");
            app.showScreen("darksouls");
        });
        snB.addActionListener(e -> {
            app.getMainPanel().add(new SnakePanel(app), "snake");
            app.showScreen("snake");
        });
        guessB.addActionListener(e -> {
            app.getMainPanel().add(new ColorGuessPanel(app), "guess");
            app.showScreen("guess");
        });
        ticB.addActionListener(e -> {
            app.getMainPanel().add(new TicTacToePanel(app), "tictactoe");
            app.showScreen("tictactoe");
        });

        exitBtn.addActionListener(e -> System.exit(0));
        add(dsB);
        add(snB);
        add(guessB);
        add(ticB);
        add(exitBtn);

    }
}
