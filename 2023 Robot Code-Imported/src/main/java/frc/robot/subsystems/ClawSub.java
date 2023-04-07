// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClawSub extends SubsystemBase {

  // Compressor and Solenoids
  private DoubleSolenoid m_clawDouble = new DoubleSolenoid(Constants.pcm, PneumaticsModuleType.CTREPCM, 0, 1);

  public ClawSub() {
    m_clawDouble.set(Value.kReverse);
  }

  /*
   *  Manipulation Methods
   */

  public void open(){
    m_clawDouble.set(Value.kForward);
  }

  public void close(){
    m_clawDouble.set(Value.kReverse);
  }

  public void toggle(){
    m_clawDouble.toggle();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
