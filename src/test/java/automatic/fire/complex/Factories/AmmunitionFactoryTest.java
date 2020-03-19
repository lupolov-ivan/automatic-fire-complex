package automatic.fire.complex.Factories;

import automatic.fire.complex.ShellsSystem.Ammunition;
import automatic.fire.complex.ShellsSystem.Cassette;
import automatic.fire.complex.ShellsSystem.Shell;
import automatic.fire.complex.ShellsSystem.TypeShell;
import automatic.fire.complex.simulation.EnemyData;
import automatic.fire.complex.units.enemy.EnemyType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AmmunitionFactoryTest {

    Ammunition ammunition;
    EnemyData enemyDataAPS;
    EnemyData enemyDataBS;

    @Before
    public void setUp() {
        ammunition = new Ammunition(1, 1);
        enemyDataAPS = new EnemyData();
        enemyDataBS = new EnemyData();
        enemyDataBS.setType(EnemyType.INFANTRY);
        enemyDataAPS.setType(EnemyType.TANK);
    }

    @Test
    public void givenQuantitiesOfLists_whenAmmunitionWasCreatedA1B1_thenSizeIsA1B1(){
        assertTrue(ammunition.getListOfBurstingS().size() == 1);
        assertTrue(ammunition.getListOfArmorPS().size() == 1);

        assertEquals(ammunition.getListOfArmorPS().getFirst(), ammunition.getCassette(enemyDataAPS));
        assertEquals(ammunition.getListOfBurstingS().getFirst(), ammunition.getCassette(enemyDataBS));

        assertTrue(ammunition.getListOfArmorPS().size() == 0);
        assertTrue(ammunition.getListOfBurstingS().size() == 0);

        Cassette<Shell> cassette = CassetteFactory.createCassette(5, TypeShell.ARMOR);

        ammunition.addCassette(cassette);

        assertTrue(ammunition.getListOfArmorPS().size() == 1);
        assertTrue(ammunition.getListOfArmorPS().getLast().getBalance() == 5);
    }
}