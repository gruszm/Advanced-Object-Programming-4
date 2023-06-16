package level4;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer
{
    private static Map<String, PrintWriter> clientWriters = new HashMap<>();

    public static void main(String[] args) throws Exception
    {
        try (ServerSocket listener = new ServerSocket(6000))
        {
            while (true)
            {
                new Handler(listener.accept()).start();
            }
        }
    }

    private static class Handler extends Thread
    {
        private String name;
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;

        public Handler(Socket socket)
        {
            this.socket = socket;
        }

        public void run()
        {
            try
            {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                while (true)
                {
                    out.println("SUBMITNAME");
                    name = in.readLine();
                    if (name == null)
                    {
                        return;
                    }
                    synchronized (clientWriters)
                    {
                        if (!clientWriters.containsKey(name))
                        {
                            clientWriters.put(name, out);
                            break;
                        }
                    }
                }

                out.println("NAMEACCEPTED");
                String input;
                while ((input = in.readLine()) != null)
                {
                    String[] splitInput = input.split(" ", 2);
                    if (splitInput.length >= 2 && splitInput[0].equals("ALL"))
                    {
                        for (PrintWriter writer : clientWriters.values())
                        {
                            writer.println("MESSAGE " + name + ": " + splitInput[1]);
                        }
                    }
                    else if (splitInput.length >= 2 && clientWriters.containsKey(splitInput[0]))
                    {
                        PrintWriter writer = clientWriters.get(splitInput[0]);
                        writer.println("MESSAGE " + name + ": " + splitInput[1]);
                    }
                }
            }
            catch (IOException e)
            {
                System.out.println(e);
            }
            finally
            {
                if (name != null)
                {
                    clientWriters.remove(name);
                    System.out.println("User " + name + " has disconnected");
                }
                try
                {
                    socket.close();
                }
                catch (IOException e)
                {
                }
            }
        }
    }
}
