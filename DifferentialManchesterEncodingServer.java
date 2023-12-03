import java.io.*;
import java.net.*;

public class DifferentialManchesterEncodingServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server waiting for connection...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");


            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            String message = "Hello, Differential Manchester encoding!";
            System.out.println("Original message: " + message);


            String encodedMessage = differentialManchesterEncode(message);
            System.out.println("Encoded message: " + encodedMessage);


            dataOutputStream.writeUTF(encodedMessage);

            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String differentialManchesterEncode(String message) {
        StringBuilder encodedMessage = new StringBuilder();
        boolean lastBit = false;

        for (char c : message.toCharArray()) {
            String binary = String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0');
            String encodedChar = "";

            for (char bit : binary.toCharArray()) {
                if (bit == '0') {
                    encodedChar += (lastBit ? "01" : "10");
                    lastBit = !lastBit;
                } else {
                    encodedChar += (lastBit ? "10" : "01");
                }
            }
            encodedMessage.append(encodedChar).append(" ");
        }
        return encodedMessage.toString();
    }
}