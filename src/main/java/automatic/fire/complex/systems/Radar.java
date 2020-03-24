package automatic.fire.complex.systems;

import automatic.fire.complex.simulation.EnemyData;
import automatic.fire.complex.simulation.RealitySimulationModule;
import automatic.fire.complex.units.Unit;
import automatic.fire.complex.units.enemy.EnemyType;
import automatic.fire.complex.units.enemy.Infantry;
import automatic.fire.complex.units.enemy.Tank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Radar {

    Logger log = LoggerFactory.getLogger(Radar.class);

    private RealitySimulationModule rsm;

    private Set<EnemyType> ignoreTypes;

    public Radar(RealitySimulationModule realitySimulation) {
        this.rsm = realitySimulation;
        this.ignoreTypes = new HashSet<>();
    }

    public List<EnemyData> checkField() {

        List<EnemyData> enemiesPosition = new ArrayList<>();

        int width = rsm.getBattlefield().getWidth();
        int length = rsm.getBattlefield().getLength();

        log.debug("Radar are starting checking battlefield...");
        for (int y = 0; y < length; y++) {
            for (int x = 0; x < width; x++) {
                Unit unit = rsm.getUnit(x, y);

                if (unit != null && unit.isAlive() && unit.sendSecretString().equals("ENEMY") && !ignoreTypes.contains(determineEnemyType(unit))) {
                    EnemyData data = new EnemyData();
                    data.setPosX(x);
                    data.setPosY(y);
                    data.setType(determineEnemyType(unit));

                    enemiesPosition.add(data);
                    log.debug("Detected new enemy: {}", unit);
                }
            }
        }
        log.debug("Radar finish checking battlefield. Enemy count: {}", enemiesPosition.size());

        return enemiesPosition;
    }

    public boolean addTypeToIgnore(EnemyType type) {
        return ignoreTypes.add(type);
    }

    public boolean removeTypeFromIgnore(EnemyType enemyType){
        return ignoreTypes.remove(enemyType);
    }

    public int getSizeIgnoreList() {
        return ignoreTypes.size();
    }

    private EnemyType determineEnemyType(Unit unit) {
        if (unit.getClass() == Tank.class) {
            return EnemyType.TANK;
        }
        if (unit.getClass() == Infantry.class) {
            return EnemyType.INFANTRY;
        }
        return EnemyType.UNKNOWN;
    }
}
