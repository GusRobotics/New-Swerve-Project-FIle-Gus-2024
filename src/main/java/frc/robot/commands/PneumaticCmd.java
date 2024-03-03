package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Pneumatics;

public class PneumaticCmd extends Command {
    private Pneumatics pneumatic;

    public PneumaticCmd(Pneumatics pneumatic) {
        this.pneumatic = pneumatic;

        addRequirements(pneumatic);
    }

    // Start
    @Override
    public void initialize() {
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