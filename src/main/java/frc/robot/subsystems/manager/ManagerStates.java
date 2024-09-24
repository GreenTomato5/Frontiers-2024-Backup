package frc.robot.subsystems.manager;

import frc.robot.subsystems.SubsystemStates;

public enum ManagerStates implements SubsystemStates {
    IDLE("IDLE");
    
    ManagerStates(String stateString) {
        this.stateString = stateString;
    }

    String stateString;

    @Override
    public String getStateString() {
        return stateString;
    }        
}
