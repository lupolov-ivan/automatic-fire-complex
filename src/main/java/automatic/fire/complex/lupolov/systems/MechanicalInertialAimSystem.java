package automatic.fire.complex.lupolov.systems;

import automatic.fire.complex.lupolov.units.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;

public class MechanicalInertialAimSystem extends AimingSystem {

    Logger log = LoggerFactory.getLogger(MechanicalInertialAimSystem.class);

    @Override
    public double catchTarget(List<Unit> enemies) {

        int enemiesQuantity = enemies.size();
        Random random = new Random();
        int newTargetIndex = (int)(Math.random() * enemiesQuantity);
        Unit newTarget = enemies.get(newTargetIndex);
        log.debug("Catch new target: {}", newTarget);

        if(newTarget.equals(lastTarget)) {
            log.debug("Target caught is the same");
            lastTarget = newTarget;
            countShotSameTarget++;
        } else {
            log.debug("Target caught is new");
            lastTarget = newTarget;
            countShotSameTarget = 1;
        }
        return computeAccuracyFactor(countShotSameTarget, lastTarget.getType());
    }

    private double computeAccuracyFactor(int shotSameTarget, String typeTarget) {
        switch (typeTarget) {
            case "Tank":
            {
                if(shotSameTarget == 1) {
                    double min = 0.2;
                    double max = 0.6;
                    return getCoefficient(min, max);
                } else if (shotSameTarget == 2) {
                    double min = 0.5;
                    double max = 0.8;
                    return getCoefficient(min, max);
                } else if (shotSameTarget >= 3) {
                    double min = 0.8;
                    double max = 1.0;
                    return getCoefficient(min, max);
                }
            }
            case "Infantry":
            {
                if(shotSameTarget == 1) {
                    double min = 0.2;
                    double max = 1.0;
                    return getCoefficient(min, max);
                } else if (shotSameTarget >= 2) {
                    double min = 0.5;
                    double max = 1.0;
                    return getCoefficient(min, max);
                }
            }
            default:
                return 0.0;
        }
    }

    private double getCoefficient(double min, double max) {
        return Math.random() * (max - min) + min;
    }
}
