    import java.net.*;
import java.io.*;

public class ClientThread{
    public static void main(String args[]){
        try{
            Socket socket=new Socket("localhost",81);
            BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out=new PrintWriter(socket.getOutputStream(),true);

            Thread thread=new Thread(()->{
                try{
                    String message;
                    while((message=in.readLine())!=null)
                        System.out.println("Server: "+message);
                } catch(IOException e){
                    e.printStackTrace();
                }
            });
            thread.start();

            BufferedReader consoleIn=new BufferedReader(new InputStreamReader(System.in));
            String userInput;
            while((userInput=consoleIn.readLine())!=null)
                out.println(userInput);

            socket.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}