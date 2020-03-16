package automatic.fire.complex.simulation;

import automatic.fire.complex.units.Unit;
import automatic.fire.complex.units.enemy.Enemy;
import automatic.fire.complex.units.enemy.EnemyType;
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
        double currentTakenDamage = enemy.getDamageTaken();
        log.debug("Taken damage before shot: {}", currentTakenDamage);

        enemy.setHitCount(++currentHitCount);
        enemy.setDamageTaken(currentTakenDamage + data.getDamage());

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
