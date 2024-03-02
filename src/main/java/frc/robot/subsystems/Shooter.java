package frc.robot.subsystems;

import com.revrobotics.CANSparkFlex;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.util.datalog.DoubleLogEntry;
import edu.wpi.first.wpilibj.DataLogManager;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;

/** Lower Intake Subsystem */
public class Shooter implements Subsystem {
    // Hardware
    private CANSparkFlex topShooterMotor = new CANSparkFlex(Constants.topShooterMotor, MotorType.kBrushless);    
    private CANSparkFlex bottomShooterMotor = new CANSparkFlex(Constants.bottomShooterMotor, MotorType.kBrushless);

    private SparkPIDController shooterController;

    // Init
    public Shooter() {
    }
    /** Sets the shooter's default command (not moving) */
    public void initDefaultCommand() {
        setDefaultCommand(new InstantCommand(
            this::end, this
        ));
    }

    /** Runs the intake forward */
    public void enableShooter() {
        topShooterMotor.set(0.5);
        bottomShooterMotor.set(-0.63);
    }

    public void enableLowShooter() {
        //best so far was .1, -.5
        topShooterMotor.set(0.15);
        bottomShooterMotor.set(-0.625);
    }

    public void stopShooter() {
        topShooterMotor.set(0);
        bottomShooterMotor.set(0);
    }

    /** Runs the intake in reverse */

    // public void reverseShooter() {
    //     topShooterMotor.set(-0.6);
    //     bottomShooterMotor.set(0.6);
    // }

    public void forewardShooterState(){
        topShooterMotor.set(Constants.topIntakeSpeed);
        bottomShooterMotor.set(Constants.bottomIntakeSpeed);
    }

    /** Ends the intake function */
    public void end() {
        topShooterMotor.set(0);
        bottomShooterMotor.set(0);
    }

    @Override
    public void periodic() {

    }
}

