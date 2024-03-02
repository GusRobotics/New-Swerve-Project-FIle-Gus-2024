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


    private AnalogInput distSensorLeft = new AnalogInput(0);
    private AnalogInput distSensorRight = new AnalogInput(1);
    // Init
    public Intake() {
 
    }
    /** Sets the intake's default command (not moving) */
    public void initDefaultCommand() {
        setDefaultCommand(new InstantCommand(
            this::end, this
        ));
    }

    public boolean noteDetected(){
        return distSensorLeft.getValue() > 700 || distSensorRight.getValue() > 700;
    }
    
    /** Runs the intake forward */
    public void enableIntake() {
        bottomIntakeMotor.setInverted(true);
        SmartDashboard.putNumber("Left Distance Sensor", distSensorLeft.getValue());
        SmartDashboard.putNumber("Right Distance Sensor", distSensorRight.getValue());

        if(distSensorLeft.getValue() > 700 || distSensorRight.getValue() > 700){
            topIntakeMotor.set(/*Constants.topIntakeSpeed*/ 0);
            bottomIntakeMotor.set(/*Constants.bottomIntakeSpeed*/0);
            lightstrip.set(Constants.blueLights);
        }
        else {
        //  else if(distSensorLeft.getValue() >= 350 || distSensorRight.getValue() >= 350) {
            topIntakeMotor.set(0.27);
            bottomIntakeMotor.set(0.27);
            lightstrip.set(Constants.yellowLights);
        }

    }

    public double sensorVal(){
        return distSensorLeft.getValue();
    }

    /** Runs the intake in reverse */
    public void reverseIntake() {
        SmartDashboard.putNumber("Left Distance Sensor", distSensorLeft.getValue());
        SmartDashboard.putNumber("Right Distance Sensor", distSensorRight.getValue());
        bottomIntakeMotor.setInverted(true);
        topIntakeMotor.set(-0.3);
        bottomIntakeMotor.set(-0.3);
    }

    public void indexToShoot(){
        bottomIntakeMotor.setInverted(true);
        if(distSensorLeft.getValue() > 350 || distSensorRight.getValue() > 350){
            topIntakeMotor.set(0.4);
            bottomIntakeMotor.set(0.4);
            lightstrip.set(Constants.blueLights);
        }
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

    public void setBlueLights(){
        lightstrip.set(Constants.blueLights);
    }

    @Override
    public void periodic() {

    }
}