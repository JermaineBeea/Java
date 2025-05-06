package ClientPackage;

public class Position {
    
    private double xPos;
    private double yPos;
    private Direction direction;
    private int xUnitChange = direction.getXunitChange();
    private int yUnitChange = direction.getYunitChange();
    private int indexDirection = direction.getRotationIndex();

    // Reassignment methods.
    public void setX(double xArg){
        this.xPos = xArg;
    }

    public void setY(double yArg){
        this.yPos = yArg;
    }

    public void setDirection(Direction directionArg){
        this.direction = directionArg;
    }

    // Retrieval methods.
    public double getX(){
        return xPos;
    }

    public double getY(){
        return yPos;
    }

    public Direction getDirection(){
        return direction;
    }

        // Helper functions
        private void move(double distance, int xUnitChange, int yUnitChange){
            this.xPos += xUnitChange * distance;
            this.yPos += yUnitChange * distance;
        }
    
        private Direction getDirection(int index){
            return switch(index){
                case 0 -> Direction.EAST;
                case 1 -> Direction.SOUTH;
                case 2 -> Direction.WEST;
                case 3 -> Direction.NORTH;
                default -> null;
            };
        } 
        
        // Delta functions.
        public void rotateRight(int rotation){
            int newIndex = (indexDirection + rotation) % 4;
            this.setDirection(getDirection(newIndex));
        }
    
        public void rotateLeft(int rotation){
            int newIndex = (4 + indexDirection + rotation) % 4;
            this.setDirection(getDirection(newIndex));
        }
    
        public void forward(double distance){
            move(distance, xUnitChange, yUnitChange);
        }
    
        public void backward(double distance){
            move(distance, -xUnitChange, -yUnitChange);
        }
    
        public void right(double distance){
            move(distance, yUnitChange, -xUnitChange);
        }
    
        public void left(double distance){
            move(distance, -yUnitChange, xUnitChange);
        }
    
}
