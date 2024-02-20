package frc.robot;

import com.revrobotics.CANSparkFlex;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
//import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.IntakeCmd;
import frc.robot.commands.SwerveJoystickCmd;
//import frc.robot.commands.Autonomous.AutoTest;
import edu.wpi.first.wpilibj.PS4Controller;

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
  PS4Controller baseController = new PS4Controller(0);
  Rotation2d desRot = new Rotation2d(0);
  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;

  //temporary for testing purposes
  CANSparkMax topIntake = new CANSparkMax(Constants.topIntakeMotor, MotorType.kBrushless);
  CANSparkMax bottomIntake = new CANSparkMax(Constants.bottomIntakeMotor, MotorType.kBrushless);

  CANSparkMax topShooter = new CANSparkMax(Constants.topShooterMotor, MotorType.kBrushless);
  CANSparkMax bottomShooter = new CANSparkMax(Constants.bottomShooterMotor, MotorType.kBrushless);

  /**
   * This function is run when the robot is first started up and should be used
   * for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    new RobotContainer();
    CommandScheduler.getInstance().setDefaultCommand(RobotContainer.drive, new SwerveJoystickCmd(RobotContainer.drive,
    RobotContainer.controller::getLeftX, RobotContainer.controller::getLeftY, RobotContainer.controller::getR2Axis,
    RobotContainer.controller::getTriangleButtonPressed));

     m_robotContainer = new RobotContainer();
    
     //for actually intaking: charli gets the button, toggle on/off, when sensor triggered stop
     //for shooting: charli gets spin up flywheel, carter gets l2 to run intake to shoot
     CommandScheduler.schedule(new IntakeCmd(RobotContainer.intake, 
     RobotContainer.coDriveControl::getL2Button, RobotContainer.intake::getSensorInRange, 
     RobotContainer.coDriveControl::getL1ButtonPressed, RobotContainer.controller::getL2ButtonPressed));

  }

  @Override
  public void robotPeriodic() {
      CommandScheduler.getInstance().run();

      SmartDashboard.putNumber("RightJoystickX", RobotContainer.controller.getR2Axis());
      SmartDashboard.putNumber("LeftJoystickX", RobotContainer.controller.getLeftX());

      if(baseController.getL2Button()){
        //add your actual values between -1 and 1 depending on forward or reverse and whatnot
        topIntake.set(.6);
        bottomIntake.set(.6);
      } else{
        topIntake.set(0);
        bottomIntake.set(0);
      }

      if(baseController.getL2Button()){
        //add your actual values between -1 and 1 depending on forward or reverse and whatnot
        topShooter.set(0);
        bottomShooter.set(0);
      } else{
        topShooter.set(0);
        bottomShooter.set(0);
      }

    }
  /** This function is run once each time the robot enters autonomous mode. */
  @Override
  public void autonomousInit() {
    // m_timer.reset();
    // m_timer.start();
    m_autonomousCommand =  m_robotContainer.getAutonomousCommand();
    if (m_autonomousCommand != null) {
      SmartDashboard.putData(m_autonomousCommand);
      m_autonomousCommand.schedule();
    }
    m_robotContainer.getAutonomousCommand().schedule();

  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    // Drive for 2 seconds
    // if (m_timer.get() < 2.0) {
    // m_robotDrive.arcadeDrive(0.5, 0.0); // drive forwards half speed
    // } else {
    // m_robotDrive.stopMotor(); // stop robot
    // }

    // if (m_autonomousCommand != null)
    //   m_autonomousCommand.cancel();
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