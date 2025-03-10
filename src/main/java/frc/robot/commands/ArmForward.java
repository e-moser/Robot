// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;
import java.util.function.DoubleSupplier;

public class ArmForward extends CommandBase {
  private final Arm m_arm;
  private DoubleSupplier m_speed;

  public ArmForward(Arm subsystem, DoubleSupplier speed) {
    m_arm = subsystem;
    m_speed = speed;
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_arm.setMotorForward(m_speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_arm.stopMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_arm.queryFront();
  }
}
