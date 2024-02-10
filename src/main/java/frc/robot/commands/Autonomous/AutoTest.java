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
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import frc.robot.Constants;

public class AutoTest {
    SwerveDrive drive;
    SequentialCommandGroup commands;
    TrajectoryConfig trajectoryConfig;
    PIDController x;
    PIDController y;

    public AutoTest(SwerveDrive drive, SequentialCommandGroup commands){
        this.drive = drive;
        this.commands = commands;
    }

    public void initialize(){
        //1. Create trajectory settings
        SwerveModuleState[]startingState = new SwerveModuleState[4];
        startingState[0] = new SwerveModuleState(0, new Rotation2d(Constants.kBlueDriveAbsoluteEncoderOffset));
        startingState[1] = new SwerveModuleState(0, new Rotation2d(Constants.kOrangeDriveAbsoluteEncoderOffset));
        startingState[2] = new SwerveModuleState(0, new Rotation2d(Constants.kRedDriveAbsoluteEncoderOffset));
        startingState[3] = new SwerveModuleState(0, new Rotation2d(Constants.kGreenDriveAbsoluteEncoderOffset));
        drive.setModuleStates(startingState);
    }

        // An example command will be run in autonomous
    //return Autos.exampleAuto(m_exampleSubsystem);

    public void execute(){
        TrajectoryConfig trajectoryConfig = new TrajectoryConfig(
                Constants.kPhysicalMaxSpeedMetersPerSecond,
                Constants.kTeleDriveMaxAccelerationUnitsPerSecond)
                        .setKinematics(Constants.kDriveKinematics);


        // 2. Generate trajectory
        Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
                new Pose2d(0, 0, new Rotation2d(0)),
                List.of(
                        new Translation2d(1, 0),
                        new Translation2d(1, -1)),
                new Pose2d(2, -1, Rotation2d.fromDegrees(180)),
                trajectoryConfig);


        // 3. Define PID controllers for tracking trajectory
        x = new PIDController(Constants.kPXController, 0, 0);
        y = new PIDController(Constants.kPYController, 0, 0);
        ProfiledPIDController thetaController = new ProfiledPIDController(
               Constants.kPThetaController, 0, 0, Constants.kThetaControllerConstraints);
        thetaController.enableContinuousInput(-Math.PI, Math.PI);


        // 4. Construct command to follow trajectory
        SwerveControllerCommand controllerCommand = new SwerveControllerCommand(
                trajectory,
                drive::getPose,
                Constants.kDriveKinematics,
                x,
                y,
                thetaController,
                drive::setModuleStates,
                drive);

        commands.addCommands(controllerCommand);
       
// return new SequentialCommandGroup(
//                 new InstantCommand(() -> drive.resetOdometry(trajectory.getInitialPose())),
//                 swerveControllerCommand,
//                 new InstantCommand(() -> drive.stopModules()));

    }

}
