package automatic.fire.complex.lupolov.observers;

import automatic.fire.complex.lupolov.units.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Radar implements Subject {

    Logger log = LoggerFactory.getLogger(Radar.class);

    private List<automatic.fire.complex.lupolov.observers.Observer> complexes;
    private List<Unit> enemiesPosition;

    public Radar() {
        this.complexes = new ArrayList<>();
        this.enemiesPosition = new ArrayList<>();
    }

    @Override
    public void register(automatic.fire.complex.lupolov.observers.Observer complex) {
        complexes.add(complex);
    }

    @Override
    public void remove(automatic.fire.complex.lupolov.observers.Observer complex) {
        complexes.remove(complex);
    }

    @Override
    public void notifyAllAFC() {
        log.debug("Starting of sending enemy's position information to all AFC");
        complexes.forEach(observer -> observer.updatePosition(enemiesPosition));
        log.debug("End of sending enemy's position information to all AFC");
    }

    public void checkField(Unit[][] battleField) {
        enemiesPosition.clear();

        int width = battleField[0].length;
        int length = battleField.length;

        log.debug("Radar are starting checking battlefield...");
        for (int y = 0; y < width; y++) {
            for (int x = 0; x < length; x++) {
                Unit unit = battleField[y][x];
                if (unit != null && unit.sendSecretString().equals("ENEMY")) {
                    enemiesPosition.add(unit);
                    log.debug("Detected new enemy: {}", unit);
                }
            }
        }
        log.debug("Radar finish checking battlefield.");
        notifyAllAFC();
    }
}
