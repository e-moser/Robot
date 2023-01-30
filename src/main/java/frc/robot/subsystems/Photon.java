// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.photonvision.PhotonCamera;

public class Photon extends SubsystemBase {

  public Photon() {}

  private PhotonCamera camera = new PhotonCamera("photonvision");

  public boolean hasTarget() {
    var results = camera.getLatestResult();
    boolean hasTarget = results.hasTargets();
    return hasTarget;
  }

  public double getYaw() {
    var results = camera.getLatestResult();
    double yaw = results.getBestTarget().getYaw();
    return yaw;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
