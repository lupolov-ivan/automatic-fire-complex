package automatic.fire.complex.lupolov.units;

import java.util.Objects;

public abstract class Unit {

    private int posX;
    private int posY;

    private String type;

    public Unit(int posX, int posY, String type) {
        this.posX = posX;
        this.posY = posY;
        this.type = type;
    }

    abstract public String sendSecretString();

    @Override
    public String toString() {
        return "Unit{" +
                "posX=" + posX +
                ", posY=" + posY +
                ", type='" + type + '\'' +
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

    public int getPosY() {
        return posY;
    }

    public String getType() {
        return type;
    }
}
