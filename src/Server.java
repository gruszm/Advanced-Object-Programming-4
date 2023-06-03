import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    public static void main(String[] args) throws IOException
    {
        ServerSocket serverSocket = new ServerSocket(7);

        while (true)
        {
            System.out.println("czekam na klienta");
            Socket socket = serverSocket.accept();
            System.out.println("klient podlaczony, do widzenia");
            socket.close();
        }
    }
}