package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;

import com.revrobotics.CANSparkLowLevel.MotorType;
import frc.robot.Constants;

public class LeftClimber implements Subsystem {
    CANSparkMax leftClimb;

    public LeftClimber(){
        leftClimb  = new CANSparkMax(Constants.leftClimb, MotorType.kBrushless);
    }   

    public void initDefaultCommand() {
        setDefaultCommand(new InstantCommand(
            this::end, this
        ));
    }

    public void end(){
        leftClimb.set(0);
    }

    /** Runs the intake forward */
    public void setLeftClimbUp() {
        leftClimb.set(0.5);
    }

    public void setLeftClimbDown(){
        leftClimb.set(-0.5);
    }

    @Override
    public void periodic() {

    }
}
