
import java.io.*;
import java.net.*;
public final class EchoServer {

    public static void main(String[] args) throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(22222)) {
            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    String address = socket.getInetAddress().getHostAddress();
                    System.out.printf("Client connected: %s%n", address);
                    OutputStream os = socket.getOutputStream();
                    PrintStream out = new PrintStream(os, true, "UTF-8");
                    InputStream is = socket.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is, "UTF-8");
                    BufferedReader br = new BufferedReader(isr);
    
                    String client;
                    while((client = br.readLine()) != null) {
                        out.println(client);
                    }
                    System.out.printf("Client disconnected: %s%n", address);  
                }
            }
        }
    }
}