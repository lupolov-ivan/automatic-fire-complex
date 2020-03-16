package automatic.fire.complex.ShellsSystem;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Ammunition {
    public List<Cassette<ArmorPiercingShell>> listOfArmorPS = new LinkedList<>();
    List<Cassette<BurstingShell>> listOfBurstingS = new LinkedList<>();



    public void addCassette (Cassette cassette) {
        if (cassette.getInstanceInnerElement().getClass() == ArmorPiercingShell.class) {
            listOfArmorPS.add(cassette);
        } else if (cassette.getInstanceInnerElement().getClass() == BurstingShell.class){
            listOfBurstingS.add(cassette);
        }
    }

    public boolean hasNext(Cassette cassette) {
        if (cassette.getInstanceInnerElement().getClass() == ArmorPiercingShell.class) {
            if (cassette.getBalance() > 0) {
                return true;
            } else {
                return false;
            }
        } else {
            if (cassette.getBalance() > 0) {
                return true;
            }else {
                return false;
            }
        }
    }

    public Cassette getCassette(Cassette cassette) {
        if (cassette.getInstanceInnerElement().getClass() == ArmorPiercingShell.class) {
            listOfArmorPS.remove(listOfArmorPS.size()-1);
            return listOfArmorPS.get(0);
        } else if (cassette.getInstanceInnerElement().getClass() == BurstingShell.class){
            listOfBurstingS.remove(listOfBurstingS.size()-1);
            return listOfBurstingS.get(0);
        } else {
            return null;
        }
    }
}
