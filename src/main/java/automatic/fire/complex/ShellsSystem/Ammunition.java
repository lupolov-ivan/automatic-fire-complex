package automatic.fire.complex.ShellsSystem;

import automatic.fire.complex.Factories.AmmunitionFactory;
import automatic.fire.complex.simulation.EnemyData;
import automatic.fire.complex.units.enemy.EnemyType;

import java.util.LinkedList;

public class Ammunition {
    private LinkedList<Cassette<Shell>> listOfArmorPS = new LinkedList<>();
    private LinkedList<Cassette<Shell>> listOfBurstingS = new LinkedList<>();

    public Ammunition(){}

    public Ammunition(int quantityOfArmor, int quantityOfBurst){
        listOfArmorPS = AmmunitionFactory.initListOfCassette(quantityOfArmor, TypeShell.ARMOR);
        listOfBurstingS = AmmunitionFactory.initListOfCassette(quantityOfBurst, TypeShell.BURST);
    }

    public <T extends Shell> void addCassette (Cassette<T> cassette) {
        if (cassette.getBalance() > 0) {
            if (cassette.getInstanceInnerElement().getClass() == ArmorPiercingShell.class) {
                listOfArmorPS.add((Cassette<Shell>) cassette);
            } else if (cassette.getInstanceInnerElement().getClass() == BurstingShell.class) {
                listOfBurstingS.add((Cassette<Shell>) cassette);
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

    public LinkedList<Cassette<Shell>> getListOfBurstingS() {
        return listOfBurstingS;
    }

    public LinkedList<Cassette<Shell>> getListOfArmorPS() {
        return listOfArmorPS;
    }
}
