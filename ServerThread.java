import java.io.*;
import java.net.*;

public class ServerThread {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(81);
            System.out.println("Server started. Waiting for a client to connect...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected.");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            Thread readThread = new Thread(() -> {
                try {
                    String message;
                    while ((message = in.readLine()) != null) {
                        System.out.println("Client: " + message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            readThread.start();

            BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in));
            String userInput;
            while ((userInput = consoleIn.readLine()) != null) {
                out.println(userInput);
            }

            serverSocket.close();
            clientSocket.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}