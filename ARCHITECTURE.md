# 🏗️ ARCHITECTURE ET DOCUMENTATION TECHNIQUE

## 📐 Diagramme UML Simplifié

```
┌─────────────────────────────────────────────────────────────┐
│                        SocialNetwork                        │
├─────────────────────────────────────────────────────────────┤
│ - users: DoublyLinkedList<User>                             │
│ - subscriptionGraph: Graph<User>                            │
├─────────────────────────────────────────────────────────────┤
│ + addUser(name): boolean                                    │
│ + removeUser(name): boolean                                 │
│ + postPublication(userName, content): boolean               │
│ + commentOnPublication(...): boolean                        │
│ + findPathBetweenUsers(name1, name2): List<User>            │
│ + displayStatistics(): void                                 │
└─────────────────────────────────────────────────────────────┘
         │
         │ gère
         ▼
┌──────────────────────────────────────────────┐
│                    User                      │
├──────────────────────────────────────────────┤
│ - id: int                                    │
│ - name: String                               │
│ - inscriptionDate: LocalDateTime             │
│ - publications: SinglyLinkedList<Pub>        │
│ - friends: DoublyLinkedList<User>            │
│ - subscribers: DoublyLinkedList<User>        │
├──────────────────────────────────────────────┤
│ + getId(): int                               │
│ + getName(): String                          │
│ + addFriend(User): void                      │
│ + addPublication(Publication): void          │
└──────────────────────────────────────────────┘
         │
         │ contient
         ▼
┌───────────────────────────────────────────────────────┐
│                  Publication                         │
├───────────────────────────────────────────────────────┤
│ - id: int                                             │
│ - authorId: int                                       │
│ - content: String                                     │
│ - timestamp: LocalDateTime                           │
│ - comments: Queue<Comment>                           │
├───────────────────────────────────────────────────────┤
│ + getId(): int                                        │
│ + addComment(Comment): void                          │
│ + getCommentCount(): int                             │
└───────────────────────────────────────────────────────┘
         │
         │ contient (FIFO)
         ▼
┌───────────────────────────────────────────────────────┐
│                   Comment                            │
├───────────────────────────────────────────────────────┤
│ - id: int                                             │
│ - authorId: int                                       │
│ - authorName: String                                 │
│ - content: String                                     │
│ - timestamp: LocalDateTime                           │
├───────────────────────────────────────────────────────┤
│ + getId(): int                                        │
│ + getContent(): String                               │
│ + getTimestamp(): LocalDateTime                      │
└───────────────────────────────────────────────────────┘
```

## 🔄 Flux de Données

### Ajout d'Utilisateur
```
[Menu Principal]
    ↓
[Saisie du nom]
    ↓
[SocialNetwork.addUser(name)]
    ↓
[Vérification: nom unique?]
    ├─ NON → Afficher erreur
    └─ OUI → Créer User + Ajouter au graphe
              ↓
          [DoublyLinkedList.insert(user)]
          [Graph.addVertex(user)]
              ↓
          [Afficher succès]
```

### Ajout d'Amitié
```
[Menu Relation] → [Saisir 2 noms]
    ↓
[SocialNetwork.addFriend(name1, name2)]
    ↓
[Trouver les 2 utilisateurs]
    ├─ Utilisateur non trouvé → Erreur
    └─ Trouvé → Ajouter relation mutuelle
              ↓
          [user1.addFriend(user2)]
          [user2.addFriend(user1)]
              ↓
          [Relation établie]
```

### Posting et Commentaires
```
[Poster Publication]
    ↓
[SocialNetwork.postPublication(userName, content)]
    ↓
[Créer Publication + Ajouter au User]
    ↓
[user.getPublications().insert(publication)]
    ↓
[Publication créée avec Queue<Comment> vide]
    ↓
[Commentaire ajouté]
    ↓
[publication.getComments().enqueue(comment)]
    ↓
[Queue a sa structure FIFO]
```

## 🏛️ Organisation des Packages

### `structures/`
Contient les implémentations des structures de données fondamentales:
- **Node.java** - Nœud simple pour liste simplement chaînée
- **DoubleNode.java** - Nœud double pour liste doublement chaînée
- **Stack.java** - Pile LIFO (réservée pour future utilisation)
- **Queue.java** - File FIFO (utilisée pour les commentaires)
- **SinglyLinkedList.java** - Liste simplement chaînée (publications)
- **DoublyLinkedList.java** - Liste doublement chaînée (amis, abonnés)
- **Graph.java** - Graphe orienté (abonnements)

### `models/`
Classes métier représentant les entités du domaine:
- **User.java** - Représente un utilisateur avec relations
- **Publication.java** - Représente une publication
- **Comment.java** - Représente un commentaire

