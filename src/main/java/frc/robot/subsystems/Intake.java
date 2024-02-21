package frc.robot.subsystems;

import com.playingwithfusion.TimeOfFlight;
import com.revrobotics.CANSparkFlex;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase{

        private CANSparkFlex topIntakeMotor;//    
        private CANSparkFlex bottomIntakeMotor;// 
        private TimeOfFlight sensor;

    public Intake(){ //TimeOfFlight sensor){
        topIntakeMotor = new CANSparkFlex(Constants.topIntakeMotor, MotorType.kBrushless);
        bottomIntakeMotor = new CANSparkFlex(Constants.bottomIntakeMotor, MotorType.kBrushless);
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
