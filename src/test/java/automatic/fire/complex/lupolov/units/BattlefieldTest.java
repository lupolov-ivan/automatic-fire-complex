package automatic.fire.complex.lupolov.units;

import automatic.fire.complex.lupolov.simulation.Battlefield;
import automatic.fire.complex.lupolov.units.enemy.Infantry;
import automatic.fire.complex.lupolov.units.enemy.Tank;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BattlefieldTest {

    private Battlefield battlefield;

    @Before
    public void setUp() {
        battlefield = new Battlefield(3, 4);
    }

    @Test
    public void givenBattlefieldW3L4_whenGetLastElementX2Y3_thenNullValue() {
        assertNull(battlefield.getCellValue(2,3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void givenBattlefieldW3L4_whenGetElementOutOfBoundsArray_thenIndexOutOfBoundsException() {
        battlefield.getCellValue(3,3);
    }

    @Test
    public void givenBattlefieldW3L4_whenPutUnitToCellX2Y3_thenTrue() {
        Unit expected = new Tank(2, 3, 10);

        battlefield.putUnit(expected);

        assertEquals(expected, battlefield.getCellValue(2,3));
    }

    @Test
    public void givenBattlefieldW3L4_whenPutListUnits_thenTrue() {
        List<Unit> units = new ArrayList<>();

        Unit tank = new Tank(2, 2, 10);
        Unit infantry = new Infantry(2, 3, 10);

        units.add(tank);
        units.add(infantry);

        assertTrue(battlefield.putUnits(units));
        assertEquals(tank,battlefield.getCellValue(2,2));
        assertEquals(infantry,battlefield.getCellValue(2,3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void givenBattlefieldW3L4_whenPutListContainsWrongUnits_thenIndexOutOfBoundsException() {
        List<Unit> units = new ArrayList<>();
        units.add(new Tank(2, 3, 10));
        units.add(new Tank(4, 3, 10));

        battlefield.putUnits(units);
    }

    @Test
    public void givenBattlefieldW3L4_whenRemoveExistingUnitToCellX2Y3_thenNullValue() {
        Unit expected = new Infantry(2, 3, 10);

        battlefield.putUnit(expected);
        assertEquals(expected, battlefield.getCellValue(2,3));

        battlefield.clearCellValue(2,3);
        assertNull(battlefield.getCellValue(2,3));
    }
}