package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Multi-threaded TCP server that listens on port 5000.
 * Uses a fixed thread pool to handle multiple clients concurrently.
 */
public class TCPServer {

    /** Port on which the server listens. */
    private static final int PORT = 5000;

    /** Maximum number of concurrent client threads. */
    private static final int THREAD_POOL_SIZE = 10;

    public static void main(String[] args) {
        // Create a fixed thread pool to limit concurrent connections
        ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        System.out.println("Serveur TCP démarré sur le port " + PORT);
        System.out.println("En attente de connexions...");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            // Accept client connections indefinitely
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nouveau client connecté : " + clientSocket.getInetAddress());

                // Delegate client handling to a thread from the pool
                threadPool.execute(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            System.err.println("Erreur du serveur : " + e.getMessage());
        } finally {
            threadPool.shutdown();
        }
    }
}
