package frc.robot.subsystems;

import frc.robot.Constants;
import java.util.List;
import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class VisionSub extends SubsystemBase {
    
    private PhotonCamera m_photon = new PhotonCamera(Constants.camName);
    private boolean hasTarget;
    private PhotonPipelineResult result;

    public VisionSub(){}

    @Override
    public void periodic() {
        PhotonPipelineResult result = m_photon.getLatestResult();
        hasTarget = result.hasTargets();
        if (hasTarget) {
            this.result = result;
        }
    }
    public PhotonTrackedTarget getTargetWithID(int id) {
        List<PhotonTrackedTarget> targets = result.getTargets();
        for (PhotonTrackedTarget tar : targets) {
            if (tar.getFiducialId() == id) {
                return tar;
            }
        }
        return null;
    }
    
    public PhotonTrackedTarget getBestTarget() {
        if (hasTarget) {
        return result.getBestTarget();
        }
    return null;
    }

    public boolean getHasTarget() {
        return hasTarget;
    }

    public double getDistance(){
        if(result.hasTargets()){
            return PhotonUtils.calculateDistanceToTargetMeters(
                Constants.camHeight, 
                Constants.gridTagHeight, 
                Constants.camPitch, 
                Units.degreesToRadians(result.getBestTarget().getPitch())
            );
        }
        return 0.0;
    }
}