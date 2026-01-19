package network;

import models.User;
import models.Publication;
import models.Comment;
import structures.SinglyLinkedList;
import structures.DoublyLinkedList;
import structures.Graph;
import structures.Queue;

public class SocialNetwork {
    private DoublyLinkedList<User> users;
    private Graph<User> subscriptionGraph;

    public SocialNetwork() {
        this.users = new DoublyLinkedList<>();
        this.subscriptionGraph = new Graph<>();
    }

    // ==================== Gestion des utilisateurs ====================

    public boolean addUser(String name) {
        // Vérifier les doublons (nom unique)
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getName().equalsIgnoreCase(name)) {
                return false; // Utilisateur déjà existe
            }
        }
        User newUser = new User(name);
        users.insert(newUser);
        subscriptionGraph.addVertex(newUser);
        return true;
    }

    public boolean removeUser(String name) {
        User user = findUserByName(name);
        if (user == null) {
            return false;
        }

        // Supprimer toutes les relations
        DoublyLinkedList<User> friends = user.getFriends();
        for (int i = 0; i < friends.size(); i++) {
            User friend = friends.get(i);
            friend.removeFriend(user);
        }

        DoublyLinkedList<User> subscribers = user.getSubscribers();
        for (int i = 0; i < subscribers.size(); i++) {
            User subscriber = subscribers.get(i);
            subscriber.removeSubscriber(user);
        }

        // Supprimer du graphe
        subscriptionGraph.removeVertex(user);
        users.remove(user);
        return true;
    }

    public User findUserByName(String name) {
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getName().equalsIgnoreCase(name)) {
                return user;
            }
        }
        return null;
    }

    public User findUserById(int id) {
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public void displayAllUsers() {
        if (users.size() == 0) {
            System.out.println("❌ Aucun utilisateur trouvé.\n");
            return;
        }
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("📋 LISTE DE TOUS LES UTILISATEURS");
        System.out.println("═══════════════════════════════════════════════════════════");
        for (int i = 0; i < users.size(); i++) {
            System.out.println(users.get(i));
        }
        System.out.println("═══════════════════════════════════════════════════════════\n");
    }

    // ==================== Gestion des relations ====================

    public boolean addFriend(String userName1, String userName2) {
        User user1 = findUserByName(userName1);
        User user2 = findUserByName(userName2);

        if (user1 == null || user2 == null) {
            return false;
        }

        if (user1.equals(user2)) {
            return false; // Pas d'amitié avec soi-même
        }

        user1.addFriend(user2);
        user2.addFriend(user1); // L'amitié est mutuelle
        return true;
    }

    public boolean removeFriend(String userName1, String userName2) {
        User user1 = findUserByName(userName1);
        User user2 = findUserByName(userName2);

        if (user1 == null || user2 == null) {
            return false;
        }

        user1.removeFriend(user2);
        user2.removeFriend(user1); // Supprimer la relation mutuelle
        return true;
    }

    public boolean addSubscription(String subscriberName, String publisherName) {
        User subscriber = findUserByName(subscriberName);
        User publisher = findUserByName(publisherName);

        if (subscriber == null || publisher == null) {
            return false;
        }

        if (subscriber.equals(publisher)) {
            return false; // Pas d'abonnement à soi-même
        }

        subscriber.addSubscriber(publisher);
        publisher.addSubscriber(subscriber);
        subscriptionGraph.addEdge(subscriber, publisher);
        return true;
    }

    public boolean removeSubscription(String subscriberName, String publisherName) {
        User subscriber = findUserByName(subscriberName);
        User publisher = findUserByName(publisherName);

        if (subscriber == null || publisher == null) {
            return false;
        }

        subscriber.removeSubscriber(publisher);
        publisher.removeSubscriber(subscriber);
        subscriptionGraph.removeEdge(subscriber, publisher);
        return true;
    }

    public void displayFriends(String userName) {
        User user = findUserByName(userName);
        if (user == null) {
            System.out.println("❌ Utilisateur non trouvé.\n");
            return;
        }

        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("👥 AMIS DE " + user.getName().toUpperCase());
        System.out.println("═══════════════════════════════════════════════════════════");
        DoublyLinkedList<User> friends = user.getFriends();
        if (friends.size() == 0) {
            System.out.println("Aucun ami trouvé.");
        } else {
            friends.display("  ");
        }
        System.out.println("═══════════════════════════════════════════════════════════\n");
    }

    public void displaySubscribers(String userName) {
        User user = findUserByName(userName);
        if (user == null) {
            System.out.println("❌ Utilisateur non trouvé.\n");
            return;
        }

        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("📢 ABONNÉS DE " + user.getName().toUpperCase());
        System.out.println("═══════════════════════════════════════════════════════════");
        DoublyLinkedList<User> subscribers = user.getSubscribers();
        if (subscribers.size() == 0) {
            System.out.println("Aucun abonné trouvé.");
        } else {
            subscribers.display("  ");
        }
        System.out.println("═══════════════════════════════════════════════════════════\n");
    }

    public SinglyLinkedList<User> findPathBetweenUsers(String userName1, String userName2) {
        User user1 = findUserByName(userName1);
        User user2 = findUserByName(userName2);

        if (user1 == null || user2 == null) {
            return null;
        }

        return subscriptionGraph.findPathBFS(user1, user2);
    }

    // ==================== Gestion des publications ====================

    public boolean postPublication(String userName, String content) {
        User user = findUserByName(userName);
        if (user == null) {
            return false;
        }

        Publication pub = new Publication(user.getId(), user.getName(), content);
        user.addPublication(pub);
        return true;
    }

    public boolean commentOnPublication(String userName, String publisherName, int pubId, String commentContent) {
        User user = findUserByName(userName);
        User publisher = findUserByName(publisherName);

        if (user == null || publisher == null) {
            return false;
        }

        SinglyLinkedList<Publication> publications = publisher.getPublications();
        for (int i = 0; i < publications.size(); i++) {
            Publication pub = publications.get(i);
            if (pub.getId() == pubId) {
                Comment comment = new Comment(user.getId(), user.getName(), commentContent);
                pub.addComment(comment);
                return true;
            }
        }
        return false;
    }

    public void displayUserPublications(String userName) {
        User user = findUserByName(userName);
        if (user == null) {
            System.out.println("❌ Utilisateur non trouvé.\n");
            return;
        }

        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("📰 PUBLICATIONS DE " + user.getName().toUpperCase());
        System.out.println("═══════════════════════════════════════════════════════════");

        SinglyLinkedList<Publication> publications = user.getPublications();
        if (publications.size() == 0) {
            System.out.println("Aucune publication.");
        } else {
            for (int i = 0; i < publications.size(); i++) {
                Publication pub = publications.get(i);
                System.out.println(pub);
                Queue<Comment> comments = pub.getComments();
                if (comments.size() > 0) {
                    System.out.println("   Commentaires:");
                    // Afficher les commentaires (sans les supprimer de la file)
                    displayQueueComments(comments);
                }
                System.out.println();
            }
        }
        System.out.println("═══════════════════════════════════════════════════════════\n");
    }

    public void displayFriendsPublications(String userName) {
        User user = findUserByName(userName);
        if (user == null) {
            System.out.println("❌ Utilisateur non trouvé.\n");
            return;
        }

        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("📰 PUBLICATIONS DES AMIS DE " + user.getName().toUpperCase());
        System.out.println("═══════════════════════════════════════════════════════════");

        DoublyLinkedList<User> friends = user.getFriends();
        if (friends.size() == 0) {
            System.out.println("Aucun ami.");
        } else {
            for (int i = 0; i < friends.size(); i++) {
                User friend = friends.get(i);
                SinglyLinkedList<Publication> publications = friend.getPublications();
                System.out.println("\n👤 Publications de " + friend.getName() + ":");
                if (publications.size() == 0) {
                    System.out.println("   Aucune publication.");
                } else {
                    for (int j = 0; j < publications.size(); j++) {
                        Publication pub = publications.get(j);
                        System.out.println("   " + pub);
                        Queue<Comment> comments = pub.getComments();
                        if (comments.size() > 0) {
                            System.out.println("      Commentaires:");
                            displayQueueComments(comments);
                        }
                    }
                }
            }
        }
        System.out.println("\n═══════════════════════════════════════════════════════════\n");
    }

    // ==================== Recherche et statistiques ====================

    public int getTotalUserCount() {
        return users.size();
    }

    public int getUserFriendCount(String userName) {
        User user = findUserByName(userName);
        if (user == null) {
            return -1;
        }
        return user.getFriendCount();
    }

    public int getUserSubscriberCount(String userName) {
        User user = findUserByName(userName);
        if (user == null) {
            return -1;
        }
        return user.getSubscriberCount();
    }

    public User getUserWithMostFriends() {
        if (users.size() == 0) {
            return null;
        }

        User maxUser = users.get(0);
        for (int i = 1; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getFriendCount() > maxUser.getFriendCount()) {
                maxUser = user;
            }
        }
        return maxUser;
    }

    public User getUserWithMostSubscribers() {
        if (users.size() == 0) {
            return null;
        }

        User maxUser = users.get(0);
        for (int i = 1; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getSubscriberCount() > maxUser.getSubscriberCount()) {
                maxUser = user;
            }
        }
        return maxUser;
    }

    public User getUserWithMostPublications() {
        if (users.size() == 0) {
            return null;
        }

        User maxUser = users.get(0);
        for (int i = 1; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getPublicationCount() > maxUser.getPublicationCount()) {
                maxUser = user;
            }
        }
        return maxUser;
    }

    public Publication getPublicationWithMostComments() {
        Publication maxPub = null;
        int maxComments = 0;

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            SinglyLinkedList<Publication> publications = user.getPublications();
            for (int j = 0; j < publications.size(); j++) {
                Publication pub = publications.get(j);
                if (pub.getCommentCount() > maxComments) {
                    maxComments = pub.getCommentCount();
                    maxPub = pub;
                }
            }
        }
        return maxPub;
    }

    public void displayStatistics() {
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("📊 STATISTIQUES DU RÉSEAU SOCIAL");
        System.out.println("═══════════════════════════════════════════════════════════");

        System.out.println("Total d'utilisateurs: " + getTotalUserCount());

        User maxFriends = getUserWithMostFriends();
        if (maxFriends != null) {
            System.out.println("Utilisateur avec le plus d'amis: " + maxFriends.getName() + 
                             " (" + maxFriends.getFriendCount() + " amis)");
        }

        User maxSubscribers = getUserWithMostSubscribers();
        if (maxSubscribers != null) {
            System.out.println("Utilisateur avec le plus d'abonnés: " + maxSubscribers.getName() + 
                             " (" + maxSubscribers.getSubscriberCount() + " abonnés)");
        }

        User maxPubs = getUserWithMostPublications();
        if (maxPubs != null) {
            System.out.println("Utilisateur avec le plus de publications: " + maxPubs.getName() + 
                             " (" + maxPubs.getPublicationCount() + " publications)");
        }

        Publication maxCommentPub = getPublicationWithMostComments();
        if (maxCommentPub != null) {
            System.out.println("Publication avec le plus de commentaires: " + maxCommentPub.getAuthorName() + 
                             " (" + maxCommentPub.getCommentCount() + " commentaires)");
        }

        System.out.println("═══════════════════════════════════════════════════════════\n");
    }

    // ==================== Méthode utilitaire ====================

    private void displayQueueComments(Queue<Comment> comments) {
        // Créer une copie de la file pour l'affichage sans la modifier
        structures.Queue<Comment> tempQueue = new structures.Queue<>();
        Comment comment;
        while ((comment = comments.peek()) != null) {
            Comment c = comments.dequeue();
            tempQueue.enqueue(c);
            System.out.println("      " + c);
        }
        // Restaurer les commentaires
        while ((comment = tempQueue.dequeue()) != null) {
            comments.enqueue(comment);
        }
    }
}
