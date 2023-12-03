import java.io.*;
import java.net.*;

public class DifferentialManchesterEncodingClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);
            System.out.println("Connected to server.");

            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);

            String receivedMessage = dataInputStream.readUTF();
            System.out.println("Received encoded message: " + receivedMessage);


            String decodedMessage = differentialManchesterDecode(receivedMessage);
            System.out.println("Decoded message: " + decodedMessage);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String differentialManchesterDecode(String encodedMessage) {
        StringBuilder decodedMessage = new StringBuilder();
        boolean lastBit = false;

        String[] encodedChars = encodedMessage.split(" ");

        for (String encodedChar : encodedChars) {
            StringBuilder binary = new StringBuilder();
            for (int i = 0; i < encodedChar.length(); i += 2) {
                if (encodedChar.charAt(i) == '0' && encodedChar.charAt(i + 1) == '1') {
                    binary.append(lastBit ? '0' : '1');
                } else if (encodedChar.charAt(i) == '1' && encodedChar.charAt(i + 1) == '0') {
                    binary.append(lastBit ? '1' : '0');
                } else {

                    System.out.println("Invalid encoding detected.");
                }
                lastBit = !lastBit;
            }
            int charCode = Integer.parseInt(binary.toString(), 2);
            decodedMessage.append((char) charCode);
        }
        return decodedMessage.toString();
    }
}