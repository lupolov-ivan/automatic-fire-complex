package automatic.fire.complex.systems.aim;

import automatic.fire.complex.simulation.EnemyData;
import automatic.fire.complex.units.enemy.EnemyType;
import automatic.fire.complex.units.enemy.Infantry;
import automatic.fire.complex.units.enemy.Tank;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MechanicalInertialAimSystemTest {


    private List<EnemyData> enemies;
    private AimingSystem aimingSystem;

    @Before
    public void setUp() {
        enemies = new ArrayList<>();
        aimingSystem = new MechanicalInertialAimSystem(0, 2);
    }

    @After
    public void cleanUp() {
        enemies.clear();
    }

    /*
        0   1   2   3   4
    0 |   |   | A |   |   |
    1 |   |   |   |   |   |
    2 |   |   |   |   |   |
    3 |   |(E)|   |   |   |
    4 |   |   |   |   |   |
     */

    @Test
    public void givenListEnemies_whenFieldContainsSingleEnemy_thenReturnIt() {
        EnemyData expected = new EnemyData(1, 3, EnemyType.TANK);
        enemies.add(expected);

        EnemyData actual = aimingSystem.catchTarget(enemies);

        assertEquals(expected, actual);
    }

    /*
        0   1   2   3   4
    0 |   |   | A |   |   |
    1 |   |   |   |   |   |
    2 |(E)|   |   |   |   |
    3 |   |   |   |   | E |
    4 |   |   |   | E |   |
     */

    @Test
    public void givenListEnemies_whenFieldContainsThreeEnemiesWithDifferentDistance_thenReturnClosest() {
        EnemyData expected = new EnemyData(0, 2, EnemyType.TANK);

        enemies.add(expected);
        enemies.add(new EnemyData(3, 4, EnemyType.TANK));
        enemies.add(new EnemyData(4, 3, EnemyType.TANK));

        EnemyData actual = aimingSystem.catchTarget(enemies);

        assertEquals(expected, actual);
    }

    /*
        0   1   2   3   4
    0 |   |   | A |   |   |
    1 |   |   |   |   |   |
    2 |(E)|   |   |   |   |
    3 |   |   |   | E |   |
    4 |   |   |   |   | E |
     */

    @Test
    public void givenListEnemies_whenFieldContainsThreeEnemiesWithSameDistanceButTheyLocatedDiffLines_thenReturnClosest() {
        EnemyData expected = new EnemyData(0, 2, EnemyType.TANK);

        enemies.add(expected);
        enemies.add(new EnemyData(3, 3, EnemyType.TANK));
        enemies.add(new EnemyData(4, 4, EnemyType.TANK));

        EnemyData actual = aimingSystem.catchTarget(enemies);

        assertEquals(expected, actual);
    }

    /*
        0   1   2   3   4
    0 |   |   | A |   |   |
    1 |   |   |   |   |   |
    2 |(E)|   |   |   | E |
    3 |   |   |   |   |   |
    4 |   |   | E |   |   |
     */

    @Test
    public void givenListEnemies_whenFieldContainsThreeEnemiesWithSameDistanceAndTwoOfTheyLocatedSameLines_thenReturnClosest() {
        EnemyData expected = new EnemyData(0, 2, EnemyType.TANK);

        enemies.add(expected);
        enemies.add(new EnemyData(2, 4, EnemyType.TANK));
        enemies.add(new EnemyData(4, 2, EnemyType.TANK));

        EnemyData actual = aimingSystem.catchTarget(enemies);

        assertEquals(expected, actual);
    }
}