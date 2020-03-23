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
    private Ammunition ammunition;
    private FireSystem fireSystem;
    private AutomationLoadingSystem automationLoadingSystem;

    public AutomaticFireComplex(int posX, int posY, int protectionLevel, AimingSystem aimingSystem,
                                RealitySimulationModule rsm, AutomationLoadingSystem automationLoadingSystem,
                                FireSystem fireSystem, Ammunition ammunition) {
        super(posX, posY, protectionLevel);
        this.radar = new Radar(rsm);
        this.aimingSystem = aimingSystem;
        this.rsm = rsm;
        this.automationLoadingSystem = automationLoadingSystem;
        this.fireSystem = fireSystem;
        this.ammunition = ammunition;
        //   fireSystem.setAutomationLoadingSystem(automationLoadingSystem);
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

            Cassette currentCassette = automationLoadingSystem.getCurrentCassette();

            if (!changeСassette(currentCassette, target)) {
                break;
            }

            fireSystem.makeShot(target);
            log.debug("AFC '{}' shot to target '{}'", this, target);
            rsm.toDamage(target);
        }
        log.debug("All enemies destroyed. Stopping fire...");
    }


    private boolean changeСassette(Cassette currentCassette, EnemyData enemyData) {

        if (!ammunition.hasNext(enemyData.getType())) {
            return false;
        }

        if (currentCassette == null || currentCassette.getBalance() == 0) {

            automationLoadingSystem.loadCassette(ammunition.getCassette(enemyData.getType()));
            fireSystem.setAutomationLoadingSystem(automationLoadingSystem);
            return true;

        } else if (enemyData.getType() == EnemyType.TANK &&
                currentCassette.getTypeShell().equals(TypeShell.BURSTING)) {


            ammunition.addCassette(automationLoadingSystem.disconnectCassette());
            automationLoadingSystem.loadCassette(ammunition.getCassette(enemyData.getType()));
            fireSystem.setAutomationLoadingSystem(automationLoadingSystem);
            return true;

        } else if (enemyData.getType() == EnemyType.INFANTRY &&
                currentCassette.getTypeShell().equals(TypeShell.ARMOR_PIERCING)) {

            ammunition.addCassette(automationLoadingSystem.disconnectCassette());
            automationLoadingSystem.loadCassette(ammunition.getCassette(enemyData.getType()));
            fireSystem.setAutomationLoadingSystem(automationLoadingSystem);
            return true;
        }
        return true;

    }

    public void setAimingSystem(AimingSystem aimingSystem) {
        this.aimingSystem = aimingSystem;
    }

    @Override
    public void run() {
        fire();
    }
}
