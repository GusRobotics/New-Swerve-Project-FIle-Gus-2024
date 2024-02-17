package frc.robot.commands;

import java.util.function.Supplier;

import com.playingwithfusion.TimeOfFlight;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCmd extends SubsystemBase{
    Supplier<Boolean> running; 
    Supplier<Boolean> trigger;
    CANSparkMax topShoot; 
    CANSparkMax bottomShoot;
    IntakeSubsystem intake = new IntakeSubsystem(topShoot, bottomShoot); 

    //running == controller input, trigger == flight sensor input
    //pray to god it works
    public IntakeCmd(IntakeSubsystem shoot, Supplier<Boolean> running, Supplier<Boolean> trigger){
        this.running = running;
        this.intake = shoot;
        this.trigger = trigger;
    }

    public void initialize(){
        intake.baseIntakeState();
    }

    public void execute(){
        if(running.get()){
            intake.intakeState();
        } 
        if(!trigger.get()){
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