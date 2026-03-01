# TP-Serveur-TCP

Serveur TCP multi-thread Java avec pool de threads pour communication Telnet.

## Description

Ce projet implémente un serveur TCP simple en Java qui :
- Écoute sur le **port 5000**
- Gère plusieurs clients simultanément grâce à un **pool de threads fixe** (10 threads)
- Traite chaque client dans un **thread séparé** (`ClientHandler`)
- Communique via **Telnet** avec des réponses basiques aux messages des clients

## Structure du projet

```
src/
└── main/
    └── java/
        └── server/
            ├── TCPServer.java      # Point d'entrée – écoute les connexions et délègue au pool
            └── ClientHandler.java  # Gestion d'un client dans un thread dédié
```

## Prérequis

- Java 17+
- Apache Maven 3.6+

## Compilation

```bash
mvn package
```

Le JAR exécutable est généré dans `target/serveur-tcp.jar`.

## Lancement du serveur

```bash
java -jar target/serveur-tcp.jar
```

## Connexion avec Telnet

```bash
telnet localhost 5000
```

Une fois connecté, tapez un message et appuyez sur Entrée. Le serveur renvoie le message reçu. Tapez `quit` pour vous déconnecter.
