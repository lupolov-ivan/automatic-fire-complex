package automatic.fire.complex.simulation;

import automatic.fire.complex.units.Unit;
import automatic.fire.complex.units.enemy.Infantry;
import automatic.fire.complex.units.enemy.Tank;
import automatic.fire.complex.units.gun.AutomaticFireComplex;
import org.w3c.dom.ls.LSOutput;

import java.util.Arrays;
import java.util.List;

public class Battlefield {

    private int width;
    private int length;
    private Unit[][] battlefield;

    public Battlefield(int width, int length) {
        this.width = width; //correspond max value X
        this.length = length; //correspond max value Y
        this.battlefield = new Unit[length][width];
    }

    public boolean putUnit(Unit unit) {
        int x = unit.getPosX();
        int y = unit.getPosY();

        if (battlefield[y][x] == null) {
            battlefield[y][x] = unit;
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
                    }
                }
        );
        return true;
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
}
