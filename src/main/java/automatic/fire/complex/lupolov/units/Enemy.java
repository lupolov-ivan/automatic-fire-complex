package automatic.fire.complex.lupolov.units;

public class Enemy extends Unit {

    public Enemy(int posX, int posY, String type) {
        super(posX, posY, type);
    }

    @Override
    public String sendSecretString() {
        return "ENEMY";
    }
}
