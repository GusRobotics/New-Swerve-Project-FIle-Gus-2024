package frc.robot.commands.Autonomous;

import frc.robot.subsystems.SwerveDrive;
import java.util.List;

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
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
//import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import frc.robot.Constants;

public class fourPieceCenterMiddle extends SequentialCommandGroup{
    public fourPieceCenterMiddle(SwerveDrive s_Swerve) {
        s_Swerve.zeroHeading();
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
                new Pose2d(0, 0, new Rotation2d(0)),
                // Pass through these two interior waypoints
                List.of(new Translation2d(0, 1.1), new Translation2d(0.8, 1.1)),
                // Ending
                new Pose2d(0.8, 3, new Rotation2d(0)),
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
