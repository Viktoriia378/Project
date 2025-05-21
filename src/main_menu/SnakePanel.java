package main_menu;

import snake.GamePanel;

import javax.swing.*;
import java.awt.*;

public class SnakePanel extends JPanel {
    public SnakePanel(MainMenu app) {
        setLayout(new BorderLayout());

        GamePanel gameSnake = new GamePanel();
        add(gameSnake, BorderLayout.CENTER);

        JButton backButton = new JButton("\uD83E\uDDE9Back to menu");
        backButton.addActionListener(e -> app.showScreen("menu"));
        add(backButton, BorderLayout.SOUTH);


        SwingUtilities.invokeLater(gameSnake::requestFocusInWindow);
    }
}
