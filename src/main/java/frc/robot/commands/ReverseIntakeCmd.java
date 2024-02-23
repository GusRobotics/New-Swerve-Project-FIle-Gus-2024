package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

public class ReverseIntakeCmd extends Command {
    private Intake intake;
    private boolean direction;

    public ReverseIntakeCmd(Intake intake, boolean direction) {
        this.intake = intake;
        this.direction = direction;

        addRequirements(intake);
    }

    // Start
    @Override
    public void initialize() {
        if (direction) 
        {
            intake.enableIntake();
        }
        else 
        {
            intake.reverseIntake();
        }
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
