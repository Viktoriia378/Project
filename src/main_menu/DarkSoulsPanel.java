package main_menu;

import darksouls_game.DarkSoulsWindow;

import javax.swing.*;
import java.awt.*;

public class DarkSoulsPanel extends JPanel {

    public DarkSoulsPanel(MainMenu app) {
        setLayout(new BorderLayout());
        DarkSoulsWindow gameDS = new DarkSoulsWindow();
        gameDS.setVisible(false);

        this.add(gameDS.getContentPane(), BorderLayout.CENTER);

        JButton backBtn = new JButton("\uD83E\uDDE9Back to menu");
        backBtn.addActionListener(e -> app.showScreen("menu"));
        add(backBtn, BorderLayout.SOUTH);
    }
}