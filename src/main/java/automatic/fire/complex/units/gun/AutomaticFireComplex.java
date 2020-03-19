package automatic.fire.complex.units.gun;

import automatic.fire.complex.simulation.EnemyData;
import automatic.fire.complex.simulation.RealitySimulationModule;
import automatic.fire.complex.systems.Radar;
import automatic.fire.complex.systems.aim.AimingSystem;
import automatic.fire.complex.units.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AutomaticFireComplex extends Unit implements Runnable {

    Logger log = LoggerFactory.getLogger(AutomaticFireComplex.class);

    private AimingSystem aimingSystem;
    private RealitySimulationModule rsm;
    private List<EnemyData> lastPosition;
    private Radar radar;

    public AutomaticFireComplex(int posX, int posY, int protectionLevel, AimingSystem aimingSystem, RealitySimulationModule rsm) {
        super(posX, posY, protectionLevel);
        this.radar = new Radar(rsm);
        this.aimingSystem = aimingSystem;
        this.rsm = rsm;
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
