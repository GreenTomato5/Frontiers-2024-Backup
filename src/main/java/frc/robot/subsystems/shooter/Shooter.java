package frc.robot.subsystems.shooter;

import frc.robot.Constants;
import frc.robot.subsystems.Subsystem;
import frc.robot.subsystems.manager.ManagerStates;
import frc.robot.subsystems.shooter.ShooterIO.ShooterIOOutputs;
import org.littletonrobotics.junction.Logger;

public class Shooter extends Subsystem<ShooterStates> {

	private ShooterIO io;
	ShooterIOInputsAutoLogged inputs;
	private ShooterIOOutputs outputs;

	public Shooter(ShooterIO io) {
		super("Shooter", ShooterStates.OFF);
		this.io = io;
		inputs = new ShooterIOInputsAutoLogged();
		outputs = new ShooterIOOutputs();

		switch (Constants.currentMode) {
			case REAL:
				break;
			case REPLAY:
				break;
			case SIM:
				break;
			default:
				break;
		}
	}

	@Override
	protected void runState() {
		io.setSpeed(getState().getMotorSpeedpoint());
	}

    // IDK if this is needed
	public boolean nearSpeedPoint() {
		return io.nearSpeedPoint();
	}

	public void stop() {
		io.stop();
	}

	@Override
	public void periodic() {
		super.periodic();
		io.updateInputs(inputs);
		io.updateOutputs(outputs);
		Logger.processInputs("Shooter", inputs);
	}
}
