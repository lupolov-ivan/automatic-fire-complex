package automatic.fire.complex.ShellsSystem;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CassetteTest {

    private Shell shellAPS;
    private Shell shellBS;
    private Cassette<Shell> cassetteAPS;
    private Cassette<Shell> cassetteBS;


    @Before
    public void setUp() {
        shellAPS = new ArmorPiercingShell();
        shellBS = new BurstingShell();
        cassetteAPS = new Cassette<>(2);
        cassetteBS = new Cassette<>(3);

    }

    @Test
    public void add() {
        assertTrue(cassetteAPS.add(shellAPS));

    }

    @Test
    public void getShell() {
    }
}