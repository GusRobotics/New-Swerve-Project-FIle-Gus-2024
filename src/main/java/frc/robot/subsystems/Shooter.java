package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import frc.robot.Constants;

public class Shooter {
    CANSparkMax topMotor;
    CANSparkMax bottomMotor;
    
    public Shooter(CANSparkMax topMotor, CANSparkMax bottomMotor){
        this.topMotor = topMotor;
        this.bottomMotor = bottomMotor;
    }

    public void baseState(){
        topMotor.set(0);
        bottomMotor.set(0);
    }

    public void shootState(){
        topMotor.set(Constants.topShooterSpeed);
        bottomMotor.set(Constants.bottomShooterSpeed);
    }
}
