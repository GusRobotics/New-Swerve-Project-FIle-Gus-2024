// package frc.robot.commands.Autonomous;

// import java.util.List;

// import frc.robot.subsystems.Intake;
// import frc.robot.subsystems.SwerveDrive;

// import edu.wpi.first.wpilibj.Timer;
// //import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj2.command.Command;
// import edu.wpi.first.wpilibj2.command.InstantCommand;
// //import edu.wpi.first.wpilibj2.command.InstantCommand;
// import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
// import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
// import frc.robot.Constants;
// import frc.robot.RobotContainer;
// import frc.robot.commands.HighShootCmd;
// import frc.robot.commands.IntakeBaseCmd;
// import frc.robot.subsystems.SwerveDrive;


// public class AutoTest extends SequentialCommandGroup{
//     private Timer autoTimer = new Timer();
//         IntakeBaseCmd intakeBaseCmd = new IntakeBaseCmd(RobotContainer.intake, true);
//         HighShootCmd highShootCmd = new HighShootCmd(RobotContainer.shooter, true, false);

//     public AutoTest(SwerveDrive s_Swerve) {
//         autoTimer.restart();
//         if (autoTimer.get() < 8)
//         {
//             highShootCmd.schedule();
//         }
        
//         if (autoTimer.get() > 5 && autoTimer.get() < 8)
//         {
//             intakeBaseCmd.schedule();
//         }
//     }
// }

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

 
public class AutoTest extends Command {

    private Timer timer;
    private Shooter s_Shooter;
    private Intake s_Intake;
    
    public AutoTest(Shooter s_Shooter, Intake s_Intake) {
        this.s_Shooter = s_Shooter;
        this.s_Intake = s_Intake;
        timer = new Timer();
        timer.reset();

        addRequirements(s_Shooter);
        addRequirements(s_Intake);
    }

    @Override
    public void initialize() {
        s_Shooter.enableShooter();
    }

    @Override
    public void execute() {
            timer.start();
            s_Shooter.enableShooter();

        if (timer.hasElapsed(5)){
            s_Intake.indexToShoot();
        }
    }

    @Override
    public boolean isFinished() {
        if (timer.hasElapsed(10)) {
            timer.stop();
            timer.reset();
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void end(boolean interrupted) {
        s_Shooter.stopShooter();
    }
}