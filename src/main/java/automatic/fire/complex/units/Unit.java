package automatic.fire.complex.units;

import java.util.Objects;

public abstract class Unit {

    private int posX;
    private int posY;
    private boolean isAlive;

    public Unit(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        this.isAlive = true;
    }

    abstract public String sendSecretString();

    @Override
    public String toString() {
        return "Unit{" +
                "posX=" + posX +
                ", posY=" + posY +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unit unit = (Unit) o;
        return posX == unit.posX &&
                posY == unit.posY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(posX, posY);
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
