package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class TestCmd extends CommandBase{

    public TestCmd() {
        
    }

    // Start
    public void execute(){
        SmartDashboard.putBoolean("TEST", true);
    }

    @Override
    public void initialize() {
       
    }

    @Override
    public void end(boolean terminated) {
        
    }

    @Override
    public boolean isFinished() { 
        return false; 
    }
}

