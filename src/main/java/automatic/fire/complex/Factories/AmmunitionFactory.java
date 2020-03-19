package automatic.fire.complex.Factories;

import automatic.fire.complex.ShellsSystem.Cassette;
import automatic.fire.complex.ShellsSystem.Shell;
import automatic.fire.complex.ShellsSystem.TypeShell;

import java.util.LinkedList;

public class AmmunitionFactory {
    private static final int QUANTITY_SHELLS_IN_AP_CASSETTES = 10;
    private static final int QUANTITY_SHELLS_IN_B_CASSETTES = 15;

    public static LinkedList<Cassette<Shell>> initListOfCassette(int quantityCassettes, TypeShell typeShell, int quantityShells){
        LinkedList<Cassette<Shell>> listCassettes = new LinkedList<>();
        for (int i = 1; i <= quantityCassettes; i++) {
            listCassettes.add(CassetteFactory.createCassette(quantityShells, typeShell));
        }
        return listCassettes;
    }

    public static LinkedList<Cassette<Shell>> initListOfCassette(int quantityCassettes, TypeShell typeShell) {
        if (typeShell == TypeShell.ARMOR){
            return initListOfCassette(quantityCassettes, typeShell, QUANTITY_SHELLS_IN_AP_CASSETTES);
        } else {
            return initListOfCassette(quantityCassettes, typeShell, QUANTITY_SHELLS_IN_B_CASSETTES);
        }
    }
}
