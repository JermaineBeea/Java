package za.co.wethinkcode.robots.Server;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.logging.Logger;
import za.co.wethinkcode.robots.Utility.LogConfiguration;
import za.co.wethinkcode.robots.RobotModules.Robot;
import za.co.wethinkcode.robots.RobotModules.Position;
import za.co.wethinkcode.robots.RobotModules.Direction;

/**
 * Manages the robot world including positions, obstacles, and combat mechanics
 */
public class World {
    private static final LogConfiguration logConfig = new LogConfiguration(World.class.getName());
    private static final Logger logger = logConfig.getLogger();
    
    // World boundaries
    private final int MAX_X = 100;
    private final int MIN_X = -100;
    private final int MAX_Y = 100;
    private final int MIN_Y = -100;
    
    // Robot management
    private final Map<String, Robot> robots = new HashMap<>();
    private final Set<Position> obstacles = new HashSet<>();
    
    /**
     * Launches a robot into the world at default position (0,0) facing NORTH
     */
    public boolean launchRobot(Robot robot) {
        if (robot == null || robot.getName().isEmpty()) {
            logger.warning("Cannot launch robot: robot is null or has no name");
            return false;
        }
        
        Position startPosition = new Position(0, 0, Direction.NORTH);
        
        // Check if position is occupied
        if (isPositionOccupied(startPosition)) {
            logger.warning("Cannot launch robot " + robot.getName() + ": position (0,0) is occupied");
            return false;
        }
        
        robots.put(robot.getName(), robot);
        robot.getPosition().setX(0);
        robot.getPosition().setY(0);
        robot.getPosition().setDirection(Direction.NORTH);
        
        logger.info("Robot " + robot.getName() + " launched successfully at (0,0)");
        return true;
    }
    
    /**
     * Removes a robot from the world
     */
    public boolean removeRobot(String robotName) {
        Robot removed = robots.remove(robotName);
        if (removed != null) {
            logger.info("Robot " + robotName + " removed from world");
            return true;
        }
        return false;
    }
    
    /**
     * Gets a robot by name
     */
    public Robot getRobot(String name) {
        return robots.get(name);
    }
    
    /**
     * Gets all robots in the world
     */
    public Set<Robot> getAllRobots() {
        return new HashSet<>(robots.values());
    }
    
    /**
     * Checks if a position is within world boundaries
     */
    public boolean isValidPosition(Position position) {
        return position.getX() >= MIN_X && position.getX() <= MAX_X &&
               position.getY() >= MIN_Y && position.getY() <= MAX_Y;
    }
    
    /**
     * Checks if a position is occupied by another robot or obstacle
     */
    public boolean isPositionOccupied(Position position) {
        // Check for obstacles
        if (obstacles.contains(position)) {
            return true;
        }
        
        // Check for other robots
        for (Robot robot : robots.values()) {
            Position robotPos = robot.getPosition();
            if (robotPos.getX() == position.getX() && robotPos.getY() == position.getY()) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Validates if a robot can move to a new position
     */
    public boolean canMoveTo(Robot robot, Position newPosition) {
        if (!isValidPosition(newPosition)) {
            logger.info("Robot " + robot.getName() + " cannot move: position out of bounds");
            return false;
        }
        
        if (isPositionOccupied(newPosition)) {
            logger.info("Robot " + robot.getName() + " cannot move: position occupied");
            return false;
        }
        
        return true;
    }
    
    /**
     * Fire mechanic - robot shoots in its current direction
     */
    public String fire(Robot shooter) {
        if (shooter.getMaxShots() <= 0) {
            return "No ammunition remaining";
        }
        
        // Calculate target position based on robot's direction
        Position shooterPos = shooter.getPosition();
        Direction dir = shooterPos.getDirection();
        
        Position targetPos = new Position(
            shooterPos.getX() + dir.getXunitChange(),
            shooterPos.getY() + dir.getYunitChange(),
            dir
        );
        
        // Check if target position is valid
        if (!isValidPosition(targetPos)) {
            shooter.shoot(); // Still consume ammo
            return "Shot fired into the void";
        }
        
        // Check for hit
        for (Robot target : robots.values()) {
            if (!target.equals(shooter)) {
                Position targetRobotPos = target.getPosition();
                if (targetRobotPos.getX() == targetPos.getX() && 
                    targetRobotPos.getY() == targetPos.getY()) {
                    
                    // Hit! Reduce target durability
                    target.takeDamage(20);
                    shooter.shoot();
                    
                    String result = shooter.getName() + " hit " + target.getName() + "!";
                    if (target.getDurability() <= 0) {
                        removeRobot(target.getName());
                        result += " " + target.getName() + " is destroyed!";
                    }
                    
                    logger.info(result);
                    return result;
                }
            }
        }
        
        // Miss
        shooter.shoot();
        return shooter.getName() + " missed";
    }
    
    /**
     * Reload mechanic - robot restores ammunition
     */
    public String reload(Robot robot) {
        if (robot.getFuelAmount() < 20) {
            return "Not enough fuel to reload (requires 20 fuel)";
        }
        
        robot.setFuelAmount(robot.getFuelAmount() - 20);
        robot.reload();
        
        String result = robot.getName() + " reloaded successfully";
        logger.info(result);
        return result;
    }
    
    /**
     * Adds an obstacle to the world
     */
    public void addObstacle(int x, int y) {
        obstacles.add(new Position(x, y, Direction.NORTH));
    }
    
    /**
     * Gets world status as a string
     */
    public String getStatus() {
        StringBuilder status = new StringBuilder();
        status.append("World Status:\n");
        status.append("Robots: ").append(robots.size()).append("\n");
        status.append("Obstacles: ").append(obstacles.size()).append("\n");
        
        for (Robot robot : robots.values()) {
            Position pos = robot.getPosition();
            status.append("- ").append(robot.getName())
                  .append(" at (").append(pos.getX()).append(",").append(pos.getY()).append(") ")
                  .append("facing ").append(pos.getDirection())
                  .append(" [Fuel: ").append(robot.getFuelAmount())
                  .append(", Health: ").append(robot.getDurability())
                  .append(", Ammo: ").append(robot.getMaxShots()).append("]\n");
        }
        
        return status.toString();
    }
}