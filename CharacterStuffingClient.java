import java.io.*;
import java.net.*;

public class CharacterStuffingClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Input data from the user
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter data to send to server: ");
            String inputData = userInput.readLine();

            out.println(inputData);

            String stuffedData = in.readLine();
            System.out.println("Received stuffed data from server: " + stuffedData);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
