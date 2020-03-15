package automatic.fire.complex.lupolov.units.gun;

import automatic.fire.complex.lupolov.observer.Observer;
import automatic.fire.complex.lupolov.simulation.EnemyData;
import automatic.fire.complex.lupolov.simulation.RealitySimulationModule;
import automatic.fire.complex.lupolov.systems.AimingSystem;
import automatic.fire.complex.lupolov.units.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AutomaticFireComplex extends Unit implements Observer {

    Logger log = LoggerFactory.getLogger(AutomaticFireComplex.class);

    private AimingSystem aimingSystem;
    private RealitySimulationModule rsm;
    private List<EnemyData> lastPosition;
    private double protectionLevel;

    public AutomaticFireComplex(int posX, int posY, double protectionLevel, AimingSystem aimingSystem, RealitySimulationModule rsm) {
        super(posX, posY);
        this.protectionLevel = protectionLevel;
        this.aimingSystem = aimingSystem;
        this.rsm = rsm;
    }

    @Override
    public void updatePosition(List<EnemyData> enemiesPosition) {
        lastPosition = enemiesPosition;
        if (lastPosition.size() == 0) {
            log.debug("All enemies destroyed");
            return;
        }
        EnemyData ed = fire();
        log.debug("AFC '{}' shot with accuracy factor {} to target '{}', type = {}", this, ed.getDamage(), ed.getUnit(), ed.getType());
    }

    @Override
    public String sendSecretString() {
        return "ALLY";
    }

    public EnemyData fire() {
        EnemyData target = aimingSystem.catchTarget(lastPosition);
        rsm.toDamage(target);
        return target;
    }

    public void setAimingSystem(AimingSystem aimingSystem) {
        this.aimingSystem = aimingSystem;
    }
}
