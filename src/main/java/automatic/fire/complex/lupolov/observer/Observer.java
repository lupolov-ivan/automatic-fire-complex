package automatic.fire.complex.lupolov.observer;

import automatic.fire.complex.lupolov.simulation.EnemyData;

import java.util.List;

public interface Observer {

    void updatePosition(List<EnemyData> enemiesPosition);
}
