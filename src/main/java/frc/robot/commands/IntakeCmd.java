package frc.robot.commands;

import java.util.function.Supplier;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCmd extends SubsystemBase{
    Supplier<Boolean> running; 
    CANSparkMax topShoot; 
    CANSparkMax bottomShoot;
    IntakeSubsystem intake = new IntakeSubsystem(topShoot, bottomShoot); 

    public IntakeCmd(IntakeSubsystem shoot, Supplier<Boolean> running){
        this.running = running;
        this.intake = shoot;
    }

    public void initialize(){
        intake.baseIntakeState();
    }

    public void execute(){
        if(running.get()){
            intake.intakeState();
        } else{
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