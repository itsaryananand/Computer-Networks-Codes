import java.io.*;
import java.net.*;

public class client2way {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 4000);

            // Input stream to receive data from the server
            BufferedReader serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Output stream to send data to the server
            PrintWriter clientOut = new PrintWriter(socket.getOutputStream(), true);

            // Input stream to receive data from the client (console)
            BufferedReader clientIn = new BufferedReader(new InputStreamReader(System.in));

            String serverMessage;
            String clientMessage;

            while (true) {
                // Read and send server message
                serverMessage = serverIn.readLine();
                if (serverMessage != null) {
                    System.out.println("Server: " + serverMessage);
                }

                // Read and send client message
                System.out.print("Client: ");
                clientMessage = clientIn.readLine();
                clientOut.println(clientMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
