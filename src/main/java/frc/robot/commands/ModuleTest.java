package frc.robot.commands;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

public class ModuleTest extends Command{

    SwerveModuleState desiredState = new SwerveModuleState();

    @Override
    public void initialize() {
        SmartDashboard.putNumber("move", 0);
        SmartDashboard.putNumber("rotation", 0);
    }

    @Override
    public void execute() {
        //SmartDashboard.putNumber("encoder values", red.getAbsoluteEncoderRad());
        double desiredMove = SmartDashboard.getNumber("move", 0);
        double desiredRot = SmartDashboard.getNumber("rotation", 0);
        desiredState.speedMetersPerSecond = desiredMove;
        desiredState.angle = Rotation2d.fromRadians(desiredRot);
        //red.setDesiredState(desiredState);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}