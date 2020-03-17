package automatic.fire.complex.systemsTest;

import automatic.fire.complex.ShellsSystem.BurstingShell;
import automatic.fire.complex.ShellsSystem.Cassette;
import automatic.fire.complex.lupolov.simulation.EnemyData;
import automatic.fire.complex.lupolov.systems.AutomationLoadingSystem;
import automatic.fire.complex.lupolov.systems.AutomationLoadingSystem3000;
import automatic.fire.complex.lupolov.systems.FireSystem;
import automatic.fire.complex.lupolov.systems.FireSystem3000;
import automatic.fire.complex.lupolov.units.Unit;
import automatic.fire.complex.lupolov.units.enemy.Infantry;
import org.junit.Before;
import org.junit.Test;

public class FireSystem3000Test {

    private FireSystem fireSystem;
    private Unit unit;
    private EnemyData enemyData;
    private AutomationLoadingSystem automationLoadingSystem;

    @Before
    public void setUp() {

        automationLoadingSystem = new AutomationLoadingSystem3000();
        automationLoadingSystem.loadCassette(new Cassette<>(new BurstingShell()));
        fireSystem = new FireSystem3000(automationLoadingSystem);
        unit = new Infantry(1, 1, 3);
        enemyData = new EnemyData();
        enemyData.setUnit(unit);

    }

    @Test
    public void makeShotTestDamage(){
        fireSystem.makeShot(enemyData, 0.3);
        assert(enemyData.getDamage() == 0.3);
        fireSystem.makeShot(enemyData, 0.4);
        assert(enemyData.getDamage() == 0.7);
    }

    @Test
    public void makeShotTestShotPeriodTime(){
        long startTime = System.currentTimeMillis();
        fireSystem.makeShot(enemyData,0.3);
        fireSystem.makeShot(enemyData,0.4);
        assert((System.currentTimeMillis() - startTime)> 2000 );
    }

    @Test
    public void  makeShotTestCassetteBalance(){
        int startBalance = automationLoadingSystem.getCurrentCassette().getBalance();
        fireSystem.makeShot(enemyData,0.3);
        int balanceAfterShot = automationLoadingSystem.getCurrentCassette().getBalance();
        assert (startBalance == ( balanceAfterShot+1));
    }



}
