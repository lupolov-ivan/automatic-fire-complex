package automatic.fire.complex.systems.aim;

import automatic.fire.complex.simulation.EnemyData;
import automatic.fire.complex.units.enemy.EnemyType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class AimingSystem {

    protected EnemyData lastTarget;
    protected int countShotSameTarget = 1;
    protected Set<EnemyType> ignoreTypes;

    public AimingSystem() {
        this.ignoreTypes = new HashSet<>();
    }

    public abstract EnemyData catchTarget(List<EnemyData> enemies);

    public boolean addTypeToIgnore(EnemyType type) {
        return ignoreTypes.add(type);
    }

    public boolean removeTypeToIgnore(EnemyType type) {
        return ignoreTypes.remove(type);
    }
}
