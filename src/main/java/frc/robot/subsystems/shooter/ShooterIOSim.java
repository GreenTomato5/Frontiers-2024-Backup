package frc.robot.subsystems.shooter;

import edu.wpi.first.math.controller.BangBangController;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;

public class ShooterIOSim implements ShooterIO {

	private FlywheelSim sim;
	private BangBangController controller;
	private double speedPoint;
	private double appliedVolts;

	public ShooterIOSim() {
		sim = new FlywheelSim(
			DCMotor.getFalcon500(1),
			1,
            1
		);
		controller = new BangBangController();
		speedPoint = 0.0;
	}

	@Override
	public void updateInputs(ShooterIOInputs inputs) {
		sim.update(0.02);

		inputs.shooterSpeed = sim.getAngularVelocityRPM() / 60;
		inputs.shooterSpeedPoint = speedPoint;
	}

	@Override
	public void updateOutputs(ShooterIOOutputs outputs) {
		outputs.shooterAppliedVolts = appliedVolts;
	}

	@Override
	public void stop() {
		appliedVolts = 0;
		sim.setInputVoltage(appliedVolts);
	}

	@Override
	public void setSpeed(double rps) {
		speedPoint = rps;
		appliedVolts = controller.calculate(
			sim.getAngularVelocityRPM() / 60,
			rps
		);
		sim.setInputVoltage(appliedVolts);
	}

	@Override
	public boolean nearSpeedPoint() {
		return (
			Math.abs((sim.getAngularVelocityRPM() / 60) - speedPoint) >
            1.0
		);
	}
}
