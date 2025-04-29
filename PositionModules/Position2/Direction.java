package PositionModules.Position2;

public enum Direction {
    NORTH(0.25), EAST(0.0), SOUTH(0.75), WEST(0.5);

    private double rotation;

    Direction(double rotation){
        this.rotation = rotation;
    }

    public double getRotation(){
        return rotation;
    }
}
