package automatic.fire.complex.ShellsSystem;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CassetteTest {

    private Shell aps;
    private Shell bs;
    private Cassette<Shell> cassetteAps;
    private Cassette<Shell> cassetteBs;

    @Before
    public void setUp() {
        aps = new ArmorPiercingShell();
        bs = new BurstingShell();
        cassetteAps = new Cassette<>(2);
        cassetteBs = new Cassette<>(1);
    }


    @Test
    public void add() {
        assertTrue(cassetteAps.add(aps));
        assertTrue(cassetteAps.add(aps));
        assertFalse(cassetteAps.add(aps));

        assertTrue(cassetteBs.add(bs));
        assertFalse(cassetteBs.add(bs));
    }


}