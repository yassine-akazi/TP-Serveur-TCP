package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Handles communication with a single connected client.
 * Runs in its own thread (provided by the server's thread pool).
 * Reads lines sent by the client (e.g. via Telnet) and sends back responses.
 */
public class ClientHandler implements Runnable {

    private final Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        String clientAddress = clientSocket.getInetAddress().toString();

        try (
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            // Welcome message sent to the client upon connection
            writer.println("Bienvenue sur le serveur TCP ! Tapez 'quit' pour vous déconnecter.");

            String line;
            // Read messages from the client line by line
            while ((line = reader.readLine()) != null) {
                System.out.println("[" + clientAddress + "] " + line);

                if (line.equalsIgnoreCase("quit")) {
                    writer.println("Au revoir !");
                    break;
                }

                // Echo the message back with a simple acknowledgement
                writer.println("Serveur reçu : " + line);
            }

        } catch (IOException e) {
            System.err.println("Erreur avec le client " + clientAddress + " : " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Erreur lors de la fermeture du socket : " + e.getMessage());
            }
            System.out.println("Client déconnecté : " + clientAddress);
        }
    }
}
