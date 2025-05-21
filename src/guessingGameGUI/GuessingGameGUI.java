package guessingGameGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Simple Swing GUI for the guessing game.
 */
public class GuessingGameGUI {

    private final GuessingGame game = new GuessingGame();
    private final JTextArea outputArea = new JTextArea();
    private final JTextField inputField = new JTextField();
    private final JLabel promptLabel = new JLabel();

    public GuessingGameGUI() {
        JFrame frame = new JFrame("Guessing Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 320);
        frame.setLayout(new BorderLayout(10, 10));

        promptLabel.setFont(promptLabel.getFont().deriveFont(Font.BOLD, 16f));
        promptLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        frame.add(promptLabel, BorderLayout.NORTH);

        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        frame.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        JButton submitButton = new JButton("Submit");

        bottomPanel.add(inputField, BorderLayout.CENTER);
        bottomPanel.add(submitButton, BorderLayout.EAST);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Update initial prompt
        promptLabel.setText(game.getCurrentPrompt());

        ActionListener sendAction = e -> {
            String text = inputField.getText().trim();
            if (text.isEmpty()) return;
            outputArea.append("> " + text + "\n");
            String feedback = game.processInput(text);
            outputArea.append(feedback + "\n\n");
            inputField.setText("");

            if (game.isFinished()) {
                promptLabel.setText("Game finished!");
                inputField.setEnabled(false);
            } else {
                promptLabel.setText(game.getCurrentPrompt());
            }
        };

        submitButton.addActionListener(sendAction);
        inputField.addActionListener(sendAction);

        frame.setLocationRelativeTo(null); // center on screen
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GuessingGameGUI::new);
    }
}
