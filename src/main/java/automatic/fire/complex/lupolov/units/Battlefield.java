package automatic.fire.complex.lupolov.units;

public class Battlefield {
    private int width;
    private int length;
    private Unit[][] battlefield;

    public Battlefield(int width, int length) {
        this.width = width; //correspond max value X
        this.length = length; //correspond max value Y
        this.battlefield = new Unit[width][length];
    }

    public boolean putUnit(Unit unit) {
        int x = unit.getPosX();
        int y = unit.getPosY();

        if (battlefield[x][y] == null) {
            battlefield[x][y] = unit;
            return true;
        }
        return false;
    }

    public void clearCellValue(int x, int y) {
        if (battlefield[x][y] != null) {
            battlefield[x][y] = null;
        }
    }

    public Unit getCellValue(int x, int y) {
        return battlefield[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public Unit[][] getBattlefield() {
        return battlefield;
    }
}
