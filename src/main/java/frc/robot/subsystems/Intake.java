package frc.robot.subsystems;

import com.playingwithfusion.TimeOfFlight;
import com.revrobotics.CANSparkFlex;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogInput;
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
    private CANSparkFlex topIntakeMotor = new CANSparkFlex(Constants.topIntakeMotor, MotorType.kBrushless);    
    private CANSparkFlex bottomIntakeMotor = new CANSparkFlex(Constants.bottomIntakeMotor, MotorType.kBrushless);
    private AnalogInput distSensor = new AnalogInput(1);

    private Lights lights;

    // Init
    public Intake() {
 
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
        bottomIntakeMotor.set(-0.3);
        lights.setIntakeOn();
    }

    /** Runs the intake in reverse */
    public void reverseIntake() {
        topIntakeMotor.set(-0.3);
        bottomIntakeMotor.set(0.3);
    }

    public void indexToShoot(){
        // if(sensor.getRange() > 110){
        //     topIntakeMotor.set(Constants.topIntakeSpeed);
        //     bottomIntakeMotor.set(Constants.bottomIntakeSpeed);
        // }
    }

    public void forewardIntakeState(){
        if(/*distSensor.getValue() < 110 */ true){
            topIntakeMotor.set(Constants.topIntakeSpeed);
            bottomIntakeMotor.set(Constants.bottomIntakeSpeed);
            lights.setIntakeOn(); }
        // } else{
        //     topIntakeMotor.set(0);
        //     bottomIntakeMotor.set(0);
        //     lights.setSensorTriggered();
        // }

    }

    /** Ends the intake function */
    public void end() {
        topIntakeMotor.set(0);
        bottomIntakeMotor.set(0);
        lights.baseLights();
    }

    @Override
    public void periodic() {

    }
}
