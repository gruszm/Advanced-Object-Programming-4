package lab4;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServerMT {
    public static void main(String args[]) throws IOException {
        ServerSocket ss = new ServerSocket(7);
        while (true) {
            System.out.println("Czekam na klienta");
            Socket s1 = ss.accept();
            Socket s2 = ss.accept();
            new ServerThread(s1, s2);
        }
    }
}