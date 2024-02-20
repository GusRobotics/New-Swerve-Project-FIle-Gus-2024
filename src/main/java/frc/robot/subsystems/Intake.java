package frc.robot.subsystems;

import com.playingwithfusion.TimeOfFlight;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase{

        private CANSparkMax topIntakeMotor;    
        private CANSparkMax bottomIntakeMotor;
        private TimeOfFlight sensor;

    public Intake(){
        topIntakeMotor = new CANSparkMax(Constants.IntakeConstants.topIntakeMotorId, CANSparkMax.MotorType.kBrushless);
        bottomIntakeMotor = new CANSparkMax(Constants.IntakeConstants.bottomIntakeMotorId, CANSparkMax.MotorType.kBrushless);
        sensor = new TimeOfFlight(Constants.flightId);
    }

    public void baseIntakeState(){
        topIntakeMotor.set(0);
        bottomIntakeMotor.set(0);
    }

    //we need the measurement in milimeters 
    //goal is if the sensor is triggered (ie if the note is in intake) it auto stops
    public boolean getSensorInRange(){
        if(sensor.getRange() < 110){
            return true;
        }
        return false;
    }

    public void intakeState(){
        topIntakeMotor.set(Constants.topIntakeSpeed);
        bottomIntakeMotor.set(Constants.bottomIntakeSpeed);
    }

    public void reverseIntakeState(){
        topIntakeMotor.set(Constants.negIntakeSpeed);
        bottomIntakeMotor.set(Constants.negIntakeSpeed);
    }

}
