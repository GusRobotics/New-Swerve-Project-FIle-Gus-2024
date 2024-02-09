package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.SwerveDrive;

public class SwerveJoystickCmd extends Command {

    private final SwerveDrive swerveSubsystem;
    private final Supplier<Double> xSpdFunction, ySpdFunction, turningSpdFunction;
    //private final Supplier<Boolean> fieldOrientedFunction;
    private final SlewRateLimiter xLimiter, yLimiter, turningLimiter;

    Supplier<Boolean> resetGyro;

    public SwerveJoystickCmd(SwerveDrive swerveSubsystem,
            Supplier<Double> xSpdFunction, Supplier<Double> ySpdFunction, Supplier<Double> turningSpdFunction, 
            Supplier<Boolean> resetGyro) {
        this.swerveSubsystem = swerveSubsystem;
        this.xSpdFunction = xSpdFunction;
        this.ySpdFunction = ySpdFunction;
        this.turningSpdFunction = turningSpdFunction;
        this.xLimiter = new SlewRateLimiter(Constants.kTeleDriveMaxAccelerationUnitsPerSecond);
        this.yLimiter = new SlewRateLimiter(Constants.kTeleDriveMaxAccelerationUnitsPerSecond);
        this.turningLimiter = new SlewRateLimiter(Constants.kTeleDriveMaxAngularAccelerationUnitsPerSecond);
        addRequirements(swerveSubsystem);
        this.resetGyro = resetGyro;
    }

    @Override
    public void initialize() {
        SwerveModuleState[] startStates = new SwerveModuleState[4];
        startStates[0] = new SwerveModuleState(0, new Rotation2d(Constants.kBlueDriveAbsoluteEncoderOffset));
        startStates[1] = new SwerveModuleState(0, new Rotation2d(Constants.kOrangeDriveAbsoluteEncoderOffset));
        startStates[2] = new SwerveModuleState(0, new Rotation2d(Constants.kRedDriveAbsoluteEncoderOffset));
        startStates[3] = new SwerveModuleState(0, new Rotation2d(Constants.kGreenDriveAbsoluteEncoderOffset));
        swerveSubsystem.setModuleStates(startStates);
    }

    @Override
    public void execute() {
        if(resetGyro.get()){
            swerveSubsystem.zeroHeading();
        }
        // 1. Get real-time joystick inputs
        double xSpeed = (xSpdFunction.get()*2);
        double ySpeed = (ySpdFunction.get()*2);
        double turningSpeed = (turningSpdFunction.get()* 0.6);
        
        // 2. Apply deadband
        xSpeed = Math.abs(xSpeed) > Constants.OIConstants ? 
            Math.signum(xSpeed) * (Math.abs(xSpeed) - Constants.OIConstants) / (1 - Constants.OIConstants) : 0.0;
        ySpeed = Math.abs(ySpeed) > Constants.OIConstants ? 
            Math.signum(ySpeed) * (Math.abs(ySpeed) - Constants.OIConstants) / (1 - Constants.OIConstants) : 0.0;
        turningSpeed = Math.abs(turningSpeed) > Constants.OIConstants ? 
            Math.signum(turningSpeed) * (Math.abs(turningSpeed) - Constants.OIConstants) / (1 - Constants.OIConstants) : 0.0;

        // 3. Make the driving smoother
        xSpeed = xLimiter.calculate(xSpeed) * Constants.kTeleDriveMaxSpeedMetersPerSecond;
        ySpeed = yLimiter.calculate(ySpeed) * Constants.kTeleDriveMaxSpeedMetersPerSecond;
        turningSpeed = turningLimiter.calculate(turningSpeed)
                * Constants.kTeleDriveMaxAngularSpeedRadiansPerSecond;

        // 4. Construct desired chassis speeds
        ChassisSpeeds chassisSpeeds;

        //ensures field orientation 
            chassisSpeeds = ChassisSpeeds.fromFieldRelativeSpeeds(
                    xSpeed, ySpeed, turningSpeed, swerveSubsystem.getRotation2d());

        // 5. Convert chassis speeds to individual module states
        SwerveModuleState[] moduleStates = Constants.kDriveKinematics.toSwerveModuleStates(chassisSpeeds);

        // 6. Output each module states to wheels
        swerveSubsystem.setModuleStates(moduleStates);

        SmartDashboard.putNumber("wanted value", swerveSubsystem.getBlueTurnSetPoint());

        SmartDashboard.putNumber("blue desired state", swerveSubsystem.getBluePosition());
        SmartDashboard.putNumber("red desired state", swerveSubsystem.getRedPosition());
        SmartDashboard.putNumber("green desired state", swerveSubsystem.getGreenPosition());
        SmartDashboard.putNumber("orange desired state", swerveSubsystem.getOrangePosition());
    }

    @Override
    public void end(boolean interrupted) {
        swerveSubsystem.stopModules();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}