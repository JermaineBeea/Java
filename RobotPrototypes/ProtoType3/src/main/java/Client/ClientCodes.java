package Client;

/**
 * Defines status codes used in client-server communication from the client perspective.
 */
public enum ClientCodes {
    STATUS_OK(200),         // Successful operation
    STATUS_EXCEPTION(400),  // Client-side error or validation issue 
    STATUS_ERROR(500),     // Server-side error
    HANDSHAKE(700);

    public final int code;
    
    ClientCodes(int code) {
        this.code = code;
    }
    
    /**
     * Gets the ClientCodes enum value that corresponds to the given code
     * @param code The integer status code
     * @return The corresponding ClientCodes enum value, or null if not found
     */
    public static ClientCodes fromCode(int code) {
        for (ClientCodes status : ClientCodes.values()) {
            if (status.code == code) {
                return status;
            }
        }
        return null;
    }
}