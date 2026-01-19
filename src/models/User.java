package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import structures.SinglyLinkedList;
import structures.DoublyLinkedList;

public class User {
    private static int idCounter = 1;
    private int id;
    private String name;
    private LocalDateTime inscriptionDate;
    private SinglyLinkedList<Publication> publications;
    private DoublyLinkedList<User> friends;
    private DoublyLinkedList<User> subscribers;

    public User(String name) {
        this.id = idCounter++;
        this.name = name;
        this.inscriptionDate = LocalDateTime.now();
        this.publications = new SinglyLinkedList<>();
        this.friends = new DoublyLinkedList<>();
        this.subscribers = new DoublyLinkedList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getInscriptionDate() {
        return inscriptionDate;
    }

    public SinglyLinkedList<Publication> getPublications() {
        return publications;
    }

    public DoublyLinkedList<User> getFriends() {
        return friends;
    }

    public DoublyLinkedList<User> getSubscribers() {
        return subscribers;
    }

    public void addPublication(Publication pub) {
        publications.insert(pub);
    }

    public void addFriend(User user) {
        if (!friends.contains(user)) {
            friends.insert(user);
        }
    }

    public void removeFriend(User user) {
        friends.remove(user);
    }

    public void addSubscriber(User user) {
        if (!subscribers.contains(user)) {
            subscribers.insert(user);
        }
    }

    public void removeSubscriber(User user) {
        subscribers.remove(user);
    }

    public boolean isFriendWith(User user) {
        return friends.contains(user);
    }

    public boolean hasSubscriber(User user) {
        return subscribers.contains(user);
    }

    public int getFriendCount() {
        return friends.size();
    }

    public int getSubscriberCount() {
        return subscribers.size();
    }

    public int getPublicationCount() {
        return publications.size();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "👤 [ID: " + id + "] " + name + 
               " | Inscrit le: " + inscriptionDate.format(formatter) +
               " | Amis: " + friends.size() + 
               " | Abonnés: " + subscribers.size() + 
               " | Publications: " + publications.size();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return id == user.id;
    }
}
