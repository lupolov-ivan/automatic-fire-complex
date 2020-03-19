package automatic.fire.complex.Factories;

import automatic.fire.complex.ShellsSystem.*;

public class CassetteFactory {

    public static Cassette<Shell> createCassette(int quantityShells, TypeShell typeShell){
        Cassette<Shell> cassette = new Cassette<>(quantityShells);
        switch (typeShell) {
            case ARMOR:
                for (int i = 1; i <= quantityShells; i++){
                    cassette.add(new ArmorPiercingShell());
                }
                return cassette;
            case BURST:
                for (int i = 1; i <= quantityShells; i++){
                    cassette.add(new BurstingShell());
                }
                return cassette;
            default:
                return null;
        }
    }
}
