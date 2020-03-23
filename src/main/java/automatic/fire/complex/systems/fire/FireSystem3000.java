package automatic.fire.complex.systems.fire;


import automatic.fire.complex.ammunition.Cassette;
import automatic.fire.complex.ammunition.TypeShell;
import automatic.fire.complex.exceptions.ShellJammedException;
import automatic.fire.complex.simulation.EnemyData;
import automatic.fire.complex.systems.loading.AutomationLoadingSystem;

public class FireSystem3000 extends FireSystem {

    private final double shotPeriod = 1;
    //   private AutomationLoadingSystem automationLoadingSystem;
 //   private Cassette currentCassette;

    public FireSystem3000(AutomationLoadingSystem automationLoadingSystem) {

        super(automationLoadingSystem);
    }


    public double getShotPeriod() {
        return shotPeriod;
    }


    @Override
    public boolean makeShot(EnemyData data) {
        Cassette currentCassette = automationLoadingSystem.getCurrentCassette();

        try {
            isJammed();
        } catch (ShellJammedException ex) {
            automationLoadingSystem.extractShell();
            //todo logger if will be need
        }

        if (currentCassette.hasNext()) {

            currentCassette.getShell();
            double currentDamage = data.getDamage();
            data.setDamage(currentDamage + data.getAccuracyFactor());
//            try {
//                Thread.sleep((long) (shotPeriod * 1000));
//            } catch (InterruptedException ignored) {
//            }
            return true;

        } else {
            return false;
        }

    }

    public AutomationLoadingSystem getAutomationLoadingSystem() {
        return automationLoadingSystem;
    }

    @Override
    public boolean changeCassette(TypeShell typeShell) {

        automationLoadingSystem.disconnectCassette();
       return automationLoadingSystem.loadCassette(typeShell);

    }


    @Override
    public Cassette getCurrentCassette() {
        return automationLoadingSystem.getCurrentCassette();
    }

    public void setCurrentCassette(TypeShell typeShell){
        automationLoadingSystem.loadCassette(typeShell);
    }
}
