// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.commands.AutoBalance;
import frc.robot.subsystems.*;

public class Balance extends SequentialCommandGroup {

  private DrivetrainSub m_drive;

  public Balance(DrivetrainSub p_drive) {
    // Add your commands in the addCommands() call, e.g.
    m_drive = p_drive;
    addRequirements(m_drive);

    // addCommands(new FooCommand(), new BarCommand());

    // Timed Approach
    /* addCommands(new RunCommand(() -> m_drive.drive(-0.6, 0.0)).withTimeout(Constants.balanceTime));
    addCommands(new InstantCommand(() -> m_drive.stop()));
    addCommands(new InstantCommand(() -> m_drive.setMode("brake"), m_drive));   
    addCommands(new HoldTimer(4.0));
    addCommands(new InstantCommand(() -> m_drive.setMode("coast"))); */


    // IMU Approach
    addCommands(new RunCommand(() -> m_drive.drive(-0.45, 0.0)).withTimeout(2.0));
    addCommands(new AutoBalance(p_drive));
  }
}
