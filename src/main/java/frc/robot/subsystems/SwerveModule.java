package frc.robot.subsystems;

import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.signals.AbsoluteSensorRangeValue;
import com.ctre.phoenix6.signals.SensorDirectionValue;
//import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
//import edu.wpi.first.math.kinematics.SwerveModulePosition;
//import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import frc.robot.Constants;

//import com.ctre.phoenix6.hardware.Pigeon2;

public class SwerveModule {

    private final CANSparkMax driveMotor;
    private final CANSparkMax turningMotor;

    private final PIDController turningPidController;

    private final CANcoder absoluteEncoder;
    private final CANcoderConfiguration config = new CANcoderConfiguration(); 

    // private final boolean absoluteEncoderReversed;
    // private final double absoluteEncoderOffsetRad;
    //Pigeon2 pigeon = new Pigeon2(Constants.kPigeonPort, "Canbus");

    public SwerveModule(int driveMotorId, int turningMotorId, boolean driveMotorReversed, boolean turningMotorReversed,
            int absoluteEncoderId, double absoluteEncoderOffset, boolean absoluteEncoderReversed) {

        // swtiched AbsoluteEncoder to type CANcoder --> broke the getVoltage() and
        // getChannel() methods
        absoluteEncoder = new CANcoder(absoluteEncoderId);
        config.MagnetSensor.MagnetOffset = absoluteEncoderOffset;
        config.MagnetSensor.AbsoluteSensorRange = AbsoluteSensorRangeValue.Signed_PlusMinusHalf;
        //config.MagnetSensor.SensorDirection = SensorDirectionValue.Clockwise_Positive;
        if (absoluteEncoderReversed){
             config.MagnetSensor.SensorDirection = SensorDirectionValue.Clockwise_Positive;
        }
        else 
        {
            config.MagnetSensor.SensorDirection = SensorDirectionValue.CounterClockwise_Positive;
        }

        absoluteEncoder.getConfigurator().apply(config);

        driveMotor = new CANSparkMax(driveMotorId, MotorType.kBrushless);
        turningMotor = new CANSparkMax(turningMotorId, MotorType.kBrushless);

        driveMotor.setInverted(driveMotorReversed);
        turningMotor.setInverted(turningMotorReversed);

        // driveEncoder.setPosabsoluteitionConversionFactor(ModuleConstants.kDriveEncoderRot2Meter);
        // driveEncoder.setVelocityConversionFactor(ModuleConstants.kDriveEncoderRPM2MeterPerSec);
        // turningEncoder.setPositionConversionFactor(ModuleConstants.kTurningEncoderRot2Rad);
        // turningEncoder.setVelocityConversionFactor(ModuleConstants.kTurningEncoderRPM2RadPerSec);

        turningPidController = new PIDController(Constants.kPTurning, 0, 0);
        turningPidController.enableContinuousInput(-Math.PI, Math.PI);

        driveMotor.setSmartCurrentLimit(Constants.driveMotorCurrentLimit);
        turningMotor.setSmartCurrentLimit(Constants.driveMotorCurrentLimit);
    }

    // public SwerveModulePosition getDrivePosition() {
    //     return absoluteEncoder.getAbsolutePosition().getValue();
    // }

    public CANSparkMax getDriveMotor() {
    return driveMotor;
    }

    public CANSparkMax getSteeringMotor() {
    return turningMotor;
    }

    public double getTurningPosition() {
        return absoluteEncoder.getAbsolutePosition().getValue() * Math.PI * 2;
    }

    public double getTurnSetPoint(){
        return turningPidController.getSetpoint();
    }
    //need drive motor encoder??
    public double getDriveVelocity() {
        return driveMotor.getEncoder().getVelocity();
    }

    public double getCancoder(){
        return absoluteEncoder.getAbsolutePosition().getValue();
    }

    //kinda confused at the functionality of this method bc idk the parent class but im thinking
    //we dont need it because the pigeon also returns the same thing
//     public StatusSignal<Double> getVelocity()
//    {
//        return super.lookupStatusSignal(SpinValue.CANcoder_Velocity.value, Double.class, "Velocity", true);
//     }

    //replacement method for above
    //ok so update the above method needs straight velocity and pigeon only returns angular velocity so id use the driving
    //encoder id just from the spark bc obv that doesnt present as many issues as the rotational


    public double getAbsoluteEncoderRad() {
        return absoluteEncoder.getAbsolutePosition().getValue()*2*Math.PI;
    }

    public void resetEncoders() {
        absoluteEncoder.setPosition(0);
    }

    // switched from return type swervemoduleposition to swervemodulestate for
    // functionality
    public SwerveModuleState getState() {
        return new SwerveModuleState(driveMotor.getEncoder().getVelocity(), new Rotation2d(getTurningPosition()));
    }

    public SwerveModulePosition getPosition(){
        return new SwerveModulePosition(driveMotor.getEncoder().getPosition() * Constants.kDriveMotorGearRatio * 4 * Math.PI, 
            new Rotation2d(getTurningPosition()));
    }
    
    // public ChassisSpeeds getChassisSpeed(SwerveModuleState module){
    //     return ((Object) module).toChassisSpeeds(module);
    // }

    public void setDesiredState(SwerveModuleState state) {
        if (Math.abs(state.speedMetersPerSecond) < 0.001) {
             stop();
             return;
        }
        state = SwerveModuleState.optimize(state, getState().angle);
        driveMotor.set(state.speedMetersPerSecond / Constants.kPhysicalMaxSpeedMetersPerSecond);
        turningMotor.set(turningPidController.calculate(getTurningPosition(), state.angle.getRadians()));
        // SmartDashboard.putString("Swerve[" + absoluteEncoder.getChannel() + "]
        // state", state.toString());
    }

    public void stop() {
        driveMotor.set(0);
        turningMotor.set(0);
    }

}