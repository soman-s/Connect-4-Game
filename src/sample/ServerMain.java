package sample;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerMain {

    public static void main(String[] args) {
        //using serversocket as argument to automatically close the socket
        //the port number is unique for each server

        //list to add all the clients thread
        ArrayList<ServerThread> threadList = new ArrayList<>();
        int count = 0;
        try (ServerSocket serversocket = new ServerSocket(8080)){
            while(true) {
                Socket socket = serversocket.accept();
                if(count %2 == 0){
                    count+=1;
                    ServerThread serverThread = new ServerThread(socket, threadList,'X');
                    threadList.add(serverThread);
                    serverThread.start();
                }
                else{
                    count+=1;
                    ServerThread serverThread = new ServerThread(socket, threadList,'O');
                    threadList.add(serverThread);
                    serverThread.start();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}