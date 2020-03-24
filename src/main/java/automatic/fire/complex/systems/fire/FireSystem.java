package automatic.fire.complex.systems.fire;


import automatic.fire.complex.ammunition.Shell;
import automatic.fire.complex.exceptions.ShellJammedException;
import automatic.fire.complex.simulation.EnemyData;

import java.util.Random;

public abstract class FireSystem {

    protected int shotPeriod;
    protected Shell currentShell;

    abstract public boolean makeShot(EnemyData data);

    protected void isJammed() throws ShellJammedException {
        Random random = new Random();

        if (random.nextInt(100) < 5) {
            throw new ShellJammedException("Shell is Jammed");
        }
    }

    public int getShotPeriod() {
        return shotPeriod;
    }
}
