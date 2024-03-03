package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Lights;

public class LightsCmd extends Command {
    private Lights lights;
    private boolean on;

    public LightsCmd(Lights lights, boolean on) {
        this.lights = lights;
        this.on = on;

        addRequirements(lights);
    }

    // Start
    @Override
    public void initialize() {
        if (on) 
        {
            //lights.setIntakeOn();
        }
        else 
        {
            lights.baseLights();
        }
    }

    @Override
    public void end(boolean terminated) {
        lights.baseLights();
    }

    @Override
    public boolean isFinished() { 
        return false; 
    }
}