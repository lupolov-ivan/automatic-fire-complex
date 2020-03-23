package automatic.fire.complex.systems.loading;

import automatic.fire.complex.ammunition.Ammunition;
import automatic.fire.complex.ammunition.Cassette;
import automatic.fire.complex.ammunition.TypeShell;
import automatic.fire.complex.units.enemy.EnemyType;

public abstract class AutomationLoadingSystem {

    protected Cassette currentCassette;
    protected Ammunition ammunition;
//    protected EnemyType currentEnemyTypeCassette;
//    protected Ammunition ammunition;

    protected AutomationLoadingSystem(Ammunition ammunition) {
        this.ammunition = ammunition;
    }

    abstract public boolean loadCassette(TypeShell typeShell);

    abstract public void disconnectCassette();

    abstract public void extractShell();

    public Cassette getCurrentCassette() {
        return currentCassette;
    }

  //  public EnemyType getCurrentEnemyTypeCassette() {
//        return currentEnemyTypeCassette;
//    }


}
