package main_menu;

import tic_tac_toe.TicTacToe;

import javax.swing.*;
import java.awt.*;

public class TicTacToePanel extends JPanel {

    public TicTacToePanel(MainMenu app) {
        setLayout(new BorderLayout());


        TicTacToe gameTicTacToe = new TicTacToe();
        gameTicTacToe.main(new String[0]);

        JButton backButton = new JButton("\uD83E\uDDE9Back to menu");
        backButton.addActionListener(e -> app.showScreen("menu"));
        add(backButton, BorderLayout.SOUTH);
    }
}