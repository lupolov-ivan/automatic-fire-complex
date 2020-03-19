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

    public boolean addCassette (Cassette cassette) {
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

    public boolean hasNext(EnemyType enemyType) {
        if (enemyType.equals(EnemyType.TANK)) {
            return balanceArmorPiercingCassette > 0;
        }
        if (enemyType.equals(EnemyType.INFANTRY)){
            return balanceBurstingCassette > 0;
        }
        return false;
    }

    public Cassette getCassette(EnemyType enemyData) {
        if (enemyData.equals(EnemyType.TANK)) {
            balanceArmorPiercingCassette--;
            return armorPiercingCassettes.remove(0);
        } else {
            balanceBurstingCassette--;
            return burstingCassettes.remove(0);
        }
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
