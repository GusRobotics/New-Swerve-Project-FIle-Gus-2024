package frc.robot.commands.Autonomous;

import java.util.List;

import frc.robot.subsystems.SwerveDrive;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
//import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import frc.robot.Constants;

public class FourNoteNoMid extends SequentialCommandGroup{

    public FourNoteNoMid(SwerveDrive s_Swerve) {
        SwerveModuleState[] startStates = new SwerveModuleState[4];
        startStates[0] = new SwerveModuleState(0, new Rotation2d(Constants.kBlueDriveAbsoluteEncoderOffset));
        startStates[1] = new SwerveModuleState(0, new Rotation2d(Constants.kOrangeDriveAbsoluteEncoderOffset));
        startStates[2] = new SwerveModuleState(0, new Rotation2d(Constants.kRedDriveAbsoluteEncoderOffset));
        startStates[3] = new SwerveModuleState(0, new Rotation2d(Constants.kGreenDriveAbsoluteEncoderOffset));
        s_Swerve.setModuleStates(startStates);

        TrajectoryConfig config =
            new TrajectoryConfig(
                    Constants.AutoConstants.kMaxSpeedMetersPerSecond,
                    Constants.AutoConstants.kMaxAccelerationMetersPerSecondSquared)
                .setKinematics(Constants.kDriveKinematics);
    
        // All units in meters.
        Trajectory exampleTrajectory =
            TrajectoryGenerator.generateTrajectory(
                // Start at the origin facing the +X direction
                // new Pose2d(0, 0, new Rotation2d(0)),
                // // Pass through these two interior waypoints, making an 's' curve path
                // List.of(new Translation2d(1, 1), new Translation2d(2, -1)),
                // // End 3 meters straight ahead of where we started, facing forward
                // new Pose2d(3, 0, new Rotation2d(0)),
                // config);

                //rev up shooter and fire
                //drive backwards aka forwards if we call battery front 
                // new Pose2d(0, 0, new Rotation2d(0)),
                // // // Pass through these two interior waypoints, making an 's' curve path
                // List.of(new Translation2d(0, 1)), new Translation2d(0.5, 0.5)),
                // // // End 3 meters straight ahead of where we started, facing forward
                //  new Pose2d(0, 1, new Rotation2d(0)),
                //  config);

                new Pose2d(0, 0, new Rotation2d(0)),
                // Pass through these two interior waypoints, making an 's' curve path
                List.of(new Translation2d(.25, .1), 
                new Translation2d(0, 1), 
                new Translation2d(.25, .1), 
                new Translation2d(1.25, 1), 
                new Translation2d(1.5, .1)),
                // End 
                new Pose2d(2, 1, new Rotation2d(0)),
                config);

    
        var thetaController =
            new ProfiledPIDController(
                Constants.AutoConstants.kPThetaController,
                0,
                0,
                Constants.AutoConstants.kThetaControllerConstraints);
        thetaController.enableContinuousInput(-Math.PI, Math.PI);
    
        SwerveControllerCommand swerveControllerCommand =
            new SwerveControllerCommand(
                exampleTrajectory,
                s_Swerve::getPose,
                Constants.kDriveKinematics,
                new PIDController(Constants.AutoConstants.kPXController, 0, 0),
                new PIDController(Constants.AutoConstants.kPYController, 0, 0),
                thetaController,
                s_Swerve::setModuleStates,
                s_Swerve);
    
        addCommands(
            new InstantCommand(() -> s_Swerve.resetOdometry(exampleTrajectory.getInitialPose())),
            swerveControllerCommand);
        }    
}
//     SwerveDrive drive;
//     SequentialCommandGroup commands;
//     TrajectoryConfig trajectoryConfig;
//     PIDController x;
//     PIDController y;

//     private final SwerveDrive swerveDrive;

//     public AutoTest(SwerveDrive swerveDrive){
//         this.swerveDrive = swerveDrive;
//         TrajectoryConfig trajectoryConfig = new TrajectoryConfig(
//                 Constants.kPhysicalMaxSpeedMetersPerSecond,
//                 Constants.kTeleDriveMaxAccelerationUnitsPerSecond)
//                         .setKinematics(Constants.kDriveKinematics);

//         x = new PIDController(Constants.kPXController, 0, 0);
//         y = new PIDController(Constants.kPYController, 0, 0);
        
        
//     }

//     // public void initialize(){
//         //1. Create trajectory settings
//         SwerveModuleState[]startingState = new SwerveModuleState[4];
//         startingState[0] = new SwerveModuleState(0, new Rotation2d(Constants.kBlueDriveAbsoluteEncoderOffset));
//         startingState[1] = new SwerveModuleState(0, new Rotation2d(Constants.kOrangeDriveAbsoluteEncoderOffset));
//         startingState[2] = new SwerveModuleState(0, new Rotation2d(Constants.kRedDriveAbsoluteEncoderOffset));
//         startingState[3] = new SwerveModuleState(0, new Rotation2d(Constants.kGreenDriveAbsoluteEncoderOffset));
//         drive.setModuleStates(startingState);
//     }

//         // An example command will be run in autonomous
//     //return Autos.exampleAuto(m_exampleSubsystem);

//     public void execute(){


//         // 2. Generate trajectory
//         Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
//                 new Pose2d(0, 0, new Rotation2d(0)),
//                 List.of(
//                         new Translation2d(1, 0),
//                         new Translation2d(1, -1)),
//                 new Pose2d(2, -1, Rotation2d.fromDegrees(180)),
//                 trajectoryConfig);

//         // 3. Define PID controllers for tracking trajectory
//         ProfiledPIDController thetaController = new ProfiledPIDController(
//                Constants.kPThetaController, 0, 0, Constants.kThetaControllerConstraints);
//         thetaController.enableContinuousInput(-Math.PI, Math.PI);


//         // 4. Construct command to follow trajectory
//         SwerveControllerCommand controllerCommand = new SwerveControllerCommand(
//                 trajectory,
//                 drive::getPose,
//                 Constants.kDriveKinematics,
//                 x,
//                 y,
//                 thetaController,
//                 drive::setModuleStates,
//                 drive);



// // return new SequentialCommandGroup(
// //                 new InstantCommand(() -> drive.resetOdometry(trajectory.getInitialPose())),
// //                 swerveControllerCommand,
// //                 new InstantCommand(() -> drive.stopModules()));

//     }

// }
