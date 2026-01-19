# 📑 INDEX COMPLET DU PROJET

## 📂 Structure des Fichiers

```
social_network/
│
├── 📄 README.md                          # Documentation principale
├── 📄 QUICK_START.md                     # Guide de démarrage rapide
├── 📄 ARCHITECTURE.md                    # Documentation technique
├── 📄 TEST_PLAN.md                       # Plan de test complet
├── 📄 SUJET_IMPLEMENTATION.md           # Mapping sujet → code
├── 📄 this file (INDEX.md)
│
├── 🚀 run.bat                            # Script pour lancer l'application
├── 🎬 demo.sh                            # Script de démonstration
│
└── 📁 src/
    │
    ├── 🔷 App.java                       # Application principale (interface console)
    │   └── ~600 lignes - Menu interactif
    │
    ├── 📁 structures/                    # Structures de données personnalisées
    │   ├── Node.java                    # Nœud simple pour listes chaînées
    │   ├── DoubleNode.java              # Nœud double pour listes bidirectionnelles
    │   ├── Stack.java                   # Pile (LIFO)
    │   ├── Queue.java                   # File (FIFO) - Pour commentaires
    │   ├── SinglyLinkedList.java        # Liste simplement chaînée - Pour publications
    │   ├── DoublyLinkedList.java        # Liste doublement chaînée - Pour amis/abonnés
    │   └── Graph.java                   # Graphe orienté - Pour abonnements
    │
    ├── 📁 models/                        # Classes métier (entités du domaine)
    │   ├── User.java                    # Représente un utilisateur
    │   ├── Publication.java             # Représente une publication
    │   └── Comment.java                 # Représente un commentaire
    │
    └── 📁 network/                       # Logique métier
        └── SocialNetwork.java           # Gestionnaire principal du réseau social

└── 📁 lib/                               # Dépendances (actuellement vide)
```

## 📊 Statistiques du Projet

| Métrique | Valeur |
|----------|--------|
| Fichiers Java | 12 |
| Lignes de code | ~2500 |
| Packages | 4 |
| Classes | 12 |
| Méthodes principales | 50+ |

## 🗂️ Fichiers par Catégorie

### 🔧 Configuration & Documentation
| Fichier | Objectif | Lire d'abord? |
|---------|----------|---------------|
| README.md | Vue d'ensemble générale | ⭐⭐⭐ |
| QUICK_START.md | Démarrage rapide | ⭐⭐⭐ |
| ARCHITECTURE.md | Détails implémentation | ⭐⭐ |
| SUJET_IMPLEMENTATION.md | Mapping sujet→code | ⭐⭐ |
| TEST_PLAN.md | Plan de test | ⭐ |

### 🏗️ Structures de Données
| Fichier | Rôle | Complexité |
|---------|------|-----------|
| Node.java | Bloc de base | Trivial |
| DoubleNode.java | Nœud double | Trivial |
| Stack.java | Pile LIFO | Facile |
| Queue.java | File FIFO | Facile |
| SinglyLinkedList.java | Liste simple | Moyen |
| DoublyLinkedList.java | Liste double | Moyen |
| Graph.java | Graphe orienté | Complexe |

### 🎯 Modèles Métier
| Fichier | Entité | Dépendances |
|---------|--------|-------------|
| Comment.java | Commentaire | Aucune |
| Publication.java | Publication | Queue<Comment> |
| User.java | Utilisateur | SinglyLinkedList, DoublyLinkedList |

### ⚙️ Logique & Interface
| Fichier | Rôle | Lignes |
|---------|------|--------|
| SocialNetwork.java | Gestionnaire principal | ~390 |
| App.java | Interface console | ~600 |

## 🚀 Flux de Lecture Recommandé

### Pour Comprendre le Projet (1ère lecture)
1. **README.md** - Vue d'ensemble (5 min)
2. **QUICK_START.md** - Utilisation basique (10 min)
3. **SUJET_IMPLEMENTATION.md** - Correspondance sujet (15 min)

