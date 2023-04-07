// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.*;
import frc.robot.commands.Autonomous.*;
import frc.robot.commands.Helpers.Rotate;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  // Subsystems
  private DrivetrainSub m_drive = new DrivetrainSub();
  private ClawSub m_claw = new ClawSub();
  private ArmSub m_arm = new ArmSub();
  private ArmWinchSub m_armWinch = new ArmWinchSub();

  // Controllers
  // private XboxController xbox = new XboxController(0);
  // private XboxController xbox2 = new XboxController(1);
  private XboxController fightStick = new XboxController(2);

  // Optional Controllers:
  private PS4Controller driver = new PS4Controller(0);
  private PS4Controller aux = new PS4Controller(1);

  //Misc
  SendableChooser<Command> m_autoSelector = new SendableChooser<>();
  //externalWhile m_externalThread = new externalWhile(m_arm, m_armWinch);
  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    DriverStation.silenceJoystickConnectionWarning(true);

    // Auto Chooser setup
    m_autoSelector.setDefaultOption("Taxi", new RunCommand(() -> m_drive.drive(0.2, 0)).withTimeout(3.0));
    m_autoSelector.addOption("Balance", new Balance(m_drive));
    m_autoSelector.addOption("Push", new Push(m_drive));
    m_autoSelector.addOption("Score High Node", new ScoreHighNode(m_claw, m_arm, m_armWinch));
    m_autoSelector.addOption("Retract TESTER", new Retract(m_arm, m_armWinch));
    m_autoSelector.addOption("Extend TESTER", new Extend(m_arm, m_armWinch));
    m_autoSelector.addOption("Rotate Test", new Rotate(180, m_drive));

    m_autoSelector.addOption("Score + Balance", new ScoreAndBalance(m_drive, m_claw, m_arm, m_armWinch));
    m_autoSelector.addOption("Score + Taxi", new ScoreAndTaxi(m_drive, m_claw, m_arm, m_armWinch));
    m_autoSelector.addOption("Score + Taxi + Balance", new ScoreAndBalanceAndTaxi(m_drive, m_claw, m_arm, m_armWinch));
    m_autoSelector.addOption("Score + Push", new ScoreAndPush(m_drive, m_claw, m_arm, m_armWinch));

    // USB Camera Capture
    CameraServer.startAutomaticCapture();

    // Configure the button bindings
    configureButtonBindings();

    // Add Autonomous selector to Dashboard
    SmartDashboard.putData(m_autoSelector);
    
    // Drivetrain Default Command
     m_drive.setDefaultCommand(new RunCommand(() -> m_drive.drive(Math.pow(-driver.getLeftY(), 3), Math.pow(-driver.getRightX(), 3) * 0.5), m_drive));   // Arcade Drive
  
     // Arm Winch Default Command
     m_armWinch.setDefaultCommand(new RunCommand(() -> m_armWinch.drive(-0.10), m_armWinch));
     //m_externalThread.start();
    }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    
    /*
     * Auto Balancing Button
     */
    JoystickButton driveMode = new JoystickButton(driver, XboxController.Button.kLeftBumper.value);
    driveMode.whileFalse(new InstantCommand(() -> m_drive.setMode("coast"), m_drive));

    /*
     * Claw Button
     */
    JoystickButton clawToggle = new JoystickButton(aux, PS4Controller.Button.kL1.value);
    clawToggle.whileTrue(new InstantCommand(() -> m_claw.toggle(), m_claw));

    JoystickButton clawToggle2 = new JoystickButton(fightStick, XboxController.Button.kLeftBumper.value); // FIGHTSTICK
    clawToggle2.whileTrue(new InstantCommand(() -> m_claw.toggle(), m_claw));

    /*
     * Arm Buttons
     */
    JoystickButton armUp = new JoystickButton(aux, PS4Controller.Button.kTriangle.value);
    armUp.whileTrue(new ArmManipulator(m_arm, -0.4));

    JoystickButton armDown = new JoystickButton(aux, PS4Controller.Button.kCross.value);
    armDown.whileTrue(new ArmManipulator(m_arm, 0.4));

    JoystickButton armForward = new JoystickButton(aux, PS4Controller.Button.kCircle.value);
    armForward.whileTrue(new ArmWinchManipulator(m_armWinch, 0.4));

    JoystickButton armBackwards = new JoystickButton(aux, PS4Controller.Button.kSquare.value);
    armBackwards.whileTrue(new ArmWinchManipulator(m_armWinch, -0.6));
    
    JoystickButton shelf = new JoystickButton(aux, PS4Controller.Button.kR1.value);
    shelf.whileTrue(new Shelf(m_arm, m_armWinch));

    JoystickButton limitsOff = new JoystickButton(aux, PS4Controller.Button.kL3.value);
    limitsOff.whileTrue(new InstantCommand(() -> m_arm.limited(false), m_arm));

    JoystickButton limitsOn = new JoystickButton(aux, PS4Controller.Button.kR3.value);
    limitsOn.whileTrue(new InstantCommand(() -> m_arm.limited(true), m_arm));

  /*
   * Arm Buttons (FIGHTSTICK)
   */
    JoystickButton midNode = new JoystickButton(fightStick, XboxController.Button.kA.value);
    midNode.whileTrue(new MidArmPos(m_arm, m_armWinch));

    JoystickButton highNode = new JoystickButton(fightStick, XboxController.Button.kX.value);
    highNode.whileTrue(new Extend(m_arm, m_armWinch));

    

    JoystickButton home = new JoystickButton(fightStick, XboxController.Button.kB.value);
    home.whileTrue(new HomeAngle(m_armWinch).alongWith(new HomeExtension(m_arm)));

    JoystickButton limitsOff2 = new JoystickButton(fightStick, XboxController.Button.kLeftStick.value);
    limitsOff2.whileTrue(new InstantCommand(() -> m_arm.limited(false), m_arm));

    JoystickButton limitsOn2 = new JoystickButton(fightStick, XboxController.Button.kRightStick.value);
    limitsOn2.whileTrue(new InstantCommand(() -> m_arm.limited(true), m_arm));
  }
  
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return m_autoSelector.getSelected();
  }
}
/* 
class externalWhile extends Thread{
  private ArmSub m_arm;
  private ArmWinchSub m_armWinch;
  private int def;

  public externalWhile(ArmSub p_arm, ArmWinchSub p_armWinch){
    m_arm = p_arm;
    m_armWinch = p_armWinch;
    def = 0;
  }

  public void run(){
    while(def != -1){
      System.out.println("DEF integer: " + def);
      if(def == 0){
        // default state
        // new HomeAngle(m_armWinch);
        // new HomeExtension(m_arm);
        //System.out.println("ARM HOME POSITION");
      } else if(def == 1){
        // middle node
        //System.out.println("ARM MIDDLE POSITION");
      } else if(def == 2){
        // shelf 
        //System.out.println("ARM SHELF POSITION");
      } else if(def == 3){
        // high node
        //System.out.println("ARM HIGH POSITION");
      }
    }
  }

  public void setMode(int m){
    def = m;
    System.out.println("M integer: " + m);
  }
} */