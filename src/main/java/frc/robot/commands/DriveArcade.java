// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Drive;
import frc.robot.subsystems.ExampleSubsystem;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class DriveArcade extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Drive m_subsystem;
  private final DoubleSupplier m_fSpeed;
  private final DoubleSupplier m_rotation;

  public DriveArcade(Drive subsystem, DoubleSupplier fSpeed, DoubleSupplier rotation) {
    m_subsystem = subsystem;
    m_fSpeed = fSpeed;
    m_rotation = rotation;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    //m_subsystem.setArcadeDrive(m_fSpeed, m_rotation);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
