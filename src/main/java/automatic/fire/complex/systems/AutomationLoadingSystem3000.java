package automatic.fire.complex.systems;

import automatic.fire.complex.ShellsSystem.Cassette;
import automatic.fire.complex.ShellsSystem.Shell;

public class AutomationLoadingSystem3000 implements AutomationLoadingSystem {

    private Cassette<?> currentCassette;

    // @Override
    public boolean loadCassette(Cassette<? extends Shell> cassette) {  // мы возврат кассеты

        if (currentCassette == null) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {//todo}
            }
            currentCassette = cassette;
            return true;
        } else {
            return false;
        }

    }

    // @Override
    public Cassette<?> disconnectCassette() {
        // вернуть кассету в амуницию (лучше всего в конец списка);

        if (currentCassette != null && currentCassette.getBalance() == 0) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                //todo
            }
            currentCassette = null;
            return null; // НА СВОЙ СТРАХ

        }
        if (currentCassette != null && currentCassette.getBalance() != 0) {
            Cassette<?> tempCassette = currentCassette;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                //todo
            }
            currentCassette = null;
            return tempCassette;

        } else {
            // случай когда пушка пустая, должен ли поток ждать 2с.?
            return null;
        }
    }

    @Override
    public void extractShell() {

        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            //todo
        }

    }

    public Cassette<?> getCurrentCassette() {
        return currentCassette;
    }
}

