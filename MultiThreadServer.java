
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadServer {

    public static void main(String[] args) {
        int port = 5000;

        // Pool de 5 threads
        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Serveur démarré sur le port " + port);

            while (true) {
                // Attente d'un client
                Socket clientSocket = serverSocket.accept();

                System.out.println("Client connecté : " +
                        clientSocket.getInetAddress().getHostAddress());

                // Envoyer le client au pool de threads
                threadPool.execute(new ClientHandler(clientSocket));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
