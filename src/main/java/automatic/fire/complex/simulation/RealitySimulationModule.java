package automatic.fire.complex.simulation;

import automatic.fire.complex.ammunition.Ammunition;
import automatic.fire.complex.ammunition.Cassette;
import automatic.fire.complex.ammunition.TypeShell;
import automatic.fire.complex.units.Unit;
import automatic.fire.complex.units.enemy.Enemy;
import automatic.fire.complex.units.enemy.EnemyType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RealitySimulationModule {

    Logger log = LoggerFactory.getLogger(RealitySimulationModule.class);

    private volatile Battlefield battlefield;

    private long startTime;


    public RealitySimulationModule(Battlefield battlefield) {
        this.battlefield = battlefield;
        startTime = System.currentTimeMillis();
    }

    public synchronized Unit getUnit(int x, int y) {
        if(battlefield.isEmpty(x, y)) {
            return null;
        }
        return battlefield.getCellValue(x, y);
    }

    public List<Unit> getAllUnits() {
        List<Unit> units = new ArrayList<>();

        int width = battlefield.getWidth();
        int length = battlefield.getLength();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < length; y++) {
                Unit unit = getUnit(x, y);
                if (unit != null) {
                    units.add(unit);
                }
            }
        }
        return units;
    }

    public synchronized void toDamage(EnemyData data) {

        Enemy enemy = (Enemy) getUnit(data.getPosX(), data.getPosY());

        int currentHitCount = enemy.getHitCount();
        double currentTakenDamage = enemy.getDamageTaken();

        enemy.setHitCount(++currentHitCount);
        enemy.setDamageTaken(data.getDamage() + currentTakenDamage);

        log.debug("Current number of hit: {}", enemy.getHitCount());
        log.debug("Current taken damage: {}", enemy.getDamageTaken());

        if (data.getType() == EnemyType.TANK && enemy.getProtectionLevel() <= enemy.getDamageTaken()) {
            enemy.setAlive(false);
            log.debug("Target '{}' destroyed. Type: {}", enemy, data.getType().toString());
        }

        if (data.getType() == EnemyType.INFANTRY && (enemy.getProtectionLevel()*0.7) <= enemy.getDamageTaken()) {
            enemy.setAlive(false);
            log.debug("Target '{}' destroyed. Type: {}", enemy, data.getType().toString());
        }

    }

    public void battleWasFinished(List<Ammunition> ammunitionList) {
        long finishTime = System.currentTimeMillis();
        long battleDuration = finishTime - startTime;

        log.info("=======================");
        log.info("Duration of the battle was: {}s and {}ms", battleDuration / 1000, battleDuration % 1000);

        int countShotsOfArmor = 0;
        int countShotsOfBurst = 0;

        log.debug("list of amm size is {}", ammunitionList.size());

        for (Ammunition amm: ammunitionList) {
            if (amm.getArmorPiercingCassettes().size() < amm.getQuantityArmorPiercingCassette()){
                countShotsOfArmor += (amm.getQuantityArmorPiercingCassette() - amm.getArmorPiercingCassettes().size())
                        * amm.getQuantityShellsInArmorCassette();
            }

            for (Cassette cassette: amm.getArmorPiercingCassettes()){
                countShotsOfArmor += (amm.getQuantityShellsInArmorCassette() - cassette.getBalance());
            }

            if (amm.getBurstingCassettes().size() < amm.getQuantityBurstingCassette()){
                countShotsOfBurst += (amm.getQuantityBurstingCassette() - amm.getBurstingCassettes().size())
                        * amm.getQuantityShellsInBurstCassette();
            }

            for (Cassette cassette: amm.getBurstingCassettes()) {
                countShotsOfBurst += (amm.getQuantityShellsInBurstCassette() - cassette.getBalance());
            }
        }

        log.info("Count shots of armor shells: {}", countShotsOfArmor);
        log.info("Count shots of burst shells: {}", countShotsOfBurst);
        log.info("Count shots all shells: {}", countShotsOfBurst + countShotsOfArmor);


        log.info("count of hits for {} equals {}", getAllUnits().get(0).getClass(), getAllUnits().get(0).getHitCount());

        log.info("quantity of units on battlefield {}", getAllUnits().size());

        List<Unit> enemiesList = new LinkedList<>();
        for (Unit unit: getAllUnits()){
            if (!unit.sendSecretString().equals("ALLY")) {
                enemiesList.add(unit);
            }
        }
        log.info("quantity of units on battlefield {}", enemiesList.size());

        int sumShots = 0;

        for (Unit enemy: enemiesList) {
            sumShots += enemy.getHitCount();
            log.info("count of hits to {} {} enemy {}",enemy.getPosY(), enemy.getPosX(), enemy.getHitCount());
        }
        log.info("count of all shots {}", sumShots);
    }

    public Battlefield getBattlefield() {
        return battlefield;
    }
}
