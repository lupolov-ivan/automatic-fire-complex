package automatic.fire.complex.units.enemy;

import automatic.fire.complex.simulation.RealitySimulationModule;

public class Tank extends Enemy implements Runnable {

    public Tank(int posX, int posY, int protectionLevel, int speed, RealitySimulationModule rsm) {
        super(posX, posY, protectionLevel, speed, rsm);
    }

    @Override
    public void run() {
        move();
    }
}
