package automatic.fire.complex.Factories;

import automatic.fire.complex.ShellsSystem.Cassette;
import automatic.fire.complex.ShellsSystem.Shell;
import automatic.fire.complex.ShellsSystem.TypeShell;

import java.util.LinkedList;

public class AmmunitionFactory {
    private static final int quantityShellsInArmorCassette = 10;
    private static final int quantityShellsInBurstCassette = 15;

    public static LinkedList<Cassette<Shell>> initListOfCassette(int quantityCassettes, TypeShell typeShell){
        LinkedList<Cassette<Shell>> listCassettes = new LinkedList<>();
        int quantity;
        if (typeShell == TypeShell.ARMOR){
            quantity = quantityShellsInArmorCassette;
        } else {
            quantity = quantityShellsInBurstCassette;
        }
        for (int i = 1; i <= quantityCassettes; i++) {
            listCassettes.add(CassetteFactory.createCassette(quantity, typeShell));
        }
        return listCassettes;
    }

}
