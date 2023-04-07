// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Helpers;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class HoldTimer extends CommandBase {

  private double seconds;
  private Timer time = new Timer();

  public HoldTimer(double seconds) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.seconds = seconds;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    time.reset();
    time.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // Command ends when the timer has reached the seconds parameter
    return time.get() >= seconds;
  }
}
