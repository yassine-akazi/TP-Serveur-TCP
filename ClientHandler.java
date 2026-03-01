import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;

public class ClientHandler implements Runnable {

    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        String clientIP = clientSocket.getInetAddress().getHostAddress();

        System.out.println("Thread " + threadName + " traite le client " + clientIP);

        try (
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(
                    clientSocket.getOutputStream(), true)
        ) {
            String message;

            while ((message = in.readLine()) != null) {
                System.out.println("Message reçu de " + clientIP + " : " + message);

                if (message.equalsIgnoreCase("hello")) {
                    out.println("Bonjour client !");
                } 
                else if (message.equalsIgnoreCase("time")) {
                    out.println("Date et heure actuelles : " + LocalDateTime.now());
                } 
                else if (message.equalsIgnoreCase("bye")) {
                    out.println("Connexion fermée");
                    break;
                } 
                else {
                    out.println("Message reçu : " + message);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
                System.out.println("Connexion fermée avec " + clientIP);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
