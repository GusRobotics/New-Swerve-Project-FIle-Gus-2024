// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
 
package frc.robot;
 
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.commands.PathPlannerAuto;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.VideoSource;
import edu.wpi.first.math.geometry.Rotation2d;
// import edu.wpi.first.math.geometry.Pose2d;
// import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
//import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.IntakeCmd;
import frc.robot.commands.LowShootCmd;
import frc.robot.commands.PneumaticCmd;
import frc.robot.commands.ReverseIntakeCmd;
import frc.robot.commands.ReverseShooterCmd;
import frc.robot.commands.HighShootCmd;
import frc.robot.commands.IntakeBaseCmd;
import frc.robot.commands.SwerveJoystickCmd;
import frc.robot.commands.Autonomous.AutoTest;
import frc.robot.commands.Autonomous.TestPath;
//import frc.robot.commands.TestCmd;
//import frc.robot.commands.Autonomous.AutoTest;
//import frc.robot.commands.Autonomous.FourNoteNoMid;
//import frc.robot.commands.Autonomous.fourPieceCenterMiddle;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Pneumatics;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.SwerveDrive;
 
 
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
  public static CommandXboxController coController = new CommandXboxController(1);
  public static CommandXboxController climbController = new CommandXboxController(2);
  
 
  //plan b
  //public static CommandXboxController baseController = new CommandXboxController(0);
  SendableChooser<Command> m_chooser;// = new SendableChooser<Command>();
 
  public static Intake intake = new Intake();
  public static  Shooter shooter = new Shooter();
  public static Pneumatics pneumatic = new Pneumatics();
 
 //forward and reverse flywheel
  private Trigger highSpinup = coController.leftBumper();
  private Trigger intakeForward = coController.rightBumper();

  private Trigger reverseShooter = coController.a();

 private Trigger intakeBase = baseController.L1();
//private Trigger intakeBase = baseController.L1();

  private Trigger pneumaticActuate = baseController.R1();

  //private Trigger reverseShooter = baseController.square();

  public static CameraServer camera;

  //b is circle
  //add a bit of logic into hte code where you go into robotperiodic, have an if statement constantly check of either trigger
  //if trigger axis is greater than .75 and ifi t is scheudle a command and if it isn't schedule a command
  //inside of if statement, commandscheudler.getinstance.schedule and then the command
  //make sure you aren't repeatedly calling the command so need a flag inside of if statement which makes sure youve only pressed it once 
 
 
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // NamedCommands.registerCommand("High Shoot Command", new HighShootCmd(shooter, true, false));
    // NamedCommands.registerCommand("Intake Command", new IntakeCmd(intake, false));
    // NamedCommands.registerCommand("Reverse Intake Commands", new ReverseIntakeCmd(intake, true));
    // NamedCommands.registerCommand("Low Shoot Command ", new HighShootCmd(shooter, false, false));
    // NamedCommands.registerCommand("Intake Base", new IntakeBaseCmd(intake, true));
    // NamedCommands.registerCommand("Pneumatics Up", new PneumaticCmd(pneumatic));
    // NamedCommands.registerCommand("Stop Shooter", new HighShootCmd(shooter, false, true));
    //m_chooser = AutoBuilder.buildAutoChooser();
    m_chooser = new SendableChooser<Command>();
    
   ///COMMENT BACK IN
   //m_chooser.addOption("wait", new PathPlannerAuto("New Auto"));

    //m_chooser.addOption("Blue center two note", new PathPlannerAuto("Two Note Blue"));
        //m_chooser.addOption("AutoAttempt", new PathPlannerAuto("AutoAttempt"));
 
 
  //  SmartDashboard.putData("Auto Chooser", m_chooser);
    
    // Configure the trigger bindings
    configureBindings();
 
    //enable compressor
    pneumatic.startCompressor();
 
    //set pink lights
    intake.setDefaultLights();
    CameraServer.startAutomaticCapture();
    pneumatic.basePosition();
    
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

    //l1 co (high spinup)
    highSpinup.whileTrue(new HighShootCmd(shooter, true, false));

    //l2 co (low spin up)
    coController.leftTrigger(0.1).whileTrue(new LowShootCmd(shooter, true));

    //r1 co (intake forward)
    intakeForward.whileTrue(new IntakeCmd(intake, false));

    //r2 co (intake reverse)
    coController.rightTrigger(0.1).whileTrue(new ReverseIntakeCmd(intake, true));

    pneumaticActuate.whileTrue(new PneumaticCmd(pneumatic));

    intakeBase.whileTrue(new IntakeBaseCmd(intake, true));

    reverseShooter.whileTrue(new ReverseShooterCmd(shooter, intake, true));

    //turnGreen.whileTrue(new ReverseShooterCmd(shooter, intake, false));

    //lowSpinup.whileTrue(new LowShootCmd(shooter, false));
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`x
 
    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancellin on release.
    //m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
 
    //SmartDashboard.putData(new IntakeCmd(intake, false));
    // intakeReverse.whileTrue(new ReverseIntakeCmd(intake, true));

    //SmartDashboard.putData(new HighShootCmd(shooter, true));
    SmartDashboard.putData(new SwerveJoystickCmd(drive, baseController::getLeftX,
       baseController::getLeftY, baseController::getRightY, RobotContainer.baseController.triangle()::getAsBoolean));
  }
 
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    SmartDashboard.putString("hi", "hi");
    // An example command will be run in autonomous
    //return m_chooser.getSelected();
  //  Command selectedCommand = m_chooser.getSelected();
  //   if (selectedCommand instanceof PathPlannerAuto) {
  //     PathPlannerAuto selectedAuto = (PathPlannerAuto)selectedCommand;
  //   //  Pose2d transformedPose = mirrorPose2d(startingPose);
  //   }
     //return selectedCommand;

     return Commands.runOnce( () -> drive.zeroHeading())
        .andThen(new TestPath(drive))
        .andThen(new AutoTest(shooter, intake));
  }
}
