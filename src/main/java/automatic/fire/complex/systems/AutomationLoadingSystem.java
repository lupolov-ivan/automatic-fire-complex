package automatic.fire.complex.systems;

import automatic.fire.complex.ShellsSystem.Cassette;
import automatic.fire.complex.ShellsSystem.Shell;

public interface AutomationLoadingSystem {

    boolean loadCassette(Cassette<? extends Shell> cassette);

    Cassette<?> disconnectCassette();   // think about VOID

    void extractShell();

    Cassette<?> getCurrentCassette();  // пока что так

}
