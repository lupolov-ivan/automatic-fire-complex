package automatic.fire.complex.simulation;

import automatic.fire.complex.units.Unit;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Battlefield {

    private int width;
    private int length;
    private Unit[][] battlefield;
    List<Unit> unitsToUpdate;

    public Battlefield(int width, int length) {
        this.width = width; //correspond max value X
        this.length = length; //correspond max value Y
        this.battlefield = new Unit[length][width];
        this.unitsToUpdate = new CopyOnWriteArrayList<>();
    }

    public boolean putUnit(Unit unit) {
        int x = unit.getPosX();
        int y = unit.getPosY();

        if (battlefield[y][x] == null) {
            battlefield[y][x] = unit;
            unitsToUpdate.add(unit);
            return true;
        }
        return false;
    }

    public boolean putUnits(List<Unit> units) {
        units.forEach(
                unit -> {
                    int x = unit.getPosX();
                    int y = unit.getPosY();

                    if (battlefield[y][x] == null) {
                        battlefield[y][x] = unit;
                        unitsToUpdate.add(unit);
                    }
                }
        );
        return true;
    }

    public synchronized boolean updateUnitPosition() {
        clearBattlefield();
        return putUnits(unitsToUpdate);
    }

    public void clearCellValue(int x, int y) {
        if (battlefield[y][x] != null) {
            battlefield[y][x] = null;
        }
    }

    public Unit getCellValue(int x, int y) {
        return battlefield[y][x];
    }

    public boolean isEmpty(int x, int y) {
        return battlefield[y][x] == null;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public void clearBattlefield() {
        for (int y = 0; y < length; y++) {
            for (int x = 0; x < width; x++) {
                battlefield[y][x] = null;
            }
        }
    }
}
