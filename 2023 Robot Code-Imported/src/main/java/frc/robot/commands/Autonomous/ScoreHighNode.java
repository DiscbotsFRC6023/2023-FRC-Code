// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.commands.Helpers.HoldTimer;
import frc.robot.subsystems.*;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ScoreHighNode extends SequentialCommandGroup {

  public ScoreHighNode(ClawSub m_claw, ArmSub m_arm, ArmWinchSub m_armWinch) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());

    // GOOD:
    /*
    addCommands(new InstantCommand(() -> m_arm.limited(false), m_arm));
    addCommands(new ArmWinchManipulator(m_armWinch, 0.3).withTimeout(1.0));
    addCommands(new ArmManipulator(m_arm, -0.3).withTimeout(1.2));
    addCommands(new ArmWinchManipulator(m_armWinch, 0.1).withTimeout((0.6)));
    addCommands(new InstantCommand(() -> m_claw.open()));
    addCommands(new HoldTimer(1.0));
    addCommands(new ArmWinchManipulator(m_armWinch, -0.4).withTimeout(2.2));
    addCommands(new InstantCommand(() -> m_arm.limited(true), m_arm));
    addCommands(new ArmManipulator(m_arm, 0.4).withTimeout(2.5));
    */
    
    // Experimental:
    addCommands(new Extend(m_arm, m_armWinch));
    addCommands(new InstantCommand(() -> m_claw.open()));
    addCommands(new HoldTimer(1.0));
    addCommands(new Retract(m_arm, m_armWinch));
    
  }    
}