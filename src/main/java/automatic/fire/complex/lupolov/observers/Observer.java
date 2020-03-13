package automatic.fire.complex.lupolov.observers;

import automatic.fire.complex.lupolov.units.Unit;

import java.util.List;

public interface Observer {

    void updatePosition(List<Unit> enemiesPosition);
}
