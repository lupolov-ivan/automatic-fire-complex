package automatic.fire.complex.units.enemy;

import automatic.fire.complex.simulation.RealitySimulationModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Infantry extends Enemy implements Runnable {

    Logger log = LoggerFactory.getLogger(Infantry.class);

    public Infantry(int posX, int posY, int protectionLevel, int speed, RealitySimulationModule rsm) {
        super(posX, posY, protectionLevel, speed, rsm);
    }

    @Override
    public void run() {
        move();
    }
}
