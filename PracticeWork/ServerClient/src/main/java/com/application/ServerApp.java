package com.application;

public class ServerApp {

    static String IP = "localhost";
    static int PORT = 5000;

    public static void main(String[] args) {

        if(args.length == 1){
            try {
                PORT = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                IP = args[0];
            }
        }else if(args.length == 2){
            try {
                IP = args[0];
                PORT = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.err.println("Invalid PORT argument. Exiting program...");
                System.exit(0);
            }
        }else if(args.length > 2){
            System.err.println("Invalid PORT and IP argument. Exiting program...");
        }

        System.out.println("Starting server on: " + IP + ":" + PORT);
        Server server = new Server(PORT, IP);
        server.runServer();
    }

}
