import java.io.*;
import java.net.*;

public class CharacterStuffingServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server started. Waiting for client...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String receivedData = br.readLine();
            System.out.println("Received data from client: " + receivedData);

            String stuffedData = characterStuffing(receivedData);
            System.out.println("Stuffed data: " + stuffedData);

            out.println(stuffedData);

            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String characterStuffing(String data) {
        StringBuilder res = new StringBuilder("DLESTX");

        for (int i = 0; i < data.length(); i++) {
            char currentChar = data.charAt(i);
            if (currentChar == 'd' || currentChar == 'D' || currentChar == 'l' || currentChar == 'L' || currentChar == 'e' || currentChar == 'E') {
                res.append("ESC");
            }
            res.append(currentChar);
        }
        res.append("DLEETX");
        return res.toString();
    }
}
