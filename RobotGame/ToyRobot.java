package RobotGame;

/**
* This class holds the logic of the Robot.
*/
public class ToyRobot{

    /**
     * Represents possible movement directions for the robot.
     */
    public enum Direction
    {
        LEFT(-1, 0), RIGHT(1, 0), UP(0, 1), DOWN(0, -1);

        private int xUnitXhange;
        private int yUnitChange;

        Direction(int xUnitXhange, int yUnitChange)
        {
            this.xUnitXhange = xUnitXhange;
            this.yUnitChange = yUnitChange;
        }

        public int getXunitChange()
        {
            return xUnitXhange;
        }

            public int getYunitChange()
        {
            return yUnitChange;
        }
    }

    /**
     * This defined the Robot of Robot and has the methiods for movement.
     * @param xPos The initial x posiiton.
     * @param yPos The initial y Robot.
     */
    public static class Robot
    {
        private int xPos;
        private int yPos;

        Robot(int xPos, int yPos)
        {
            this.xPos = xPos;
            this.yPos = yPos;
        }

        Robot()
        {
            this(0, 0);
        }

        public int getxPos() {
            return xPos;
        }

        public int getyPos() {
            return yPos;
        }

        public int[] getPos()
        {
            return new int[]{xPos, yPos};
        }
    
        /**
         * The movement method that changes the Robot of Robot.
         *
         * @param direction The direction of movement.
         * @param quantity Steps of movement.
         */
        public void Move(Direction direction, int quantity)
        {
            this.xPos += direction.xUnitXhange * quantity;
            this.yPos += direction.yUnitChange * quantity;
        }

        @Override
        public String toString()
        {
            return "(" + xPos + ", " + yPos + ")";
        }

    }
}

class ImplementToyRobot extends ToyRobot{
    public static void main(String[] args)
    {
        Robot robot1 = new Robot(2, 3);
        Robot robot2 = new Robot();

        robot1.Move(Direction.LEFT, 3);
        robot2.Move(Direction.UP, 5);

        System.out.println("The robots posiiton is " + robot1);
        System.out.println("The robots posiiton is " + robot2);
    }
}