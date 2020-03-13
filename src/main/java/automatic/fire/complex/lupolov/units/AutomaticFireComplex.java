package automatic.fire.complex.lupolov.units;

import automatic.fire.complex.lupolov.observers.Observer;
import automatic.fire.complex.lupolov.systems.AimingSystem;
import automatic.fire.complex.lupolov.systems.MechanicalInertialAimSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AutomaticFireComplex extends Unit implements Observer {

    Logger log = LoggerFactory.getLogger(AutomaticFireComplex.class);

    private AimingSystem aimingSystem;
    private List<Unit> lastPosition;

    public AutomaticFireComplex(int x, int y, String type) {
        super(x, y, type);
        this.aimingSystem = new MechanicalInertialAimSystem();
    }

    @Override
    public void updatePosition(List<Unit> enemiesPosition) {
        lastPosition = enemiesPosition;
        log.debug("AFC '{}' shot with accuracy factor {}", this, shot());
    }

    @Override
    public String sendSecretString() {
        return "ALLY";
    }

    public double shot() {
        return aimingSystem.catchTarget(lastPosition);
    }

    public void setAimingSystem(AimingSystem aimingSystem) {
        this.aimingSystem = aimingSystem;
    }
}
