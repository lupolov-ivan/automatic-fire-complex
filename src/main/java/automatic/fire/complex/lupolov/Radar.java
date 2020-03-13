package automatic.fire.complex.lupolov.observers;

import automatic.fire.complex.lupolov.Subject;
import automatic.fire.complex.lupolov.units.Unit;

import java.util.ArrayList;
import java.util.List;

public class Radar implements Subject {

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
        complexes.forEach(observer -> observer.updatePosition(enemiesPosition));
        System.out.printf("Enemy's position information send to all AFC%n");
    }

    public void checkField(Unit[][] battleField) {
        enemiesPosition.clear();

        int width = battleField[0].length;
        int length = battleField.length;

        System.out.printf("Radar are starting checking battlefield...%n");
        for (int y = 0; y < width; y++) {
            for (int x = 0; x < length; x++) {
                Unit unit = battleField[y][x];
                if (unit != null && unit.sendSecretString().equals("ENEMY")) {
                    enemiesPosition.add(unit);
                    System.out.printf("Detected new enemy: %s%n", unit);
                }
            }
        }
        System.out.printf("Radar finish checking battlefield%n");
        notifyAllAFC();
    }
}
