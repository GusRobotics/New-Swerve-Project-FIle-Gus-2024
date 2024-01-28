package frc.robot.commands;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.SwerveModule;

public class ModuleTest extends Command{
    // SwerveModule blue = new SwerveModule(
    // Constants.blueDrive,
    // Constants.blueSteer,
    // Constants.kBlueDriveEncoderReversed,
    // Constants.kBlueTurningEncoderReversed,
    // Constants.kBlueDriveAbsoluteEncoderPort,
    // Constants.kBlueDriveAbsoluteEncoderOffset,
    // Constants.kBlueDriveAbsoluteEncoderReversed,
    // Constants.blueDriveInvert,
    // Constants.blueTurnInvert);

    // SwerveModule orange = new SwerveModule(
    // Constants.orangeDrive,
    // Constants.orangeSteer,
    // Constants.kOrangeDriveEncoderReversed,
    // Constants.kOrangeTurningEncoderReversed,
    // Constants.kOrangeDriveAbsoluteEncoderPort,
    // Constants.kOrangeDriveAbsoluteEncoderOffset,
    // Constants.kOrangeDriveAbsoluteEncoderReversed,
    // Constants.orangeDriveInvert,
    // Constants.orangeTurnInvert);

    // SwerveModule green = new SwerveModule(
    // Constants.greenDrive,
    // Constants.greenSteer,
    // Constants.kGreenTurningEncoderReversed,
    // Constants.kGreenTurningEncoderReversed,
    // Constants.kGreenDriveAbsoluteEncoderPort,
    // Constants.kGreenDriveAbsoluteEncoderOffset,
    // Constants.kGreenDriveAbsoluteEncoderReversed,
    // Constants.greenDriveInvert,
    // Constants.greenTurnInvert);

    // SwerveModule red = new SwerveModule(
    // Constants.redDrive,
    // Constants.redSteer,
    // Constants.kRedDriveEncoderReversed,
    // Constants.kRedTurningEncoderReversed,
    // Constants.kRedDriveAbsoluteEncoderPort,
    // Constants.kRedDriveAbsoluteEncoderOffset,
    // Constants.kRedDriveAbsoluteEncoderReversed,
    // Constants.redDriveInvert,
    // Constants.redTurnInvert);

    SwerveModuleState desiredState = new SwerveModuleState();

    @Override
    public void initialize() {
        SmartDashboard.putNumber("move", 0);
        SmartDashboard.putNumber("rotation", 0);
    }

    @Override
    public void execute() {
        //SmartDashboard.putNumber("encoder values", red.getAbsoluteEncoderRad());
        double desiredMove = SmartDashboard.getNumber("move", 0);
        double desiredRot = SmartDashboard.getNumber("rotation", 0);
        desiredState.speedMetersPerSecond = desiredMove;
        desiredState.angle = Rotation2d.fromRadians(desiredRot);
        //red.setDesiredState(desiredState);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
