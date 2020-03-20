package automatic.fire.complex.units.gun;

import automatic.fire.complex.ShellsSystem.*;
import automatic.fire.complex.simulation.Battlefield;
import automatic.fire.complex.simulation.EnemyData;
import automatic.fire.complex.simulation.RealitySimulationModule;
import automatic.fire.complex.systems.Radar;
import automatic.fire.complex.systems.aim.AimingSystem;
import automatic.fire.complex.systems.aim.MechanicalInertialAimSystem;
import automatic.fire.complex.systems.fire.FireSystem;
import automatic.fire.complex.systems.fire.FireSystem3000;
import automatic.fire.complex.systems.loading.AutomationLoadingSystem;
import automatic.fire.complex.systems.loading.AutomationLoadingSystem3000;
import automatic.fire.complex.units.Unit;
import automatic.fire.complex.units.enemy.Infantry;
import automatic.fire.complex.units.enemy.Tank;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AutomaticFireComplexTest {

    private AutomaticFireComplex automaticFireComplex;
    private Ammunition ammunition;
    private FireSystem fireSystem;
    private AimingSystem aimingSystem;
    private Radar radar;
    private Unit unit1;
    private Unit unit2;
    private Battlefield battlefield;
    private RealitySimulationModule realitySimulationModule;
    private AutomationLoadingSystem automationLoadingSystem;


    @Before
    public void setUp() {

        aimingSystem = new MechanicalInertialAimSystem();
        automationLoadingSystem = new AutomationLoadingSystem3000();
        fireSystem = new FireSystem3000();
        ammunition = creatAmmunition();

    }

    @Test
    public void fireOnBattlefieldWithOneEnemy() {

        long startTime = System.currentTimeMillis();
        realitySimulationModule = new RealitySimulationModule(creatBattlefieldWithOneEnemy());
        radar = new Radar(realitySimulationModule);
        automaticFireComplex = new AutomaticFireComplex(0, 0, 88, aimingSystem,
                realitySimulationModule, automationLoadingSystem, fireSystem, ammunition);
        automaticFireComplex.run();
        long finishTime = System.currentTimeMillis();
        assert (finishTime - startTime >= 4000);
        assert(!unit1.isAlive());

    }


    @Test
  public void fireOnBattlefieldWithTwoEnemy(){
        realitySimulationModule = new RealitySimulationModule(creatBattlefieldWithTwoEnemy());
        radar = new Radar(realitySimulationModule);
        automaticFireComplex = new AutomaticFireComplex(0, 0, 88, aimingSystem,
                realitySimulationModule, automationLoadingSystem, fireSystem, ammunition);
        automaticFireComplex.run();
        assert(!unit1.isAlive());
        assert(!unit2.isAlive());
    }

    public Battlefield creatBattlefieldWithOneEnemy() {
        battlefield = new Battlefield(3, 3);
        unit1 = new Infantry(1, 1, 3);
        battlefield.putUnit(unit1);
        return battlefield;
    }

    public Battlefield creatBattlefieldWithTwoEnemy() {
        battlefield = new Battlefield(3, 3);
        unit1 = new Infantry(1, 1, 1);
        unit2 = new Tank(2, 1, 3);
        battlefield.putUnit(unit1);
        battlefield.putUnit(unit2);
        return battlefield;
    }

    public Ammunition creatAmmunition() {
        Ammunition ammunition = new Ammunition();
        Cassette<Shell> cassetteAPS = new Cassette<>(10);
        Cassette<Shell> cassetteBS = new Cassette<>(10);
        for (int i = 0; i < 10; i++) {
            cassetteAPS.add(new ArmorPiercingShell());
            cassetteBS.add(new BurstingShell());
        }

        ammunition.addCassette(cassetteAPS);
        ammunition.addCassette(cassetteBS);
        return ammunition;

    }


}