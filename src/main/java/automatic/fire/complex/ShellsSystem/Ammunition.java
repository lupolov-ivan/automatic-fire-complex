package automatic.fire.complex.ShellsSystem;

import automatic.fire.complex.simulation.EnemyData;
import automatic.fire.complex.units.enemy.EnemyType;

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

    public boolean hasNext(EnemyData enemyData) {
        if (enemyData.getType() == EnemyType.TANK) {
            return listOfArmorPS.size() > 0;
        } else {
            return listOfBurstingS.size() > 0;
        }
    }

    public Cassette<? extends Shell> getCassette(EnemyData enemyData) {
        if (enemyData.getType() == EnemyType.TANK) {
            return listOfArmorPS.remove(0);
        } else if (enemyData.getType() == EnemyType.INFANTRY){
            return listOfBurstingS.remove(0);
        } else {
            return null;
        }
    }

    public LinkedList<Cassette<BurstingShell>> getListOfBurstingS() {
        return listOfBurstingS;
    }

    public LinkedList<Cassette<ArmorPiercingShell>> getListOfArmorPS() {
        return listOfArmorPS;
    }
}
