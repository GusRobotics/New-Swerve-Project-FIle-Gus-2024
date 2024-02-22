// package frc.robot.subsystems;

// import com.playingwithfusion.TimeOfFlight;
// import com.revrobotics.CANSparkMax;

// import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import frc.robot.Constants;

// public class Intake extends SubsystemBase{

//         private CANSparkMax topIntakeMotor;    
//         private CANSparkMax bottomIntakeMotor;
//         private TimeOfFlight sensor;

//     public Intake(){
//         

//     public void baseState(){
//         topIntakeMotor.set(0);
//         bottomIntakeMotor.set(0);
//     }

//     //we need the measurement in milimeters 
//     //goal is if the sensor is triggered (ie if the note is in intake) it auto stops
//     public boolean getSensorInRange(){
//         if(sensor.getRange() < 110){
//             return true;
//         }
//         return false;
//     }

//     public void forewardState(){
//         topIntakeMotor.set(Constants.topIntakeSpeed);
//         bottomIntakeMotor.set(Constants.bottomIntakeSpeed);
//     }

//     public void reverseState(){
//         topIntakeMotor.set(Constants.negIntakeSpeed);
//         bottomIntakeMotor.set(Constants.negIntakeSpeed);
//     }

// }

package frc.robot.subsystems;

import com.playingwithfusion.TimeOfFlight;
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
import com.revrobotics.CANSparkLowLevel.MotorType;

/** Lower Intake Subsystem */
public class Intake implements Subsystem {
    // Hardware
    private CANSparkFlex topIntakeMotor = new CANSparkFlex(40, MotorType.kBrushless);    
    private CANSparkFlex bottomIntakeMotor = new CANSparkFlex(16, MotorType.kBrushless);;
    private TimeOfFlight sensor;

    private SparkPIDController intakeController;

    // Init
    public Intake() {
        // intake.setIdleMode(IdleMode.kBrake);
        // intake.setInverted(false);
        // intake.setSmartCurrentLimit(IntakeConstants.kINTAKE_CURRENT_LIMIT);

        // intakeController = intake.getPIDController();
        // intakeController.setP(IntakeConstants.kINTAKE_P, 0);
        // intakeController.setI(IntakeConstants.kINTAKE_I, 0);
        // intakeController.setD(IntakeConstants.kINTAKE_D, 0);

    // public Intake(){ //TimeOfFlight sensor){
    //     topIntakeMotor = new CANSparkFlex(Constants.topIntakeMotor, MotorType.kBrushless);
    //     bottomIntakeMotor = new CANSparkFlex(Constants.bottomIntakeMotor, MotorType.kBrushless);
    // }
    }
    /** Sets the intake's default command (not moving) */
    public void initDefaultCommand() {
        setDefaultCommand(new InstantCommand(
            this::end, this
        ));
    }

    /** Runs the intake forward */
    public void enableIntake() {
        topIntakeMotor.set(0.3);
        bottomIntakeMotor.set(0.3);
    }

    /** Runs the intake in reverse */
    public void reverseIntake() {
        topIntakeMotor.set(-0.3);
        bottomIntakeMotor.set(-0.3);
    }


        public void forewardIntakeState(){
        topIntakeMotor.set(Constants.topIntakeSpeed);
        bottomIntakeMotor.set(Constants.bottomIntakeSpeed);
    }

    /** Ends the intake function */
    public void end() {
        topIntakeMotor.set(0);
        bottomIntakeMotor.set(0);
    }

    @Override
    public void periodic() {

    }
}
