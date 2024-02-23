// package frc.robot.subsystems;

// import com.revrobotics.CANSparkFlex;
// import com.revrobotics.CANSparkMax;

// import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import frc.robot.Constants;

// public class Shooter extends SubsystemBase{
//     private CANSparkFlex topShooterMotor; //= new CANSparkMax(Constants.ShooterConstants.topShooterId, CANSparkMax.MotorType.kBrushless);    
//     private CANSparkFlex bottomShooterMotor; //= new CANSparkMax(Constants.ShooterConstants.bottomShooterId, CANSparkMax.MotorType.kBrushless);
    
//     public Shooter(CANSparkMax topMotor, CANSparkMax bottomMotor){
//         // this.topShooterMotor = topMotor;
//         // this.bottomShooterMotor = bottomMotor;
//     }

//     public void baseShootState(){
//         topShooterMotor.set(0);
//         bottomShooterMotor.set(0);
//     }

//     public void shootState(){
//         topShooterMotor.set(Constants.topShooterSpeed);
//         bottomShooterMotor.set(Constants.bottomShooterSpeed);
//     }
// }

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
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkBase.IdleMode;

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
        topShooterMotor.set(0.6);
        bottomShooterMotor.set(-0.6);
    }

    public void stopShooter() {
        topShooterMotor.set(0);
        bottomShooterMotor.set(0);
    }

    /** Runs the intake in reverse */
    public void reverseIntake() {
        topShooterMotor.set(-0.6);
        bottomShooterMotor.set(0.6);
    }

    public void forewardIntakeState(){
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

