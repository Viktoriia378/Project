package darksouls_game;

public class Boss implements Fighter{
    private int hp;

    public Boss() {
        this.hp = 150;
    }

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public void takeDamage(int damage){
        this.hp -= damage;
    }

    @Override
    public int attack(){
        return (int)(Math.random() * 20 + 5);
    }
}
