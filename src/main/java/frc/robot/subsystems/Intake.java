package frc.robot.subsystems;

import com.revrobotics.CANSparkFlex;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogInput;
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
    private CANSparkMax floorIntakeRoller = new CANSparkMax(Constants.groundIntakeMotor, MotorType.kBrushless);

    private Spark lightstrip = new Spark(0);


    private AnalogInput distSensorLeft = new AnalogInput(0);
    private AnalogInput distSensorRight = new AnalogInput(1);
    // Init
    public Intake() {
        floorIntakeRoller.setSmartCurrentLimit(30);
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
        floorIntakeRoller.setInverted(true);
        SmartDashboard.putNumber("Left Distance Sensor", distSensorLeft.getValue());
        SmartDashboard.putNumber("Right Distance Sensor", distSensorRight.getValue());
        
        if(distSensorLeft.getValue() > 755 || distSensorRight.getValue() > 755){
            topIntakeMotor.set(/*Constants.topIntakeSpeed*/ 0);
            bottomIntakeMotor.set(/*Constants.bottomIntakeSpeed*/0);
            floorIntakeRoller.set(/*Constants.bottomIntakeSpeed*/0);
            lightstrip.set(Constants.blueLights);
        }
        else {
        //  else if(distSensorLeft.getValue() >= 350 || distSensorRight.getValue() >= 350) {
            topIntakeMotor.set(0.55);
            bottomIntakeMotor.set(0.55);
            floorIntakeRoller.set(0.9);
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
        topIntakeMotor.set(-0.1);
        bottomIntakeMotor.set(-0.1);
        floorIntakeRoller.set(-0.75);
    }

    public void reverseIntakeWSensor() {
        bottomIntakeMotor.setInverted(true);
        SmartDashboard.putNumber("Left Sensor", distSensorLeft.getValue());
        SmartDashboard.putNumber("Right Sensor", distSensorRight.getValue());
            topIntakeMotor.set(-0.03);
            bottomIntakeMotor.set(-0.03);
            floorIntakeRoller.set(.75);
            lightstrip.set(-.11);
    }

    public void indexToShoot(){
        bottomIntakeMotor.setInverted(true);
        //originally 360
        //showed 1700 in smartdashboard
        topIntakeMotor.set(0.4);
        bottomIntakeMotor.set(0.4);
        floorIntakeRoller.set(0.4);
        lightstrip.set(Constants.blueLights);
    }

    /** Ends the intake function */
    public void end() {
        topIntakeMotor.set(0);
        bottomIntakeMotor.set(0);
        floorIntakeRoller.set(0);
        lightstrip.set(Constants.pinkLights);
    }

    public void setDefaultLights(){
        lightstrip.set(Constants.pinkLights);
    }

    public void setBlueLights(){
        lightstrip.set(Constants.blueLights);
    }

    public void setPinkLights(){
        lightstrip.set(Constants.pinkLights);
    }

    @Override
    public void periodic() {

    }
}