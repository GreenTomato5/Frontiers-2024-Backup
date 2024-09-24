package frc.robot.subsystems.shooter;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.math.controller.BangBangController;
import frc.robot.Constants;

public class ShooterIOTalonFX implements ShooterIO {

	private TalonFX shooterMotor;
	private BangBangController feedbackController;
	private double speedPoint;
    private double appliedVolts;
	private StatusSignal<Double> shooterCurrent;
	private StatusSignal<Double> shooterVelocity;

	public ShooterIOTalonFX() {
		feedbackController = new BangBangController();
        shooterMotor = new TalonFX(Constants.Shooter.SHOOTER_ID);

		shooterVelocity = shooterMotor.getVelocity();
        shooterCurrent = shooterMotor.getSupplyCurrent();
		speedPoint = 0.0;

		BaseStatusSignal.setUpdateFrequencyForAll(50, shooterVelocity, shooterCurrent);
	}

	public void updateInputs(ShooterIOInputs inputs) {
		inputs.shooterSpeed = shooterVelocity.getValueAsDouble();
		inputs.shooterSpeedPoint = speedPoint;
		inputs.shooterCurrent = shooterCurrent.getValueAsDouble();
	}

	public void updateOutputs(ShooterIOOutputs outputs) {
		outputs.shooterAppliedVolts = appliedVolts;
	}

	public void setSpeed(double rps) {
		if (rps == 0) return;

		speedPoint = rps;
		appliedVolts = feedbackController.calculate(shooterVelocity.getValueAsDouble(), rps);
		shooterMotor.set(appliedVolts);
	}

	public void stop() {
		appliedVolts = 0.0;
		shooterMotor.stopMotor();
	}

	public boolean nearSpeedPoint() {
		return (
			Math.abs(speedPoint - shooterMotor.getVelocity().getValueAsDouble()) <
			1.0
		);
	}
}
