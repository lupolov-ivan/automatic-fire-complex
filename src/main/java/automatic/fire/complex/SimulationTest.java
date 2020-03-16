package automatic.fire.complex;

import automatic.fire.complex.simulation.Battlefield;
import automatic.fire.complex.simulation.Radar;
import automatic.fire.complex.simulation.RealitySimulationModule;
import automatic.fire.complex.systems.AimingSystem;
import automatic.fire.complex.systems.MechanicalInertialAimSystem;
import automatic.fire.complex.units.Unit;
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

        Battlefield battlefield = new Battlefield(4,4);
        RealitySimulationModule rsm = new RealitySimulationModule(battlefield);
        AimingSystem aim1 = new MechanicalInertialAimSystem();
        AimingSystem aim2 = new MechanicalInertialAimSystem();
        List<Unit> units = new ArrayList<>();

        AutomaticFireComplex afc1 = new AutomaticFireComplex(0, 0,10, aim1, rsm);
        AutomaticFireComplex afc2 = new AutomaticFireComplex(3, 3,10, aim2, rsm);
        units.add(afc1);
        units.add(afc2);
        units.add(new Tank(1,1, 10));
        units.add(new Tank(2,2, 10));
        units.add(new Infantry(2,1, 10));
        units.add(new Infantry(1,2, 10));

        battlefield.putUnits(units);

        Radar radar = new Radar(rsm);
        radar.register(afc1);
        radar.register(afc2);

        int countEnemies = -1;
        int count = 1;

        while (countEnemies != 0) {
            log.debug("============================================> Check #" + count++);
            radar.checkField();
            countEnemies = radar.getEnemiesPosition().size();
        }
    }
}
