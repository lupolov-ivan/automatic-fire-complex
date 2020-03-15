package automatic.fire.complex.lupolov.units.enemy;

import automatic.fire.complex.lupolov.units.Unit;

public abstract class Enemy extends Unit {

    protected double protectionLevel;
    protected int hitCount = 0;

    public Enemy(int posX, int posY, double protectionLevel) {
        super(posX, posY);
        this.protectionLevel = protectionLevel;
    }

    public double getProtectionLevel() {
        return protectionLevel;
    }

    public void setProtectionLevel(double protectionLevel) {
        this.protectionLevel = protectionLevel;
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
