package automatic.fire.complex.simulation;

import automatic.fire.complex.units.Unit;
import automatic.fire.complex.units.enemy.EnemyType;

public class EnemyData {

    private Unit unit;
    private EnemyType type;
    private double damage = 0;

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
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
}
