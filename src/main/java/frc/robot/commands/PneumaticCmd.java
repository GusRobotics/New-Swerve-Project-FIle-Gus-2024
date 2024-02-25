package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Pneumatics;

public class PneumaticCmd extends Command {
    private Pneumatics pneumatic;
    private boolean direction;

    public PneumaticCmd(Pneumatics pneumatic, boolean direction) {
        this.pneumatic = pneumatic;
        this.direction = direction;

        addRequirements(pneumatic);
    }

    // Start
    @Override
    public void initialize() {
        // if (direction){
        //     pneumatic.shootingPosition();
        // } else{
        //     pneumatic.basePosition();
        // }
        pneumatic.shootingPosition();
    }

    @Override
    public void end(boolean terminated) {
        pneumatic.basePosition();
    }

    @Override
    public boolean isFinished() { 
        return false; 
    }
}