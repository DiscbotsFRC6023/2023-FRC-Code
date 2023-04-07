// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    // CAN IDs:
    public static int LD1 = 1;
    public static int LD2 = 2;
    public static int LD3 = 3;
    public static int RD1 = 4;
    public static int RD2 = 5;
    public static int RD3 = 6;
    public static int armWinchMotor = 7;
    public static int armWinchMotor2 = 8;
    public static int armMotor = 9;
    public static int pcm = 16;


    // Apriltag Constants:
    public static double camHeight = Units.inchesToMeters(8);   // TUNE ME
    public static double camPitch = Units.degreesToRadians(15); // TUNE ME
    public static double gridTagHeight = Units.inchesToMeters(14.25);
    public static double shelfTagHeight = Units.inchesToMeters(23.375);
    public static double gridTagRange = Units.inchesToMeters(14.5); // TUNE ME

    // Arm Stuff:
    public static double midNode = 1.50;
    public static double shelf = 0.85;             // CHANGE ME
    public static double highNode = 2.0;    // CHANGE ME
    

    // Misc:
    public static double balanceThresh = 8.0;
    public static double encoderRotationsPerFoot = 14187;
    public static double driveRampTime = 1.0;
    public static double balanceTime = 2.10 - 0.075 ;
    public static String camName = "limelight";
}
