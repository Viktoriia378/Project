package darksouls_game;

public class BattleManager {
    private final Player player;
    private final Boss boss;
    private boolean playerDodge = false;

    public BattleManager(Player player, Boss boss) {
        this.player = player;
        this.boss = boss;
    }

    public String playerAttack(){
        int dmg = player.attack();
        boss.takeDamage(dmg);
        return "You dealt " + dmg + " damage!";
    }

    public String playerHeal(){
        if (player.hasEstus()) {
            int healed = player.heal();
            return "You drank estus and restored " + healed + " HP.";
        }else{
            return "You don't have estus anymore.";
        }
    }

    public String playerDodge(){
        playerDodge = Math.random() < 0.6;
            return playerDodge ? "You protected yourself!" : "You failed to protect yourself.";
        }
    public String bossTurn(){
        if(playerDodge){
            playerDodge = false;
            return "Boss is attacking you but you used shield!";
        }
        int damage = (int)(Math.random() * 20 + 10);
        player.takeDamage(damage);
        return "Boss is attacking you with " + damage + " damage!";
    }
    public boolean isGameOver(){
        return player.getHp() <= 0 || boss.getHp() <= 0;
    }
    public boolean playerWon(){
        return boss.getHp() <= 0;
    }
    public int getPlayerHp(){
        return player.getHp();
    }
    public int getBossHp(){
        return boss.getHp();
    }
}
