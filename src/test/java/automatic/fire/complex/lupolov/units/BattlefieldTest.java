package automatic.fire.complex.lupolov.units;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BattlefieldTest {

    private Battlefield battlefield;

    @Before
    public void setUp() {
        battlefield = new Battlefield(3, 4);
    }

    @Test
    public void givenBattlefieldW3L4_whenGetLastElementX2Y3_ThenNullValue() {
        assertNull(battlefield.getCellValue(2,3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void givenBattlefieldW3L4_whenGetElementOutOfBoundsArray_ThenIndexOutOfBoundsException() {
        battlefield.getCellValue(3,3);
    }

    @Test
    public void givenBattlefieldW3L4_whenPutUnitToCellX2Y3_ThenNotNullUnit() {
        Unit expected = new Enemy(2, 3, "Test");

        battlefield.putUnit(expected);

        assertEquals(expected, battlefield.getCellValue(2,3));
        assertEquals("Test", battlefield.getCellValue(2,3).getType());
    }

    @Test
    public void givenBattlefieldW3L4_whenRemoveExistingUnitToCellX2Y3_ThenNullValue() {
        Unit expected = new Enemy(2, 3, "Test");

        battlefield.putUnit(expected);
        assertEquals(expected, battlefield.getCellValue(2,3));

        battlefield.clearCellValue(2,3);
        assertNull(battlefield.getCellValue(2,3));
    }
}