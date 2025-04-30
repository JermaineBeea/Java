package RobotPackage;

public class Robot extends Position {
    
    private String robotname;

    Robot(int xPos, int yPos){
        super(xPos, yPos);
    }

    public Robot(){
        this(0,0);
    }
}
