package automatic.fire.complex.lupolov.systems;

import automatic.fire.complex.lupolov.simulation.EnemyData;

import java.util.List;

public abstract class AimingSystem {

    protected EnemyData lastTarget;
    protected int countShotSameTarget = 1;

    public abstract EnemyData catchTarget(List<EnemyData> enemies);
}
