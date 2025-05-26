package za.co.wethinkcode.robots.Server;

/**
 * Defines status codes used in client-server communication from the server perspective.
 */
public enum ServerCodes {
    STATUS_OK(200),         // Successful operation
    STATUS_EXCEPTION(400),  // Client-side error or validation issue
    STATUS_ERROR(500),      // Server-side error
    HANDSHAKE(700);
    
    public final int code;
    
    ServerCodes(int code) {
        this.code = code;
    }
    
    /**
     * Gets the ServerCodes enum value that corresponds to the given code
     * @param code The integer status code
     * @return The corresponding ServerCodes enum value, or null if not found
     */
    public static ServerCodes fromCode(int code) {
        for (ServerCodes status : ServerCodes.values()) {
            if (status.code == code) {
                return status;
            }
        }
        return null;
    }
}