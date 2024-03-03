package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;

import com.revrobotics.CANSparkLowLevel.MotorType;
import frc.robot.Constants;

public class LeftClimber implements Subsystem {
    CANSparkMax leftClimb;
    CANSparkMax rightClimb; 

    public LeftClimber(){
        leftClimb  = new CANSparkMax(Constants.leftClimb, MotorType.kBrushless);
    }   

    public void initDefaultCommand() {
        setDefaultCommand(new InstantCommand(
            this::end, this
        ));
    }

    private void setDefaultCommand(InstantCommand instantCommand) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setDefaultCommand'");
    }

    public void end(){
        leftClimb.set(0);
        rightClimb.set(0);
    }
}
