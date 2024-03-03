package frc.robot.commands;

//import com.fasterxml.jackson.databind.JsonSerializable.Base;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

public class IntakeBaseCmd extends Command {
    private Intake intakebase;
    private boolean direction;
    private Timer baseTimer;

    public IntakeBaseCmd(Intake intakebase, boolean direction) {
        this.intakebase = intakebase;
        this.direction = direction;

        this.baseTimer = new Timer();
        addRequirements(intakebase);
    }

    // Start
    @Override
    public void initialize() {
        
        if (direction) 
        {
            intakebase.indexToShoot();
        }
        else 
        {
            intakebase.end();
        }

        baseTimer.restart();
    }

    public void execute(){
        intakebase.indexToShoot();
    }

    @Override
    public void end(boolean terminated) {
        intakebase.end();
    }

    @Override
    public boolean isFinished() { 
        return DriverStation.isAutonomous() && baseTimer.get() > 1;
    }
}