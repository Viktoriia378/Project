package darksouls_game;

public class BattleManager {
    private final Player player;
    private final Boss boss;

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
            int heald = player.heal();
            return "You drank estus and restored " + heald + " HP.";
        }else{
            return "You dont have anymore estus.";
        }
    }

    public String playerDodge(){
        boolean dodged = Math.random() < 0.5;
        if(dodged){
            return "You dodged the attack.";
        } else {
            int dmg = boss.attack();
            player.takeDamage(dmg);
            return "No luck for you.. " + dmg + " damage.";
        }
    }
    public String bossTurn(){
        if(boss.getHp() > 0){
            int dmg = boss.attack();
            player.takeDamage(dmg);
            return "Boss is attacking you and dealing to you " + dmg + " damage.";
        }
        return "";
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
