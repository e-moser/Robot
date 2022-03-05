// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.function.DoubleSupplier;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.SparkMaxRelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase {
  /** Creates a new Drive subsystem. */


    private CANSparkMax leftFront = new CANSparkMax(1, MotorType.kBrushless);
    //private CANSparkMax leftBack = new CANSparkMax(2, MotorType.kBrushless);
    //private CANSparkMax rightFront = new CANSparkMax(3, MotorType.kBrushless);
    private CANSparkMax rightBack = new CANSparkMax(4, MotorType.kBrushless);
    private SparkMaxPIDController leftFrontPidController;
    private SparkMaxPIDController leftBackPidController;
    private SparkMaxPIDController rightFrontPidController;
    private SparkMaxPIDController rightBackPidController;
    private MotorControllerGroup leftGroup = new MotorControllerGroup(leftFront);//FIXME add leftBack
    private MotorControllerGroup rightGroup = new MotorControllerGroup(rightBack);//FIXME add rightFront
    private RelativeEncoder leftFrontEncoder = leftFront.getEncoder();
    //private RelativeEncoder leftBackEncoder = leftBack.getEncoder();
    //private RelativeEncoder rightFrontEncoder = rightFront.getEncoder();
    private RelativeEncoder rightBackEncoder = rightBack.getEncoder();

    private DifferentialDrive driveDifferential = new DifferentialDrive(leftGroup, rightGroup);

  public void arcadeDrive(DoubleSupplier speed, DoubleSupplier rotation) {
      driveDifferential.arcadeDrive(speed.getAsDouble(), rotation.getAsDouble());
  }

  public void setDrive(double speed, double rotation){
    driveDifferential.arcadeDrive(speed, rotation);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Left Velocity", leftFrontEncoder.getVelocity());
    SmartDashboard.putNumber("Right Velocity", rightBackEncoder.getVelocity());//FIXME change to back
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public double getLeftRotations() {
    return leftFrontEncoder.getPosition();
  }

  public void setLeftRotations(double target) {
    leftFrontPidController.setReference(target, CANSparkMax.ControlType.kPosition);
  }

  public double getRightRotations() {
    return rightBackEncoder.getPosition();//FIXME change to front
  }

  public void setRightRotations(double target) {
    rightBackPidController.setReference(target, CANSparkMax.ControlType.kPosition);
  }

  public void doInit() {
    // PID coefficients
    double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;
    kP = 0.1;
    kI = 1e-4;
    kD = 1;
    kIz = 0;
    kFF = 0;
    kMaxOutput = 1;
    kMinOutput = -1;

    /**
     * The restoreFactoryDefaults method can be used to reset the configuration parameters
     * in the SPARK MAX to their factory default state. If no argument is passed, these
     * parameters will not persist between power cycles
     */
    leftFront.restoreFactoryDefaults();
    //leftBack.restoreFactoryDefaults(); //FIXME uncomment
    //rightFront.restoreFactoryDefaults(); //FIXME uncomment
    rightBack.restoreFactoryDefaults();

    /**
     * In order to use PID functionality for a controller, a SparkMaxPIDController object
     * is constructed by calling the getPIDController() method on an existing
     * CANSparkMax object
     */
    leftFrontPidController = leftFront.getPIDController();
    //leftBackPidController = leftBack.getPIDController();
    //rightFrontPidController = rightFront.getPIDController(); //FIXME uncomment
    rightBackPidController = rightBack.getPIDController();

     /**
    * In CAN mode, one SPARK MAX can be configured to follow another. This is done by calling
    * the follow() method on the SPARK MAX you want to configure as a follower, and by passing
    * as a parameter the SPARK MAX you want to configure as a leader.
    */
    //leftBack.follow(leftFront);
    //rightBack.follow(rightFront);

    // set PID coefficients
    leftFrontPidController.setP(kP);
    leftFrontPidController.setI(kI);
    leftFrontPidController.setD(kD);
    leftFrontPidController.setIZone(kIz);
    leftFrontPidController.setFF(kFF);
    leftFrontPidController.setOutputRange(kMinOutput, kMaxOutput);

    /*leftBackPidController.setP(kP);
    leftBackPidController.setI(kI);
    leftBackPidController.setD(kD);
    leftBackPidController.setIZone(kIz);
    leftBackPidController.setFF(kFF);
    leftBackPidController.setOutputRange(kMinOutput, kMaxOutput);

    rightFrontPidController.setP(kP);
    rightFrontPidController.setI(kI);
    rightFrontPidController.setD(kD);
    rightFrontPidController.setIZone(kIz);
    rightFrontPidController.setFF(kFF);
    rightFrontPidController.setOutputRange(kMinOutput, kMaxOutput);
*/
    rightBackPidController.setP(kP);
    rightBackPidController.setI(kI);
    rightBackPidController.setD(kD);
    rightBackPidController.setIZone(kIz);
    rightBackPidController.setFF(kFF);
    rightBackPidController.setOutputRange(kMinOutput, kMaxOutput);

    // display PID coefficients on SmartDashboard
    SmartDashboard.putNumber("P Gain", kP);
    SmartDashboard.putNumber("I Gain", kI);
    SmartDashboard.putNumber("D Gain", kD);
    SmartDashboard.putNumber("I Zone", kIz);
    SmartDashboard.putNumber("Feed Forward", kFF);
    SmartDashboard.putNumber("Max Output", kMaxOutput);
    SmartDashboard.putNumber("Min Output", kMinOutput);
    SmartDashboard.putNumber("Set Rotations", 0);
  }
}
