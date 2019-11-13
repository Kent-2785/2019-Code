/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private DifferentialDrive m_myRobot;
  private Joystick m_Stick;
  private DigitalInput limitSwitch;
  private SpeedControllerGroup m_right;
  private SpeedControllerGroup m_left;
  private SpeedControllerGroup arm;
  private SpeedController angle;
  private SpeedController back;
  private boolean rpressed;
  private boolean rotherPressed;
  private boolean lpressed;
  private boolean lotherPressed;
  private boolean oneTimeUse;
  private int count;
  private Timer time;
  private Timer otherTime;

  @Override
  public void robotInit() {
    m_left = new SpeedControllerGroup(new Spark(2),new Spark(3));
    m_right = new SpeedControllerGroup(new Spark(0),new Spark(1));
    arm = new SpeedControllerGroup(new Spark(4),new Spark(5));
    angle = new Spark(6);
    back = new Spark(7);
    m_myRobot = new DifferentialDrive(m_left,m_right);
    m_Stick = new Joystick(2);
    time = new Timer();
    otherTime = new Timer();
    // time.reset();
    // time.stop();
    // otherTime.reset();
    // otherTime.stop();
    CameraServer.getInstance().startAutomaticCapture(0);
    CameraServer.getInstance().startAutomaticCapture(1);
    rpressed = false;
    rotherPressed = false;
    lpressed = false;
    lotherPressed = false;
    oneTimeUse = true;
    count = 0;
  }

  public void autonomousPeriodic(){
    teleopPeriodic();
  }

  @Override
  public void teleopPeriodic() {
    if(m_Stick.getRawButton(7)) {
      //positive angle is going forward
      angle.set(0.5);
      Timer.delay(0.5);
      angle.set(0);
    }
    if(m_Stick.getRawButton(5))
    {
      angle.set(-0.5);
      Timer.delay(0.5);
      angle.set(0);
    }
    if(m_Stick.getRawButton(1) && !rotherPressed) {
      rotherPressed = true;
      back.set(-1);
      // otherTime.reset();
      // otherTime.start();
    }
    else if(m_Stick.getRawButton(2) && !lotherPressed) {
      lotherPressed = true;
      back.set(1);
      // otherTime.reset();
      // otherTime.start();
    }
    // else if(otherTime.get() >= 1) {
    //   back.set(0);
    //   otherPressed = false;
    //   otherTime.stop();
    // }
    else if(rotherPressed && !m_Stick.getRawButton(1)) {
      back.set(0);
      rotherPressed = false;
    }
    else if(lotherPressed && !m_Stick.getRawButton(2)) {
      back.set(0);
      lotherPressed = false;
    }
    if(m_Stick.getRawButton(3)) {
      m_myRobot.tankDrive(-m_Stick.getY(), -m_Stick.getY());
    }
    else {
      m_myRobot.tankDrive(-m_Stick.getY()*0.8, -m_Stick.getThrottle()*0.8);
    }
    if(m_Stick.getRawButton(8) && !rpressed) {
      rpressed = true;
      arm.set(1);
      // time.reset();
      // time.start();
    }
    else if(m_Stick.getRawButton(6) && !lpressed) {
      lpressed = true;
      arm.set(-1);
      // time.reset();
      // time.start();
    }
    else if(rpressed && !m_Stick.getRawButton(8)) {
      arm.set(0);
      rpressed = false;
    }
    else if(lpressed && !m_Stick.getRawButton(6)) {
      arm.set(0);
      lpressed = false;
    }
    // else if(time.get() >= 1) {
    //   arm.set(0);
    //   pressed = false;
    //   time.stop();
    // }
  }
}
