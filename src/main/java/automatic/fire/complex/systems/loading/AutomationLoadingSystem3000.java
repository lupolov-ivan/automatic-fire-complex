package automatic.fire.complex.systems.loading;

import automatic.fire.complex.ammunition.Ammunition;
import automatic.fire.complex.units.enemy.EnemyType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AutomationLoadingSystem3000 extends AutomationLoadingSystem {

    Logger log = LoggerFactory.getLogger(AutomationLoadingSystem3000.class);

    public AutomationLoadingSystem3000(Ammunition ammunition) {
        super(ammunition);
    }

    @Override
    public boolean loadCassette(EnemyType enemyType) {

        if (currentCassette != null) {
            disconnectCassette();
        }

        if (ammunition.hasNext(enemyType)) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ignored) {
            }

            currentCassette = ammunition.getCassette(enemyType);
            currentEnemyTypeCassette = enemyType;
            log.info("Next cassette received");
            return true;
        } else {
            log.info("Shells for target with type '{}' is over.", enemyType);
            return false;
        }
    }

    @Override
    public void disconnectCassette() {
        if (currentCassette.getBalance() != 0) {
            ammunition.addCassette(currentCassette);
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {
        }

        currentEnemyTypeCassette = null;
        currentCassette = null;
    }

    @Override
    public void extractShell() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ignored) {
        }
    }
}

