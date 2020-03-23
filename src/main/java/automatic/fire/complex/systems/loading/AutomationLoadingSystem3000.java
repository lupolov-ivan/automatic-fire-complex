package automatic.fire.complex.systems.loading;

import automatic.fire.complex.ammunition.Cassette;

public class AutomationLoadingSystem3000 extends AutomationLoadingSystem {

    private Cassette currentCassette;

    @Override
    public boolean loadCassette(Cassette cassette) {  // мы возврат кассеты

        if (currentCassette == null) {
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {//todo}
//            }
            currentCassette = cassette;
            return true;
        } else {
            return false;
        }

    }

    @Override
    public Cassette disconnectCassette() {
        // вернуть кассету в амуницию (лучше всего в конец списка);
        Cassette tempCassette;
        if (currentCassette != null && currentCassette.getBalance() == 0) {
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException ex) {
//                todo
//            }
            tempCassette = currentCassette;
            currentCassette = null;
            return tempCassette;

        }
        if (currentCassette != null && currentCassette.getBalance() != 0) {
            tempCassette = currentCassette;
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException ex) {
//                //todo
//            }
            currentCassette = null;
            return tempCassette;

        } else {
            // случай когда пушка пустая, должен ли поток ждать 2с.?
            return null;
        }
    }

    @Override
    public void extractShell() {

//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException ex) {
            //todo
//        }

    }

    public Cassette getCurrentCassette() {
        return currentCassette;
    }
}

