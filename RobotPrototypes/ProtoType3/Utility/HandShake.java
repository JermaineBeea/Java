package Utility;

import java.util.logging.Level;
import java.util.logging.Logger;

import Server.ServerCodes;
import Server.ServerHandler;

import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class HandShake {
    private LogModule logMod = new LogModule(ServerHandler.class).launchLog(false, false);
    private Logger logger = logMod.getLogger();
    
    private DataInputStream fromPartner;
    private DataOutputStream toPartner;
   

    public HandShake(Socket partnerSocket){
        try{
            this.fromPartner = new DataInputStream(partnerSocket.getInputStream());
            this.toPartner = new DataOutputStream(partnerSocket.getOutputStream());
        }catch(IOException e){
            logger.log(Level.SEVERE, "Connection error.", e);
            logMod.printStackTrace(e);
        }
    }

    public void sendHandshake() throws IOException{
        try{
            toPartner.writeInt(ServerCodes.HANDSHAKE.code);
            fromPartner.readInt(); // Expected to recieve same HandShake code.
        }catch(IOException e){
            logger.log(Level.SEVERE, "Error establishing handshake", e);
            logMod.printStackTrace(e);
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
            logMod.printStackTrace(e);
            fromPartner.close();
            toPartner.close();
        }
    }
    
}
