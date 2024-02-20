package frc.robot.subsystems;

import com.playingwithfusion.TimeOfFlight;
import com.revrobotics.CANSparkFlex;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase{

        private CANSparkFlex topIntakeMotor;// = new CANSparkMax(Constants.IntakeConstants.topIntakeMotorId, CANSparkMax.MotorType.kBrushless);    
        private CANSparkFlex bottomIntakeMotor;// = new CANSparkMax(Constants.IntakeConstants.bottomIntakeMotorId, CANSparkMax.MotorType.kBrushless);
        private TimeOfFlight sensor;

    public Intake(CANSparkFlex topIntakeMotor, CANSparkFlex bottomIntakeMotor){ //TimeOfFlight sensor){
        this.topIntakeMotor = topIntakeMotor;
        this.bottomIntakeMotor = bottomIntakeMotor;
    }

    public void baseState(){
        topIntakeMotor.set(0);
        bottomIntakeMotor.set(0);
    }

    //we need the measurement in milimeters 
    //goal is if the sensor is triggered (ie if the note is in intake) it auto stops
    public boolean sensorInRange(){
        if(sensor.getRange() < 110){
            return true;
        }
        return false;
    }

    public void forewardState(){
        topIntakeMotor.set(Constants.topIntakeSpeed);
        bottomIntakeMotor.set(Constants.bottomIntakeSpeed);
    }

}
