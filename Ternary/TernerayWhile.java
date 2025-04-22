public class TernaryWhile
{
    String outMessage;
    while (true) {
    outMessage = consoleIn.readLine(); // Read input (could be null)
    
    // Handle null or exit command
    if (outMessage == null || "exit".equalsIgnoreCase(outMessage.trim())) {
        break; // Exit loop if input is null or "exit"
    }
    
    toClient.println(outMessage); // Send message to client
    }

    String outMessage;
    while (!"exit".equalsIgnoreCase(
        (outMessage = consoleIn.readLine()) != null ? outMessage.trim() : ""
    )) {
        toClient.println(outMessage);
    }

}