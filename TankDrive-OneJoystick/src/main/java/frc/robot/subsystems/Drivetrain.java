/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.TankDrive;
//import frc.robot.commands.DriveArcade;
/**
 * Add your docs here.
 * 
 * @param <Public>
 */
public class Drivetrain extends Subsystem {

    Spark leftFront;
    Spark leftBack;
    Spark rightFront;
    Spark rightBack;

    DifferentialDrive diffDrive;

    Encoder driveEncoder;

    public Drivetrain() {

        leftFront = new Spark(RobotMap.LEFT_FRONT);
        leftBack = new Spark(RobotMap.LEFT_BACK);
        rightFront = new Spark(RobotMap.RIGHT_FRONT);
        rightBack = new Spark(RobotMap.RIGHT_BACK);

        SpeedControllerGroup left = new SpeedControllerGroup(leftFront, leftBack);
        SpeedControllerGroup right = new SpeedControllerGroup(rightFront, rightBack);

        diffDrive = new DifferentialDrive(left, right);

        driveEncoder = new Encoder(1, 2);
    }
    
    public void drive(double left, double right)
    {
        diffDrive.tankDrive(left,right);
    }

    public void setLeftMotor(double speed)
    {
        leftFront.set(speed);
        leftBack.set(speed);
    }

    public void setRightMotor(double speed)
    {
        rightFront.set(-speed);
        rightBack.set(-speed);
    }

    public void initDefaultCommand()
    {
        setDefaultCommand(new TankDrive());
        
    }
}
