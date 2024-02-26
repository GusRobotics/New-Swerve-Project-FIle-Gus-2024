package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;

/** Lower Intake Subsystem */
public class Lights implements Subsystem {

    private Spark lightstrip = new Spark(Constants.ledChannel);
    
    // Init
    public Lights() {
 
    }
    /** Sets the intake's default command (not moving) */
    public void initDefaultCommand() {
        setDefaultCommand(new InstantCommand(
            this::baseLights, this
        ));
    }

    /** Runs the intake forward */
    public void baseLights(){
        lightstrip.set(Constants.pinkLights);
    }

    public void setSensorTriggered(){
        lightstrip.set(Constants.blueLights);
    }

    public void setIntakeOn(){
        lightstrip.set(Constants.yellowLights);
    }

    @Override
    public void periodic() {

    }
}