public class Direction{

    enum DirectionFacing {
        // Ratio to circumfrence for each direction.
        NORTH(0.25), EAST(0.0), SOUTH(0.75), WEST(0.5);
    
        private double currentRatio;
        Movement FORWARD;
        Movement BACKWARD;
        Movement RIGHT;
        Movement LEFT;

        DirectionFacing(double currentRatio){
            this.currentRatio = currentRatio;
            this.FORWARD.setXUnitChange(currentRatio);
            this.BACKWARD.setYUnitChange(currentRatio);
        }
    }
    
    enum Movement{
        // Mapped to ratio of circumfrence facing east.
        FORWARD(0.0), BACKWARD(0.5), RIGHT(0.75), LEFT(0.25);
    
        private double defaultRatio;
        private double xUnitChange;
        private double yUnitChange;
    
        Movement(double defaultRatio){
            this.defaultRatio = defaultRatio;
        }

        public void setXUnitChange(double currentRatio){
            double radians = 2 * Math.PI * (defaultRatio + currentRatio);
            xUnitChange = Math.cos(radians);
        }
         
        public void setYUnitChange(double currentRatio){
            double radians = 2 * Math.PI * (defaultRatio + currentRatio);
            yUnitChange = Math.sin(radians);
        }

        public double getxUnitChange() {
            return xUnitChange;
        }

        public double getyUnitChange() {
            return yUnitChange;
        }
    }
    
}


