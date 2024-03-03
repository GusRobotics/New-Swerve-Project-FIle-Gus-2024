package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;

public class RightClimber implements Subsystem {
    CANSparkMax rightClimb;
    
    public RightClimber(){
        rightClimb = new CANSparkMax(Constants.rightClimb, MotorType.kBrushless);
    }
    
    /** Sets the shooter's default command (not moving) */
    public void initDefaultCommand() {
        setDefaultCommand(new InstantCommand(
            this::end, this
        ));
    }

    /** Runs the intake forward */
    public void setRightClimbUp() {
        rightClimb.set(0.5);
    }

    public void setRightClimbDown(){
        rightClimb.set(-0.5);
    }

    /** Ends the intake function */
    public void end() {
        rightClimb.set(0);
    }

    @Override
    public void periodic() {

    }
}


