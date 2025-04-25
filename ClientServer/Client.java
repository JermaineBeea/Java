package ClientServer;

import java.net.*;
import java.io.*;

public class Client{
    public static void main(String[] args) {
        try
        (
            Socket server = new Socket("localhost", 3000);
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(server.getInputStream()));
            PrintStream toServer = new PrintStream(server.getOutputStream())
        ){
            System.out.println(fromServer.readLine());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}