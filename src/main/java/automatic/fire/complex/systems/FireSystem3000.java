package automatic.fire.complex.systems;

import automatic.fire.complex.exceptions.ShellJammedException;
import automatic.fire.complex.simulation.EnemyData;

public class FireSystem3000 extends FireSystem {

    private final double shotPeriod = 1;
    private AutomationLoadingSystem automationLoadingSystem;

    public FireSystem3000(AutomationLoadingSystem automationLoadingSystem) {

        this.automationLoadingSystem = automationLoadingSystem;
    }

    public double getShotPeriod() {
        return shotPeriod;
    }


    @Override
    public boolean makeShot(EnemyData data, double accuracyFactor) {

        try {
            isJammed();
        } catch (ShellJammedException ex) {
            automationLoadingSystem.extractShell();
            //todo logger if will be need
        }

        int balance = automationLoadingSystem.getCurrentCassette().getBalance();

        if (balance > 0) {

            automationLoadingSystem.getCurrentCassette().setBalance(--balance);

            double currentDamage = data.getDamage();
            data.setDamage(currentDamage + accuracyFactor);
            try {
                Thread.sleep((long) (shotPeriod * 1000));
            } catch (InterruptedException ignored) {}
            return true;

        } else {
            return false;
        }

    }

}
