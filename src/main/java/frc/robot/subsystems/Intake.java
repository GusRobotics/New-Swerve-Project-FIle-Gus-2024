package frc.robot.subsystems;

import com.playingwithfusion.TimeOfFlight;
import com.revrobotics.CANSparkFlex;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;

/** Lower Intake Subsystem */
public class Intake implements Subsystem {
    // Hardware
    private CANSparkFlex topIntakeMotor = new CANSparkFlex(Constants.topIntakeMotor, MotorType.kBrushless);    
    private CANSparkFlex bottomIntakeMotor = new CANSparkFlex(Constants.bottomIntakeMotor, MotorType.kBrushless);

    private Spark lightstrip = new Spark(0);


    private AnalogInput distSensor = new AnalogInput(1);

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
        // topIntakeMotor.set(0.3);
        // bottomIntakeMotor.set(-0.3);
        // lightstrip.set(Constants.blueLights);
        bottomIntakeMotor.setInverted(true);
        SmartDashboard.putNumber("BSDFSKDFHJSDF", distSensor.getValue());
        
        //nothing happened when less than 200
        //means the value is greater than 200
        //stuff happened when less than 500
        //means its less than 500 and greater than 200
        //worked when less than 400 so between 200 and 400
        if(distSensor.getValue() < 210.99){
            topIntakeMotor.set(/*Constants.topIntakeSpeed*/ 0.05);
            bottomIntakeMotor.set(/*Constants.bottomIntakeSpeed*/ 0.05);
            lightstrip.set(Constants.blueLights);
        }
        else if(distSensor.getValue() >= 210.6) {
            topIntakeMotor.set(0);
            bottomIntakeMotor.set(0);
            lightstrip.set(Constants.yellowLights);
        }
    }

    public double sensorVal(){
        return distSensor.getValue();
    }

    /** Runs the intake in reverse */
    public void reverseIntake() {
        bottomIntakeMotor.setInverted(true);
        topIntakeMotor.set(-0.3);
        bottomIntakeMotor.set(-0.3);
    }

    public void indexToShoot(){
        if(distSensor.getValue() > 30){
            topIntakeMotor.set(Constants.topIntakeSpeed);
            bottomIntakeMotor.set(Constants.bottomIntakeSpeed);
        }
    }

    public void forewardIntakeState(){
        // bottomIntakeMotor.setInverted(true);
        // SmartDashboard.putNumber("ultrasonic range", distSensor.getValue());
        // if(distSensor.getValue() > 200){
        //     topIntakeMotor.set(/*Constants.topIntakeSpeed*/ 0.05);
        //     bottomIntakeMotor.set(/*Constants.bottomIntakeSpeed*/ 0.05);
        //     lightstrip.set(Constants.blueLights);
        // } else if(distSensor.getValue() <= 75){
        //     topIntakeMotor.set(0);
        //     bottomIntakeMotor.set(0);
        //     lightstrip.set(Constants.yellowLights);
        // }

    }

    /** Ends the intake function */
    public void end() {
        topIntakeMotor.set(0);
        bottomIntakeMotor.set(0);
        lightstrip.set(Constants.pinkLights);
    }

    public void setDefaultLights(){
        lightstrip.set(Constants.pinkLights);
    }

    @Override
    public void periodic() {

    }
}
