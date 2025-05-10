package Utility;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import Server.ServerCodes;

public class HandShake {
    private static LogConfiguration logConfig = new LogConfiguration(HandShake.class.getName());
    private static Logger logger = logConfig.getLogger();
    
    private DataInputStream fromPartner;
    private DataOutputStream toPartner;
   
    public HandShake(Socket partnerSocket){
        try{
            this.fromPartner = new DataInputStream(partnerSocket.getInputStream());
            this.toPartner = new DataOutputStream(partnerSocket.getOutputStream());
        }catch(IOException e){
            logger.log(Level.SEVERE, "Connection error.", e);
            logConfig.printStack(e);
        }
    }

    public void sendHandshake() throws IOException{
        try{
            toPartner.writeInt(ServerCodes.HANDSHAKE.code);
            fromPartner.readInt(); // Expected to recieve same HandShake code.
        }catch(IOException e){
            logger.log(Level.SEVERE, "Error establishing handshake", e);
            logConfig.printStack(e);
            fromPartner.close();
            toPartner.close();
        }
    }

    public void recieveHandshake() throws IOException{
        try{
            int code = fromPartner.readInt();
            toPartner.writeInt(code); // Send same code that you recieved from partner.
        }catch(IOException e){
            logger.log(Level.SEVERE, "Error establishing handshake.", e);
            logConfig.printStack(e);
            fromPartner.close();
            toPartner.close();
        }
    }
    
}
