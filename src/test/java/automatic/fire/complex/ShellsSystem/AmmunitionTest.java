package automatic.fire.complex.ShellsSystem;

import automatic.fire.complex.simulation.EnemyData;
import automatic.fire.complex.units.enemy.EnemyType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AmmunitionTest {

    Cassette<Shell> cassetteAPS;
    Cassette<Shell> cassetteBS;
    Ammunition ammunition;
    Shell aps;
    Shell bs;
    EnemyData enemyDataTank;
    EnemyData enemyDataInfantry;
    EnemyData enemyDataUnknown;

    @Before
    public void setUp(){
        cassetteAPS = new Cassette<>(1);
        cassetteBS = new Cassette<>(1);
        aps = new ArmorPiercingShell();
        bs = new BurstingShell();
        cassetteAPS.add(aps);
        cassetteBS.add(bs);
        ammunition = new Ammunition();
        enemyDataTank = new EnemyData();
        enemyDataTank.setType(EnemyType.TANK);
        enemyDataInfantry = new EnemyData();
        enemyDataInfantry.setType(EnemyType.INFANTRY);
        enemyDataUnknown = new EnemyData();
        enemyDataUnknown.setType(EnemyType.UNKNOWN);

    }

    @Test
    public void givenAmmunition2Lists_whenAddCassetteToAmmunition_thenSizeOfListsMustBeIncremental(){
        ammunition.addCassette(cassetteAPS);
        assertTrue(ammunition.getListOfArmorPS().size() == 1);
        ammunition.addCassette(cassetteBS);
        assertTrue(ammunition.getListOfBurstingS().size() == 1);

        assertEquals(ammunition.getListOfBurstingS().getFirst().getInstanceInnerElement().getClass(), BurstingShell.class);
        assertEquals(ammunition.getListOfArmorPS().getFirst().getInstanceInnerElement().getClass(), ArmorPiercingShell.class);
    }

    @Test
    public void givenSizeAndHasNext_whenGetCassetteFromAmmunition_thenBalanceDecrementAndTrueIfListsAreNotEmpty(){
        ammunition.addCassette(cassetteAPS);
        ammunition.addCassette(cassetteBS);
        assertTrue(ammunition.getListOfArmorPS().size() == 1);
        assertTrue(ammunition.getListOfBurstingS().size() == 1);
        assertTrue(ammunition.hasNext(enemyDataTank));
        assertTrue(ammunition.hasNext(enemyDataInfantry));
        assertEquals(ammunition.getListOfArmorPS().getFirst().getClass(), ammunition.getCassette(enemyDataTank).getClass());
        assertEquals(ammunition.getListOfBurstingS().getFirst().getClass(), ammunition.getCassette(enemyDataInfantry).getClass());

        assertTrue(ammunition.getListOfArmorPS().size() == 0);
        assertTrue(ammunition.getListOfBurstingS().size() == 0);

        assertFalse(ammunition.hasNext(enemyDataTank));
        assertFalse(ammunition.hasNext(enemyDataInfantry));

        assertNull(ammunition.getCassette(enemyDataUnknown));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void givenCassetteFromAmmunition_whenListsInAmmunitionAreEmpty_thenMustBeNull(){
        ammunition.getCassette(enemyDataTank);
        ammunition.getCassette(enemyDataInfantry);
    }
}