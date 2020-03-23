package automatic.fire.complex.simulation;

import automatic.fire.complex.units.enemy.EnemyType;

import java.util.Objects;

public class EnemyData {

    private int posX;
    private int posY;
    private EnemyType type;
    private double accuracyFactor;
    private double damage;

    public EnemyData() {
    }

    public EnemyData(int posX, int posY, EnemyType type) {
        this.posX = posX;
        this.posY = posY;
        this.type = type;
    }

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

    public double getAccuracyFactor() {
        return accuracyFactor;
    }

    public void setAccuracyFactor(double accuracyFactor) {
        this.accuracyFactor = accuracyFactor;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnemyData data = (EnemyData) o;
        return posX == data.posX &&
                posY == data.posY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(posX, posY);
    }

    @Override
    public String toString() {
        return "EnemyData{" +
                "posX=" + posX +
                ", posY=" + posY +
                ", type=" + type +
                ", accuracyFactor=" + accuracyFactor +
                ", damage=" + damage +
                '}';
    }
}
