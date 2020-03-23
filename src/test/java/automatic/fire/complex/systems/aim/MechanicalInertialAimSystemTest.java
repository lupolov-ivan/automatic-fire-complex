package automatic.fire.complex.systems.aim;

import automatic.fire.complex.simulation.EnemyData;
import automatic.fire.complex.units.enemy.EnemyType;
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
        EnemyData expected = new EnemyData(3, 1, EnemyType.TANK);
        enemies.add(expected);

        EnemyData actual = aimingSystem.catchTarget(enemies);

        assertEquals(expected, actual);
    }

    /*
        0   1   2   3   4
    0 |   |   | A |   |   |
    1 |   |   |   |   |   |
    2 |(E)|   |   |   |   |
    3 |   |   |   |   |   |
    4 |   |   |   | E |   |
     */

    @Test
    public void givenListEnemies_whenFieldContainsTwoEnemiesWithDifferentDistance_thenReturnClosest() {
        EnemyData expected = new EnemyData(2, 0, EnemyType.TANK);
        EnemyData anotherEnemy = new EnemyData(4, 3, EnemyType.TANK);
        enemies.add(expected);
        enemies.add(anotherEnemy);

        EnemyData actual = aimingSystem.catchTarget(enemies);

        assertEquals(expected, actual);
    }

    /*
        0   1   2   3   4
    0 |   |   | A |   |   |
    1 |   |   |   |   |   |
    2 |(E)|   |   |   |   |
    3 |   |   |   | E |   |
    4 |   |   |   |   |   |
     */

    @Test
    public void givenListEnemies_whenFieldContainsTwoEnemiesWithSameDistanceButTheyLocatedDiffLines_thenReturnClosest() {
        EnemyData expected = new EnemyData(2, 0, EnemyType.TANK);
        EnemyData anotherEnemy = new EnemyData(3, 3, EnemyType.TANK);
        enemies.add(expected);
        enemies.add(anotherEnemy);

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
        EnemyData expected = new EnemyData(2, 0, EnemyType.TANK);
        EnemyData anotherEnemy1 = new EnemyData(4, 2, EnemyType.TANK);
        EnemyData anotherEnemy2 = new EnemyData(2, 4, EnemyType.TANK);
        enemies.add(expected);
        enemies.add(anotherEnemy1);
        enemies.add(anotherEnemy2);

        EnemyData actual = aimingSystem.catchTarget(enemies);

        assertEquals(expected, actual);
    }
}