
package org.usfirst.frc.team1710.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
    final String defaultAuto = "Default";
    final String customAuto = "My Auto";
    String autoSelected;
    SendableChooser chooser;
    boolean shooterVal = false;
    public void robotInit() {
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", customAuto);
        SmartDashboard.putData("Auto choices", chooser);
        RobotMap.intake = new Talon(1);
        RobotMap.spinners = new Talon(2);
        RobotMap.shooterL = new Talon(3);
        RobotMap.shooterR = new Talon(4);
        RobotMap.intakeSpinners = new Talon (10);
        RobotMap.driveTrain = new RobotDrive(5,6,7,8);
        RobotMap.driveStick = new Joystick(9);
    }
    
    public void autonomousInit() {
    	autoSelected = (String) chooser.getSelected();
//		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
    
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	switch(autoSelected) {
    	case customAuto:
        //Put custom auto code here   
            break;
    	case defaultAuto:
    	default:
    	//Put default auto code here
            break;
    	}
    }

    public void teleopPeriodic() {
    	double speed, turn, spinVal, intakeRaiseVal, intakespinVal;
    	speed = RobotMap.driveStick.getRawAxis(3);
    	turn = RobotMap.driveStick.getRawAxis(2);
    	spinVal = RobotMap.driveStick.getRawAxis(5);
    	intakeRaiseVal  = RobotMap.driveStick.getRawAxis(6);
    	intakespinVal = RobotMap.driveStick.getRawAxis(7);
    	shooterVal = RobotMap.driveStick.getRawButton(4);
    	if (shooterVal == true){
    		RobotMap.shooterL.set(1);
    		RobotMap.shooterR.set(-1);
    	}
    	else {
    		RobotMap.shooterL.set(0);
    		RobotMap.shooterR.set(0);
    	}
    	RobotMap.driveTrain.arcadeDrive(speed, turn);
    	RobotMap.spinners.set(spinVal);
    	RobotMap.intake.set(intakeRaiseVal);
    	RobotMap.intakeSpinners.set(intakespinVal);
    }
    

    public void testPeriodic() {
    
    }
    
}
