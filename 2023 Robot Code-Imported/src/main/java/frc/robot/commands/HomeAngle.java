// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;

public class HomeAngle extends CommandBase {
  /** Creates a new HomeArm. */
  ArmWinchSub m_armWinch;
  
  public HomeAngle(ArmWinchSub p_armWinch) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_armWinch = p_armWinch;
    addRequirements(m_armWinch);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_armWinch.drive(-0.3);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_armWinch.drive(-0.10);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_armWinch.getPosition() <= 0.20;
  }
}
