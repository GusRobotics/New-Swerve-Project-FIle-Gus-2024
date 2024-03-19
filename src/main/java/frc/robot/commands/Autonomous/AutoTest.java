package frc.robot.commands.Autonomous;

import java.util.List;

import frc.robot.subsystems.Intake;
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
import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
//import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.HighShootCmd;
import frc.robot.commands.IntakeBaseCmd;
import frc.robot.subsystems.SwerveDrive;


public class AutoTest extends SequentialCommandGroup{
    private Timer autoTimer = new Timer();
        IntakeBaseCmd intakeBaseCmd = new IntakeBaseCmd(RobotContainer.intake, true);
        HighShootCmd highShootCmd = new HighShootCmd(RobotContainer.shooter, true, false);

    public AutoTest(SwerveDrive s_Swerve) {
        autoTimer.restart();
        if (autoTimer.get() < 8)
        {
            highShootCmd.schedule();
        }
        
        if (autoTimer.get() > 5 && autoTimer.get() < 8)
        {
            intakeBaseCmd.schedule();
        }
    }
}