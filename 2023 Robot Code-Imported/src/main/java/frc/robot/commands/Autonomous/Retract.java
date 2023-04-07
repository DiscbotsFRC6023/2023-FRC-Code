// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autonomous;
import frc.robot.subsystems.*;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Retract extends ParallelCommandGroup {
  /** Creates a new Retract. */
  public Retract(ArmSub m_arm, ArmWinchSub m_armWinch) {
    addCommands(new ArmWinchManipulator(m_armWinch, -0.3).withTimeout(1.8));
    addCommands(new ArmManipulator(m_arm, 0.2).withTimeout(2.0));
  }}