package automatic.fire.complex.units.enemy;

import automatic.fire.complex.simulation.RealitySimulationModule;
import automatic.fire.complex.units.Unit;

import java.util.concurrent.TimeUnit;

public abstract class Enemy extends Unit {

    protected int speed;

    public Enemy(int posX, int posY, int protectionLevel, int speed, RealitySimulationModule rsm) {
        super(posX, posY, protectionLevel, rsm);
        this.speed = speed;
    }

    protected void move() {

        while (isAlive()) {

            int oldPosX = posX;
            int oldPosY = posY;

            if(oldPosY == 2) {
                rsm.setCriticalDistanceReached(true);
                break;
            }

            posY = (--posY);

            rsm.updateUnitPosition(oldPosX, oldPosY, this);

            try {
                TimeUnit.SECONDS.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String sendSecretString() {
        return "ENEMY";
    }
}
