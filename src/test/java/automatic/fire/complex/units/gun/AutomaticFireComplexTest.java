package automatic.fire.complex.units.gun;

import automatic.fire.complex.simulation.EnemyData;
import automatic.fire.complex.systems.Radar;
import automatic.fire.complex.systems.aim.AimingSystem;
import automatic.fire.complex.systems.fire.FireSystem;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AutomaticFireComplexTest {

    private AutomaticFireComplex automaticFireComplex;
    private List<EnemyData> targetList;
    private FireSystem fireSystem;
    private AimingSystem aimingSystem;
    private Radar radar;



    @Before
    public void setUp(){
        targetList = new ArrayList<>();
        targetList.add(new EnemyData());
    }





}