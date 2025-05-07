package ServerPackage;

public enum ServerStatus {
    
    EXCEPTION(300), CONTINUE(200);

    int statusCode;

    ServerStatus(int  code){
        this.statusCode = code;
    }
}
