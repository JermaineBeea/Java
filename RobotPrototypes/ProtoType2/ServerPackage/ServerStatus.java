package ServerPackage;

public enum ServerStatus {
    STATUS_ERROR(300), STATUS_OK(200), HANDSHAKE_RESPONSE(700);

    int code;
    ServerStatus(int  statuscode){
        this.code = statuscode;
    }
}
