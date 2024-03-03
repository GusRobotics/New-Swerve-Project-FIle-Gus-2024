package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.LeftClimber;
import frc.robot.subsystems.RightClimber;

public class LeftClimbCmd extends Command{
    
    private LeftClimber climb;
    private boolean direction;

    public LeftClimbCmd(LeftClimber climb, boolean direction){
        this.climb = climb;
        this.direction = direction;
    }

    @Override
    public void initialize() { 
        if (direction) 
        {
            climb.setLeftClimbUp();
        }
        else 
        {
            climb.setLeftClimbDown();
        }
    }

    @Override
    public void end(boolean terminated) {
        climb.end();
    }

    @Override
    public boolean isFinished(){
        return false;
    }
}