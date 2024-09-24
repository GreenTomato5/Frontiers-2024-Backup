package frc.robot.subsystems.manager;

import frc.robot.Constants;
import frc.robot.subsystems.Subsystem;
import frc.robot.subsystems.drive.Drive;
import frc.robot.subsystems.drive.GyroIO;
import frc.robot.subsystems.drive.GyroIONavx2;
import frc.robot.subsystems.drive.ModuleIO;
import frc.robot.subsystems.drive.ModuleIOSim;
import frc.robot.subsystems.drive.ModuleIOSparkMax;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.subsystems.shooter.ShooterIO;
import frc.robot.subsystems.shooter.ShooterIOSim;
import frc.robot.subsystems.shooter.ShooterIOTalonFX;

public class Manager extends Subsystem<ManagerStates> {
    Drive driveSubsystem;
    Shooter shooterSubsystem;

    public Manager() {
        super("Manager", ManagerStates.IDLE);

        switch (Constants.currentMode) {
            case REAL:
                shooterSubsystem = new Shooter(new ShooterIOTalonFX());
                driveSubsystem = new Drive(
                        new GyroIONavx2(),
                        new ModuleIOSparkMax(0),
                        new ModuleIOSparkMax(1),
                        new ModuleIOSparkMax(2),
                        new ModuleIOSparkMax(3));
                break;
            case REPLAY:
                shooterSubsystem = new Shooter(new ShooterIO() {});
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
                shooterSubsystem = new Shooter(new ShooterIOSim());
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

        // Intaking
        addTrigger(ManagerStates.IDLE, ManagerStates.INTAKING, () -> Constants.controller.getBButtonPressed());
        addTrigger(ManagerStates.INTAKING, ManagerStates.IDLE, () -> Constants.controller.getBButtonPressed());

        // Shooting
        addTrigger(ManagerStates.IDLE, ManagerStates.SHOOTING, () -> Constants.controller.getAButtonPressed());
        addTrigger(ManagerStates.SHOOTING, ManagerStates.IDLE, () -> Constants.controller.getAButtonPressed());
    }

    @Override
    public void periodic() {
        super.periodic();
    }

    @Override
    public void runState() {
        driveSubsystem.periodic();
        shooterSubsystem.periodic();

        shooterSubsystem.setState(getState().getShooterState());
    }

    public void stop() {
        driveSubsystem.stop();
    }
}
