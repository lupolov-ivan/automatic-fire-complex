package automatic.fire.complex.lupolov.simulation;

import automatic.fire.complex.lupolov.units.enemy.Infantry;
import automatic.fire.complex.lupolov.units.enemy.Tank;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RealitySimulationModuleTest {

    private Battlefield battlefield;
    private RealitySimulationModule rsm;
    private Tank tank;
    private Infantry infantry;

    @Before
    public void setUp() {
        battlefield = new Battlefield(3,4);
        rsm = new RealitySimulationModule(battlefield);
        tank = new Tank(0,0, 10);
        infantry = new Infantry(2,1, 10);
        battlefield.putUnit(tank);
        battlefield.putUnit(infantry);
    }

    @Test
    public void givenBattlefieldW3L4_whenGetEmptyCellX1Y1_thenNull() {
        assertNull(rsm.getUnit(1,1));
    }

    @Test
    public void givenBattlefieldW3L4_whenGetFilledCellX0Y0_thenUnit() {
        assertNotNull(rsm.getUnit(0,0));
    }

    @Test
    public void givenBattlefieldW3L4_whenSetDamageUnit_thenUnitProtectionLevelDecreases() {
        EnemyData data = new EnemyData();
        data.setUnit(tank);
        data.setDamage(3);

        rsm.toDamage(data);

        assertEquals(7,tank.getProtectionLevel(), 0);
    }
}