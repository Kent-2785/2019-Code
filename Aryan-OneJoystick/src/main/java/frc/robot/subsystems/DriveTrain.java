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
import com.ctre.pheonix.motorcontrol.can.TalonSRX;
import com.ctre.pheonix.motorcontrol.ControlMode;

/**
 * Add your docs here.
 */
  
  private TalonSRX fronLeft;
  private TalonSRX frontRight;
  private TalonSRX backLeft;
  private TalonSRX backRight;

  
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  frontLeft = new TalonSRX(RobotMap.FRONT_LEFT);
  frontRight = new TalonSRX(RobotMap.FRONT_RIGHT);
  backLeft = new TalonSRX(RobotMap.BACK_LEFT);
  backRight = new TalonSRX(RobotMap.BACK_RIGHT);

  

  public void setLeftMotor(double speed)
  {
    frontLeft.set(ControlMode.PercentOutput, speed);
    backLeft.set(ControlMode.PercentOutput, speed);
  }

  public void setRightMotor(double speed)
  {
    frontRight.set(ControlMode.PercentOutput, speed);
    backRight.set(ControlMode.PercentOutput, speed);
  }

  public double setRightPower(double degrees)
  {
    if(degrees >= 0 && degrees <= 90)
    {
      return degrees*(10/9)/(100);
    }
    else if(degrees >= 270 && degrees < 360)
    {
      return ((90-((degrees-270)*(10/9)))*-1)/100;
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
      return (90 - (degrees-90)*(10/9))/100;
    }
    else if(degrees > 190 && degrees < 270)
    {
      return ((degrees-180)*(10/9)*-1)/100;
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
