package automatic.fire.complex.systems.loading;

import automatic.fire.complex.ammunition.Ammunition;
import automatic.fire.complex.units.enemy.EnemyType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

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
                TimeUnit.SECONDS.sleep(3);
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
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException ignored) {
        }

        currentEnemyTypeCassette = null;
        currentCassette = null;
    }

    @Override
    public void extractShell() {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException ignored) {
        }
    }
}

