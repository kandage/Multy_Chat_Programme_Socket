package Server_Side;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    private static Socket socket;
    private static ServerSocket serverSocket;

    public static void main(String[] args) {

        ArrayList<ServerThread> threadArrayList = new ArrayList<>();

        try (ServerSocket serverSocket = new ServerSocket(5000)) {

            System.out.println("Server Starting...Wating for The Clients..!");

            while (true) {
                Socket socket = serverSocket.accept();
                ServerThread serverThread = new ServerThread(socket, threadArrayList);

                threadArrayList.add(serverThread);
                serverThread.start();

            }
        } catch (IOException e) {
            System.out.println("Error occurred in main: " + e.getStackTrace());
        } finally {
            try {
                socket.close();
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}