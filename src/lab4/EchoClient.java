package lab4;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient
{
    public static void main(String[] args) throws IOException
    {
        Socket socket = new Socket("localhost", 7);
        Scanner sc_keyb = new Scanner(System.in);
        Scanner sc_sock = new Scanner(socket.getInputStream());
        PrintWriter pw = new PrintWriter((socket.getOutputStream()), true);
        String line_out = "";
        String line_in = "";
        while (!line_out.equals("bye"))
        {
            System.out.println("Podaj tekst: ");
            line_out = sc_keyb.nextLine();
            pw.println(line_out);
            line_in = sc_sock.nextLine();
            System.out.println("Serwer odpisal: " + line_in);
        }
        socket.close();
    }
}