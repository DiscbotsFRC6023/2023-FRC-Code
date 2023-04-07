// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmWinchSub;

public class ArmWinchManipulator extends CommandBase {

  private ArmWinchSub m_armWinch;
  private double speed;
  
  public ArmWinchManipulator(ArmWinchSub p_armWinch, double s) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_armWinch = p_armWinch;
    addRequirements(m_armWinch);
    speed = s;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_armWinch.drive(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_armWinch.hold();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
