package automatic.fire.complex.lupolov.units;

import automatic.fire.complex.lupolov.observers.Observer;
import automatic.fire.complex.lupolov.AimingSystem;
import automatic.fire.complex.lupolov.MechanicalInertialAimSystem;

import java.util.List;

public class AutomaticFireComplex extends Unit implements Observer {

    private AimingSystem aimingSystem;
    private List<Unit> lastPosition;

    public AutomaticFireComplex(int x, int y, String type) {
        super(x, y, type);
        this.aimingSystem = new MechanicalInertialAimSystem();
    }

    @Override
    public void updatePosition(List<Unit> enemiesPosition) {
        lastPosition = enemiesPosition;
        System.out.printf("AFC '%s' shot with accurancy factor %s%n", this, shot());
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
