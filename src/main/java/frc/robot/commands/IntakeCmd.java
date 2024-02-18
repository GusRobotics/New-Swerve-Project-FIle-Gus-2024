package frc.robot.commands;

import java.util.function.Supplier;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.Intake;

public class IntakeCmd extends SubsystemBase{
    Supplier<Boolean> running; 
    Supplier<Boolean> reversed;
    Supplier<Boolean> trigger;
    Intake intake; 
    Supplier<Boolean> indexToShoot;
    //running == controller input, trigger == flight sensor input
    //pray to god it works
    public IntakeCmd(Intake shoot, Supplier<Boolean> running, Supplier<Boolean> trigger, 
        Supplier<Boolean> reversed, Supplier<Boolean> indexToShoot){
            this.running = running;
            this.intake = shoot;
            this.trigger = trigger;
            this.reversed = reversed;
            this.indexToShoot = indexToShoot;
    }

    public void initialize(){
        intake.baseIntakeState();
    }

    public void execute(){
        //run the intake either when codriver is pressing and the sensor isn't triggered
        //or when the sensor is triggered and the base driver runs intake to index
        if(running.get() || (trigger.get() && indexToShoot.get())){
            intake.intakeState();
        } else if(trigger.get()){
            intake.baseIntakeState();
        } 
        if(reversed.get()){
            intake.reverseIntakeState();
        } else {
            intake.baseIntakeState();
        }
    }

    public void end(boolean interrupted) {
        intake.baseIntakeState();
    }

    public boolean isFinished() {
        return false;
    }
}