package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;

public class LowShootCmd extends Command {
    private Shooter shooter;
    private boolean direction;

    public LowShootCmd(Shooter shooter, boolean direction) {
        this.shooter = shooter;
        this.direction = direction;

        addRequirements(shooter);
    }

    // Start
    @Override
    public void initialize() {
        if (direction) 
        {
            shooter.enableLowShooter();
        }
        else 
        {
            shooter.stopShooter();
        }
    }

    @Override
    public void end(boolean terminated) {
        shooter.end();
    }

    @Override
    public boolean isFinished() { 
        return false; 
    }
}