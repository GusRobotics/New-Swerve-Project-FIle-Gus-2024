package frc.robot.commands;

//import java.sql.Driver;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;

public class HighShootCmd extends Command {
    private Shooter shooter;
    private boolean direction;
    private boolean secondDirection;
    private Timer ourTimer;

    public HighShootCmd(Shooter shooter, boolean direction, boolean secondDirection) {
        this.shooter = shooter;
        this.direction = direction;
        this.ourTimer = new Timer();
        addRequirements(shooter);
    }

    // Start
    @Override
    public void initialize() {
        if (direction) 
        {
            shooter.enableShooter();
        }
        else if(secondDirection){
            shooter.stopShooter();
        }
        else 
        {
            shooter.enableAutoLowShooter();
        }
        ourTimer.restart();
    }

    @Override
    public void end(boolean terminated) {
        if (!DriverStation.isAutonomous())
        {
            shooter.end();
        }
    }

    @Override
    public boolean isFinished() { 
        return DriverStation.isAutonomous() && ourTimer.get() > 3; 
    }
}