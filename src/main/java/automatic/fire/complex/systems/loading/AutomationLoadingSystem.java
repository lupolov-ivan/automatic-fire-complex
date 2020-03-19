package automatic.fire.complex.systems.loading;

import automatic.fire.complex.ammunition.Ammunition;
import automatic.fire.complex.ammunition.Cassette;
import automatic.fire.complex.units.enemy.EnemyType;

public abstract class AutomationLoadingSystem {

    protected Cassette currentCassette;
    protected EnemyType currentEnemyTypeCassette;
    protected Ammunition ammunition;

    protected AutomationLoadingSystem(Ammunition ammunition) {
        this.ammunition = ammunition;
    }

    abstract public boolean loadCassette(EnemyType data);

    abstract public void disconnectCassette();

    abstract public void extractShell();

    public Cassette getCurrentCassette() {
        return currentCassette;
    }

    public EnemyType getCurrentEnemyTypeCassette() {
        return currentEnemyTypeCassette;
    }
}
