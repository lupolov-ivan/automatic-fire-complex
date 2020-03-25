package automatic.fire.complex.units.enemy;

import automatic.fire.complex.simulation.RealitySimulationModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class Tank extends Enemy implements Runnable {

    Logger log = LoggerFactory.getLogger(Tank.class);

    public Tank(int posX, int posY, int protectionLevel, int speed, RealitySimulationModule rsm) {
        super(posX, posY, protectionLevel, speed, rsm);
    }

    @Override
    void move() {
        while (isAlive() && !rsm.isCriticalDistanceReached()) {
            int currentPosY = getPosY();

            if(currentPosY == 2) {
                rsm.setCriticalDistanceReached(true);
                break;
            }

            setPosY(--currentPosY);

            rsm.getBattlefield().updateUnitPosition();

            try {
                TimeUnit.SECONDS.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        move();
    }
}
