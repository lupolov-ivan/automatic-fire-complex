package automatic.fire.complex.systems;


import automatic.fire.complex.exceptions.ShellJammedException;
import automatic.fire.complex.simulation.EnemyData;

import java.util.Random;

public abstract class FireSystem {
    protected int shotPeriod;
    //  protected Cassette<?> currentCassette; // хз
    private AutomationLoadingSystem automationLoadingSystem;  //


    abstract public boolean makeShot(EnemyData data, double accuracyFactor);

    protected void isJammed() throws ShellJammedException {
        Random random = new Random();

        if (random.nextInt() <= 5) {
            throw new ShellJammedException("Shell is Jammed");
        }

    }
}
