package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;

public class RightClimber implements Subsystem {
    CANSparkMax rightClimb;
    
    public RightClimber(){
        rightClimb = new CANSparkMax(Constants.rightClimb, MotorType.kBrushless);
    }
}
