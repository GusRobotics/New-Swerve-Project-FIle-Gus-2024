package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class ReverseShooterCmd extends Command {
    
    private Shooter shooter;
    private Intake intake;
    private boolean direction;

    public ReverseShooterCmd(Shooter shooter, Intake intake, boolean direction) {
        this.shooter = shooter;
        this.intake = intake;
        this.direction = direction;

        addRequirements(shooter);
    }

    // Start
    @Override
    public void initialize() {
        if (direction) 
        {
            shooter.reverseShooter();
            intake.reverseIntakeWSensor();
        }
        else 
        {

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



   