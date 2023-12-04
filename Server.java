import java.net.*;
import java.io.*;

public class Server{
    public static void main(String args[]){
        try{
            ServerSocket serverSocket=new ServerSocket(4000);
            System.out.println("Server started. Waiting for a client to connect...");

            Socket clientSocket=serverSocket.accept();
            System.out.println("Client connected.");

            BufferedReader in=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String message;
            while((message=in.readLine())!=null)
                System.out.println("Client: "+message);

            serverSocket.close();
            clientSocket.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}