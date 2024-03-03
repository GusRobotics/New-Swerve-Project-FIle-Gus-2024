package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

public class IntakeCmd extends Command {
    private Intake intake;
    private boolean direction;
    private Timer intakeTimer;


    public IntakeCmd(Intake intake, boolean direction) {
        this.intake = intake;
        // this.direction = direction;
        this.intakeTimer = new Timer();
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
            // if(!DriverStation.isAutonomous()){
            //     intake.setBlueLights();
            // }
        }
        intakeTimer.restart();

    }

    // public void execute(){
    //     intake.enableIntake();
    // }

    @Override
    public void end(boolean terminated) {
        if (!DriverStation.isAutonomous())
        {
            intake.end();
            intake.setBlueLights();
        }
    }

    @Override
    public boolean isFinished() { 
        // return DriverStation.isAutonomous() && intakeTimer.get() > 1; 
        return intake.noteDetected();
    }
}