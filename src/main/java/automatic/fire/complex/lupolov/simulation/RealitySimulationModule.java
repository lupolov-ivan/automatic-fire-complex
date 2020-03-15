package automatic.fire.complex.lupolov.simulation;

import automatic.fire.complex.lupolov.units.Unit;
import automatic.fire.complex.lupolov.units.enemy.Enemy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RealitySimulationModule {

    Logger log = LoggerFactory.getLogger(RealitySimulationModule.class);

    private Battlefield battlefield;

    public RealitySimulationModule(Battlefield battlefield) {
        this.battlefield = battlefield;
    }

    public Unit getUnit(int x, int y) {
        if(battlefield.isEmpty(x, y)) {
            return null;
        }
        return battlefield.getCellValue(x, y);
    }

    public void toDamage(EnemyData data) {

        Enemy enemy = (Enemy) data.getUnit();

        int currentHitCount = enemy.getHitCount();
        log.debug("Number of hit before shot: {}", currentHitCount);
        double currentProtectionLevel = enemy.getProtectionLevel();
        log.debug("Protection level before shot: {}", currentProtectionLevel);

        enemy.setHitCount(++currentHitCount);
        enemy.setProtectionLevel(currentProtectionLevel - data.getDamage());

        if (enemy.getProtectionLevel() <= 0) {
            enemy.setAlive(false);
            log.debug("Target '{}' destroyed", enemy);
        }
    }

    public Battlefield getBattlefield() {
        return battlefield;
    }
}
