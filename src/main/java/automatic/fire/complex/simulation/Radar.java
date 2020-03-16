package automatic.fire.complex.simulation;

import automatic.fire.complex.observer.Observer;
import automatic.fire.complex.observer.Subject;
import automatic.fire.complex.units.Unit;
import automatic.fire.complex.units.enemy.EnemyType;
import automatic.fire.complex.units.enemy.Infantry;
import automatic.fire.complex.units.enemy.Tank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Radar implements Subject {

    Logger log = LoggerFactory.getLogger(Radar.class);

    private RealitySimulationModule rsm;

    public List<EnemyData> getEnemiesPosition() {
        return enemiesPosition;
    }

    private List<Observer> complexes;
    private List<EnemyData> enemiesPosition;

    public Radar(RealitySimulationModule realitySimulation) {
        this.rsm = realitySimulation;
        this.complexes = new ArrayList<>();
        this.enemiesPosition = new ArrayList<>();
    }

    @Override
    public void register(Observer complex) {
        complexes.add(complex);
    }

    @Override
    public void remove(Observer complex) {
        complexes.remove(complex);
    }

    @Override
    public void notifyAllAFC() {
        log.debug("Starting of sending enemy's position information to all AFC");
        complexes.forEach(observer -> observer.updatePosition(enemiesPosition));
        log.debug("End of sending enemy's position information to all AFC");
    }

    public void checkField() {

        enemiesPosition.clear();

        int width = rsm.getBattlefield().getWidth();
        int length = rsm.getBattlefield().getLength();

        log.debug("Radar are starting checking battlefield...");
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < length; y++) {
                Unit unit = rsm.getUnit(x, y);

                if (unit != null &&
                    unit.isAlive() &&
                    unit.sendSecretString().equals("ENEMY")
                ) {
                    EnemyData data = new EnemyData();
                    data.setUnit(unit);
                    data.setType(determineEnemyType(unit));
                    enemiesPosition.add(data);
                    log.debug("Detected new enemy: {}", unit);
                }
            }
        }
        log.debug("Radar finish checking battlefield. Enemy count: {}", enemiesPosition.size());
        notifyAllAFC();
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
