import java.io.*;
import java.net.*;

public final class EchoServer {
    private static class EchoThread extends Thread {
        private Socket socket = null;
        private String address;
        public EchoThread(Socket s) {
            this.socket = s;
            address = s.getInetAddress().getHostAddress();
        }
        public void run() {
            try {

                System.out.printf("Client connected: %s%n", address);
                OutputStream os = socket.getOutputStream();
                PrintStream out = new PrintStream(os, true, "UTF-8");
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is, "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                String client;
                while ((client = br.readLine()) != null) {
                    out.println(client);
                }
                System.out.printf("Client disconnected: %s%n", address);
            } catch (IOException e) {
                System.out.printf("Client Failed.%n");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(22222);
        try {
            while (true) {
                new EchoThread(serverSocket.accept()).start();
            }
        } finally {
            serverSocket.close();
        }
    }
}