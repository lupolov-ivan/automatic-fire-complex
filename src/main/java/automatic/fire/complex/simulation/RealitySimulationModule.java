package automatic.fire.complex.simulation;

import automatic.fire.complex.ammunition.Ammunition;
import automatic.fire.complex.ammunition.Cassette;
import automatic.fire.complex.units.Unit;
import automatic.fire.complex.units.enemy.EnemyType;
import automatic.fire.complex.units.enemy.Infantry;
import automatic.fire.complex.units.enemy.Tank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class RealitySimulationModule {

    Logger log = LoggerFactory.getLogger(RealitySimulationModule.class);

    private volatile Battlefield battlefield;
    private volatile boolean isCriticalDistanceReached;

    private long startTime;
    private List<Ammunition> ammunitionList;

    private int countOfMisses = 0;

    public RealitySimulationModule() {
        startTime = System.currentTimeMillis();
        ammunitionList = new ArrayList<>();
    }

    public RealitySimulationModule(Battlefield battlefield) {
        this.battlefield = battlefield;
        startTime = System.currentTimeMillis();
        ammunitionList = new ArrayList<>();
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

        Unit unit = getUnit(data.getPosX(), data.getPosY());

        if(unit == null) {
            countOfMisses++;
            log.debug("Miss");
            return;
        }

        int currentHitCount = unit.getHitCount();
        double currentTakenDamage = unit.getDamageTaken();

        unit.setHitCount(++currentHitCount);
        unit.setDamageTaken(data.getDamage() + currentTakenDamage);

        log.debug("Current number of hit: {}", unit.getHitCount());
        log.debug("Current taken damage: {}", unit.getDamageTaken());

        if(!unit.isAlive()) {
            log.debug("Target is already destroyed, It was useless shot!");
            return;
        }

        if (data.getType() == EnemyType.TANK && unit.getProtectionLevel() <= unit.getDamageTaken()) {
            unit.setAlive(false);
            log.debug("Target '{}' destroyed. Type: {}", unit, data.getType().toString());
        }

        if (data.getType() == EnemyType.INFANTRY && (unit.getProtectionLevel()*0.7) <= unit.getDamageTaken()) {
            unit.setAlive(false);
            log.debug("Target '{}' destroyed. Type: {}", unit, data.getType().toString());
        }
    }

    public synchronized boolean updateUnitPosition(int oldX, int oldY, Unit unit) {
        return battlefield.updateUnitPosition(oldX, oldY, unit);
    }

    public synchronized boolean isCriticalDistanceReached() {
        return isCriticalDistanceReached;
    }

    public synchronized void setCriticalDistanceReached(boolean criticalDistanceReached) {
        isCriticalDistanceReached = criticalDistanceReached;
    }

    public void printFightReport() {

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

        for (Unit unit: getAllUnits()){
            if (!unit.sendSecretString().equals("ALLY")) {

                if (unit.getClass() == Tank.class && !unit.isAlive()) {
                    sumShotsToTANK += unit.getHitCount();
                    countOfDeadTank++;
                    if (minShotsToTANK == 0 || maxShotsToTANK == 0) {
                        minShotsToTANK = unit.getHitCount();
                        maxShotsToTANK = unit.getHitCount();
                    } else if (minShotsToTANK > unit.getHitCount()) {
                        minShotsToTANK = unit.getHitCount();
                    } else if (maxShotsToTANK < unit.getHitCount()) {
                        maxShotsToTANK = unit.getHitCount();
                    }

                } else if (unit.getClass() == Infantry.class && !unit.isAlive()) {
                    sumShotsToINFANTRY += unit.getHitCount();
                    countOfDeadInfantry++;
                    if (minShotsToINFANTRY == 0 || maxShotsToINFANTRY == 0) {
                        minShotsToINFANTRY = unit.getHitCount();
                        maxShotsToINFANTRY = unit.getHitCount();
                    } else if (minShotsToINFANTRY > unit.getHitCount()) {
                        minShotsToINFANTRY = unit.getHitCount();
                    } else if (maxShotsToINFANTRY < unit.getHitCount()) {
                        maxShotsToINFANTRY = unit.getHitCount();
                    }
                }

            }
        }

        log.debug("Count of misses: {} ({} %)", countOfMisses, (100*countOfMisses)/(countShotsOfBurst + countShotsOfArmor));

        if(countOfDeadTank > 0) {
            avgShotsToTANK = sumShotsToTANK / countOfDeadTank;
        }

        if(countOfDeadInfantry > 0) {
            avgShotsToINFANTRY = sumShotsToINFANTRY / countOfDeadInfantry;
        }

        log.debug("minShotsToTANK is {}", minShotsToTANK);
        log.debug("maxShotsToTANK is {}", maxShotsToTANK);
        log.debug("avgShotsToTANK is {}", avgShotsToTANK);
        log.debug("minShotsToINFANTRY is {}", minShotsToINFANTRY);
        log.debug("maxShotsToINFANTRY is {}", maxShotsToINFANTRY);
        log.debug("avgShotsToINFANTRY is {}", avgShotsToINFANTRY);

        log.debug("sum count of all enemies {}", sumShotsToINFANTRY + sumShotsToTANK);
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
