// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ArmWinchSub extends SubsystemBase {

  // Motors
  private WPI_TalonSRX armWinchMotor = new WPI_TalonSRX(Constants.armWinchMotor);
  private WPI_TalonSRX armWinchMotor2 = new WPI_TalonSRX(Constants.armWinchMotor2);

  private MotorControllerGroup m_motors = new MotorControllerGroup(armWinchMotor, armWinchMotor2);

  private DutyCycleEncoder winchEncoder = new DutyCycleEncoder(0);

  public ArmWinchSub() {
    armWinchMotor.setNeutralMode(NeutralMode.Brake);
    armWinchMotor2.setNeutralMode(NeutralMode.Brake);
    armWinchMotor.configOpenloopRamp(1.0);
    armWinchMotor2.configOpenloopRamp(1.0);
    winchEncoder.reset();
  }

  public void setDefaultCommand(Command defaultCommand){
    super.setDefaultCommand(defaultCommand);
  }

  /*
   *  Manipulation Methods
   */
  public void drive(double speed){
    m_motors.set(speed);
  }

  public void stop(){
    m_motors.set(0.0);
  }

  public void hold(){
    m_motors.set(-0.10);
  }

  public void reset(){
    winchEncoder.reset();
  }

  public double getPosition(){
    return winchEncoder.get();
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
