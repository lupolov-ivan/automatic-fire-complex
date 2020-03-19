package automatic.fire.complex.systems;

import automatic.fire.complex.ShellsSystem.BurstingShell;
import automatic.fire.complex.ShellsSystem.Cassette;
import automatic.fire.complex.ShellsSystem.Shell;
import automatic.fire.complex.simulation.EnemyData;
import automatic.fire.complex.systems.fire.FireSystem;
import automatic.fire.complex.systems.fire.FireSystem3000;
import automatic.fire.complex.systems.loading.AutomationLoadingSystem;
import automatic.fire.complex.systems.loading.AutomationLoadingSystem3000;
import automatic.fire.complex.units.Unit;
import automatic.fire.complex.units.enemy.Infantry;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FireSystem3000Test {

    private FireSystem fireSystem;
    private Unit unit;
    private EnemyData enemyData;
    private AutomationLoadingSystem automationLoadingSystem;

    @Before
    public void setUp() {

        automationLoadingSystem = new AutomationLoadingSystem3000();
        automationLoadingSystem.loadCassette(getNewCassette());
        fireSystem = new FireSystem3000(automationLoadingSystem);

        unit = new Infantry(1, 1, 3);
        enemyData = new EnemyData();
        enemyData.setPosX(unit.getPosX());
        enemyData.setPosY(unit.getPosY());
    }

    @Test
    public void makeShotTestDamage() {
        enemyData.setAccuracyFactor(0.3);
        fireSystem.makeShot(enemyData);
        assertEquals(0.3, enemyData.getDamage(),0);

        enemyData.setAccuracyFactor(0.4);
        fireSystem.makeShot(enemyData);
        assertEquals(0.7, enemyData.getDamage(),0);
    }

    @Test
    public void makeShotTestCassetteBalance() {
        int startBalance = automationLoadingSystem.getCurrentCassette().getBalance();
        enemyData.setAccuracyFactor(0.3);
        fireSystem.makeShot(enemyData);
        int balanceAfterShot = automationLoadingSystem.getCurrentCassette().getBalance();
        assertEquals(startBalance - 1, balanceAfterShot);
    }


    private Cassette<? extends Shell> getNewCassette() {
        Cassette<BurstingShell> cassette = new Cassette<>(10);
        for (int i = 0; i < 10; i++) {
            cassette.add(new BurstingShell());
        }
        return cassette;
    }
}
