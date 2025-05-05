public class Robot {
    
    private String buildType;
    private final Position posInstance;

    public Robot(Position instance){
        this.posInstance = instance;
    }

    // Assignment methods.
    public void setBuildType(String buildTypeArg){
        this.buildType = buildTypeArg;
    }

    // Retrieval methods.
    public String getBuildType(){
        return buildType;
    }

    public Position position(){
        return posInstance;
    }

}
