package automatic.fire.complex;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SimulationFromProperties {
    public static void main(String[] args) throws ClassNotFoundException {
        Yaml cfg = new Yaml();
        InputStream inputStream = SimulationFromProperties.class.
                getClassLoader().
                getResourceAsStream("configuration.yml");
        Person person = cfg.load(inputStream);
        System.out.println(person);

//        Set<Map.Entry<String, Object>> entries = obj.entrySet();

//        for (Map.Entry<String, Object> entry : entries) {
//            System.out.println(entry.getKey());
//            for (Object object : (List<Object>) entry.getValue()) {
//                System.out.println(object);
//            }
//        }
    }
}
