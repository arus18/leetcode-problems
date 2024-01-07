package com.example.proxy;

import java.io.*;
import java.net.*;

public class ProxyServer {

    private final int port;

    public ProxyServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Proxy server started on port: " + port);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket.getInetAddress());

            new Thread(new ClientHandler(clientSocket)).start();
        }
    }

    private class ClientHandler implements Runnable {

        private final Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                // Read request from client
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String request = reader.readLine();

                // Parse request line
                String[] parts = request.split(" ");
                String method = parts[0];
                String url = parts[1];

                // Modify the target URL to google.lk
                URL targetUrl = new URL("http://google.lk" + url);
                URLConnection connection = targetUrl.openConnection();

                // Send request to target server
                OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                writer.write(request + "\r\n");
                writer.flush();

                // Read response from target server
                BufferedReader serverReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = serverReader.readLine()) != null) {
                    // Forward response to client
                    OutputStreamWriter clientWriter = new OutputStreamWriter(clientSocket.getOutputStream());
                    clientWriter.write(line + "\r\n");
                    clientWriter.flush();
                }

                // Close connections
                reader.close();
                writer.close();
                serverReader.close();
                clientSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {

    }
}

