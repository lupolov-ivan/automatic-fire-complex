package automatic.fire.complex.ammunition;

import automatic.fire.complex.units.enemy.EnemyType;

import java.util.ArrayList;
import java.util.List;

public class Ammunition {

    private final static int DEFAULT_BURSTING_CASSETTE_QUANTITY = 10;
    private final static int DEFAULT_ARMOR_PIERCING_CASSETTE_QUANTITY = 15;

    private List<Cassette> burstingCassettes;
    private List<Cassette> armorPiercingCassettes;

    private int quantityBurstingCassette;
    private int balanceBurstingCassette;
    private int quantityArmorPiercingCassette;
    private int balanceArmorPiercingCassette;

    private Cassette currentCassette;

    private Ammunition(int quantityBurstingCassette, int quantityArmorPiercingCassette) {
        this.burstingCassettes = new ArrayList<>(quantityBurstingCassette);
        this.armorPiercingCassettes = new ArrayList<>(quantityArmorPiercingCassette);
        this.quantityBurstingCassette = quantityBurstingCassette;
        this.quantityArmorPiercingCassette = quantityArmorPiercingCassette;
    }

    public static Ammunition createAmmunition(int quantityBurstingCassette, int capacityBurstingCassette, int quantityArmorPiercingCassette, int capacityArmorPiercingCassette) {
        Ammunition ammunition = new Ammunition(quantityBurstingCassette, quantityArmorPiercingCassette);
        for (int i = 0; i < quantityBurstingCassette; i++) {
            ammunition.addCassette(Cassette.createCassette(TypeShell.BURSTING, capacityBurstingCassette));
        }
        for (int i = 0; i < quantityArmorPiercingCassette; i++) {
            ammunition.addCassette(Cassette.createCassette(TypeShell.ARMOR_PIERCING, capacityArmorPiercingCassette));
        }
        return ammunition;
    }

    public static Ammunition createAmmunition() {
        return createAmmunition(DEFAULT_BURSTING_CASSETTE_QUANTITY, 10, DEFAULT_ARMOR_PIERCING_CASSETTE_QUANTITY, 10);
    }

    public boolean addCassette(Cassette cassette) {
        if (cassette.getTypeShell().equals(TypeShell.BURSTING)) {
            if (burstingCassettes.size() < quantityBurstingCassette) {
                burstingCassettes.add(cassette);
                balanceBurstingCassette = burstingCassettes.size();
                return true;
            }
        }

        if (cassette.getTypeShell().equals(TypeShell.ARMOR_PIERCING)) {
            if (armorPiercingCassettes.size() < quantityArmorPiercingCassette) {
                armorPiercingCassettes.add(cassette);
                balanceArmorPiercingCassette = armorPiercingCassettes.size();
                return true;
            }
        }
        return false;
    }

    @Deprecated
    public boolean hasNext(EnemyType enemyType) {
        if (enemyType.equals(EnemyType.TANK)) {
            return balanceArmorPiercingCassette > 0;
        }
        if (enemyType.equals(EnemyType.INFANTRY)) {
            return balanceBurstingCassette > 0;
        }
        return false;
    }

    public boolean hasNext(TypeShell typeShell) {
        if (typeShell.equals(TypeShell.BURSTING)) {
            return balanceBurstingCassette > 0;
        }

        if (typeShell.equals(TypeShell.ARMOR_PIERCING)) {
            return balanceArmorPiercingCassette > 0;
        }

        return false;
    }
@Deprecated
    public Cassette getCassette(EnemyType enemyData) {
        if (enemyData.equals(EnemyType.TANK)) {
            balanceArmorPiercingCassette--;
            return setCurrentCassette(armorPiercingCassettes.remove(0));
        } else {
            balanceBurstingCassette--;
            return setCurrentCassette(burstingCassettes.remove(0));
        }
    }


    public Cassette getCassette( TypeShell typeShell) {
        if (typeShell.equals(TypeShell.BURSTING)) {
            balanceBurstingCassette--;
            return setCurrentCassette(burstingCassettes.remove(0));
        } else {
            balanceArmorPiercingCassette--;
            return setCurrentCassette(armorPiercingCassettes.remove(0));
        }
    }


    private Cassette setCurrentCassette(Cassette cassette) {
        currentCassette = cassette;
        return currentCassette;
    }

    public Cassette getCurrentCassette() {
        return currentCassette;
    }

    @Override
    public String toString() {
        return "Ammunition{" +
                "burstingCassettes=" + burstingCassettes +
                ", armorPiercingCassettes=" + armorPiercingCassettes +
                ", capacityBurstingCassette=" + quantityBurstingCassette +
                ", balanceBurstingCassette=" + balanceBurstingCassette +
                ", capacityArmorPiercingCassette=" + quantityArmorPiercingCassette +
                ", balanceArmorPiercingCassette=" + balanceArmorPiercingCassette +
                '}';
    }
}
