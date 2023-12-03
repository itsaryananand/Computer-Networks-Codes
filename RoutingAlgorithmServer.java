

import java.io.*;
        import java.net.*;

public class RoutingAlgorithmServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server waiting for connection...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");


            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);


            String[][] routingTable = {
                    {"192.168.1.1", "192.168.1.10"},
                    {"192.168.1.2", "192.168.1.20"},

            };

            while (true) {

                String clientIP = bufferedReader.readLine();
                System.out.println("Received IP: " + clientIP);


                String nextHop = getNextHop(routingTable, clientIP);


                printWriter.println(nextHop);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getNextHop(String[][] routingTable, String clientIP) {
        for (String[] entry : routingTable) {
            if (entry[0].equals(clientIP)) {
                return entry[1];
            }
        }
        return "No route found";
    }
}
