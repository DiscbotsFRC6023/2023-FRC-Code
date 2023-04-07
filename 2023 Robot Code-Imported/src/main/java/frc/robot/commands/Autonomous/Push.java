// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSub;

public class Push extends CommandBase {
  /** Creates a new Push. */
  DrivetrainSub m_drive;
  Timer time = new Timer();
  public Push(DrivetrainSub p_drive) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drive = p_drive;
    addRequirements(m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    time.reset();
    time.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drive.drive(-0.6, 0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    time.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return time.get() >= 3.7;
  }
}
