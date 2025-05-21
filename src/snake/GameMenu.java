package snake;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;

public class GameMenu extends JFrame {
    private JPanel mainPanel = new JPanel();
    private LinkedHashMap<String, Runnable> gameLaunchers = new LinkedHashMap<>();

    public GameMenu() {
        this.setTitle("Game Girl Menu");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("🎮 Game Girl Menu 🎮", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 34));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(title);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Ajoute les boutons plus tard avec addGame
        this.add(mainPanel);
        this.setVisible(false);
    }
    public void addGame(String gameName, Runnable launchFunction) {
        JButton button = new JButton(gameName);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(e -> {
            dispose(); // ferme le menu
            launchFunction.run(); // lance le jeu
        });

        mainPanel.add(button);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        this.revalidate();
    }

}
