package darksouls_game;
import javax.swing.*;
import java.awt.*;

public class DarkSoulsWindow extends JFrame {

    private JTextArea logArea;
    private JButton attackButton, estusButton, dodgeButton;
    private JLabel playerHpLabel, bossHpLabel;
    private final BattleManager battle;
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel mainPanel = new JPanel(cardLayout);
    // intro
    private final JPanel introPanel = new JPanel(new BorderLayout());
    private final JLabel imageLabel = new JLabel();
    private final JLabel subtitleLabel = new JLabel("", SwingConstants.CENTER);
    //fight
    private final JPanel battlePanel = new JPanel(new BorderLayout());
    private final JLabel imageBattle = new JLabel();

    public DarkSoulsWindow() {
        Player player = new Player();
        Boss boss = new Boss();
        battle = new BattleManager(player, boss);

        setTitle("Dark Souls Mini");
        setSize(400, 500); //sets the x-dimension and y-dimension
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit out of application
        ImageIcon icon = new ImageIcon("img/256x256.png");
        setIconImage(icon.getImage());


        // panel HP
        JPanel statusPanel = new JPanel(new GridLayout(1, 2));
        playerHpLabel = new JLabel();
        bossHpLabel = new JLabel();
        statusPanel.add(playerHpLabel);
        statusPanel.add(bossHpLabel);

        battlePanel.add(statusPanel, BorderLayout.NORTH);


        // log
        logArea = new JTextArea();
        logArea.setEditable(false);
        battlePanel.add(new JScrollPane(logArea), BorderLayout.CENTER);

        // Panel with buttons
        attackButton = new JButton("Attack");
        estusButton = new JButton("Drink estus");
        dodgeButton = new JButton("Dodge");

        attackButton.addActionListener(e -> handleAction("attack"));
        estusButton.addActionListener(e -> handleAction("heal"));
        dodgeButton.addActionListener(e -> handleAction("dodge"));



        updateStatus();
        log("You woke up by the bonfire... Forward into battle!");

        //intro panel

        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        subtitleLabel.setPreferredSize(new Dimension(400, 100));
        subtitleLabel.setFont(new Font("Serif", Font.PLAIN, 18));

        introPanel.add(imageLabel, BorderLayout.CENTER);
        introPanel.add(subtitleLabel, BorderLayout.SOUTH);

        setVisible(true);
        mainPanel.add(introPanel, "intro");
        mainPanel.add(battlePanel, "battle");
        add(mainPanel);
        startIntroSequence();
    }

    private void setImage(String path) {
        ImageIcon icon = new ImageIcon(path);
        Image scaled = icon.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaled));
    }

    private void startIntroSequence() {
        cardLayout.show(mainPanel, "intro");
        setImage("img/bonfire.png");
        subtitleLabel.setText("You woke up by the bonfire...");
        Timer t1 = new Timer(4500, e1 -> {
            setImage("img/dark.png");
            subtitleLabel.setText("You are going to the dark...");

            Timer t2 = new Timer(4500, e2 -> {
                setImage("img/fight.png");
                subtitleLabel.setText("for the battle!");
                Timer t3 = new Timer(4500, e3 -> {
                    startBattleUI();
                });
                t3.setRepeats(false);
                t3.start();
            });
            t2.setRepeats(false);
            t2.start();
        });
        t1.setRepeats(false);
        t1.start();
    }

    public void handleAction(String action) {
        if (battle.isGameOver()) return;

        switch (action) {
            case "attack" -> log(battle.playerAttack());
            case "heal" -> log(battle.playerHeal());
            case "dodge" -> log(battle.playerDodge());
        }

        if (!battle.isGameOver()) {
            log(battle.bossTurn());
        }

        updateStatus();

        if (battle.isGameOver()) {
            if (battle.playerWon()) {
                log("VICTORY" + "\nSouls received: 500");
            } else {
                log("YOU DEAD");
            }
            disableButtons();
        }
    }

    private void startBattleUI() {
        cardLayout.show(mainPanel, "battle");
        battlePanel.removeAll();
        battlePanel.setLayout(new BoxLayout(battlePanel, BoxLayout.Y_AXIS));
        ImageIcon icon = new ImageIcon("img/fight.png");
        Image scaled = icon.getImage().getScaledInstance(400, 200, Image.SCALE_SMOOTH);
        imageBattle.setIcon(new ImageIcon(scaled));
        battlePanel.add(imageBattle);

        JPanel statusPanel = new JPanel(new GridLayout(1, 2));
        //playerHpLabel = new JLabel();
        //bossHpLabel = new JLabel();
        statusPanel.add(playerHpLabel);
        statusPanel.add(bossHpLabel);
        battlePanel.add(statusPanel);

        battlePanel.add(new JScrollPane(logArea));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(attackButton);
        buttonPanel.add(estusButton);
        buttonPanel.add(dodgeButton);
        battlePanel.add(buttonPanel);

        updateStatus();
        log("He is coming...");
    }

    private void updateStatus() {
        playerHpLabel.setText("Knight HP: " + Math.max(battle.getPlayerHp(), 0));
        bossHpLabel.setText("Boss HP: " + Math.max(battle.getBossHp(), 0));
    }

    public void log(String text) {
        logArea.append(text + "\n");
    }

    private void disableButtons() {
        attackButton.setEnabled(false);
        estusButton.setEnabled(false);
        dodgeButton.setEnabled(false);
    }

    public static void main(String[] args) {
        new DarkSoulsWindow();
    }
}



