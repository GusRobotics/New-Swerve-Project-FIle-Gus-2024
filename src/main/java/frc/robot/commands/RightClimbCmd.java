package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.RightClimber;

public class RightClimbCmd extends Command{
    
    private RightClimber climb;
    private boolean direction;

    public RightClimbCmd(RightClimber climb, boolean direction){
        this.climb = climb;
        this.direction = direction;
    }

    @Override
    public void initialize() { 
        if (direction) 
        {
            climb.setRightClimbUp();
        }
        else 
        {
            climb.setRightClimbDown();
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