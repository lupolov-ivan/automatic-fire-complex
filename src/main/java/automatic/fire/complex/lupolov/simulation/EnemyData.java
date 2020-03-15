package automatic.fire.complex.lupolov.simulation;

import automatic.fire.complex.lupolov.units.Unit;

public class EnemyData {

    private Unit unit;
    private String type;
    private double damage = 0;

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }
}
