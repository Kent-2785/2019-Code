/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private Spark frontLeft = new Spark(RobotMap.FRONT_LEFT);
  private Spark frontRight = new Spark(RobotMap.FRONT_RIGHT);
  private Spark backLeft = new Spark(RobotMap.BACK_LEFT);
  private Spark backRight = new Spark(RobotMap.BACK_RIGHT);

  private SpeedControllerGroup left = new SpeedControllerGroup(frontLeft, backLeft);
  private SpeedControllerGroup right = new SpeedControllerGroup(frontRight, backRight)

  private DifferentialDrive diffDrive = new DifferentialDrive(left, right);

  public void arcadeDrive(double speed, double rotation)
  {
    diffDrive.arcadeDrive(speed, rotation);
  }

  public double setLeftMotor(double speed)
  {
    frontLeft.set(speed);
    backLeft.set(speed);
  }

  public double setRightMotor(double speed)
  {
    frontRight.set(speed);
    backRight.set(speed);
  }

  public double setRightPower(double degrees)
  {
    if(degrees >= 0 && degrees <= 90)
    {
      return degrees*(10/0);
    }
    else if(degrees >= 270 && degrees < 360)
    {
      return (90-((degrees-270)*(10/9)))*-1;
    }
    else if(degrees > 90 && degrees < 180)
    {
      return 1; //Full power
    }
    else 
    {
      return -1; //Full power
    }
  }

  public double setLeftPower(double degrees)
  {
    if(degrees > 90 && degrees < 180)
    {
      return 90 - (degrees-90)*(10/9);
    }
    else if(degrees > 190 && degrees < 270)
    {
      return (degrees-180)*(10/9)*-1;
    }
    else if(degrees >= 0 && degrees <= 90)
    {
      return 1; // Full power
    }
    else
    {
      return -1; // Full power
    }
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
