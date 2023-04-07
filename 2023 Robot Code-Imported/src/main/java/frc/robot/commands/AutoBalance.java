// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DrivetrainSub;

public class AutoBalance extends CommandBase {

  private DrivetrainSub m_drive;
  private boolean withinThresh;

  public AutoBalance(DrivetrainSub p_drive) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drive = p_drive;
    addRequirements(m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drive.setMode("brake");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_drive.isConnected()){
      System.out.println(m_drive.getPitch());
      // Determine if we are balanced
      if(
        m_drive.getPitch() > Constants.balanceThresh|| 
        m_drive.getPitch() < -Constants.balanceThresh
        ){
          withinThresh = false;
        } else {
          withinThresh = true;
        }

      if(withinThresh){
        // balanced = true
        m_drive.drive(0, 0);
      } else {
        // balanced = false
        double pitchRadians = Units.degreesToRadians(m_drive.getPitch());
        m_drive.drive(MathUtil.clamp(pitchRadians * -1.4, -0.4, 0.4), 0);
      }

    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drive.stop();
    m_drive.setMode("brake");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
