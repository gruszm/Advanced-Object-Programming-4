package lab4;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer
{
    public static void main(String[] args) throws IOException
    {
        ServerSocket ss = new ServerSocket(7);
        while (true)
        {
            System.out.println("Czekam na klienta");
            Socket socket = ss.accept();
            System.out.println("Jest klient. Port: " + socket.getPort());
            Scanner sc = new Scanner(socket.getInputStream());
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            String line = "";
            while (!line.equals("bye"))
            {
                line = sc.nextLine();
                pw.println(line.toUpperCase());
                System.out.println("Klientowi odsyłam linie: " + line);
            }
            socket.close();
        }
    }
}
