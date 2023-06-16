package level4;

import java.io.*;
import java.net.*;

public class ChatClient {
    private BufferedReader keyboard;
    private PrintWriter out;
    private BufferedReader in;

    public void connect(String serverAddress) throws IOException {
        Socket socket = new Socket(serverAddress, 6000);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        keyboard = new BufferedReader(new InputStreamReader(System.in));
    }

    public void start() throws IOException {
        String line = in.readLine();
        if (line.startsWith("SUBMITNAME")) {
            System.out.println("Enter your name: ");
            String name = keyboard.readLine();
            out.println(name);
        }
        while (true) {
            String serverResponse = in.readLine();
            if (serverResponse.startsWith("NAMEACCEPTED")) {
                System.out.println("Name accepted. You can start chatting.");
                startChat();
            } else if (serverResponse.startsWith("MESSAGE")) {
                System.out.println(serverResponse.substring(8));
            }
        }
    }

    private void startChat() throws IOException {
        new Thread(() -> {
            while (true) {
                try {
                    String message = keyboard.readLine();
                    out.println(message);
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        }).start();
    }

    public static void main(String[] args) throws IOException {
        ChatClient client = new ChatClient();
        client.connect("localhost");
        client.start();
    }
}
