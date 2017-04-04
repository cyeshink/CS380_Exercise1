import java.net.*;
import java.util.*;
import java.io.*;

public final class EchoClient {

    public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket("localhost", 22222)) {
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            OutputStream os = socket.getOutputStream();
            PrintStream out = new PrintStream(os, true, "UTF-8");
            Scanner kb = new Scanner(System.in);
            String client;
            while (true) {
                System.out.print("Client> ");
                out.printf(client = kb.nextLine() + "%n");
                client = br.readLine();
                if (client.equals("exit"))
                    break;
                System.out.println("Server> " + client);
            }
        }
    }
}