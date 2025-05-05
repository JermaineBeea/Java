public class Robot {
    
    private String buildType;
    private Commands robotCommands;

    public Robot(Position instance){
        robotCommands = new Commands(instance);
    }

    // Assignment methods.
    public void setBuildType(String buildTypeArg){
        this.buildType = buildTypeArg;
    }

    // Retrieval methods.
    public String getBuildType(){
        return buildType;
    }

    public Commands command(){
        return robotCommands;
    }

}
