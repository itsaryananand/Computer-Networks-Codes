import java.io.*;
import java.net.*;

public class ManchesterServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server waiting for connection...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");

            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);


            String message = "Hello, Manchester encoding!";
            System.out.println("Original message: " + message);


            String encodedMessage = manchesterEncode(message);
            System.out.println("Encoded message: " + encodedMessage);


            dataOutputStream.writeUTF(encodedMessage);

            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String manchesterEncode(String message) {
        StringBuilder encodedMessage = new StringBuilder();
        for (char c : message.toCharArray()) {
            String binary = Integer.toBinaryString(c);
            String encodedChar = "";


            for (char bit : binary.toCharArray()) {
                if (bit == '0') {
                    encodedChar += "01";
                } else {
                    encodedChar += "10";
                }
            }
            encodedMessage.append(encodedChar).append(" ");
        }
        return encodedMessage.toString();
    }
}