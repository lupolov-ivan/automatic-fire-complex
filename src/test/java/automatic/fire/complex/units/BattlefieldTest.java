package automatic.fire.complex.units;

import automatic.fire.complex.simulation.Battlefield;
import automatic.fire.complex.units.enemy.Infantry;
import automatic.fire.complex.units.enemy.Tank;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BattlefieldTest {

    private Battlefield battlefield;

    @Before
    public void setUp() {
        battlefield = new Battlefield(4, 3);
    }

    @Test
    public void givenBattlefieldW4L3_whenGetLastElementX3Y2_thenNullValue() {
        assertNull(battlefield.getCellValue(3,2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void givenBattlefieldW4L3_whenGetElementOutOfBoundsArray_thenIndexOutOfBoundsException() {
        battlefield.getCellValue(3,3);
    }

    @Test
    public void givenBattlefieldW4L3_whenPutUnitToCellX2Y3_thenTrue() {
        Unit expected = new Tank(3, 2, 10);

        battlefield.putUnit(expected);

        assertEquals(expected, battlefield.getCellValue(3,2));
    }

    @Test
    public void givenBattlefieldW4L3_whenPutListUnits_thenTrue() {
        List<Unit> units = new ArrayList<>();

        Unit tank = new Tank(2, 2, 10);
        Unit infantry = new Infantry(3, 2, 10);

        units.add(tank);
        units.add(infantry);

        assertTrue(battlefield.putUnits(units));
        assertEquals(tank,battlefield.getCellValue(2,2));
        assertEquals(infantry,battlefield.getCellValue(3,2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void givenBattlefieldW4L3_whenPutListContainsWrongUnits_thenIndexOutOfBoundsException() {
        List<Unit> units = new ArrayList<>();
        units.add(new Tank(3, 2, 10));
        units.add(new Tank(4, 3, 10));

        battlefield.putUnits(units);
    }

    @Test
    public void givenBattlefieldW4L3_whenRemoveExistingUnitToCellX3Y2_thenNullValue() {
        Unit expected = new Infantry(3, 2, 10);

        battlefield.putUnit(expected);
        assertEquals(expected, battlefield.getCellValue(3,2));

        battlefield.clearCellValue(3,2);
        assertNull(battlefield.getCellValue(3,2));
    }
}