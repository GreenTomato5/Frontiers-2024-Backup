package frc.robot.subsystems.manager;

import frc.robot.Constants;
import frc.robot.subsystems.Subsystem;
import frc.robot.subsystems.drive.Drive;
import frc.robot.subsystems.drive.GyroIO;
import frc.robot.subsystems.drive.GyroIONavx2;
import frc.robot.subsystems.drive.ModuleIO;
import frc.robot.subsystems.drive.ModuleIOSim;
import frc.robot.subsystems.drive.ModuleIOSparkMax;

public class Manager extends Subsystem<ManagerStates> {
    Drive driveSubsystem;

    public Manager() {
        super("Manager", ManagerStates.IDLE);

        switch (Constants.currentMode) {
            case REAL:
                driveSubsystem = new Drive(
                        new GyroIONavx2(),
                        new ModuleIOSparkMax(0),
                        new ModuleIOSparkMax(1),
                        new ModuleIOSparkMax(2),
                        new ModuleIOSparkMax(3));
                break;
            case REPLAY:
                driveSubsystem = new Drive(
                        new GyroIO() {
                        },
                        new ModuleIO() {
                        },
                        new ModuleIO() {
                        },
                        new ModuleIO() {
                        },
                        new ModuleIO() {
                        });
                break;
            case SIM:
                driveSubsystem = new Drive(
                        new GyroIO() {
                        },
                        new ModuleIOSim(),
                        new ModuleIOSim(),
                        new ModuleIOSim(),
                        new ModuleIOSim());
                break;
            default:
                break;
        }
    }

    @Override
    public void runState() {
        driveSubsystem.periodic();
    }

    public void stop() {
        driveSubsystem.stop();
    }
}
