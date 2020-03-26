package automatic.fire.complex.systems.fire;

import automatic.fire.complex.exceptions.ShellJammedException;
import automatic.fire.complex.simulation.EnemyData;
import automatic.fire.complex.systems.loading.AutomationLoadingSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class FireSystem3000 extends FireSystem {

    Logger log = LoggerFactory.getLogger(FireSystem3000.class);

    private AutomationLoadingSystem loadingSystem;

    public FireSystem3000(AutomationLoadingSystem loadingSystem) {
        shotPeriod = 1;
        this.loadingSystem = loadingSystem;
    }

    @Override
    public boolean makeShot(EnemyData enemyData) {
        if (enemyData.getType() != loadingSystem.getCurrentEnemyTypeCassette()
                || !loadingSystem.getCurrentCassette().hasNext()) {
            if (!loadingSystem.loadCassette(enemyData.getType())) {
                return false;
            }
        }

        try {
            isJammed();
        } catch (ShellJammedException ex) {
            log.debug("Shell is Jammed. Extracting shell...");
            loadingSystem.extractShell();
        }

        currentShell = loadingSystem.getCurrentCassette().getShell();
        enemyData.setDamage(currentShell.getDamageEnergy() * enemyData.getAccuracyFactor());

        try {
            TimeUnit.SECONDS.sleep(shotPeriod);
        } catch (InterruptedException ignored) {
        }
        return true;
    }

    @Override
    public void noMoreEnemies(){
        loadingSystem.disconnectCassette();
    }
}
