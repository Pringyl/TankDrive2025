// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import frc.robot.Constants.DriveConstants;



/**
 * The methods in this class are called automatically corresponding to each mode, as described in
 * the TimedRobot documentation. If you change the name of this class or the package after creating
 * this project, you must also update the Main.java file in the project.
 */
public class Robot extends TimedRobot {
  
  private final static WPI_VictorSPX driveBackLeftMotor = new WPI_VictorSPX(DriveConstants.kBackLeftMotorPort);
  private final static WPI_VictorSPX driveTopLeftMotor = new WPI_VictorSPX(DriveConstants.kTopLeftMotorPort);
  private final static WPI_VictorSPX driveBackRightMotor = new WPI_VictorSPX(DriveConstants.kBackRightMotorPort);
  private final static WPI_VictorSPX driveTopRightMotor = new WPI_VictorSPX(DriveConstants.kTopRightMotorPort);

  private final DifferentialDrive m_robotDrive;

  private final PS4Controller driveStick = new PS4Controller(0);


  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  public Robot() {
  
    driveBackRightMotor.setInverted(true);
    driveTopRightMotor.setInverted(true);
    driveBackLeftMotor.follow(driveTopLeftMotor);
    driveBackRightMotor.follow(driveTopRightMotor);
    

    m_robotDrive = new DifferentialDrive(driveTopLeftMotor,driveTopRightMotor);
    m_robotDrive.arcadeDrive(-driveStick.getLeftY(),-driveStick.getRightX());

  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {}

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
   
  private double startTime;

  @Override
  public void autonomousInit() {
    startTime = Timer.getFPGATimestamp();
  }


  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    double time = Timer.getFPGATimestamp();

    if (time - startTime < 5) {
      driveBackLeftMotor.set(0.5);
      driveTopLeftMotor.set(0.5);
      driveBackRightMotor.set(0.5);
      driveTopRightMotor.set(0.5);
    } else {
      driveBackLeftMotor.set(0);
      driveTopLeftMotor.set(0);
      driveBackRightMotor.set(0);
      driveTopRightMotor.set(0);
    }

  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    driveBackLeftMotor.follow(driveTopLeftMotor);
    driveBackRightMotor.follow(driveTopRightMotor);
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    if (driveStick.getSquareButton()) {
      
    }


    m_robotDrive.arcadeDrive(-driveStick.getLeftY(),-driveStick.getRightX());
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
