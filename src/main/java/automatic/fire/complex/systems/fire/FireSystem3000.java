package automatic.fire.complex.systems.fire;

import automatic.fire.complex.simulation.EnemyData;
import automatic.fire.complex.systems.loading.AutomationLoadingSystem;

public class FireSystem3000 extends FireSystem {

    private final double shotPeriod = 1;

    private AutomationLoadingSystem loadingSystem;

    public FireSystem3000(AutomationLoadingSystem loadingSystem) {
        this.loadingSystem = loadingSystem;
    }

    @Override
    public boolean makeShot(EnemyData enemyData) {
        if (enemyData.getType() != loadingSystem.getCurrentEnemyTypeCassette() || !loadingSystem.getCurrentCassette().hasNext()) {
            if(!loadingSystem.loadCassette(enemyData.getType())) {
                return false;
            }
        }

        currentShell = loadingSystem.getCurrentCassette().getShell();
        enemyData.setDamage(currentShell.getDamageEnergy() * enemyData.getAccuracyFactor());
        return true;
    }
}
