package PositionModules.Position1;

public class Implementation {
    public static void main(String[] args) {
        Position pos1 = new Position(0, 0);
        pos1.setDirection(Direction.NORTH);
        pos1.forward(2);
        pos1.setDirection(Direction.EAST);
        pos1.right(4);
        System.err.println(pos1.getDirection());
        System.out.println(pos1.getXPos());
        System.out.println(pos1.getYPos());
    }

}
