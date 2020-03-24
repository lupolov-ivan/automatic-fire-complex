package automatic.fire.complex.systems.fire;

import automatic.fire.complex.ammunition.Ammunition;
import automatic.fire.complex.ammunition.TypeShell;
import automatic.fire.complex.simulation.EnemyData;
import automatic.fire.complex.systems.loading.AutomationLoadingSystem;
import automatic.fire.complex.systems.loading.AutomationLoadingSystem3000;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FireSystem3000Test {

    private Ammunition ammunition;
    private AutomationLoadingSystem automationLoadingSystem;
    private FireSystem fireSystem;
    private EnemyData enemyData;


    @Before
    public void setUp() {
        ammunition = Ammunition.createAmmunition(2, 10,
                2, 10);
        automationLoadingSystem = new AutomationLoadingSystem3000(ammunition);
        fireSystem = new FireSystem3000(automationLoadingSystem);
        enemyData = new EnemyData();

    }

    @Test
    public void makeShotTestReturnNotEmptyCassetteToAmmunition() {
        fireSystem.setCurrentCassette(TypeShell.BURSTING);
        fireSystem.makeShot(enemyData);
        fireSystem.makeShot(enemyData);
        fireSystem.changeCassette(TypeShell.BURSTING);

        for (int i = 0; i < 10; i++) {
            fireSystem.makeShot(enemyData);
        }
        fireSystem.changeCassette(TypeShell.BURSTING);
        assert (fireSystem.getCurrentCassette().getBalance() == 8);

    }

    @Test
    public void changeCassetteTest() {
        fireSystem.setCurrentCassette(TypeShell.BURSTING);
        for (int i = 0; i < 10; i++) {
            fireSystem.makeShot(enemyData);
        }
        assert (fireSystem.changeCassette(TypeShell.BURSTING));
        for (int i = 0; i < 10; i++) {
            fireSystem.makeShot(enemyData);
        }
        assert (!fireSystem.changeCassette(TypeShell.BURSTING));

    }



}