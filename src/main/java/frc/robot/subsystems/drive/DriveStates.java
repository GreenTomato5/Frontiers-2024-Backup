package frc.robot.subsystems.drive;

import frc.robot.Constants;
import frc.robot.subsystems.SubsystemStates;

public enum DriveStates implements SubsystemStates {
	REGULAR_DRIVE("Regular Drive", 1, 1),
	SLOW_MODE("Slow Mode", 0.5, 0.5);

	DriveStates(String stateString, double translationModifier, double rotationModifier) {
		this.stateString = stateString;
		this.translationModifier = translationModifier;
		this.rotationModifier = rotationModifier;
	}

	String stateString;
	double translationModifier;
	double rotationModifier;

	@Override
	public String getStateString() {
		return stateString;
	}

	public double getTranslationModifier() {
		return translationModifier;
	}

	public double getRotationModifier() {
		return rotationModifier;
	}
}
