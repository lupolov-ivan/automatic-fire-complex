package automatic.fire.complex.systems.fire;

import automatic.fire.complex.ShellsSystem.Cassette;
import automatic.fire.complex.ShellsSystem.Shell;
import automatic.fire.complex.exceptions.ShellJammedException;
import automatic.fire.complex.simulation.EnemyData;
import automatic.fire.complex.systems.loading.AutomationLoadingSystem;

public class FireSystem3000 extends FireSystem {

    private final double shotPeriod = 1;
 //   private AutomationLoadingSystem automationLoadingSystem;

    public FireSystem3000(){}

    public FireSystem3000(AutomationLoadingSystem automationLoadingSystem) {

        this.automationLoadingSystem = automationLoadingSystem;
    }


    public double getShotPeriod() {
        return shotPeriod;
    }


    @Override
    public boolean makeShot(EnemyData data) {
        Cassette<? extends Shell> currentCassette = automationLoadingSystem.getCurrentCassette();

        try {
            isJammed();
        } catch (ShellJammedException ex) {
            automationLoadingSystem.extractShell();
            //todo logger if will be need
        }

        if  (currentCassette.hasNext()) {

            currentCassette.getShell();
            double currentDamage = data.getDamage();
            data.setDamage(currentDamage + data.getAccuracyFactor());
            try {
                Thread.sleep((long) (shotPeriod * 1000));
            } catch (InterruptedException ignored) {}
            return true;

        } else {
            return false;
        }

    }

    public AutomationLoadingSystem getAutomationLoadingSystem() {
        return automationLoadingSystem;
    }
}
