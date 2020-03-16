package automatic.fire.complex.observer;

import automatic.fire.complex.simulation.EnemyData;

import java.util.List;

public interface Observer {

    void updatePosition(List<EnemyData> enemiesPosition);
}
