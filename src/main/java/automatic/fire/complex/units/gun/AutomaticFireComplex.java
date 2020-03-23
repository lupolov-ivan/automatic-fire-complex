package automatic.fire.complex.units.gun;

//import automatic.fire.complex.ShellsSystem.*;

import automatic.fire.complex.ammunition.Ammunition;
import automatic.fire.complex.ammunition.Cassette;
import automatic.fire.complex.ammunition.TypeShell;
import automatic.fire.complex.simulation.EnemyData;
import automatic.fire.complex.simulation.RealitySimulationModule;
import automatic.fire.complex.systems.Radar;
import automatic.fire.complex.systems.aim.AimingSystem;
import automatic.fire.complex.systems.fire.FireSystem;
import automatic.fire.complex.systems.loading.AutomationLoadingSystem;
import automatic.fire.complex.units.Unit;
import automatic.fire.complex.units.enemy.EnemyType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AutomaticFireComplex extends Unit implements Runnable {

    Logger log = LoggerFactory.getLogger(AutomaticFireComplex.class);

    private AimingSystem aimingSystem;
    private RealitySimulationModule rsm;
    private List<EnemyData> lastPosition;
    private Radar radar;
    private FireSystem fireSystem;
    private AutomationLoadingSystem automationLoadingSystem;
    private EnemyType previousEnemyType;


    public AutomaticFireComplex(int posX, int posY, int protectionLevel, AimingSystem aimingSystem,
                                RealitySimulationModule rsm, FireSystem fireSystem) {
        super(posX, posY, protectionLevel);
        this.radar = new Radar(rsm);
        this.aimingSystem = aimingSystem;
        this.rsm = rsm;
        this.fireSystem = fireSystem;

    }

    @Override
    public String sendSecretString() {
        return "ALLY";
    }

    public void fire() {
        while (true) {
            lastPosition = radar.checkField();
            if (lastPosition.size() == 0) {
                break;
            }
            EnemyData target = aimingSystem.catchTarget(lastPosition);

            Cassette currentCassette = fireSystem.getCurrentCassette();

            if (currentCassette == null) {
                fireSystem.setCurrentCassette(chooseCassette(target.getType()));
            }
            if (currentCassette.getBalance() == 0 || isTargetTypeChanged(target)) {
                if (!fireSystem.changeCassette(chooseCassette(target.getType()))) {
                    break;
                }
            }

            previousEnemyType = target.getType();

            if (!fireSystem.makeShot(target)) {  // нечем стрелять
                break;
            }
            log.debug("AFC '{}' shot to target '{}'", this, target);
            rsm.toDamage(target);
        }
        log.debug("All enemies destroyed. Stopping fire...");
    }

    private boolean isTargetTypeChanged(EnemyData newTarget) {
        if (!previousEnemyType.equals(newTarget.getType())) {
            return true;
        } else {
            return false;
        }
    }

    private TypeShell chooseCassette(EnemyType enemyType) {
        if (enemyType.equals(EnemyType.INFANTRY)) {
            return TypeShell.ARMOR_PIERCING;
        } else {
            return TypeShell.BURSTING;
        }
    }

    public void setAimingSystem(AimingSystem aimingSystem) {
        this.aimingSystem = aimingSystem;
    }

    @Override
    public void run() {
        fire();
    }
}
