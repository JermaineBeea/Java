package za.co.wethinkcode.robots.maze;

import za.co.wethinkcode.robots.RobotModules.Position;
import za.co.wethinkcode.robots.world.Obstacle;
import za.co.wethinkcode.robots.world.SquareObstacle;

import java.util.ArrayList;
import java.util.List;

public class SimpleMaze implements Maze{
    private final List<Obstacle> obstacles;

    public SimpleMaze(){
        obstacles = new ArrayList<>();
        obstacles.add(new SquareObstacle(1,1));
    }
    @Override
    public List<Obstacle> getObstacles(){return obstacles;}

    @Override
    public boolean blocksPath(Position a, Position b){
        for(Obstacle obstacle : obstacles){
            if(obstacle.blocksPath(a, b)){
                return true;
            }
        }
        return false;
    }

    public void addObstacle(SquareObstacle newObstacle){
        obstacles.add(newObstacle);
    }
}
