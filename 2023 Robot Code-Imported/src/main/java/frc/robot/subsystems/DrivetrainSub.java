// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.*;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.Constants;

public class DrivetrainSub extends SubsystemBase {

  // Motors
  private WPI_TalonFX LD1 = new WPI_TalonFX(Constants.LD1);
  private WPI_TalonFX LD2 = new WPI_TalonFX(Constants.LD2);
  private WPI_TalonFX LD3 = new WPI_TalonFX(Constants.LD3);

  private WPI_TalonFX RD1 = new WPI_TalonFX(Constants.RD1);
  private WPI_TalonFX RD2 = new WPI_TalonFX(Constants.RD2);
  private WPI_TalonFX RD3 = new WPI_TalonFX(Constants.RD3);
  
  // Motor Controller Groups
  private MotorControllerGroup leftGroup = new MotorControllerGroup(LD1, LD2, LD3);
  private MotorControllerGroup rightGroup = new MotorControllerGroup(RD1, RD2, RD3);

  // NavX Micro
  private AHRS m_nav = new AHRS(SerialPort.Port.kMXP);

  // Drivetrain Creation
  private DifferentialDrive m_drive = new DifferentialDrive(leftGroup, rightGroup);  

  public DrivetrainSub() {
    // Inverting the left side of our drivetrain
    this.rightGroup.setInverted(true);

    // Disabling drivetrain safety
    m_drive.setSafetyEnabled(false);

    // Motor Configs
    LD1.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    LD1.setNeutralMode(NeutralMode.Coast);
    LD1.configOpenloopRamp(Constants.driveRampTime);
  
    LD2.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    LD2.setNeutralMode(NeutralMode.Coast);
    LD2.configOpenloopRamp(Constants.driveRampTime);

    LD3.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    LD3.setNeutralMode(NeutralMode.Coast);
    LD3.configOpenloopRamp(Constants.driveRampTime);

    RD1.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    RD1.setNeutralMode(NeutralMode.Coast);
    RD1.configOpenloopRamp(Constants.driveRampTime);
    
    RD2.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    RD2.setNeutralMode(NeutralMode.Coast);
    RD2.configOpenloopRamp(Constants.driveRampTime);

    RD3.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    RD3.setNeutralMode(NeutralMode.Coast);
    RD3.configOpenloopRamp(Constants.driveRampTime);
  }

  /*
   *  Manipulation Methods
   */
  public void drive(double speed, double rotation){
    m_drive.arcadeDrive(speed, rotation);
  }

  public void stop(){
    m_drive.arcadeDrive(0, 0);
  }

  public void setMode(String mode){
    if(mode.equals("brake")){

      LD1.setNeutralMode(NeutralMode.Brake);
      LD2.setNeutralMode(NeutralMode.Brake);
      LD3.setNeutralMode(NeutralMode.Brake);
      RD1.setNeutralMode(NeutralMode.Brake);
      RD2.setNeutralMode(NeutralMode.Brake);
      RD3.setNeutralMode(NeutralMode.Brake);

    } else if(mode.equals("coast")){

      LD1.setNeutralMode(NeutralMode.Coast);
      LD2.setNeutralMode(NeutralMode.Coast);
      LD3.setNeutralMode(NeutralMode.Coast);
      RD1.setNeutralMode(NeutralMode.Coast);
      RD2.setNeutralMode(NeutralMode.Coast);
      RD3.setNeutralMode(NeutralMode.Coast);

    }
  }

  public void setDefaultCommand(Command defaultCommand){
    super.setDefaultCommand(defaultCommand);
  }

  /*
   * NavX methods
   */
  public double getPitch(){
    return m_nav.getPitch();
  }

  public boolean isConnected(){
    return m_nav.isConnected();
  }

  public void zeroYaw(){
    m_nav.zeroYaw();
  }

  public Rotation2d getRot2d(){
    return m_nav.getRotation2d();
  }

  

  public float getYaw(){
    System.out.println("YAW: " + m_nav.getYaw
    ());
    System.out.println("ANGLE: " + m_nav.getAngle());
    return m_nav.getYaw();
    
  }

  /*
   * Encoder Methods 
   */
  public double getLeftDistance(){
    return -(LD1.getSelectedSensorPosition() + LD2.getSelectedSensorPosition() + LD3.getSelectedSensorPosition()) / 3;
  }

  public double getRightDistance(){
    return (RD1.getSelectedSensorPosition() + RD2.getSelectedSensorPosition() + RD3.getSelectedSensorPosition()) / 3;
  }

  public void resetEncoders(){
    LD1.setSelectedSensorPosition(0);
    LD2.setSelectedSensorPosition(0);
    LD3.setSelectedSensorPosition(0);

    RD1.setSelectedSensorPosition(0);
    RD2.setSelectedSensorPosition(0);
    RD3.setSelectedSensorPosition(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
