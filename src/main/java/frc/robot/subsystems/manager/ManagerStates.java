package frc.robot.subsystems.manager;

import frc.robot.subsystems.SubsystemStates;
import frc.robot.subsystems.shooter.ShooterStates;

public enum ManagerStates implements SubsystemStates {
    IDLE("IDLE", ShooterStates.OFF),
    INTAKING("INTAKING", ShooterStates.INTAKING),
    SHOOTING("SHOOTING", ShooterStates.SHOOTING),;

    ManagerStates(String stateString, ShooterStates shooterState) {
        this.stateString = stateString;
    }

    String stateString;
    ShooterStates shooterState;

    @Override
    public String getStateString() {
        return stateString;
    }        

    public ShooterStates getShooterState() {
        return shooterState;
    }
}
