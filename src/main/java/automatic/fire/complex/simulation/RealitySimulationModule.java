package automatic.fire.complex.simulation;

import automatic.fire.complex.units.Unit;
import automatic.fire.complex.units.enemy.Enemy;
import automatic.fire.complex.units.enemy.EnemyType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RealitySimulationModule {

    Logger log = LoggerFactory.getLogger(RealitySimulationModule.class);

    private volatile Battlefield battlefield;

    public RealitySimulationModule(Battlefield battlefield) {
        this.battlefield = battlefield;
    }

    public synchronized Unit getUnit(int x, int y) {
        if(battlefield.isEmpty(x, y)) {
            return null;
        }
        return battlefield.getCellValue(x, y);
    }

    public synchronized void toDamage(EnemyData data) {

        Enemy enemy = (Enemy) getUnit(data.getPosX(), data.getPosY());

        int currentHitCount = enemy.getHitCount();
        log.debug("Current number of hit: {}", currentHitCount);
        double currentTakenDamage = enemy.getDamageTaken();
        log.debug("Current taken damage: {}", currentTakenDamage);

        enemy.setHitCount(++currentHitCount);
        enemy.setDamageTaken(data.getDamage() + currentTakenDamage);

        if (data.getType() == EnemyType.TANK && enemy.getProtectionLevel() <= enemy.getDamageTaken()) {
            enemy.setAlive(false);
            log.debug("Target '{}' destroyed. Type: {}", enemy, data.getType().toString());
        }

        if (data.getType() == EnemyType.INFANTRY && (enemy.getProtectionLevel()*0.7) <= enemy.getDamageTaken()) {
            enemy.setAlive(false);
            log.debug("Target '{}' destroyed. Type: {}", enemy, data.getType().toString());
        }
    }

    public Battlefield getBattlefield() {
        return battlefield;
    }
}
