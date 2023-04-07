// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Helpers;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DrivetrainSub;

public class DriveToDistance extends CommandBase {

  private DrivetrainSub m_drive;
  private double speed;
  private double distance;

  public DriveToDistance(double distance, double speed, DrivetrainSub p_drive) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drive = p_drive;
    this.speed = speed;
    this.distance = distance;
    addRequirements(m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drive.resetEncoders();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drive.drive(speed, 0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drive.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // End this command when we have driven the right distance
    return m_drive.getLeftDistance() >= distance * Constants.encoderRotationsPerFoot;
  }
}
