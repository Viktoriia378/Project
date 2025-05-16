package darksouls_game;

public class Player implements Fighter{
    private int hp;
    private int estus;

    public Player(){
        this.hp = 100;
        this.estus = 1;
    }

    @Override
    public int getHp() {
        return hp;
    }

    public int getEstus() {
        return estus;
    }

    @Override
    public void takeDamage(int damage){
        this.hp -= damage;
    }

    @Override
    public int attack(){
        return (int)(Math.random() * 15 + 10);
    }
    public int heal(){
        if(estus > 0){
            int healAmount = (int) (Math.random() * 15 + 10);
            hp += healAmount;
            estus --;
            return healAmount;
        } else {
            return 0;
        }
    }
    public boolean hasEstus(){
        return estus > 0;
    }
}
