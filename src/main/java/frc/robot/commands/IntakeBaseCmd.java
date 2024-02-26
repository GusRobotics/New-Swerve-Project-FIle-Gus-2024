package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

public class IntakeBaseCmd extends Command {
    private Intake intake;
    private boolean direction;

    public IntakeBaseCmd(Intake intake, boolean direction) {
        this.intake = intake;
        this.direction = direction;

        addRequirements(intake);
    }

    // Start
    @Override
    public void initialize() {
        
        if (direction) 
        {
            intake.reverseIntake();
        }
        else 
        {
            intake.enableIntake();
        }
    }

    public void execute(){
        intake.enableIntake();
    }

    @Override
    public void end(boolean terminated) {
        intake.end();
    }

    @Override
    public boolean isFinished() { 
        return false; 
    }
}