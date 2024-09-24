package frc.robot.subsystems.shooter;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import frc.robot.Constants;

public class ShooterIOTalonFX implements ShooterIO {

	private TalonFX shooterMotor;
	private double speedPoint;
	private StatusSignal<Double> shooterCurrent;
	private StatusSignal<Double> shooterVelocity;
    private StatusSignal<Double> appliedVolts;
    private MotionMagicVoltage motionMagic;

	public ShooterIOTalonFX() {
        shooterMotor = new TalonFX(Constants.Shooter.SHOOTER_ID);

		shooterVelocity = shooterMotor.getVelocity();
        shooterCurrent = shooterMotor.getSupplyCurrent();
		speedPoint = 0.0;

		BaseStatusSignal.setUpdateFrequencyForAll(50, shooterVelocity, shooterCurrent, appliedVolts);


        // Motion Magic whatever
        motionMagic = new MotionMagicVoltage(0);

        // robot init
        var talonFXConfigs = new TalonFXConfiguration();

        // set slot 0 gains
        var slot0Configs = talonFXConfigs.Slot0;
        slot0Configs.kS = 0.24; // add 0.24 V to overcome friction
        slot0Configs.kV = 0.12; // apply 12 V for a target velocity of 100 rps

        // PID runs on position
        slot0Configs.kP = 4.8;
        slot0Configs.kI = 0;
        slot0Configs.kD = 0.1;

        // set Motion Magic settings, eh prolly right enough
        var motionMagicConfigs = talonFXConfigs.MotionMagic;
        motionMagicConfigs.MotionMagicCruiseVelocity = 80; // 80 rps cruise velocity
        motionMagicConfigs.MotionMagicAcceleration = 160; // 160 rps/s acceleration (0.5 seconds)
        motionMagicConfigs.MotionMagicJerk = 1600; // 1600 rps/s^2 jerk (0.1 seconds)

        shooterMotor.getConfigurator().apply(talonFXConfigs, 0.050);
        motionMagic.Slot = 0;
	}

	public void updateInputs(ShooterIOInputs inputs) {
		inputs.shooterSpeed = shooterVelocity.getValueAsDouble();
		inputs.shooterSpeedPoint = speedPoint;
		inputs.shooterCurrent = shooterCurrent.getValueAsDouble();
	}

	public void updateOutputs(ShooterIOOutputs outputs) {
		outputs.shooterAppliedVolts = appliedVolts.getValueAsDouble();
	}

	public void setSpeed(double rps) {
		if (rps == 0) return;

		speedPoint = rps;
        shooterMotor.setControl(motionMagic.withPosition(rps));
	}

	public void stop() {
		shooterMotor.stopMotor();
	}

	public boolean nearSpeedPoint() {
		return (
			Math.abs(speedPoint - shooterMotor.getVelocity().getValueAsDouble()) <
			1.0
		);
	}
}
