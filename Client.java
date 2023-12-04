import java.io.*;
import java.net.*;


public class Client{
    public static void main(String args[]){
        try{
            Socket socket=new Socket("localhost",4000);
            PrintWriter out=new PrintWriter(socket.getOutputStream(), true);
            BufferedReader consoleIn=new BufferedReader(new InputStreamReader(System.in));

            String userInput;
            while((userInput=consoleIn.readLine())!=null)
                out.println(userInput);
            
            socket.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}