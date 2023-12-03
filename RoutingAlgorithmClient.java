import java.io.*;
import java.net.*;

public class RoutingAlgorithmClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);
            System.out.println("Connected to server.");


            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);

            String clientIP = "192.168.1.1";
            System.out.println("Requesting next hop for IP: " + clientIP);


            printWriter.println(clientIP);


            String nextHop = bufferedReader.readLine();
            System.out.println("Next hop: " + nextHop);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}