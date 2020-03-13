package automatic.fire.complex.lupolov.systems;

import automatic.fire.complex.lupolov.units.Unit;

import java.util.List;

public abstract class AimingSystem {

    protected Unit lastTarget;
    protected int countShotSameTarget = 1;

    public abstract double catchTarget(List<Unit> enemies);
}
