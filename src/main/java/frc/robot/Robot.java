package frc.robot;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.SwerveJoystickCmd;
import frc.robot.commands.IntakeBaseCmd;
import frc.robot.commands.LowShootCmd;
import frc.robot.commands.PneumaticCmd;
import frc.robot.commands.ReverseIntakeCmd;

//FOR THE FIRST MEETING AFTER KICKOFF:: 
//write method to read the cancoder values in SwerveDrive, use in robotinit to display to smartdashboard, 
//callibrate values for cancodes for each of the modules somehow (idk average?) find reset values in test, 
//set that value as an automatic reset in robotinit

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the
 * name of this class or
 * the package after creating this project, you must also update the manifest
 * file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
  Rotation2d desRot = new Rotation2d(0);
  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;
  boolean reverseIntake = false;
  boolean lowShot = false;
  boolean indexToShoot = false;
  boolean pneumaticPivot = false;
  ReverseIntakeCmd reverseIntakeCmd = new ReverseIntakeCmd(RobotContainer.intake, true);
  LowShootCmd lowShootCmd = new LowShootCmd(RobotContainer.shooter, true);
  PneumaticCmd pneumaticsCmd = new PneumaticCmd(RobotContainer.pneumatic);
  IntakeBaseCmd intakeBaseCmd = new IntakeBaseCmd(RobotContainer.intake, true);

  /**
   * This function is run when the robot is first started up and should be used
   * for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    CommandScheduler.getInstance().setDefaultCommand(RobotContainer.drive, new SwerveJoystickCmd(RobotContainer.drive,
    RobotContainer.baseController::getLeftX, RobotContainer.baseController::getLeftY, RobotContainer.baseController::getR2Axis,
    RobotContainer.baseController.triangle()::getAsBoolean));

    m_robotContainer = new RobotContainer();
  }

  @Override
  public void robotPeriodic() {
      CommandScheduler.getInstance().run();
      //SmartDashboard.putNumber("RightJoystickX", RobotContainer.baseController.getR2Axis());



    }
  /** This function is run once each time the robot enters autonomous mode. */
  @Override
  public void autonomousInit() {
    m_autonomousCommand =  m_robotContainer.getAutonomousCommand();
    if (m_autonomousCommand != null) {
      SmartDashboard.putData(m_autonomousCommand);
      m_autonomousCommand.schedule();
    }
    //m_robotContainer.getAutonomousCommand().schedule();

  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {

  }

  /**
   * This function is called once each time the robot enters teleoperated mode.
   */
  @Override
  public void teleopInit() {
  
  }

  /** This function is called periodically during teleoperated mode. */
  @Override
  public void teleopPeriodic() {
    // //base right trigger (pneumatics)
    // if(!pneumaticPivot && RobotContainer.baseController.getRightX() > 0.1){
    //   CommandScheduler.getInstance().schedule(pneumaticsCmd);
    //   pneumaticPivot = true;
    // } else {
    //   CommandScheduler.getInstance().cancel(pneumaticsCmd);
    //   RobotContainer.pneumatic.toggle();
    //   pneumaticPivot = false;
    // }

    //base left trigger (intaking when co cannot)

   }

  /** This function is called once each time the robot enters test mode. */
  @Override
  public void testInit() {
    
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {

  }
}