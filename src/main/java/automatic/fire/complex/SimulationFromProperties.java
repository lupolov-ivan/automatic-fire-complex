package automatic.fire.complex;

import automatic.fire.complex.ammunition.Ammunition;
import automatic.fire.complex.simulation.Battlefield;
import automatic.fire.complex.simulation.RealitySimulationModule;
import automatic.fire.complex.units.Unit;
import automatic.fire.complex.units.enemy.Infantry;
import automatic.fire.complex.units.enemy.Tank;
import automatic.fire.complex.units.gun.AutomaticFireComplex;
import automatic.fire.complex.utils.BattlefieldPrinter;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SimulationFromProperties {

    public static void main(String[] args) {

        Yaml cfg = new Yaml();
        InputStream inputStream = SimulationFromProperties.class.getClassLoader().
                getResourceAsStream("configuration-fight.yml");
        Map<String, Object> fight = cfg.load(inputStream);

        Battlefield battlefield = null;
        RealitySimulationModule rsm = new RealitySimulationModule();
        List<Unit> guns = new ArrayList<>();
        List<Unit> enemies = new ArrayList<>();

        Set<Map.Entry<String, Object>> properties = fight.entrySet();

        for (Map.Entry<String, Object> entry : properties) {
            switch (entry.getKey()) {
                case "battlefield" : {
                    Map<String, Object> bf = (Map<String, Object>) entry.getValue();
                    int length = (int) bf.get("length");
                    int width = (int) bf.get("width");

                    battlefield = new Battlefield(width, length);
                    rsm.setBattlefield(battlefield);
                    break;
                }
                case "guns" : {
                    for (Object gunObj : (List<Object>) entry.getValue()) {
                        Map<String, Object> gun = (Map<String, Object>) gunObj;
                        int x = (int) gun.get("posX");
                        int y = (int) gun.get("posY");
                        int protectionLevel = (int) gun.get("protectionLevel");
                        int quantityBurstingCassette = (int) gun.get("quantityBurstingCassette");
                        int capacityBurstingCassette = (int) gun.get("capacityBurstingCassette");
                        int quantityArmorPiercingCassette = (int) gun.get("quantityArmorPiercingCassette");
                        int capacityArmorPiercingCassette = (int) gun.get("capacityArmorPiercingCassette");

                        Ammunition ammo = Ammunition.createAmmunition(quantityBurstingCassette, capacityBurstingCassette, quantityArmorPiercingCassette, capacityArmorPiercingCassette);
                        AutomaticFireComplex afc = new AutomaticFireComplex(x,y,protectionLevel,ammo,rsm);
                        guns.add(afc);
                    }
                    battlefield.putUnits(guns);
                    break;
                }
                case "enemies" : {
                    for (Object enemyObj : (List<Object>) entry.getValue()) {
                        Map<String, Object> enemy = (Map<String, Object>) enemyObj;
                        int x = (int) enemy.get("posX");
                        int y = (int) enemy.get("posY");
                        int protectionLevel = (int) enemy.get("protectionLevel");
                        int speed = (int) enemy.get("speed");
                        String type = (String) enemy.get("type");
                        switch (type) {
                            case "TANK" : {
                                Tank tank = new Tank(x,y,protectionLevel, speed, rsm);
                                enemies.add(tank);
                                break;
                            }
                            case "INFANTRY" : {
                                Infantry infantry = new Infantry(x,y,protectionLevel, speed, rsm);
                                enemies.add(infantry);
                                break;
                            }
                        }
                    }
                    battlefield.putUnits(enemies);
                    break;
                }
            }
        }

        BattlefieldPrinter.prettyPrintBattlefield(battlefield);

        List<Thread> joiningList = new ArrayList<>();

        enemies.forEach(enemy -> {
            new Thread((Runnable) enemy).start();
        });

        guns.forEach(gun -> {
            Thread t = new Thread((Runnable) gun);
            t.start();
            joiningList.add(t);
        });

        joiningList.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        BattlefieldPrinter.prettyPrintBattlefield(battlefield);

        rsm.battleWasFinished();
    }
}
