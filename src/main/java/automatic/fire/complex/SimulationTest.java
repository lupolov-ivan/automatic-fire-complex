package automatic.fire.complex;

import automatic.fire.complex.ShellsSystem.BurstingShell;
import automatic.fire.complex.ShellsSystem.Cassette;
import automatic.fire.complex.simulation.Battlefield;
import automatic.fire.complex.systems.Radar;
import automatic.fire.complex.simulation.RealitySimulationModule;
import automatic.fire.complex.systems.aim.AimingSystem;
import automatic.fire.complex.systems.aim.MechanicalInertialAimSystem;
import automatic.fire.complex.units.Unit;
import automatic.fire.complex.units.enemy.Infantry;
import automatic.fire.complex.units.enemy.Tank;
import automatic.fire.complex.units.gun.AutomaticFireComplex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimulationTest {

//    static Logger log = LoggerFactory.getLogger(SimulationTest.class);
    public static void main(String[] args) {

//        Battlefield battlefield = new Battlefield(10,10);
//        RealitySimulationModule rsm = new RealitySimulationModule(battlefield);
//
//        AimingSystem aim1 = new MechanicalInertialAimSystem();
//        AimingSystem aim2 = new MechanicalInertialAimSystem();
//        List<Unit> units = new ArrayList<>();
//
//        AutomaticFireComplex afc1 = new AutomaticFireComplex(0, 0,10, aim1, rsm);
//        AutomaticFireComplex afc2 = new AutomaticFireComplex(9, 9,10, aim2, rsm);
//
//        units.add(afc1);
//        units.add(afc2);
//
//        units.add(new Tank(1,1, 5));
//        units.add(new Tank(2,2, 5));
//        units.add(new Tank(3,3, 5));
//        units.add(new Tank(4,4, 5));
//        units.add(new Infantry(5,5, 3));
//        units.add(new Infantry(6,6, 3));
//        units.add(new Infantry(7,7, 3));
//        units.add(new Infantry(8,8, 3));
//
//        battlefield.putUnits(units);
//
//        ExecutorService executorService = Executors.newCachedThreadPool();
//
//        executorService.submit(afc1);
//        executorService.submit(afc2);
//
//        executorService.shutdown();


        LinkedList<String> ll = new LinkedList<>();
        ll.add("father");
        ll.add(null);
        System.out.println(ll);
        System.out.println(ll.size());

    }
}
