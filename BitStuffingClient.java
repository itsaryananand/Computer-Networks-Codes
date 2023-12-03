
import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.net.Socket;
        import java.util.ArrayList;
        import java.util.List;

public class BitStuffingClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);


            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String receivedStuffedMessage = in.readLine();
            System.out.println("Received Stuffed Message: " + receivedStuffedMessage);


            String destuffedMessage = bitDestuffing(receivedStuffedMessage);
            System.out.println("Destuffed Message: " + destuffedMessage);


            in.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static String bitDestuffing(String stuffedMessage) {
        int N = stuffedMessage.length();
        int[] arr = new int[N];
        List<Integer> destuffedMessage = new ArrayList<>();


        for (int i = 0; i < N; i++) {
            arr[i] = Character.getNumericValue(stuffedMessage.charAt(i));
        }


        int i = 0;
        int j = 0;


        int count = 1;


        while (i < N) {

            if (arr[i] == 1) {

                destuffedMessage.add(arr[i]);


                for (int k = i + 1; k < N && arr[k] == 1 && count < 5; k++) {
                    j++;
                    destuffedMessage.add(arr[k]);
                    count++;


                    if (count == 5) {
                        k++;
                    }
                    i = k;
                }
            } else {
                destuffedMessage.add(arr[i]);
            }
            i++;
            j++;
        }


        StringBuilder result = new StringBuilder(destuffedMessage.size());
        for (Integer value : destuffedMessage) {
            result.append(value);
        }

        return result.toString();
    }
}