### Pour Implémenter/Modifier (2ème lecture)
4. **ARCHITECTURE.md** - Design détaillé (20 min)
5. **structures/** - Les structures (30 min)
6. **models/** - Les entités (15 min)
7. **network/SocialNetwork.java** - La logique (30 min)
8. **App.java** - L'interface (20 min)

### Pour Tester/Déboguer (3ème lecture)
9. **TEST_PLAN.md** - Cas de test (30 min)
10. Tester chaque fonctionnalité (1h)

## 📌 Points d'Entrée

### Pour Lancer l'Application
```bash
java App
```

### Pour Modifier la Logique
→ Regarder: [SocialNetwork.java](src/network/SocialNetwork.java)

### Pour Ajouter une Structure
→ Regarder: [structures/](src/structures/)

### Pour Ajouter une Entité
→ Regarder: [models/](src/models/)

### Pour Ajouter une Fonctionnalité Menu
→ Regarder: [App.java](App.java)

## 🔍 Localisation des Fonctionnalités

### Gestion des Utilisateurs
- **Ajouter/Supprimer**: [SocialNetwork.java](src/network/SocialNetwork.java#L44-L67)
- **Afficher**: [App.java](App.java) - Méthode `userManagementMenu()`

### Gestion des Relations
- **Amis**: [User.java](src/models/User.java) + [SocialNetwork.java](src/network/SocialNetwork.java#L93-L120)
- **Abonnements**: [SocialNetwork.java](src/network/SocialNetwork.java#L122-L149)
- **Chemins**: [Graph.java](src/structures/Graph.java#L125-L152)

### Gestion des Publications
- **Poster**: [SocialNetwork.java](src/network/SocialNetwork.java#L193-L202)
- **Commenter**: [SocialNetwork.java](src/network/SocialNetwork.java#L204-L223)
- **Afficher**: [SocialNetwork.java](src/network/SocialNetwork.java#L225-L285)

### Statistiques
- **Statistiques globales**: [SocialNetwork.java](src/network/SocialNetwork.java#L365-L390)
- **Requêtes**: [SocialNetwork.java](src/network/SocialNetwork.java#L288-L363)

## 🧪 Tests Importants à Faire

| Test | Où | Résultat Attendu |
|------|-----|------------------|
| Ajouter utilisateur | App menu 1.1 | ✅ Succès + affichage |
| Doublon nom | App menu 1.1 | ❌ Erreur appropriée |
| Ajouter ami | App menu 2.1 | ✅ Relation mutuelle |
| Poster publication | App menu 3.1 | ✅ Dans SinglyLinkedList |
| Commenter | App menu 3.2 | ✅ Dans Queue (FIFO) |
| Afficher publications | App menu 3.3 | ✅ Avec commentaires |
| Statistiques | App menu 4.1 | ✅ Tous les chiffres |
| Chemin BFS | App menu 2.7 | ✅ Route trouvée/non trouvée |

## 💡 Conseils d'Utilisation

### Pour les Étudiants
- Lire d'abord README.md
- Tester via l'interface console
- Lire le code en commençant par le bas (structures)
- Puis monter vers le haut (application)

### Pour les Évaluateurs
- Vérifier SUJET_IMPLEMENTATION.md pour la couverture
- Exécuter les cas de test du TEST_PLAN.md
- Consulter ARCHITECTURE.md pour la validation du design

### Pour les Contributeurs
- Respecter le pattern package structure
- Ajouter des tests dans TEST_PLAN.md
- Mettre à jour ARCHITECTURE.md si changements

## 📞 Aide Rapide

**Q: Où commencer?**
A: README.md puis QUICK_START.md

**Q: Comment fonctionne la Queue?**
A: Voir Queue.java et Publication.java

**Q: Comment fonctionne le graphe?**
A: Voir Graph.java et ARCHITECTURE.md

**Q: Où ajouter une nouvelle fonctionnalité?**
A: Implémenter dans SocialNetwork.java puis ajouter le menu dans App.java

**Q: Comment les amis sont stockés?**
A: DoublyLinkedList dans User.java (structure bidirectionnelle)

**Q: Les commentaires sont dans quoi?**
A: Queue (FIFO) dans Publication.java

## 🎓 Apprentissage par Structure

### Pour Apprendre les Listes Chaînées
1. Node.java - Comprendre le concept
2. SinglyLinkedList.java - Implémentation
3. User.java - Utilisation réelle

### Pour Apprendre les Files
1. Queue.java - L'implémentation
2. Publication.java - Utilisation avec commentaires
3. App.java - Tester via menu 3.2

### Pour Apprendre les Graphes
1. Graph.java - L'implémentation complète
2. SocialNetwork.java - Intégration
3. TEST_PLAN.md - Test des chemins (phase 2)

## 📈 Progression Recommandée

```
Semaine 1: Structures
├── Day 1: Node + Stack + Queue
├── Day 2: SinglyLinkedList
├── Day 3: DoublyLinkedList
└── Day 4: Graph

Semaine 2: Modèles
├── Day 1: Comment
├── Day 2: Publication
└── Day 3: User

Semaine 3: Logique
├── Day 1-2: SocialNetwork (moitié)
├── Day 3-4: SocialNetwork (suite)
└── Day 5: Intégration tests

Semaine 4: Interface & Tests
├── Day 1-2: App.java
├── Day 3-4: Tests manuels
└── Day 5: Ajustements
```

## ✨ Highlights du Code

### Partie Favorite 1: File pour Commentaires
```java
// Publication.java
private Queue<Comment> comments;  // FIFO = ordre chronologique

public void addComment(Comment comment) {
    comments.enqueue(comment);    // Les plus anciens d'abord
}
```

### Partie Favorite 2: Relations Mutuelles
```java
// SocialNetwork.java
public boolean addFriend(String userName1, String userName2) {
    user1.addFriend(user2);  // Alice ← → Bob
    user2.addFriend(user1);  // Bidirectionnel
}
```

### Partie Favorite 3: Graphe pour Abonnements
```java
// SocialNetwork.java
private Graph<User> subscriptionGraph;  // Orienté
public SinglyLinkedList<User> findPathBetweenUsers(...) {
    return subscriptionGraph.findPathBFS(...);  // BFS O(V+E)
}
```

## 🎯 Objectif Pédagogique

Ce projet enseigne:
- ✅ Implémentation manuelle de structures de données
- ✅ Choix approprié de structure pour chaque cas
- ✅ Design orienté objet
- ✅ Gestion de complexité algorithmique
- ✅ Interface utilisateur console
- ✅ Gestion d'erreurs
- ✅ Documentation de code

---

**Bienvenue dans MiniBook!** 🎉
