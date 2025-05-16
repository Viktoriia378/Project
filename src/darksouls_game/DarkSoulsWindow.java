package darksouls_game;
import javax.swing.*;
import java.awt.*;

public class DarkSoulsWindow extends JFrame {

    private JTextArea logArea;
    private JButton attackButton, estusButton, dodgeButton;
    private JLabel playerHpLabel, bossHpLabel;

    private final BattleManager battle;

    public DarkSoulsWindow() {
        Player player = new Player();
        Boss boss = new Boss();
        battle = new BattleManager(player, boss);

        setTitle("Dark Souls Mini");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        logArea = new JTextArea();
        logArea.setEditable(false);

        // panel HP
        JPanel statusPanel = new JPanel(new GridLayout(1, 2));
        playerHpLabel = new JLabel();
        bossHpLabel = new JLabel();
        statusPanel.add(playerHpLabel);
        statusPanel.add(bossHpLabel);
        add(statusPanel, BorderLayout.NORTH);

        // log

        logArea = new JTextArea();
        logArea.setEditable(false);
        add(new JScrollPane(logArea), BorderLayout.CENTER);

// Panel with buttons

        attackButton = new JButton("Attack");
        estusButton = new JButton("Drink estus");
        dodgeButton = new JButton("Dodge");

        attackButton.addActionListener(e -> handleAction("attack"));
        estusButton.addActionListener(e -> handleAction("heal"));
        dodgeButton.addActionListener(e -> handleAction("dodge"));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(attackButton);
        buttonPanel.add(estusButton);
        buttonPanel.add(dodgeButton);
        add(buttonPanel, BorderLayout.SOUTH);

        updateStatus();
        log("You woke up by the bonefire... Forward into battle!");
        setVisible(true);
    }
    public void handleAction(String action){
        if(battle.isGameOver()) return;

        switch (action) {
            case "attack" -> log (battle.playerAttack());
            case "heal" -> log (battle.playerHeal());
            case "dodge" -> log (battle.playerDodge());
        }

        if(!battle.isGameOver()){
            log(battle.bossTurn());
        }

        updateStatus();

        if(battle.isGameOver()){
            if(battle.playerWon()){
                log("VICTORY" + "\nSouls received: 500");
            } else {
                log("YOU DEAD");
            }
            disableButtons();
        }
    }
    private void updateStatus(){
        playerHpLabel.setText("Knight HP: " + Math.max(battle.getPlayerHp(), 0));
        bossHpLabel.setText("Boss HP: " + Math.max(battle.getBossHp(), 0));
    }
    public void log(String text){
        logArea.append(text + "\n");
    }
    private void disableButtons(){
        attackButton.setEnabled(false);
        estusButton.setEnabled(false);
        dodgeButton.setEnabled(false);
    }

    public static void main(String[] args) {
        new DarkSoulsWindow();
    }
}



