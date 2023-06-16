package lab4;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class EchoClientMT {
    public static void main(String args[]) throws IOException {
        Socket s2 = new Socket("localhost", 7);
        Socket s1 = new Socket("localhost", 7);
        listenForMessages(s2);
        Scanner sc_keyb = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(s1.getOutputStream(), true);
        String line_out = "";
        while (!line_out.contains("bye")) {
            System.out.print("Podaj tekst: ");
            line_out = sc_keyb.nextLine();
            pw.println(line_out);
        }
        s1.close();
    }

    public static void listenForMessages(Socket s2){
        new Thread(()->{
            try {
                System.out.println("Połączony wątek odbioru");
                Scanner sc_sock = new Scanner(s2.getInputStream());
                String line_in = "";
                while (!line_in.contains("bye")) {
                    line_in = sc_sock.nextLine();
                    System.out.println("Serwer odpisal: " + line_in);
                }
                s2.close();
            } catch (IOException ex) {
                System.err.println("Wyjątek");
            }
        }).start();
    }
}