package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;

public class Pneumatics implements Subsystem{
    PneumaticHub pneumatic = new PneumaticHub(Constants.pneumaticHubId);
    Compressor compressor = pneumatic.makeCompressor();
    DoubleSolenoid actuation = pneumatic.makeDoubleSolenoid(Constants.pneumaticForwardChannel,
        Constants.pneumaticReverseChannel);

    public Pneumatics(){

    }

    public void initDefaultCommand() {
        setDefaultCommand(new InstantCommand(
            this::basePosition, this
        ));
    }
    public void startCompressor(){
        compressor.enableAnalog(100, 120);
    }

    public void shootingPosition(){
        actuation.set(DoubleSolenoid.Value.kReverse);
    }

    public DoubleSolenoid getSolenoid(){
        return actuation; 
    }
    
    public void basePosition(){
        actuation.set(DoubleSolenoid.Value.kForward);
    }

    public void toggle(){
        actuation.toggle();
    }
}
