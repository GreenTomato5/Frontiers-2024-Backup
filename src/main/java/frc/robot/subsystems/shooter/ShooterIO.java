package frc.robot.subsystems.shooter;

import org.littletonrobotics.junction.AutoLog;
import org.littletonrobotics.junction.AutoLogOutput;

public interface ShooterIO {
	@AutoLog
	public static class ShooterIOInputs {

		public double shooterSpeed = 0.0;
		public double shooterSpeedPoint = 0.0;
		public double shooterCurrent = 0.0;
	}

	public static class ShooterIOOutputs {

		@AutoLogOutput
		public double shooterAppliedVolts = 0.0;
	}

	public default void updateInputs(ShooterIOInputs inputs) {}

	public default void updateOutputs(ShooterIOOutputs outputs) {}

	public default void setSpeed(double rps) {}

	public default void stop() {}

	public default boolean nearSpeedPoint() {
		return false;
	}
}
