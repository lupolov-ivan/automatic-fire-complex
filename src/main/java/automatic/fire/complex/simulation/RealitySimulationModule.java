package automatic.fire.complex.simulation;

import automatic.fire.complex.ammunition.Ammunition;
import automatic.fire.complex.ammunition.Cassette;
import automatic.fire.complex.units.Unit;
import automatic.fire.complex.units.enemy.Enemy;
import automatic.fire.complex.units.enemy.EnemyType;
import automatic.fire.complex.units.enemy.Infantry;
import automatic.fire.complex.units.enemy.Tank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RealitySimulationModule {

    Logger log = LoggerFactory.getLogger(RealitySimulationModule.class);

    private volatile Battlefield battlefield;

    private long startTime;
    private List<Ammunition> ammunitionList;

    public RealitySimulationModule() {
        startTime = System.currentTimeMillis();
        ammunitionList = new LinkedList<>();
    }

    public RealitySimulationModule(Battlefield battlefield) {
        this.battlefield = battlefield;
        startTime = System.currentTimeMillis();
        ammunitionList = new LinkedList<>();
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

        if(enemy.isAlive()) {
            enemy.setHitCount(++currentHitCount);
        }

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

    public void battleWasFinished() {

        long finishTime = System.currentTimeMillis();
        long battleDuration = finishTime - startTime;

        log.info("=======================");
        log.info("Duration of the battle was: {}s and {}ms", battleDuration / 1000, battleDuration % 1000);

        int countShotsOfArmor = 0;
        int countShotsOfBurst = 0;

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

        List<Unit> enemiesList = new ArrayList<>();
        for (Unit unit: getAllUnits()){
            if (!unit.sendSecretString().equals("ALLY")) {
                enemiesList.add(unit);
            }
        }

        int sumShots = 0;

        for (Unit enemy: enemiesList) {
            sumShots += enemy.getHitCount();
        }
        log.info("count of all shots {}", sumShots);

        int minShotsToTANK = 0;
        int maxShotsToTANK = 0;
        int avgShotsToTANK = 0;
        int sumShotsToTANK = 0;
        int countOfDeadTank = 0;

        int minShotsToINFANTRY = 0;
        int maxShotsToINFANTRY = 0;
        int avgShotsToINFANTRY = 0;
        int sumShotsToINFANTRY = 0;
        int countOfDeadInfantry = 0;

        for (Unit unit: enemiesList) {
            if (unit.getClass() == Tank.class && !unit.isAlive()) {
                sumShotsToTANK += unit.getHitCount();
                countOfDeadTank++;
                if (minShotsToTANK == 0 || maxShotsToTANK == 0) {
                    minShotsToTANK = unit.getHitCount();
                    maxShotsToTANK = unit.getHitCount();
                } else if (minShotsToTANK > unit.getHitCount()) {
                    minShotsToTANK = unit.getHitCount();
                }else if (maxShotsToTANK < unit.getHitCount()) {
                    maxShotsToTANK = unit.getHitCount();
                }


            } else if (unit.getClass() == Infantry.class && !unit.isAlive()) {
                sumShotsToINFANTRY += unit.getHitCount();
                countOfDeadInfantry++;
                if (minShotsToINFANTRY == 0 || maxShotsToINFANTRY == 0) {
                    minShotsToINFANTRY = unit.getHitCount();
                    maxShotsToINFANTRY = unit.getHitCount();
                }else if (minShotsToINFANTRY > unit.getHitCount()) {
                    minShotsToINFANTRY = unit.getHitCount();
                }else if (maxShotsToINFANTRY < unit.getHitCount()) {
                    maxShotsToINFANTRY = unit.getHitCount();
                }
            }
        }
        avgShotsToTANK = sumShotsToTANK / countOfDeadTank;
        avgShotsToINFANTRY = sumShotsToINFANTRY / countOfDeadInfantry;

        log.debug("minShotsToTANK is {}", minShotsToTANK);
        log.debug("maxShotsToTANK is {}", maxShotsToTANK);
        log.debug("avgShotsToTANK is {}", avgShotsToTANK);
        log.debug("minShotsToINFANTRY is {}", minShotsToINFANTRY);
        log.debug("maxShotsToINFANTRY is {}", maxShotsToINFANTRY);
        log.debug("avgShotsToINFANTRY is {}", avgShotsToINFANTRY);
    }

    public synchronized void addAmmunition(Ammunition ammunition) {
        ammunitionList.add(ammunition);
    }

    public Battlefield getBattlefield() {
        return battlefield;
    }

    public void setBattlefield(Battlefield battlefield) {
        this.battlefield = battlefield;
    }
}
