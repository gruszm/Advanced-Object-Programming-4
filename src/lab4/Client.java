package lab4;

import java.io.IOException;
import java.net.Socket;

public class Client
{
    public static void main(String[] args) throws IOException
    {
        System.out.println("Podlaczam sie do serwera");
        Socket s = new Socket("localhost", 7);
        System.out.println("Suckes. Do widzenia.");
        s.close();
    }
}
