package automatic.fire.complex.units.enemy;

import automatic.fire.complex.units.Unit;

public abstract class Enemy extends Unit {

    protected int hitCount = 0;

    public Enemy(int posX, int posY, int protectionLevel) {
        super(posX, posY, protectionLevel);
    }

    public int getHitCount() {
        return hitCount;
    }

    public void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }

    @Override
    public String sendSecretString() {
        return "ENEMY";
    }
}