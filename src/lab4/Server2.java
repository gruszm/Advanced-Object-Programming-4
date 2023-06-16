package lab4;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server2 {
    public static void main(String[] args){
        ServerSocket ss;
        while (true){
            try {
                ss = new ServerSocket(7);
                while(true){
                    System.out.println("Czekam na klienta");
                    Socket s = ss.accept();
                    System.out.println("Klient podłączony.\nDo widzenia");
                    s.close();
                }
            }
            catch(IOException e){
                System.err.println("Błąd łączności");
            }
        }
    }
}