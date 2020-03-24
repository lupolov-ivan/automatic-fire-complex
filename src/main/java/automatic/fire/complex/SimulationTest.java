package automatic.fire.complex;

import automatic.fire.complex.ammunition.Ammunition;
import automatic.fire.complex.ammunition.Cassette;
import automatic.fire.complex.ammunition.Shell;
import automatic.fire.complex.ammunition.TypeShell;
import automatic.fire.complex.simulation.Battlefield;
import automatic.fire.complex.simulation.EnemyData;
import automatic.fire.complex.simulation.RealitySimulationModule;
import automatic.fire.complex.units.Unit;
import automatic.fire.complex.units.enemy.Enemy;
import automatic.fire.complex.units.enemy.Infantry;
import automatic.fire.complex.units.enemy.Tank;
import automatic.fire.complex.units.gun.AutomaticFireComplex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SimulationTest {

    static Logger log = LoggerFactory.getLogger(SimulationTest.class);

    public static void main(String[] args) {

        /* Parameters for creating ammunition */
        int quantityBurstingCassette = 3;
        int capacityBurstingCassette = 5;
        int quantityArmorPiercingCassette = 4;
        int capacityArmorPiercingCassette = 10;

        log.info("Create ammunition...");
        Ammunition ammo1 = Ammunition.createAmmunition(quantityBurstingCassette, capacityBurstingCassette, quantityArmorPiercingCassette, capacityArmorPiercingCassette);
//        Ammuntion ammo3 = Ammunition.createAmmunition(quantityBurstingCassette, capacityBurstingCassette, quantityArmorPiercingCassette, capacityArmorPiercingCassette);

        log.info("Create battlefield...");
        Battlefield battlefield = new Battlefield(10,10);
        RealitySimulationModule rsm = new RealitySimulationModule(battlefield);

        log.info("Create units and put them to battlefield...");
        List<Unit> guns = new ArrayList<>();

        AutomaticFireComplex afc1 = new AutomaticFireComplex(2, 0, 10, ammo1, rsm);
//        AutomaticFireComplex afc2 = new AutomaticFireComplex(7, 0, 10, ammo2, rsm);
//        AutomaticFireComplex afc3 = new AutomaticFireComplex(4, 1, 10, ammo2, rsm);

        guns.add(afc1);
//        guns.add(afc2);
//        guns.add(afc3);

        List<Unit> enemies = new ArrayList<>();

        enemies.add(new Tank(1,2, 5));
        enemies.add(new Tank(6,3, 5));
        enemies.add(new Tank(8,7, 5));
        enemies.add(new Tank(4,8, 5));
        enemies.add(new Infantry(5,5, 2));
        enemies.add(new Infantry(1,4, 2));
        enemies.add(new Infantry(8,2, 2));
        enemies.add(new Infantry(2,7, 2));

        battlefield.putUnits(guns);
        battlefield.putUnits(enemies);

        prettyPrintBattlefield(battlefield);

        log.info("Starting fight...");

        Thread t1 = new Thread(afc1);
        t1.start();
//        Thread t2 = new Thread(afc2);
//        t2.start();
//        Thread t3 = new Thread(afc3);
//        t3.start();

        try {
            t1.join();
//            t2.join();
//            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("Fight end");

        List<Unit> remainingEnemies = rsm.getAllUnits();
        remainingEnemies.removeIf(unit -> !unit.isAlive());

        log.debug("Remaining enemies: \n{}", remainingEnemies);

        log.info("Ammunition report");

        log.info("Gun #1 remaining ammunition:\n{}", ammo1);
//        log.info("Gun #2 remaining ammunition:\n{}", ammo2);
//        log.info("Gun #2 remaining ammunition:\n{}", ammo3);

        List<Ammunition> ammunitionList = new LinkedList<>();
        ammunitionList.add(ammo1);
//        ammunitionList.add(ammo2);
//        ammunitionList.add(ammo3);

        rsm.battleWasFinished(ammunitionList);
    }

    private static void prettyPrintBattlefield(Battlefield battlefield) {

        int width = battlefield.getWidth();
        int length = battlefield.getLength();

        for (int y = -1; y < length; y++) {
            if (y == -1) {
                for (int i = 0; i < width; i++) {
                    System.out.print("\t"+ i +"\t");
                }
                System.out.println();
                continue;
            }
            System.out.print(y +" |");
            for (int x = 0; x < width; x++) {

                Unit unit = battlefield.getCellValue(x,y);
                if (unit == null) {
                    System.out.print("\t" + "\t|");
                } else {
                    System.out.print("\t" + getLetter(unit) + "\t|");
                }
            }
            System.out.println();
        }
    }

    private static String getLetter(Unit unit) {
        if (unit.getClass() == Tank.class) {
            return "T";
        }
        if (unit.getClass() == Infantry.class) {
            return "I";
        }
        if (unit.getClass() == AutomaticFireComplex.class) {
            return "A";
        }
        return "U";
    }
}
