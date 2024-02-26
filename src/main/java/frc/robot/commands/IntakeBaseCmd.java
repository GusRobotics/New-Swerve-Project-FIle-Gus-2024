package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

public class IntakeBaseCmd extends Command {
    private Intake intakebase;
    private boolean direction;

    public IntakeBaseCmd(Intake intakebase, boolean direction) {
        this.intakebase = intakebase;
        this.direction = direction;

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
        return false; 
    }
}