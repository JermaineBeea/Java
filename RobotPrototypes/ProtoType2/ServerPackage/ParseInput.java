package ServerPackage;

import java.util.Set;

public class ParseInput {
    
    private final String strInput;
    private String strCommand;
    private Double quantity;
    private final String DELIMETER = "\s+";
    private final Set<String> validCommands = Set.of(
    "leftturn", "rightturn","right", 
    "left","forward", "backward"
    );

    ParseInput(String input){
        this.strInput = input;
        this.splitInput();
    }

    public String getCommand(){
        return strCommand;
    }

    public Double getQuantity(){
        return quantity;
    }

    private boolean isValidCommand(){
        return validCommands.contains(this.strCommand);
    }

    private void splitInput(){
        String[] splitInput = this.strInput.split(DELIMETER);
        if(splitInput.length != 2){
            return;
        }

        this.strCommand = splitInput[0];

        if(!isValidCommand()){
            return;
        }

        String strQuantity = splitInput[1];
        try{
            this.quantity = Double.parseDouble(strQuantity);
        }catch(NumberFormatException e){
            return;
        }
    }
}
