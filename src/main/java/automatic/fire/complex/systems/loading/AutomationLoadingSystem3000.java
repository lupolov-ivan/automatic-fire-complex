package automatic.fire.complex.systems.loading;

import automatic.fire.complex.ammunition.Ammunition;
import automatic.fire.complex.ammunition.Cassette;
import automatic.fire.complex.ammunition.TypeShell;

public class AutomationLoadingSystem3000 extends AutomationLoadingSystem {

    private Cassette currentCassette;

    public AutomationLoadingSystem3000(Ammunition ammunition) {
        super(ammunition);
    }

    @Override
    public boolean loadCassette(TypeShell typeShell) {
        if (!ammunition.hasNext(typeShell)){
            return false;
        }
            if (currentCassette == null) {
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {//todo}
//            }
                currentCassette = ammunition.getCassette(typeShell);
                return true;
            } else {
                return false;
            }

    }

    @Override
    public void disconnectCassette() {

        if (currentCassette != null && currentCassette.getBalance() == 0) {
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException ex) {
//                todo
//            }
            currentCassette = null;

        }
        if (currentCassette != null && currentCassette.getBalance() != 0) {

//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException ex) {
//                //todo
//            }

            ammunition.addCassette(currentCassette);
            currentCassette = null;

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

