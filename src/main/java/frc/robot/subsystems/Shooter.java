package frc.robot.subsystems;

import com.revrobotics.CANSparkFlex;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;
// import com.revrobotics.RelativeEncoder;
// import com.revrobotics.SparkPIDController;

/** Lower Intake Subsystem */
public class Shooter implements Subsystem {
    // Hardware
    private CANSparkFlex topShooterMotor = new CANSparkFlex(Constants.topShooterMotor, MotorType.kBrushless);    
    private CANSparkFlex bottomShooterMotor = new CANSparkFlex(Constants.bottomShooterMotor, MotorType.kBrushless);

    // Init
    public Shooter() {
        topShooterMotor.setSmartCurrentLimit(50);
        bottomShooterMotor.setSmartCurrentLimit(50);
    }
    /** Sets the shooter's default command (not moving) */
    public void initDefaultCommand() {
        setDefaultCommand(new InstantCommand(
            this::end, this
        ));
    }

    /** Runs the intake forward */
    public void enableShooter() {
        //orignally .5
        topShooterMotor.set(0.62);
        bottomShooterMotor.set(-0.625);
    }

    public void enableAutoLowShooter(){
        topShooterMotor.set(0.52);
        bottomShooterMotor.set(-0.425);
    }
    public void enableLowShooter() {
        //best so far was .1, -.5
        topShooterMotor.set(0.15);
        bottomShooterMotor.set(-0.4);
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

    public void reverseShooter(){
        topShooterMotor.set(-0.4);
        bottomShooterMotor.set(0.4);
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

