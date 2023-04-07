// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.*;

public class Shelf extends CommandBase {
  /** Creates a new MidArmPos. */
  ArmSub m_arm;
  ArmWinchSub m_armWinch;

  public Shelf(ArmSub p_arm, ArmWinchSub p_armWinch) {

    m_arm = p_arm;
    m_armWinch = p_armWinch;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_arm, m_armWinch);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_armWinch.drive(0.2);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_armWinch.getPosition() >= Constants.shelf;
  }
}
