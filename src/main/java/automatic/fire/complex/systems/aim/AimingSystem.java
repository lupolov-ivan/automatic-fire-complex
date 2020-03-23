package automatic.fire.complex.systems.aim;

import automatic.fire.complex.simulation.EnemyData;
import automatic.fire.complex.units.enemy.EnemyType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class AimingSystem {

    protected EnemyData lastTarget;
    protected int countShotSameTarget = 1;

    public abstract EnemyData catchTarget(List<EnemyData> enemies);
}
