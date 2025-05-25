package za.co.wethinkcode.robots.world;

import za.co.wethinkcode.robots.RobotModules.Position;

public class SquareObstacle implements Obstacle{
    private final int x;
    private final int y;
    private final int size = 5;

    public SquareObstacle(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public int getBottomLeftX() { return x; }
    @Override
    public int getBottomLeftY() { return y; }
    @Override
    public int getSize(){ return size; }

    @Override
    public boolean blocksPosition(Position position) {
        //Checking if position is within the obstacle's boundaries
        return position.getX() >= x && position.getX() <= x + size -1 &&
                position.getY() >= y && position.getY() <= y + size -1;
    }

    @Override
    public boolean blocksPath(Position a, Position b) {
        //vertical move on x
        if(a.getX() == b.getX()) {
            return a.getX() >= x && a.getX() <= x + size -1 &&
                    ((a.getY() <= y + size -1 && b.getY() >= y) ||
                            (b.getY() <= y + size -1 && a.getY() >= y));
        }
        //horizontal move on y
        else if(a.getY() == b.getY()) {
            return a.getY() >= y && a.getY() <= y + size -1 &&
                    ((a.getX() <= x + size -1 && b.getX() >= x) ||
                            (b.getX() <= x + size -1 && a.getX() >= x));
        }
        return false;
    }
}
