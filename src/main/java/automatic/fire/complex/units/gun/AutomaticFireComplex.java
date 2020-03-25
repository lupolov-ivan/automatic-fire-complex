package automatic.fire.complex.units.gun;

import automatic.fire.complex.ammunition.Ammunition;
import automatic.fire.complex.simulation.EnemyData;
import automatic.fire.complex.simulation.RealitySimulationModule;
import automatic.fire.complex.systems.Radar;
import automatic.fire.complex.systems.aim.AimingSystem;
import automatic.fire.complex.systems.aim.MechanicalInertialAimSystem;
import automatic.fire.complex.systems.fire.FireSystem;
import automatic.fire.complex.systems.fire.FireSystem3000;
import automatic.fire.complex.systems.loading.AutomationLoadingSystem3000;
import automatic.fire.complex.units.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AutomaticFireComplex extends Unit implements Runnable {

    Logger log = LoggerFactory.getLogger(AutomaticFireComplex.class);

    private RealitySimulationModule rsm;
    private AimingSystem aimingSystem;
    private FireSystem fireSystem;
    private Radar radar;
    private Ammunition ammunition;

    public AutomaticFireComplex(int posX, int posY, int protectionLevel, Ammunition ammunition, RealitySimulationModule rsm) {
        super(posX, posY, protectionLevel);
        this.rsm = rsm;
        this.aimingSystem = new MechanicalInertialAimSystem(this.getPosX(), this.getPosY());
        this.fireSystem = new FireSystem3000(new AutomationLoadingSystem3000(ammunition));
        this.radar = new Radar(rsm);
        this.ammunition = ammunition;
    }

    @Override
    public String sendSecretString() {
        return "ALLY";
    }

    public void patrol() {
        while (true) {

            List<EnemyData> lastPosition = radar.checkField();

            if (lastPosition.size() == 0) {
                if(radar.getSizeIgnoreList() > 0) {
                    log.debug("No shells of the required type to destroy remaining targets. Stopping fire...");
                    break;
                }
                log.debug("There is no enemies to destroy. Stopping fire...");
                fireSystem.noMoreEnemies();
                break;
            }

            EnemyData target = aimingSystem.catchTarget(lastPosition);

            if(!fireSystem.makeShot(target)) {
                radar.addTypeToIgnore(target.getType());
                log.info("No shells for {} \n{}", target.getType().toString(), ammunition);
                continue;
            }

            log.debug("AFC '{}' shot to target '{}'", this, target);

            rsm.toDamage(target);
        }


    }

    @Override
    public void run() {
        patrol();
    }
}
