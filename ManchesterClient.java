import java.io.*;
import java.net.*;

public class ManchesterClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);
            System.out.println("Connected to server.");


            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);

            String receivedMessage = dataInputStream.readUTF();
            System.out.println("Received encoded message: " + receivedMessage);


            String decodedMessage = manchesterDecode(receivedMessage);
            System.out.println("Decoded message: " + decodedMessage);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String manchesterDecode(String encodedMessage) {
        StringBuilder decodedMessage = new StringBuilder();
        String[] encodedChars = encodedMessage.split(" ");
        for (String encodedChar : encodedChars) {
            StringBuilder binary = new StringBuilder();
            for (int i = 0; i < encodedChar.length(); i += 2) {
                if (encodedChar.charAt(i) == '0' && encodedChar.charAt(i + 1) == '1') {
                    binary.append('0');
                } else if (encodedChar.charAt(i) == '1' && encodedChar.charAt(i + 1) == '0') {
                    binary.append('1');
                } else {

                    System.out.println("Invalid encoding detected.");
                }
            }
            int charCode = Integer.parseInt(binary.toString(), 2);
            decodedMessage.append((char) charCode);
        }
        return decodedMessage.toString();
    }
}