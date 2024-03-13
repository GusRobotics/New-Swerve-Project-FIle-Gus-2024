package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Pneumatics;
import edu.wpi.first.wpilibj.DriverStation;
//import edu.wpi.first.wpilibj.Timer;

public class PneumaticCmd extends Command {
    private Pneumatics pneumatic;
    //private Timer timer;
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
        if(!DriverStation.isAutonomous()){
            pneumatic.basePosition();
        }
    }

    @Override
    public boolean isFinished() { 
        return DriverStation.isAutonomous(); 
    }
}