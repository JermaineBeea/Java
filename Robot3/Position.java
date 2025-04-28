public class Position{

    enum DirectionFacing {
        // Ratio to circumfrence for each direction.
        NORTH(1/4), EAST(0), SOUTH(3/4), WEST(1/2);
    
        private double ratioCircumfrence;

        DirectionFacing(double ratioCircumfrence){
            this.ratioCircumfrence = ratioCircumfrence;
        }
    
        public double getRatio(){
            return ratioCircumfrence;
        }
    }
    
    enum Movement{
        // Mapped to ratio of circumfrence facing east.
        FORWARD(0), BACKWARD(1/2), RIGHT(3/4), LEFT(1/4);
        
        private double defaultRatio;
    
        Movement(double defaultRatio){
            this.defaultRatio = defaultRatio;
        }

        public double getdefaultRadians(){
            return defaultRatio;
        }
    }

    public void move(){
        
    }
    
}

