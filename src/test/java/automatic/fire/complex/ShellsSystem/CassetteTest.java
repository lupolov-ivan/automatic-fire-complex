package automatic.fire.complex.ShellsSystem;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CassetteTest {

    private Shell aps;
    private Cassette<Shell> cassetteAps;

    @Before
    public void setUp() {
        aps = new ArmorPiercingShell();
        cassetteAps = new Cassette<>(2);
    }

    @Test
    public void givenCassetteSize2_whenAddShellToCassette_thenTrueIfNotYetFilledAndFalseIfYet() {
        assertTrue(cassetteAps.add(aps));
        assertTrue(cassetteAps.add(aps));
        assertFalse(cassetteAps.add(aps));
    }

    @Test
    public void givenSizeAndHasNext_whenGetShellFromCassette_thenBalanceDecrementAndTrueIfCassetteIsNotEmpty() {
        cassetteAps.add(aps);
        cassetteAps.add(aps);
        assertTrue(cassetteAps.hasNext());
        assertEquals(cassetteAps.getInstanceInnerElement(), cassetteAps.getShell());
        assertTrue(cassetteAps.getBalance() == 1);
        assertTrue(cassetteAps.hasNext());
        assertEquals(cassetteAps.getInstanceInnerElement(), cassetteAps.getShell());
        assertTrue(cassetteAps.getBalance() == 0);
        assertFalse(cassetteAps.hasNext());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void givenShellFromCassette_whenBalanceOfCassetteIsEmpty_ThenIndexOutOfBoundsException() {
        cassetteAps.getShell();
    }
}

