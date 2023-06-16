package lab4;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class ServerThread extends Thread
{
    Socket s1, s2;
    PrintWriter pw;
    static Set<PrintWriter> set = new HashSet<>();

    public ServerThread(Socket socket1, Socket socket2)
    {
        this.s1 = socket1;
        this.s2 = socket2;
        try
        {
            pw = new PrintWriter(s1.getOutputStream(), true);
            set.add(pw);
            this.start();
        }
        catch (IOException e)
        {
            System.err.println("Wyjątek 1");
        }
    }

    @Override
    public void run()
    {
        try
        {
            Scanner sc = new Scanner(s2.getInputStream());
            String line = "";
            while (!line.equals("bye"))
            {
                line = sc.nextLine();
                System.out.println(line);
                for (PrintWriter pwr : set)
                {
                    if (pw != pwr)
                    {
                        pwr.println("> " + line);
                    }
                }
            }
            pw.println(line);
            s1.close();
            s2.close();
            set.remove(pw);
        }
        catch (IOException e)
        {
            System.err.println("Wyjątek 2");
        }
    }
}