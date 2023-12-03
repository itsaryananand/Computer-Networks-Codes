
import java.io.*;
        import java.net.ServerSocket;
        import java.net.Socket;
        import java.util.Scanner;

public class BitStuffingServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server waiting for client on port 12345");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected");


            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the message to be sent: ");
            String message = scanner.nextLine();


            String stuffedMessage = bitStuffing(message);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(stuffedMessage);


            out.close();
            socket.close();
            serverSocket.close();
            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Bit stuffing function
    private static String bitStuffing(String message) {
        StringBuilder stuffedMessage = new StringBuilder();
        int count = 0;

        for (char bit : message.toCharArray()) {
            stuffedMessage.append(bit);

            if (bit == '1') {
                count++;
            } else {
                count = 0;
            }

            // Insert '0' after five consecutive '1' bits
            if (count == 5) {
                stuffedMessage.append('0');
                count = 0; // Reset count after stuffing
            }
        }

        return stuffedMessage.toString();
    }
}
