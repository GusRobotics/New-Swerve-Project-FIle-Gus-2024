// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
//import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.IntakeCmd;
import frc.robot.commands.LowShootCmd;
import frc.robot.commands.PneumaticCmd;
import frc.robot.commands.ReverseIntakeCmd;
import frc.robot.commands.HighShootCmd;
import frc.robot.commands.SwerveJoystickCmd;
//import frc.robot.commands.TestCmd;
//import frc.robot.commands.Autonomous.AutoTest;
import frc.robot.commands.Autonomous.FourNoteNoMid;
import frc.robot.commands.Autonomous.fourPieceCenterMiddle;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Pneumatics;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.SwerveDrive;

import com.revrobotics.ColorSensorV3;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public static SwerveDrive drive = new SwerveDrive();
  public static CommandPS4Controller baseController = new CommandPS4Controller(0);
  public static CommandPS4Controller coController = new CommandPS4Controller(1);
  
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  private Intake intake = new Intake();
  private Shooter shooter = new Shooter();
  private Pneumatics pneumatic = new Pneumatics();

  //intaking and reversing
  private Trigger intakeForward = coController.R1();
  private Trigger intakeReverse = coController.R2();
 //forward and reverse flywheel
  private Trigger highSpinup = coController.L1();
  private Trigger lowSpinup = coController.circle();
  //private Trigger spinUpReverse = coController.cross();


  //not working r2 and l2 triggers are not working
  //pneumatics hold
  private Trigger pneumaticLift = baseController.R1();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    m_chooser.setDefaultOption(
            "RedFourNoteNoMid", 
            new FourNoteNoMid(drive)
        );

    m_chooser.addOption(
            "RedFourPieceCenterMiddle", 
            new fourPieceCenterMiddle(drive)
        );

    SmartDashboard.putData("Autonomous", m_chooser);
    // Configure the trigger bindings
    configureBindings();

    //enable compressor
    pneumatic.startCompressor();

    //set pink lights
    intake.setDefaultLights();

  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */


  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`x

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancellin on release.
    //m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());

    //SmartDashboard.putData(new IntakeCmd(intake, false));
    intakeForward.toggleOnTrue(new IntakeCmd(intake, false));
    intakeReverse.whileTrue(new ReverseIntakeCmd(intake, true));

    //become spinUpForward
    highSpinup.toggleOnTrue(new HighShootCmd(shooter, true));
    lowSpinup.toggleOnTrue(new LowShootCmd(shooter, true));
    //SmartDashboard.putData(new PneumaticCmd(pneumatic, true));
    pneumaticLift.toggleOnTrue(new PneumaticCmd(pneumatic, true));
    //need spinUpReverse
    //test.onTrue(new TestCmd());

    SmartDashboard.putData(new HighShootCmd(shooter, true));
    SmartDashboard.putData(new SwerveJoystickCmd(drive, baseController::getLeftX,
       baseController::getLeftY, baseController::getRightY, RobotContainer.baseController.triangle()::getAsBoolean));

 
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return m_chooser.getSelected();
  }
}
