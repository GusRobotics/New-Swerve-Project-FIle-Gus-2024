package frc.robot.subsystems;

import com.revrobotics.CANSparkFlex;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase{
    private CANSparkFlex topShooterMotor; //= new CANSparkMax(Constants.ShooterConstants.topShooterId, CANSparkMax.MotorType.kBrushless);    
    private CANSparkFlex bottomShooterMotor; //= new CANSparkMax(Constants.ShooterConstants.bottomShooterId, CANSparkMax.MotorType.kBrushless);
    
    public Shooter(CANSparkMax topMotor, CANSparkMax bottomMotor){
        // this.topShooterMotor = topMotor;
        // this.bottomShooterMotor = bottomMotor;
    }

    public void baseShootState(){
        topShooterMotor.set(0);
        bottomShooterMotor.set(0);
    }

    public void shootState(){
        topShooterMotor.set(Constants.topShooterSpeed);
        bottomShooterMotor.set(Constants.bottomShooterSpeed);
    }
}
