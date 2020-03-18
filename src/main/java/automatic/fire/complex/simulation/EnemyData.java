package automatic.fire.complex.simulation;

import automatic.fire.complex.units.enemy.EnemyType;

public class EnemyData {

    private int posX;
    private int posY;
    private EnemyType type;
    private double damage = 0;

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public EnemyType getType() {
        return type;
    }

    public void setType(EnemyType type) {
        this.type = type;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }
  
    @Override
    public String toString() {
        return "EnemyData{" +
                "posX=" + posX +
                ", posY=" + posY +
                ", type=" + type +
                ", damage=" + damage +
                '}';
    }
}
