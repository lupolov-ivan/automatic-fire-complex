package automatic.fire.complex.units.enemy;

import automatic.fire.complex.simulation.RealitySimulationModule;
import automatic.fire.complex.units.Unit;

public abstract class Enemy extends Unit {

    protected int speed;

    public Enemy(int posX, int posY, int protectionLevel, int speed, RealitySimulationModule rsm) {
        super(posX, posY, protectionLevel, rsm);
        this.speed = speed;
    }

    abstract void move();

    @Override
    public String sendSecretString() {
        return "ENEMY";
    }
}
