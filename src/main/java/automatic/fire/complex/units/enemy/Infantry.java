package automatic.fire.complex.units.enemy;

import automatic.fire.complex.simulation.RealitySimulationModule;

public class Infantry extends Enemy implements Runnable {

    public Infantry(int posX, int posY, int protectionLevel, int speed, RealitySimulationModule rsm) {
        super(posX, posY, protectionLevel, speed, rsm);
    }

    @Override
    public void run() {
        move();
    }
}
