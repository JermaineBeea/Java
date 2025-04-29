package RobotPackage;
public class Robot extends Position {
    
    private String name;

    Robot(int xPos, int yPos){
        super(xPos, yPos);
    }

    public Robot(){
        this(0,0);
    }
}
