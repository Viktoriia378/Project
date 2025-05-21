package main_menu;

import guessingGameGUI.ColorGuess;  // Импортируем класс с игрой

import javax.swing.*;
import java.awt.*;

public class ColorGuessPanel extends JPanel {
    private final ColorGuess game = new ColorGuess();  // Экземпляр игры
    private final JTextArea outputArea = new JTextArea();
    private final JTextField inputField = new JTextField();

    public ColorGuessPanel(MainMenu app) {
        setLayout(new BorderLayout());

        JLabel promptLabel = new JLabel(game.getPrompt(), SwingConstants.CENTER);
        promptLabel.setFont(promptLabel.getFont().deriveFont(Font.BOLD, 16f));
        add(promptLabel, BorderLayout.NORTH);

        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new BorderLayout());
        JButton submitBtn = new JButton("Submit");
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(submitBtn, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);

        submitBtn.addActionListener(e -> {
            String input = inputField.getText().trim().toLowerCase();
            if (!input.isEmpty()) {
                String feedback = game.guess(input);
                outputArea.append("> " + input + "\n" + feedback + "\n\n");
                inputField.setText("");
                if (game.isGuessed()) {
                    promptLabel.setText("You guessed it!");
                    inputField.setEnabled(false);
                }
            }
        });

        JButton backButton = new JButton("⬅ Back to menu");
        backButton.addActionListener(e -> app.showScreen("menu"));
        add(backButton, BorderLayout.NORTH);
    }
}