### `network/`
Logique métier du réseau social:
- **SocialNetwork.java** - Gestionnaire principal du réseau

## 📋 Détail des Opérations

### SinglyLinkedList (Publications)
```java
public class SinglyLinkedList<T> {
    private Node<T> head;
    private int size;
    
    // O(n) - Insérer à la fin
    public void insert(T data)
    
    // O(n) - Supprimer un élément
    public boolean remove(T data)
    
    // O(n) - Accéder par index
    public T get(int index)
    
    // O(n) - Chercher un élément
    public boolean contains(T data)
}
```

### DoublyLinkedList (Amis, Abonnés)
```java
public class DoublyLinkedList<T> {
    private DoubleNode<T> head;
    private DoubleNode<T> tail;
    private int size;
    
    // O(1) - Insérer à la fin ou début
    public void insert(T data)
    public void insertFirst(T data)
    
    // O(n) - Supprimer (mais bénéficie des pointeurs bidirectionnels)
    public boolean remove(T data)
    
    // O(n) - Traversal amélioré
    public T get(int index)
}
```

### Queue (Commentaires)
```java
public class Queue<T> {
    private Node<T> front;
    private Node<T> rear;
    
    // O(1) - Ajouter à la fin
    public void enqueue(T data)
    
    // O(1) - Retirer du début
    public T dequeue()
    
    // O(1) - Voir le premier
    public T peek()
}
```

### Graph (Abonnements)
```java
public class Graph<T> {
    private DoublyLinkedList<T> vertices;
    private DoublyLinkedList<Edge<T>> edges;
    
    // O(1) - Ajouter un sommet
    public void addVertex(T vertex)
    
    // O(1) - Ajouter une arête
    public void addEdge(T source, T dest)
    
    // O(V+E) - BFS pour trouver chemin
    public SinglyLinkedList<T> findPathBFS(T start, T end)
    
    // O(V+E) - DFS pour trouver chemin
    public SinglyLinkedList<T> findPathDFS(T start, T end)
}
```

## 🔐 Gestion des Erreurs

### Validations Utilisateur
- ✅ Nom non vide
- ✅ Nom unique (pas de doublons)
- ✅ Utilisateur existe avant suppression
- ✅ Pas d'amitié/abonnement avec soi-même

### Gestion des Relations
- ✅ Les deux utilisateurs existent
- ✅ Pas d'amis en doublon
- ✅ Suppression en cascade quand un utilisateur est supprimé

### Opérations sur Publications
- ✅ Utilisateur existe
- ✅ Publication existe (via son ID)
- ✅ L'utilisateur peut commenter n'importe quelle publication

## 🎯 Complexité des Opérations

| Opération | Complexité | Raison |
|-----------|-----------|--------|
| Ajouter utilisateur | O(n) | Vérification des doublons |
| Supprimer utilisateur | O(n) | Parcours des relations |
| Ajouter ami | O(n) | Recherche utilisateur |
| Poster publication | O(n) | Recherche utilisateur |
| Commenter | O(n) | Recherche publication |
| Trouver chemin BFS | O(V+E) | Parcours graphe |
| Afficher amis | O(n) | Traversal liste |

## 🔄 Exemple Complet: Créer une Amitié

```
1. Appel: network.addFriend("Alice", "Bob")

2. Recherche Alice:
   - Parcourir DoublyLinkedList<User> users O(n)
   - Comparer chaque nom (case-insensitive)
   - Trouver l'objet User

3. Recherche Bob:
   - Idem

4. Vérification:
   - user1 != null ? ✓
   - user2 != null ? ✓
   - user1 != user2 ? ✓
   - user1.isFriendWith(user2) ? Vérifier

5. Ajouter la relation:
   - alice.friends.insert(bob) O(n)
   - bob.friends.insert(alice) O(n)

6. Structure résultante:
   Alice {
     friends: [Bob, ...]
   }
   Bob {
     friends: [Alice, ...]
   }
```

## 🛡️ Invariants Maintenus

1. **Intégrité des utilisateurs**: Chaque utilisateur a un ID unique
2. **Intégrité des relations**: Les amis sont mutuels ou les abonnements sont orientés
3. **Cohérence des IDs**: Les IDs des publications et commentaires sont uniques
4. **Suppression en cascade**: Si un utilisateur est supprimé, toutes ses données disparaissent

## 📈 Scalabilité

L'application peut gérer:
- Centaines d'utilisateurs (DoublyLinkedList acceptable)
- Milliers de publications (SinglyLinkedList acceptable)
- Centaines de commentaires par publication (Queue acceptable)

Pour une vraie application:
- Utiliser des HashMaps pour recherche O(1) d'utilisateurs
- Utiliser des ArrayList pour accès rapide par index
- Utiliser une vraie base de données
