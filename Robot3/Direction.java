public class Direction {

    public static void main(String[] args) {
        DirectionFacing direction = DirectionFacing.NORTH;
        double unitChange = Movement.FORWARD.getYUnitChange(direction.getCurrentOrientation());
        System.out.println(unitChange); // Outputs 1.0
    }

    enum DirectionFacing {
        NORTH(0.25), EAST(0.0), SOUTH(0.75), WEST(0.5);

        private final double currentOrientation;

        DirectionFacing(double currentOrientation) {
            this.currentOrientation = currentOrientation;
        }

        public double getCurrentOrientation() {
            return currentOrientation;
        }
    }

    enum Movement {
        FORWARD(0.0), BACKWARD(0.5), RIGHT(0.75), LEFT(0.25);

        private final double defaultOrientation;

        Movement(double defaultOrientation) {
            this.defaultOrientation = defaultOrientation;
        }

        public double getXUnitChange(double currentOrientation) {
            double radians = 2 * Math.PI * (defaultOrientation + currentOrientation);
            return Math.cos(radians);
        }

        public double getYUnitChange(double currentOrientation) {
            double radians = 2 * Math.PI * (defaultOrientation + currentOrientation);
            return Math.sin(radians);
        }
    }
}