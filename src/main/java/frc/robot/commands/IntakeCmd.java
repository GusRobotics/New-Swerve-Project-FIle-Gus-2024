// package frc.robot.commands;
// import java.util.function.Supplier;
// import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import frc.robot.subsystems.Intake;

// public class IntakeCmd extends SubsystemBase{
//     Supplier<Boolean> running; 
//     Supplier<Boolean> reversed;
//     Supplier<Boolean> trigger;
//     Intake intake; 
//     Supplier<Boolean> indexToShoot;
//     //running == controller input, trigger == flight sensor input
//     //pray to god it works
//     public IntakeCmd(Intake shoot, Supplier<Boolean> running, Supplier<Boolean> trigger, 
//         Supplier<Boolean> reversed, Supplier<Boolean> indexToShoot){
//             this.running = running;
//             this.intake = shoot;
//             this.trigger = trigger;
//             this.reversed = reversed;
//             this.indexToShoot = indexToShoot;
//     }

//     public void initialize(){
//         intake.baseState();
//     }

//     //controller.l2.ontrue then command to turn intake  on 

//     //command to turn it off

//     public void execute(){
//         //run the intake either when codriver is pressing and the sensor isn't triggered
//         //or when the sensor is triggered and the base driver runs intake to index
//         // if(running.get() || (trigger.get() && indexToShoot.get())){
//         //     intake.forewardState();
//         // } else if(trigger.get()){
//         //     intake.baseState();
//         // } 
//         // if(reversed.get()){

//             //boolean of forward or reversed 
//             ]
            
//         //     //intake.reverseIntakeState();
//         // } else {
//         //     intake.baseState();
//         // }
//     }

//     public void end(boolean interrupted) {
//         intake.baseState();
//     }

//     public boolean isFinished() {
//         return false;
//     }
// }


package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

public class IntakeCmd extends Command {
    private Intake intake;
    private boolean direction;

    public IntakeCmd(Intake intake, boolean direction) {
        this.intake = intake;
        this.direction = direction;

        addRequirements(intake);
    }

    // Start
    @Override
    public void initialize() {
        if (direction) 
        {
            intake.reverseIntake();
        }
        else 
        {
            intake.enableIntake();
        }
    }

    @Override
    public void end(boolean terminated) {
        intake.end();
    }

    @Override
    public boolean isFinished() { 
        return false; 
    }
}
