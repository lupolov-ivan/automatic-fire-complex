package automatic.fire.complex.utils;

import automatic.fire.complex.simulation.Battlefield;
import automatic.fire.complex.units.Unit;
import automatic.fire.complex.units.enemy.Infantry;
import automatic.fire.complex.units.enemy.Tank;
import automatic.fire.complex.units.gun.AutomaticFireComplex;

public class BattlefieldPrinter {

    public static void prettyPrintBattlefield(Battlefield battlefield) {

        System.out.println("X - alive unit");
        System.out.println("(X) - dead unit");

        int width = battlefield.getWidth();
        int length = battlefield.getLength();

        for (int y = -1; y < length; y++) {
            if (printFirstLine(width, y)) continue;
            for (int x = 0; x < width; x++) {

                Unit unit = battlefield.getCellValue(x,y);
                if (unit == null) {
                    System.out.print("\t" + "\t|");
                } else {
                    if (unit.isAlive()) {
                        System.out.print("\t" + getLetter(unit) + "\t|");
                    } else {
                        System.out.print("\t("+ getLetter(unit) +")\t|");
                    }
                }
            }
            System.out.println();
        }
    }

    private static boolean printFirstLine(int width, int y) {
        if (y == -1) {
            for (int i = 0; i < width; i++) {
                System.out.print("\t"+ i +"\t");
            }
            System.out.println();
            return true;
        }
        System.out.print(y +" |");
        return false;
    }

    private static String getLetter(Unit unit) {
        if (unit.getClass() == Tank.class) {
            return "T";
        }
        if (unit.getClass() == Infantry.class) {
            return "I";
        }
        if (unit.getClass() == AutomaticFireComplex.class) {
            return "A";
        }
        return "U";
    }

}
