package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase{

        private CANSparkMax topIntakeMotor = new CANSparkMax(Constants.IntakeConstants.topIntakeMotorId, CANSparkMax.MotorType.kBrushless);    
        private CANSparkMax bottomIntakeMotor = new CANSparkMax(Constants.IntakeConstants.bottomIntakeMotorId, CANSparkMax.MotorType.kBrushless);
    
    public IntakeSubsystem(CANSparkMax topIntakeMotor, CANSparkMax bottomIntakeMotor){
        this.topIntakeMotor = topIntakeMotor;
        this.bottomIntakeMotor = bottomIntakeMotor;
    }

    public void baseIntakeState(){
        topIntakeMotor.set(0);
        bottomIntakeMotor.set(0);
    }

    public void intakeState(){
        topIntakeMotor.set(Constants.topShooterSpeed);
        bottomIntakeMotor.set(Constants.bottomShooterSpeed);
    }

}
