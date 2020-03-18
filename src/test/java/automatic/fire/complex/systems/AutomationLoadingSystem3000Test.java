package automatic.fire.complex.systems;

import automatic.fire.complex.ShellsSystem.BurstingShell;
import automatic.fire.complex.ShellsSystem.Cassette;
import automatic.fire.complex.systems.loading.AutomationLoadingSystem;
import automatic.fire.complex.systems.loading.AutomationLoadingSystem3000;
import org.junit.Before;
import org.junit.Test;

public class AutomationLoadingSystem3000Test {

    private AutomationLoadingSystem als;
    private Cassette<BurstingShell> cassette;

    @Before
    public void setUp() {
        als = new AutomationLoadingSystem3000();
        cassette = new Cassette<>(2); // У касеты страниый констр


    }

    @Test
    public void loadCassetteTest()  {
        long startTime = System.currentTimeMillis();
        als.loadCassette(cassette);
        long waitingTime = System.currentTimeMillis() - startTime;
        assert (waitingTime > 2999 && waitingTime < 4000);
    }

    @Test
    public void disconnectCassetteTest() {

        als.loadCassette(cassette);
        long startTime = System.currentTimeMillis();
        als.disconnectCassette();
        long waitingTime = System.currentTimeMillis() - startTime;
        assert (waitingTime > 1999 && waitingTime < 2500);


    }


}
