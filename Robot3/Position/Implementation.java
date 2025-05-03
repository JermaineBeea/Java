package Position;

public class Implementation {
    
    public static void main(String[] args) {
        Position1 pos1 = new Position1(1, 2);
        pos1.setDirection(Direction1.SOUTH);
        pos1.moveBackward(4);
        System.out.println(pos1);

        Position3 pos3 = new Position3(1, 2);
        pos3.setDirection(Direction3.SOUTH);
        pos3.moveBackward(4);
        System.out.println(pos3);
    }
}
