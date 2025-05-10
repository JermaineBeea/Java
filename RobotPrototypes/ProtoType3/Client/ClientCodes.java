package Client;

public enum ClientCodes {
    STATUS_OK(300), 
    STATUS_ERROR(700),
    STATUS_EXCEPTION(1100),  // Added to match ServerCodes
    HANDSHAKE(2468);

    public int code;

    ClientCodes(int code){
        this.code = code;
    }
}