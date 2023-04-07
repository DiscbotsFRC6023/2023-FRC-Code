// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSub;
import frc.robot.subsystems.VisionSub;

public class CenterToAprilTag extends CommandBase {
  
  private VisionSub m_vision;
  private DrivetrainSub m_drive;

  public CenterToAprilTag(DrivetrainSub p_drive, VisionSub p_vision) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_vision = p_vision;
    m_drive = p_drive;
    addRequirements(m_drive, m_vision);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_vision.getHasTarget() && m_vision.getDistance() <= 20.0){
      m_drive.drive(0.3, m_vision.getBestTarget().getYaw());
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drive.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
