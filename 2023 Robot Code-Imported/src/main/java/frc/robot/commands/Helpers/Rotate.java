// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Helpers;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSub;

public class Rotate extends CommandBase {

  private DrivetrainSub m_drive;
  private float degrees;
  private float rotation;

  public Rotate(int degrees, DrivetrainSub p_drive) {
    m_drive = p_drive;
    this.degrees = degrees;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drive.zeroYaw();
    rotation = m_drive.getYaw();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drive.drive(0.1, 0.4);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drive.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // Command ends when our current yaw is equal to our desired angle
    return m_drive.getYaw() >= degrees;
  }
}
