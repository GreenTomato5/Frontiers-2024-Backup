package frc.robot;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.XboxController;

public final class Constants {
    public static enum Mode {
        SIM, REAL, REPLAY
    }
    public static final Mode currentMode = Mode.REAL;

    public static final XboxController controller = new XboxController(0);

    public static final double DIAM_TO_RADIUS_CF = 2.0;

    public static final class Drive {
        public static final double CONTROLLER_DEADBAND = 0.1;
        public static final double DISCRETIZE_TIME_SECONDS = 0.02;

        // ???
        public static final double WHEEL_RADIUS = Units.inchesToMeters(2.0);
		public static final double MAX_LINEAR_SPEED = Units.feetToMeters(14.5);
		public static final double TRACK_WIDTH_X = Units.inchesToMeters(25.0);
		public static final double TRACK_WIDTH_Y = Units.inchesToMeters(25.0);
		public static final double DRIVE_BASE_RADIUS = Math.hypot(
			TRACK_WIDTH_X / 2.0,
			TRACK_WIDTH_Y / 2.0
		);
		public static final double MAX_ANGULAR_SPEED = MAX_LINEAR_SPEED / DRIVE_BASE_RADIUS;

        // CAN/Offsets
        public static final int TURN_ID_1 = 1;
        public static final int DRIVE_ID_1 = 2;
        public static final int CANCODER_ID_1 = 3;
        public static final double TURN_OFFSET_1 = 0.0;

        public static final int TURN_ID_2 = 4;
        public static final int DRIVE_ID_2 = 5;
        public static final int CANCODER_ID_2 = 6;
        public static final double TURN_OFFSET_2 = 0.0;

        public static final int TURN_ID_3 = 7;
        public static final int DRIVE_ID_3 = 8;
        public static final int CANCODER_ID_3 = 9;
        public static final double TURN_OFFSET_3 = 0.0;

        public static final int TURN_ID_4 = 10;
        public static final int DRIVE_ID_4 = 11;
        public static final int CANCODER_ID_4 = 12;
        public static final double TURN_OFFSET_4 = 0.0;
    }

    public static final class Shooter {

    }
}
