package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Limelight {
    NetworkTable table;
    NetworkTableEntry tx;
    NetworkTableEntry ty;
    NetworkTableEntry ta;

    double x;
    double y; 
    double area;

    //idfk i give up deal with this later
    //what goes in constructor???????
    //try: taking no parameters in the constructor, just make the network table 
    //then figure out how to integrate this with april tags!!??? 
    public Limelight(NetworkTable table, NetworkTableEntry tx, NetworkTableEntry ty, NetworkTableEntry ta){
        this.table =  NetworkTableInstance.getDefault().getTable("limelight");
        this.tx = table.getEntry("tx");
        this.ty = table.getEntry("ty");
        this.ta = table.getEntry("ta");
    }

    //read values periodically
    public void getValues(){    
        x = tx.getDouble(0.0);
        y = ty.getDouble(0.0);
        area = ta.getDouble(0.0);
    }

    public double getX(){
        return tx.getDouble(0.0);
    }

    public double gety(){
        return ty.getDouble(0.0);
    }

    public double getArea(){
        return ta.getDouble(0.0);
    }
    
    //post to smart dashboard periodically
    public void display(){
        SmartDashboard.putNumber("LimelightX", x);
        SmartDashboard.putNumber("LimelightY", y);
        SmartDashboard.putNumber("LimelightArea", area);
    }

    //limelight demands:: vision tracking for the apriltags on amps, measure distance from the limelight to april tag (high goal)
    //rumble joystick when in range if possible?? or make leds do something or whatever, 
}
