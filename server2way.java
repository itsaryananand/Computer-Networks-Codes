import java.io.*;
import java.net.*;

    public class server2way {
        public static void main(String[] args) {
            try {
                ServerSocket serverSocket = new ServerSocket(4000);
                System.out.println("Server started. Waiting for a client to connect...");

                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected.");


                BufferedReader clientIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


                PrintWriter serverOut = new PrintWriter(clientSocket.getOutputStream(), true);


                BufferedReader serverIn = new BufferedReader(new InputStreamReader(System.in));

                String clientMessage;
                String serverMessage;

                while (true) {

                    clientMessage = clientIn.readLine();
                    if (clientMessage != null) {
                        System.out.println("Client: " + clientMessage);
                    }
                    System.out.print("Server: ");
                    serverMessage = serverIn.readLine();
                    serverOut.println(serverMessage);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


