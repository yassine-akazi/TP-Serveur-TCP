# TP-Serveur-TCP

Serveur TCP multi-thread Java avec pool de threads pour communication Telnet.

## 📋 Description

Ce projet implémente un serveur TCP multi-thread en Java utilisant un pool de threads pour gérer efficacement les connexions clients simultanées via Telnet. Le serveur est conçu pour démontrer les concepts de programmation réseau et de gestion de threads en Java.

## ✨ Fonctionnalités

- **Serveur TCP multi-thread** : Gestion simultanée de plusieurs clients
- **Pool de threads** : Optimisation des ressources système avec un pool de threads configurable
- **Communication Telnet** : Support des connexions Telnet standard
- **Gestion robuste des connexions** : Traitement approprié des connexions et déconnexions clients
- **Architecture évolutive** : Conception modulaire et extensible

## 🛠️ Prérequis

- Java JDK 8 ou supérieur
- Un client Telnet (telnet, PuTTY, etc.)

## 📦 Installation

1. Clonez le dépôt :
```bash
git clone https://github.com/yassine-akazi/TP-Serveur-TCP.git
cd TP-Serveur-TCP
```

2. Compilez le projet :
```bash
javac *.java
```

## 🚀 Utilisation

### Démarrage du serveur

```bash
java ServeurTCP [port]
```

Par défaut, le serveur écoute sur le port 12345.

### Connexion au serveur

Depuis un terminal, utilisez telnet pour vous connecter :

```bash
telnet localhost 12345
```

Ou avec un port personnalisé :

```bash
telnet localhost [votre_port]
```

## 🏗️ Architecture

Le projet est structuré autour des composants suivants :

- **ServeurTCP** : Classe principale qui initialise le serveur et accepte les connexions
- **GestionnaireClient** : Thread qui gère la communication avec chaque client
- **PoolDeThreads** : Gère l'allocation et la réutilisation des threads

```
┌─────────────────┐
│  ServeurTCP     │
│  (Main Thread)  │
└────────┬────────┘
         │
         ├──► Pool de Threads
         │    ┌──────────────────┐
         │    │  Thread 1        │ ◄──► Client 1
         │    ├──────────────────┤
         │    │  Thread 2        │ ◄──► Client 2
         │    ├──────────────────┤
         │    │  Thread 3        │ ◄──► Client 3
         │    └──────────────────┘
         │
         └──► Accepte nouvelles connexions
```

## 💻 Exemple de code

### Initialisation du serveur

```java
public class ServeurTCP {
    public static void main(String[] args) {
        int port = 12345;
        ExecutorService pool = Executors.newFixedThreadPool(10);
        
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Serveur démarré sur le port " + port);
            
            while (true) {
                Socket clientSocket = serverSocket.accept();
                pool.execute(new GestionnaireClient(clientSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

## ⚙️ Configuration

Vous pouvez configurer les paramètres suivants :

- **PORT** : Port d'écoute du serveur (défaut: 12345)
- **TAILLE_POOL** : Nombre maximum de threads dans le pool (défaut: 10)
- **TIMEOUT** : Délai d'attente pour les connexions (optionnel)

## 🧪 Tests

Pour tester le serveur avec plusieurs clients simultanés :

```bash
# Terminal 1
telnet localhost 12345

# Terminal 2
telnet localhost 12345

# Terminal 3
telnet localhost 12345
```

## 📝 Commandes supportées

Une fois connecté au serveur, vous pouvez utiliser :

- `HELP` : Affiche la liste des commandes disponibles
- `ECHO [message]` : Le serveur renvoie le message
- `TIME` : Affiche l'heure du serveur
- `QUIT` : Ferme la connexion

## 🤝 Contribution

Les contributions sont les bienvenues ! N'hésitez pas à :

1. Fork le projet
2. Créer une branche pour votre fonctionnalité (`git checkout -b feature/AmazingFeature`)
3. Commit vos changements (`git commit -m 'Add some AmazingFeature'`)
4. Push vers la branche (`git push origin feature/AmazingFeature`)
5. Ouvrir une Pull Request

## 📄 Licence

Ce projet est sous licence MIT. Voir le fichier `LICENSE` pour plus de détails.

## 👤 Auteur

**Yassine Akazi**

- GitHub: [@yassine-akazi](https://github.com/yassine-akazi)


