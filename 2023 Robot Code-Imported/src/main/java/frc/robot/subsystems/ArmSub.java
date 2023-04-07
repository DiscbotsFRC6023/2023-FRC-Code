// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ArmSub extends SubsystemBase {

  // Motor
  private WPI_TalonFX armMotor = new WPI_TalonFX(Constants.armMotor);

  public ArmSub() {
    armMotor.setNeutralMode(NeutralMode.Brake);
    armMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    armMotor.setSelectedSensorPosition(0.0);
    armMotor.configReverseSoftLimitThreshold(-50000, 0);
    armMotor.configForwardSoftLimitThreshold(-1000);
    armMotor.configReverseSoftLimitEnable(true, 0);
    armMotor.configForwardSoftLimitEnable(true, 0);
  }

  /*
   *  Manipulation Methods
   */
  public void drive(double speed){
    armMotor.set(ControlMode.PercentOutput, speed);
  }

  public void hold(){
    armMotor.set(ControlMode.Current, 0.1);
  }

  public void limited(boolean limit){
    armMotor.configForwardSoftLimitEnable(limit);
    armMotor.configReverseSoftLimitEnable(limit);
  }

  public double getPosition(){
    return armMotor.getSelectedSensorPosition();
  }

  public void stop(){
    armMotor.set(ControlMode.PercentOutput, 0.0);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
