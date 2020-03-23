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

import java.util.ArrayList;
import java.util.List;

public class SimulationTest {

    static Logger log = LoggerFactory.getLogger(SimulationTest.class);

    public static void main(String[] args) {

        /* Parameters for creating ammunition */
        int quantityBurstingCassette = 2;
        int capacityBurstingCassette = 5;
        int quantityArmorPiercingCassette = 20;
        int capacityArmorPiercingCassette = 10;

        log.info("Create ammunition...");
        Ammunition ammo1 = Ammunition.createAmmunition(quantityBurstingCassette, capacityBurstingCassette, quantityArmorPiercingCassette, capacityArmorPiercingCassette);
        Ammunition ammo2 = Ammunition.createAmmunition(quantityBurstingCassette, capacityBurstingCassette, quantityArmorPiercingCassette, capacityArmorPiercingCassette);

        log.info("Create battlefield...");
        Battlefield battlefield = new Battlefield(10,10);
        RealitySimulationModule rsm = new RealitySimulationModule(battlefield);

        log.info("Create units and put them to battlefield...");
        List<Unit> guns = new ArrayList<>();

        AutomaticFireComplex afc1 = new AutomaticFireComplex(0, 2, 10, ammo1, rsm);
        AutomaticFireComplex afc2 = new AutomaticFireComplex(0, 7, 10, ammo2, rsm);

        guns.add(afc1);
        guns.add(afc2);

        List<Unit> enemies = new ArrayList<>();

        enemies.add(new Tank(2,1, 5));
        enemies.add(new Tank(3,6, 5));
        enemies.add(new Tank(7,8, 5));
        enemies.add(new Tank(8,4, 5));
        enemies.add(new Infantry(5,5, 2));
        enemies.add(new Infantry(4,1, 2));
        enemies.add(new Infantry(2,8, 2));
        enemies.add(new Infantry(7,2, 2));

        battlefield.putUnits(guns);
        battlefield.putUnits(enemies);

        log.info("Starting fight...");

        Thread t1 = new Thread(afc1);
        t1.start();
        Thread t2 = new Thread(afc2);
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("Fight end");

        List<Unit> remainingEnemies = rsm.getAllUnits();
        remainingEnemies.removeIf(unit -> !unit.isAlive());

        log.debug("Remaining enemies: \n{}", remainingEnemies);

        log.info("Ammunition report");

        log.info("Gun #1 remaining ammunition:\n{}", ammo1);
        log.info("Gun #2 remaining ammunition:\n{}", ammo2);
    }
}
