package automatic.fire.complex.units.gun;

import automatic.fire.complex.ShellsSystem.*;
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
                                FireSystem fireSystem) {
        super(posX, posY, protectionLevel);
        this.radar = new Radar(rsm);
        this.aimingSystem = aimingSystem;
        this.rsm = rsm;
        this.automationLoadingSystem = automationLoadingSystem;
        this.fireSystem = fireSystem;
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
            //////

            Cassette<? extends Shell> currentCassette = automationLoadingSystem.getCurrentCassette();

            if (currentCassette == null || currentCassette.getBalance() == 0) {
                automationLoadingSystem.loadCassette(ammunition.getCassette(target));
                fireSystem.setAutomationLoadingSystem(automationLoadingSystem);

            } else if (target.getType() == EnemyType.TANK &&
                    currentCassette.getInstanceInnerElement().getClass() == BurstingShell.class) {

                ammunition.addCassette(automationLoadingSystem.disconnectCassette());
                automationLoadingSystem.loadCassette(ammunition.getCassette(target));
                fireSystem.setAutomationLoadingSystem(automationLoadingSystem);

            } else if (target.getType() == EnemyType.INFANTRY &&
                    currentCassette.getInstanceInnerElement().getClass() == ArmorPiercingShell.class) {

                ammunition.addCassette(automationLoadingSystem.disconnectCassette());
                automationLoadingSystem.loadCassette(ammunition.getCassette(target));
                fireSystem.setAutomationLoadingSystem(automationLoadingSystem);
            }
            fireSystem.makeShot(target);
            log.debug("AFC '{}' shot to target '{}'", this, target);
            rsm.toDamage(target);
        }
        log.debug("All enemies destroyed. Stopping fire...");
    }

    public void setAimingSystem(AimingSystem aimingSystem) {
        this.aimingSystem = aimingSystem;
    }

    @Override
    public void run() {
        fire();
    }
}
