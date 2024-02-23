// package frc.robot.commands;

// import java.util.function.Supplier;

// import com.revrobotics.CANSparkMax;
// import frc.robot.subsystems.Shooter;

// public class ShooterCmd{
//     Supplier<Boolean> running; 
//     CANSparkMax topShoot; 
//     CANSparkMax bottomShoot;
//     Shooter shoot = new Shooter(topShoot, bottomShoot); 

//     public ShooterCmd(Shooter shoot, Supplier<Boolean> running){
//         this.running = running;
//         this.shoot = shoot;
//     }

//     public void initialize(){
//         shoot.baseShootState();
//     }

//     public void execute(){
//         // if(running.get()){
//         //     shoot.shootState();
//         // } else{
//         //     shoot.baseShootState();
//         // }
//     }

//     public void end(boolean interrupted) {
//         shoot.baseShootState();
//     }

//     public boolean isFinished() {
//         return false;
//     }
// }

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;

public class ShooterCmd extends Command {
    private Shooter shooter;
    private boolean direction;

    public ShooterCmd(Shooter shooter, boolean direction) {
        this.shooter = shooter;
        this.direction = direction;

        addRequirements(shooter);
    }

    // Start
    @Override
    public void initialize() {
        if (direction) 
        {
            shooter.enableShooter();
        }
        else 
        {
            shooter.stopShooter();
        }
    }

    @Override
    public void end(boolean terminated) {
        shooter.end();
    }

    @Override
    public boolean isFinished() { 
        return false; 
    }
}