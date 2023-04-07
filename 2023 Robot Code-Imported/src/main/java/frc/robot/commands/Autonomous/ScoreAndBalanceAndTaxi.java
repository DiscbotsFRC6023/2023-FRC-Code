// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.AutoBalance;
import frc.robot.commands.Helpers.Rotate;
import frc.robot.subsystems.*;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ScoreAndBalanceAndTaxi extends SequentialCommandGroup {
  /** Creates a new ScoreAndBalanceAndTaxi. */
  public ScoreAndBalanceAndTaxi(DrivetrainSub m_drive, ClawSub m_claw, ArmSub m_arm, ArmWinchSub m_armWinch) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new ScoreHighNode(m_claw, m_arm, m_armWinch));
    addCommands(new RunCommand(() -> m_drive.drive(-0.5, 0), m_drive).withTimeout(3.0));
    addCommands(new Rotate(179, m_drive));
    addCommands(new RunCommand(() -> m_drive.drive(0.45, 0), m_drive).withTimeout(1.5));
    addCommands(new AutoBalance(m_drive));
  }
}
