package frc.robot;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
//import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.math.util.Units;
//import edu.wpi.first.wpilibj2.command.TrapezoidProfileCommand;
import edu.wpi.first.wpilibj.AnalogInput;

public class Constants {
        public static final double kWheelDiameterMeters = Units.inchesToMeters(4);
        public static final double kDriveMotorGearRatio = 1 / 5.8462;
        public static final double KTurningMotorGearRatio = 1 / 18.0;
        public static final double kDriveEncoderRot2Meter = kDriveMotorGearRatio * Math.PI
                        * kWheelDiameterMeters;
        public static final double kTurningEncoderRot2Rad = KTurningMotorGearRatio * 2 * Math.PI;
        // private static int kDriveEncoderRot2Meter;
        public static final double kDriveEncoderRPM2MeterPerSec = kDriveEncoderRot2Meter / 60;
        public static final double kTurningEncoderRPM2RadPerSec = kTurningEncoderRot2Rad / 60;
        public static final double kPTurning = 0.3;

        public static final double kTrackWidth = Units.inchesToMeters(21);
        // Distance between right and left wheels
        public static final double kWheelBase = Units.inchesToMeters(25.5);
        // Distance between front and back wheels
        public static final SwerveDriveKinematics kDriveKinematics = new SwerveDriveKinematics(
                        new Translation2d(kWheelBase / 2, -kTrackWidth / 2),
                        new Translation2d(kWheelBase / 2, kTrackWidth / 2),
                        new Translation2d(-kWheelBase / 2, -kTrackWidth / 2),
                        new Translation2d(-kWheelBase / 2, kTrackWidth / 2));

                    //front right- green
                    //front left - blue
                    //back left - orange
                    //back right - red

        public static final double kPXController = 1.5;
        public static final double kPYController = 1.5;
        public static final double kPThetaController = 3;

        //we tried making red and green true
        //originally all false
        public static final boolean kBlueTurningEncoderReversed = false;
        public static final boolean kGreenTurningEncoderReversed = false;
        public static final boolean kOrangeTurningEncoderReversed = false;
        public static final boolean kRedTurningEncoderReversed = false;

        //green and red were originally false
        public static final boolean kBlueDriveEncoderReversed = true;
        public static final boolean kGreenDriveEncoderReversed = false;
        public static final boolean kOrangeDriveEncoderReversed = true;
        public static final boolean kRedDriveEncoderReversed = false;

        //Drive ID Constants 
        //Blue pod ids
        public static final int blueDrive = 9;
        public static final int blueSteer = 7;
        public static final int kBlueDriveAbsoluteEncoderPort = 8;
        //green pod ids
        public static final int greenDrive = 12;
        public static final int greenSteer = 10;
        public static final int kGreenDriveAbsoluteEncoderPort = 11;
        //orange drive ids
        public static final int orangeDrive = 5;
        public static final int orangeSteer = 3;
        public static final int kOrangeDriveAbsoluteEncoderPort = 4;
        //red drive ids
        public static final int redDrive = 21;
        public static final int redSteer = 19;
        public static final int kRedDriveAbsoluteEncoderPort = 20;
        //intake ids
         public static final int topIntakeMotor = 15;
         public static final int bottomIntakeMotor = 16;
        //shooter ids
        public static final int topShooterMotor = 17;
        public static final int bottomShooterMotor = 18;
        //pigeon id
        public static final int kPigeonPort = 23;
        //pneumatic hub id
        public static final int pneumaticHubId = 22;
        public static final int pneumaticForwardChannel = 9;
        public static final int pneumaticReverseChannel = 8;
        //time of flight id
        public static final int timeOfFlightId = 14;
        //climber ids
        public static final int leftClimb = 6;
        public static final int rightClimb = 13;

        public static final int ledChannel = 0;
        public static final double pinkLights = 0.57;
        public static final double blueLights = 0.83;
        public static final double yellowLights = 0.69;

        //light ids 
        public static final int lightId = 2; //CONFIRM ID PICKED A RANDOM NUMBER 
        
        //ADD THE ACTUAL VALUES
        public static final double topIntakeSpeed = 0.3;
        public static final double bottomIntakeSpeed = 0.3;

        public static final double topShooterSpeed = 0.6;
        public static final double bottomShooterSpeed = 0.6;

        public static final double negIntakeSpeed = -0.3;

        public static final boolean kBlueDriveAbsoluteEncoderReversed = true;
        public static final boolean kGreenDriveAbsoluteEncoderReversed = true;
        public static final boolean kOrangeDriveAbsoluteEncoderReversed = true;
        public static final boolean kRedDriveAbsoluteEncoderReversed = true;

        public static final double kBlueDriveAbsoluteEncoderOffset = -0.178223;
        public static final double kGreenDriveAbsoluteEncoderOffset = 0.416260;
        public static final double kOrangeDriveAbsoluteEncoderOffset = 0.376465;
        public static final double kRedDriveAbsoluteEncoderOffset = -0.188965;

        public static final double kPhysicalMaxSpeedMetersPerSecond = 7;
        //below originally 2 * 2 * Math.PI
        public static final double kPhysicalMaxAngularSpeedRadiansPerSecond = 5 * 2 * Math.PI;

        public static final int driveMotorCurrentLimit = 50;

        public static final double kTeleDriveMaxSpeedMetersPerSecond = kPhysicalMaxSpeedMetersPerSecond / 3;
        public static final double kTeleDriveMaxAngularSpeedRadiansPerSecond = //
                        kPhysicalMaxAngularSpeedRadiansPerSecond / 3;
        public static final double kTeleDriveMaxAccelerationUnitsPerSecond = 3;
        public static final double kTeleDriveMaxAngularAccelerationUnitsPerSecond = 3;

        public static final int kDriverControllerPort = 0;


        public static final int kDriverYAxis = 1;
        public static final int kDriverXAxis = 0;
        public static final int kDriverRotAxis = 4;
        public static final int kDriverFieldOrientedButtonIdx = 1;

        public static final double kDeadband = 0.05;

        public static final double OIConstants = 0.05;
        public static final TrapezoidProfile.Constraints kThetaControllerConstraints = new TrapezoidProfile.Constraints(
                        kPhysicalMaxSpeedMetersPerSecond, kPhysicalMaxAngularSpeedRadiansPerSecond);
        TrapezoidProfile.State previousProfiledReference = new TrapezoidProfile.State();

        public static final class AutoConstants {
                public static final double kMaxSpeedMetersPerSecond = 5;
                public static final double kMaxAccelerationMetersPerSecondSquared = 5;
                public static final double kMaxAngularSpeedRadiansPerSecond = Math.PI;
                public static final double kMaxAngularSpeedRadiansPerSecondSquared = Math.PI;
            
                public static final double kPXController = 1;
                public static final double kPYController = 1;
                public static final double kPThetaController = 1;
            
                // Constraint for the motion profilied robot angle controller
                public static final TrapezoidProfile.Constraints kThetaControllerConstraints =
                    new TrapezoidProfile.Constraints(
                        kMaxAngularSpeedRadiansPerSecond, kMaxAngularSpeedRadiansPerSecondSquared);
              }
            }