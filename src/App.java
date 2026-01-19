import java.util.Scanner;
import network.SocialNetwork;
import models.User;
import structures.SinglyLinkedList;

public class App {
    private static SocialNetwork network;
    private static Scanner scanner;

    public static void main(String[] args) {
        network = new SocialNetwork();
        scanner = new Scanner(System.in);

        System.out.println("\n");
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║      BIENVENUE AU RÉSEAU SOCIAL SIMPLIFIÉ (MiniBook)      ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");

        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    userManagementMenu();
                    break;
                case 2:
                    relationshipMenu();
                    break;
                case 3:
                    publicationMenu();
                    break;
                case 4:
                    searchStatisticsMenu();
                    break;
                case 5:
                    running = false;
                    System.out.println("\n✅ Au revoir! Merci d'avoir utilisé MiniBook.\n");
                    break;
                default:
                    System.out.println("❌ Option invalide. Veuillez réessayer.\n");
            }
        }

        scanner.close();
    }

    // ==================== MENU PRINCIPAL ====================

    private static void displayMainMenu() {
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("📱 MENU PRINCIPAL");
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("1. 👤 Gestion des utilisateurs");
        System.out.println("2. 👥 Gestion des relations");
        System.out.println("3. 📝 Gestion des publications");
        System.out.println("4. 🔍 Recherche et statistiques");
        System.out.println("5. 🚪 Quitter");
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.print("Choisissez une option: ");
    }

    // ==================== GESTION DES UTILISATEURS ====================

    private static void userManagementMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n═══════════════════════════════════════════════════════════");
            System.out.println("👤 GESTION DES UTILISATEURS");
            System.out.println("═══════════════════════════════════════════════════════════");
            System.out.println("1. ➕ Ajouter un utilisateur");
            System.out.println("2. ➖ Supprimer un utilisateur");
            System.out.println("3. 📋 Afficher tous les utilisateurs");
            System.out.println("4. 🔍 Rechercher un utilisateur par nom");
            System.out.println("5. ⬅️ Retour au menu principal");
            System.out.println("═══════════════════════════════════════════════════════════");
            System.out.print("Choisissez une option: ");

            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    addUser();
                    break;
                case 2:
                    removeUser();
                    break;
                case 3:
                    network.displayAllUsers();
                    break;
                case 4:
                    searchUser();
                    break;
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println("❌ Option invalide.\n");
            }
        }
    }

    private static void addUser() {
        System.out.print("\nEntrez le nom d'utilisateur: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("❌ Le nom ne peut pas être vide.\n");
            return;
        }

        if (network.addUser(name)) {
            System.out.println("✅ Utilisateur '" + name + "' ajouté avec succès!\n");
        } else {
            System.out.println("❌ Cet utilisateur existe déjà.\n");
        }
    }

    private static void removeUser() {
        System.out.print("\nEntrez le nom d'utilisateur à supprimer: ");
        String name = scanner.nextLine().trim();

        if (network.removeUser(name)) {
            System.out.println("✅ Utilisateur supprimé avec succès!\n");
        } else {
            System.out.println("❌ Utilisateur non trouvé.\n");
        }
    }

    private static void searchUser() {
        System.out.print("\nEntrez le nom d'utilisateur: ");
        String name = scanner.nextLine().trim();

        User user = network.findUserByName(name);
        if (user != null) {
            System.out.println("\n" + user + "\n");
        } else {
            System.out.println("❌ Utilisateur non trouvé.\n");
        }
    }

    // ==================== GESTION DES RELATIONS ====================

    private static void relationshipMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n═══════════════════════════════════════════════════════════");
            System.out.println("👥 GESTION DES RELATIONS");
            System.out.println("═══════════════════════════════════════════════════════════");
            System.out.println("1. 👫 Ajouter un ami");
            System.out.println("2. 🔗 Supprimer un ami");
            System.out.println("3. 📢 Ajouter un abonnement");
            System.out.println("4. 🔓 Supprimer un abonnement");
            System.out.println("5. 👥 Afficher les amis d'un utilisateur");
            System.out.println("6. 📢 Afficher les abonnés d'un utilisateur");
            System.out.println("7. 🗺️ Trouver un chemin entre deux utilisateurs");
            System.out.println("8. ⬅️ Retour au menu principal");
            System.out.println("═══════════════════════════════════════════════════════════");
            System.out.print("Choisissez une option: ");

            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    addFriend();
                    break;
                case 2:
                    removeFriend();
                    break;
                case 3:
                    addSubscription();
                    break;
                case 4:
                    removeSubscription();
                    break;
                case 5:
                    displayFriends();
                    break;
                case 6:
                    displaySubscribers();
                    break;
                case 7:
                    findPath();
                    break;
                case 8:
                    back = true;
                    break;
                default:
                    System.out.println("❌ Option invalide.\n");
            }
        }
    }

    private static void addFriend() {
        System.out.print("\nEntrez le nom du premier utilisateur: ");
        String name1 = scanner.nextLine().trim();
        System.out.print("Entrez le nom du second utilisateur: ");
        String name2 = scanner.nextLine().trim();

        if (network.addFriend(name1, name2)) {
            System.out.println("✅ Relation d'amitié établie entre " + name1 + " et " + name2 + "!\n");
        } else {
            System.out.println("❌ Opération échouée. Vérifiez les noms.\n");
        }
    }

    private static void removeFriend() {
        System.out.print("\nEntrez le nom du premier utilisateur: ");
        String name1 = scanner.nextLine().trim();
        System.out.print("Entrez le nom du second utilisateur: ");
        String name2 = scanner.nextLine().trim();

        if (network.removeFriend(name1, name2)) {
            System.out.println("✅ Relation d'amitié supprimée!\n");
        } else {
            System.out.println("❌ Opération échouée.\n");
        }
    }

    private static void addSubscription() {
        System.out.print("\nEntrez le nom de l'abonné: ");
        String subscriberName = scanner.nextLine().trim();
        System.out.print("Entrez le nom du créateur de contenu: ");
        String publisherName = scanner.nextLine().trim();

        if (network.addSubscription(subscriberName, publisherName)) {
            System.out.println("✅ Abonnement établi!\n");
        } else {
            System.out.println("❌ Opération échouée.\n");
        }
    }

    private static void removeSubscription() {
        System.out.print("\nEntrez le nom de l'abonné: ");
        String subscriberName = scanner.nextLine().trim();
        System.out.print("Entrez le nom du créateur de contenu: ");
        String publisherName = scanner.nextLine().trim();

        if (network.removeSubscription(subscriberName, publisherName)) {
            System.out.println("✅ Abonnement supprimé!\n");
        } else {
            System.out.println("❌ Opération échouée.\n");
        }
    }

    private static void displayFriends() {
        System.out.print("\nEntrez le nom d'utilisateur: ");
        String name = scanner.nextLine().trim();
        network.displayFriends(name);
    }

    private static void displaySubscribers() {
        System.out.print("\nEntrez le nom d'utilisateur: ");
        String name = scanner.nextLine().trim();
        network.displaySubscribers(name);
    }

    private static void findPath() {
        System.out.print("\nEntrez le nom du premier utilisateur: ");
        String name1 = scanner.nextLine().trim();
        System.out.print("Entrez le nom du second utilisateur: ");
        String name2 = scanner.nextLine().trim();

        SinglyLinkedList<User> path = network.findPathBetweenUsers(name1, name2);
        if (path != null && path.size() > 0) {
            System.out.println("\n🗺️ Chemin trouvé entre " + name1 + " et " + name2 + ":");
            path.display("  ");
            System.out.println();
        } else {
            System.out.println("❌ Aucun chemin trouvé entre ces deux utilisateurs.\n");
        }
    }

    // ==================== GESTION DES PUBLICATIONS ====================

    private static void publicationMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n═══════════════════════════════════════════════════════════");
            System.out.println("📝 GESTION DES PUBLICATIONS");
            System.out.println("═══════════════════════════════════════════════════════════");
            System.out.println("1. ✍️  Poster une publication");
            System.out.println("2. 💬 Commenter une publication");
            System.out.println("3. 📰 Afficher mes publications");
            System.out.println("4. 👥 Afficher les publications de mes amis");
            System.out.println("5. ⬅️ Retour au menu principal");
            System.out.println("═══════════════════════════════════════════════════════════");
            System.out.print("Choisissez une option: ");

            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    postPublication();
                    break;
                case 2:
                    commentPublication();
                    break;
                case 3:
                    displayMyPublications();
                    break;
                case 4:
                    displayFriendsPublications();
                    break;
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println("❌ Option invalide.\n");
            }
        }
    }

    private static void postPublication() {
        System.out.print("\nEntrez votre nom: ");
        String userName = scanner.nextLine().trim();
        System.out.print("Entrez votre publication: ");
        String content = scanner.nextLine().trim();

        if (network.postPublication(userName, content)) {
            System.out.println("✅ Publication ajoutée avec succès!\n");
        } else {
            System.out.println("❌ Utilisateur non trouvé.\n");
        }
    }

    private static void commentPublication() {
        System.out.print("\nEntrez votre nom: ");
        String userName = scanner.nextLine().trim();
        System.out.print("Entrez le nom de l'auteur de la publication: ");
        String publisherName = scanner.nextLine().trim();
        System.out.print("Entrez l'ID de la publication: ");
        int pubId = getUserChoice();
        System.out.print("Entrez votre commentaire: ");
        String commentContent = scanner.nextLine().trim();

        if (network.commentOnPublication(userName, publisherName, pubId, commentContent)) {
            System.out.println("✅ Commentaire ajouté avec succès!\n");
        } else {
            System.out.println("❌ Opération échouée.\n");
        }
    }

    private static void displayMyPublications() {
        System.out.print("\nEntrez votre nom: ");
        String userName = scanner.nextLine().trim();
        network.displayUserPublications(userName);
    }

    private static void displayFriendsPublications() {
        System.out.print("\nEntrez votre nom: ");
        String userName = scanner.nextLine().trim();
        network.displayFriendsPublications(userName);
    }

    // ==================== RECHERCHE ET STATISTIQUES ====================

    private static void searchStatisticsMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n═══════════════════════════════════════════════════════════");
            System.out.println("🔍 RECHERCHE ET STATISTIQUES");
            System.out.println("═══════════════════════════════════════════════════════════");
            System.out.println("1. 📊 Afficher les statistiques globales");
            System.out.println("2. 👥 Nombre d'amis d'un utilisateur");
            System.out.println("3. 📢 Nombre d'abonnés d'un utilisateur");
            System.out.println("4. ⬅️ Retour au menu principal");
            System.out.println("═══════════════════════════════════════════════════════════");
            System.out.print("Choisissez une option: ");

            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    network.displayStatistics();
                    break;
                case 2:
                    displayUserFriendCount();
                    break;
                case 3:
                    displayUserSubscriberCount();
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    System.out.println("❌ Option invalide.\n");
            }
        }
    }

    private static void displayUserFriendCount() {
        System.out.print("\nEntrez le nom d'utilisateur: ");
        String userName = scanner.nextLine().trim();
        int count = network.getUserFriendCount(userName);

        if (count >= 0) {
            System.out.println("✅ " + userName + " a " + count + " ami(s).\n");
        } else {
            System.out.println("❌ Utilisateur non trouvé.\n");
        }
    }

    private static void displayUserSubscriberCount() {
        System.out.print("\nEntrez le nom d'utilisateur: ");
        String userName = scanner.nextLine().trim();
        int count = network.getUserSubscriberCount(userName);

        if (count >= 0) {
            System.out.println("✅ " + userName + " a " + count + " abonné(s).\n");
        } else {
            System.out.println("❌ Utilisateur non trouvé.\n");
        }
    }

    // ==================== MÉTHODES UTILITAIRES ====================

    private static int getUserChoice() {
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            return choice;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
