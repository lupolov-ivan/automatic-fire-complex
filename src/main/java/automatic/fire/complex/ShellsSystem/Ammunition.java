package automatic.fire.complex.ShellsSystem;

import automatic.fire.complex.units.Unit;
import automatic.fire.complex.units.enemy.Infantry;
import automatic.fire.complex.units.enemy.Tank;

import java.util.LinkedList;

public class Ammunition {
    private LinkedList<Cassette<ArmorPiercingShell>> listOfArmorPS = new LinkedList<>();
    private LinkedList<Cassette<BurstingShell>> listOfBurstingS = new LinkedList<>();



    public <T extends Shell> void addCassette (Cassette<T> cassette) {
        if (cassette.getBalance() > 0) {
            if (cassette.getInstanceInnerElement().getClass() == ArmorPiercingShell.class) {
                listOfArmorPS.add((Cassette<ArmorPiercingShell>) cassette);
            } else if (cassette.getInstanceInnerElement().getClass() == BurstingShell.class) {
                listOfBurstingS.add((Cassette<BurstingShell>) cassette);
            }
        }
    }

    public boolean hasNext(Cassette cassette) {
        if (cassette.getInstanceInnerElement().getClass() == ArmorPiercingShell.class) {
            return listOfArmorPS.size() > 0;
        } else {
            return listOfBurstingS.size() > 0;
        }
    }

    public Cassette<? extends Shell> getCassette(Unit unit) {
        if (unit.getClass() == Tank.class) {
            listOfArmorPS.remove(listOfArmorPS.getLast());
            return listOfArmorPS.getFirst();
        } else if (unit.getClass() == Infantry.class){
            listOfBurstingS.remove(listOfBurstingS.getLast());
            return listOfBurstingS.getFirst();
        } else {
            return null;
        }
    }
}
