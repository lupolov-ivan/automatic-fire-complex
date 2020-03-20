package automatic.fire.complex.systems.fire;


import automatic.fire.complex.exceptions.ShellJammedException;
import automatic.fire.complex.simulation.EnemyData;
import automatic.fire.complex.systems.loading.AutomationLoadingSystem;

import java.util.Random;

public abstract class FireSystem {
    protected int shotPeriod;

    protected AutomationLoadingSystem automationLoadingSystem;  //

    abstract public boolean makeShot(EnemyData data);

    protected void isJammed() throws ShellJammedException {
        Random random = new Random();

        if (random.nextInt() <= 5) {
            throw new ShellJammedException("Shell is Jammed");
        }

    }

    public AutomationLoadingSystem getAutomationLoadingSystem() {
        return automationLoadingSystem;
    }

    public void setAutomationLoadingSystem(AutomationLoadingSystem automationLoadingSystem) {
        this.automationLoadingSystem = automationLoadingSystem;
    }
}
