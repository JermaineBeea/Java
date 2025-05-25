package za.co.wethinkcode.robots.world;

import za.co.wethinkcode.robots.RobotModules.Direction;
import za.co.wethinkcode.robots.RobotModules.Position;
import za.co.wethinkcode.robots.maze.Maze;

import java.util.List;

public class TextWorld implements IWorld{
    private Position position;
    private IWorld.Direction currentDirection;
    private final Maze maze;
    private static final int WORLD_SIZE = 200;

    public TextWorld(Maze maze) {
        this.maze = maze;
        reset();
    }

    @Override
    public IWorld.UpdateResponse updatePosition(int steps){
        Position newPosition = calculateNewPosition(steps);

        if(!isWithinWorld(newPosition)) return IWorld.UpdateResponse.FAILED_OUTSIDE_WORLD;
        if(!isNewPositionAllowed(newPosition)) return IWorld.UpdateResponse.FAILED_OBSTRUCTED;

        this.position = newPosition;
        return IWorld.UpdateResponse.SUCCESS;
    }

    @Override
    public void updateDirection(boolean turnRight) {
        if(turnRight) {
            this.currentDirection = IWorld.Direction.values()[
                    (currentDirection.ordinal() + 1) % IWorld.Direction.values().length
                    ];
        }else {
            this.currentDirection = IWorld.Direction.values()[
                    (currentDirection.ordinal() - 1 + IWorld.Direction.values().length) % IWorld.Direction.values().length
                    ];
        }
    }


    @Override
    public Position getPosition() {
        return position;
    }
    @Override
    public void reset() {
        this.position = CENTRE;
        this.currentDirection = IWorld.Direction.UP;
    }

    @Override
    public IWorld.Direction getCurrentDirection() {
        return currentDirection;
    }
    @Override
    public boolean isNewPositionAllowed(Position position) {
        return isWithinWorld(position) && !maze.blocksPath(this.position, position);
    }
    @Override
    public boolean isAtEdge(){
        return Math.abs(position.getX()) == WORLD_SIZE || Math.abs(position.getY()) == WORLD_SIZE;
    }
    @Override
    public List<Obstacle> getObstacles() {
        return maze.getObstacles();
    }
    @Override
    public void showObstacles() {
        System.out.println("There are some obstacles:");
        for (Obstacle obstacle : getObstacles()) {
            System.out.printf("- At position %d, %d (size %d)%n",
                    obstacle.getBottomLeftX(),
                    obstacle.getBottomLeftY(),
                    obstacle.getSize()
            );
        }
    }
    public Position calculateNewPosition(int steps) {
        int newX = position.getX();
        int newY = position.getY();
        switch (currentDirection){
            case UP: return new Position(newX, newY + steps, za.co.wethinkcode.robots.RobotModules.Direction.NORTH);
            case RIGHT: return new Position(newX + steps, newY, za.co.wethinkcode.robots.RobotModules.Direction.EAST);
            case DOWN: return new Position(newX, newY - steps, za.co.wethinkcode.robots.RobotModules.Direction.SOUTH);
            case LEFT: return new Position(newX - steps, newY, za.co.wethinkcode.robots.RobotModules.Direction.WEST);
            default: return position;
        }
    }
    private boolean isWithinWorld(Position position) {
        return Math.abs(position.getX()) <= WORLD_SIZE && Math.abs(position.getY()) <= WORLD_SIZE;
    }
}

