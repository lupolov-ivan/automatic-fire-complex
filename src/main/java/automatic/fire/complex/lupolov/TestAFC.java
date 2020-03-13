package automatic.fire.complex.lupolov;

import automatic.fire.complex.lupolov.observers.Observer;
import automatic.fire.complex.lupolov.observers.Radar;
import automatic.fire.complex.lupolov.units.AutomaticFireComplex;
import automatic.fire.complex.lupolov.units.Enemy;
import automatic.fire.complex.lupolov.units.Unit;

public class TestAFC {
    public static void main(String[] args) {

        Unit unit1 = new AutomaticFireComplex(0,0, "AFC");
        Unit unit2 = new AutomaticFireComplex(3,3, "AFC");
        Unit unit3 = new Enemy(1,1, "Tank");
        Unit unit4 = new Enemy(2,2, "Tank");
        Unit unit5 = new Enemy(2,1, "Infantry");
        Unit unit6 = new Enemy(1,2, "Infantry");

        Unit[][] battlefield = new Unit[][]{{unit1, null, null, null},  // {00, 01, 02, 03}
                {null, unit3, unit6, null},  // {10, 11, 12, 13}
                {null, unit5, unit4, null},  // {20, 21, 22, 23}
                {null, null, null, unit2}}; // {30, 31, 32, 33}

        Radar radar = new Radar();
        radar.register((Observer) unit1);
        radar.register((Observer) unit2);

        for (int i = 0; i < 10; i++) {
            System.out.println("============================================> Check #"+ i);
            radar.checkField(battlefield);
        }
    }
}
