package frc.robot.subsystems;
//got rid of that pigeon import bc  for some reason it wasn't getting the values of old class
//import com.ctre.phoenix6.hardware.Pigeon2;
import com.ctre.phoenix6.hardware.Pigeon2;
//import com.ctre.phoenix6.hardware.CANcoder;
//import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
//import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class SwerveDrive extends SubsystemBase {

    private final SwerveModule blue;
    private final SwerveModule red;
    private final SwerveModule green;
    private final SwerveModule orange;
    private SlewRateLimiter xLimiter = new SlewRateLimiter(0.5);
    private SlewRateLimiter yLimiter = new SlewRateLimiter(0.5);
    private SlewRateLimiter turningLimiter = new SlewRateLimiter(0.5);
    private final Pigeon2 pigeon = new Pigeon2(Constants.kPigeonPort);
    private static SwerveModulePosition[] position = new SwerveModulePosition[4]; 
    private final SwerveDriveOdometry odometer = new SwerveDriveOdometry(Constants.kDriveKinematics, new Rotation2d(0), positioning(position));
    // toggling between SwerveModelState and SwerveModelPosition, attempting to
    // debug odometer
    SwerveModuleState driveStates[] = new SwerveModuleState[4];

    public SwerveDrive () {
        blue = new SwerveModule(
                Constants.blueDrive,
                Constants.blueSteer,
                Constants.kBlueDriveEncoderReversed,
                Constants.kBlueTurningEncoderReversed,
                Constants.kBlueDriveAbsoluteEncoderPort,
                Constants.kBlueDriveAbsoluteEncoderOffset,
                Constants.kBlueDriveAbsoluteEncoderReversed);

        orange = new SwerveModule(
                Constants.orangeDrive,
                Constants.orangeSteer,
                Constants.kOrangeDriveEncoderReversed,
                Constants.kOrangeTurningEncoderReversed,
                Constants.kOrangeDriveAbsoluteEncoderPort,
                Constants.kOrangeDriveAbsoluteEncoderOffset,
                Constants.kOrangeDriveAbsoluteEncoderReversed);

        green = new SwerveModule(
                Constants.greenDrive,
                Constants.greenSteer,
                Constants.kGreenDriveEncoderReversed,
                Constants.kGreenTurningEncoderReversed,
                Constants.kGreenDriveAbsoluteEncoderPort,
                Constants.kGreenDriveAbsoluteEncoderOffset,
                Constants.kGreenDriveAbsoluteEncoderReversed);

        red = new SwerveModule(
                Constants.redDrive,
                Constants.redSteer,
                Constants.kRedDriveEncoderReversed,
                Constants.kRedTurningEncoderReversed,
                Constants.kRedDriveAbsoluteEncoderPort,
                Constants.kRedDriveAbsoluteEncoderOffset,
                Constants.kRedDriveAbsoluteEncoderReversed);

        // driveStates[0] = blue.getState();
        // driveStates[1] = orange.getState();
        // driveStates[2] = red.getState();
        // driveStates[3] = green.getState();

    }

    public SwerveModulePosition[] positioning(SwerveModulePosition[] positions) {
        positions[0] = new SwerveModulePosition(0, new Rotation2d(Constants.kGreenDriveAbsoluteEncoderOffset));
        positions[1] = new SwerveModulePosition(0, new Rotation2d(Constants.kBlueDriveAbsoluteEncoderOffset));
        positions[2] = new SwerveModulePosition(0, new Rotation2d(Constants.kOrangeDriveAbsoluteEncoderOffset));
        positions[3] = new SwerveModulePosition(0, new Rotation2d(Constants.kRedDriveAbsoluteEncoderOffset));
        return positions;
    }

    public void zeroHeading() {
        pigeon.reset();
    }

    public double getHeading() {
        return Math.IEEEremainder(pigeon.getAngle(), 360);
    }

    public Rotation2d getRotation2d() {
        return Rotation2d.fromDegrees(getHeading());
    }

    public double getBlueTurnSetPoint(){
        return blue.getTurnSetPoint();
    }

    public double getRedPosition(){
        return red.getTurningPosition();
    }
    public double getOrangePosition(){
        return orange.getTurningPosition();
    }
    public double getGreenPosition(){
        return green.getTurningPosition();
    }
    public double getBluePosition(){
        return blue.getTurningPosition();
    }

    // public double getTurningVelocity() {
    //     return pigeon.getAngularVelocityXDevice().getValue();
    // }
    public Pose2d getPose() {
        return odometer.getPoseMeters();
    }

     public void resetOdometry(Pose2d pose) {
        odometer.resetPosition(getRotation2d(), position, pose);
    }

    @Override
    public void periodic() {
        // note odometry settings commented out bc of swervedrivestate and
        // swervedriveposition
        // odometer.update(getRotation2d(), driveStates);
        SmartDashboard.putNumber("Robot Heading", getHeading());
        // SmartDashboard.putString("Robot Location",
        // getPose().getTranslation().toString());
    }

    public void stopModules() {
        blue.stop();
        orange.stop();
        green.stop();
        red.stop();
    }

    public void setModuleStates(SwerveModuleState[] desiredStates) {
        SwerveDriveKinematics.desaturateWheelSpeeds(desiredStates, Constants.kPhysicalMaxSpeedMetersPerSecond);
        green.setDesiredState(desiredStates[0]);
        blue.setDesiredState(desiredStates[1]);
        orange.setDesiredState(desiredStates[2]);
        red.setDesiredState(desiredStates[3]);
    }

    public Rotation2d getRotation2D() {
        //gets pigeon value for rotation in degrees, converts to radians
        double numDegrees = pigeon.getYaw().getValue();
        double radians = numDegrees * (Math.PI/180);
        return new Rotation2d(radians);
    }

    public void teleopControlSwerve(double leftX, double leftY, double rightX) {
        // value originally Math.atan(leftY/leftX)/(Math.PI *2), got rid of dividing by
        // pi
        Rotation2d desRot = new Rotation2d(Math.atan2(leftY, leftX));
        double velocity = leftY;

        // this works!!! time to scale the vectors!!
        // check if lefty is not 1 or -1 (full magnitudes)
        // SlewRateLimiter xLimiter = new SlewRateLimiter(0.5);
        // SlewRateLimiter yLimiter = new SlewRateLimiter(0.5);
        if (leftY < 0.05 && leftY > -0.05) {
            if (leftX >= 0.05) {
                desRot = new Rotation2d(90);
                leftX = xLimiter.calculate(leftX) * Constants.kTeleDriveMaxSpeedMetersPerSecond;
            } else if (leftX <= -0.05) {
                desRot = new Rotation2d(-90);
            }
            velocity = leftX;
        }
        // try mult vs division
        leftY = yLimiter.calculate(leftX) * Constants.kTeleDriveMaxSpeedMetersPerSecond;
        velocity = leftY;

        SwerveModuleState desiredState = new SwerveModuleState(velocity, desRot);

        // leave this alone this is the only thing that works
        driveStates[0] = desiredState;
        driveStates[1] = desiredState;
        driveStates[2] = desiredState;
        driveStates[3] = desiredState;

        green.setDesiredState(desiredState);
        blue.setDesiredState(desiredState);
        orange.setDesiredState(desiredState);
        red.setDesiredState(desiredState);
    }

    public void execute(double leftX, double leftY, double rightX) {
        // 1. Get real-time joystick inputs
        double xSpeed = leftX*5;
        double ySpeed = leftY*5;
        double turningSpeed = rightX;

        // 2. Apply deadband
        xSpeed = Math.abs(xSpeed) > Constants.OIConstants ? xSpeed : 0.0;
        ySpeed = Math.abs(ySpeed) > Constants.OIConstants ? ySpeed : 0.0;
        turningSpeed = Math.abs(turningSpeed) > Constants.OIConstants ? turningSpeed : 0.0;

        // 3. Make the driving smoother
        xSpeed = xLimiter.calculate(xSpeed) * Constants.kTeleDriveMaxSpeedMetersPerSecond;
        ySpeed = yLimiter.calculate(ySpeed) * Constants.kTeleDriveMaxSpeedMetersPerSecond;
        turningSpeed = turningLimiter.calculate(turningSpeed)
                * Constants.kTeleDriveMaxAngularSpeedRadiansPerSecond;

        // 4. Construct desired chassis speeds
        ChassisSpeeds chassisSpeeds;

        // ensures field orientation
        chassisSpeeds = ChassisSpeeds.fromFieldRelativeSpeeds(
                (xSpeed * -1), ySpeed, turningSpeed, getRotation2D());

        // 5. Convert chassis speeds to individual module states
        SwerveModuleState[] moduleStates = Constants.kDriveKinematics.toSwerveModuleStates(chassisSpeeds);

        // 6. Output each module states to wheels
        this.setModuleStates(moduleStates);
    }

}