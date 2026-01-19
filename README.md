# 📱 Réseau Social Simplifié (MiniBook)

## 📋 Description
MiniBook est une application Java qui simule un mini réseau social où les utilisateurs peuvent s'ajouter mutuellement, établir des relations d'amitié/d'abonnement, poster des publications et les commenter.

### Caractéristiques principales:
- ✅ Gestion des utilisateurs (ajout, suppression, recherche)
- ✅ Gestion des relations (amis, abonnés, chemins)
- ✅ Gestion des publications et commentaires
- ✅ Recherche et statistiques
- ✅ Interface console interactive

## 🏗️ Architecture - Structures de Données Utilisées

### 1. **Structures Implémentées** (sans utiliser les collections Java)
- **Stack (Pile)** - Structure LIFO
- **Queue (File)** - Structure FIFO (utilisée pour les commentaires)
- **SinglyLinkedList (Liste simplement chaînée)** - Utilisée pour les publications
- **DoublyLinkedList (Liste doublement chaînée)** - Utilisée pour les amis et abonnés
- **Graph (Graphe orienté)** - Représente les relations d'abonnement

### 2. **Classes Métier**

#### User (👤)
- **Attributs**: id, nom, date d'inscription
- **Structures internes**:
  - SinglyLinkedList: publications
  - DoublyLinkedList: amis
  - DoublyLinkedList: abonnés

#### Publication (📝)
- **Attributs**: id, contenu, auteur, timestamp
- **Structures internes**:
  - Queue: commentaires (File)

#### Comment (💬)
- **Attributs**: id, contenu, auteur, timestamp

## 📂 Structure du Projet

```
social_network/
├── src/
│   ├── App.java                 # Interface console principale
│   ├── structures/
│   │   ├── Node.java           # Nœud simple
│   │   ├── DoubleNode.java     # Nœud double
│   │   ├── Stack.java          # Pile (LIFO)
│   │   ├── Queue.java          # File (FIFO)
│   │   ├── SinglyLinkedList.java   # Liste simplement chaînée
│   │   ├── DoublyLinkedList.java   # Liste doublement chaînée
│   │   └── Graph.java          # Graphe orienté
│   ├── models/
│   │   ├── User.java           # Modèle utilisateur
│   │   ├── Publication.java    # Modèle publication
│   │   └── Comment.java        # Modèle commentaire
│   └── network/
│       └── SocialNetwork.java   # Gestionnaire du réseau social
├── lib/
├── README.md
```

## 🚀 Compilation et Exécution

### Prérequis
- Java JDK 8 ou supérieur

### Compilation
```bash
# Windows
cd c:\Users\HP\Desktop\Exam_structure\social_network
javac -d . src/structures/*.java src/models/*.java src/network/*.java src/App.java

# Linux/Mac
cd ~/Desktop/Exam_structure/social_network
javac -d . src/structures/*.java src/models/*.java src/network/*.java src/App.java
```

### Exécution
```bash
# Windows
java App

# Linux/Mac
java App
```

## 📋 Fonctionnalités Détaillées

### 1. Gestion des Utilisateurs
- **Ajouter un utilisateur** - Crée un nouvel utilisateur avec nom unique
- **Supprimer un utilisateur** - Supprime aussi toutes ses relations et publications
- **Afficher tous les utilisateurs** - Liste complète avec statistiques
- **Rechercher un utilisateur** - Par nom (insensible à la casse)

### 2. Gestion des Relations
- **Ajouter un ami** - Relation mutuelle entre deux utilisateurs
- **Supprimer un ami** - Supprime la relation mutuelle
- **Ajouter un abonnement** - Relation orientée (abonné -> créateur)
- **Supprimer un abonnement** - Supprime la relation
- **Afficher les amis** - Liste des amis d'un utilisateur
- **Afficher les abonnés** - Liste des abonnés d'un utilisateur
- **Trouver un chemin** - Utilise BFS pour trouver un chemin entre deux utilisateurs

### 3. Gestion des Publications
- **Poster une publication** - Ajoute une publication à l'utilisateur
- **Commenter** - Ajoute un commentaire à une publication (dans une file)
- **Afficher mes publications** - Affiche toutes les publications avec leurs commentaires
- **Afficher publications des amis** - Affiche les publications de tous les amis

### 4. Recherche et Statistiques
- **Nombre total d'utilisateurs**
- **Utilisateur avec le plus d'amis**
- **Utilisateur avec le plus d'abonnés**
- **Utilisateur avec le plus de publications**
- **Publication avec le plus de commentaires**

## 💡 Exemples d'Utilisation

### Scénario 1: Créer un réseau simple
```
1. Ajouter 3 utilisateurs: Alice, Bob, Charlie
2. Établir l'amitié: Alice ↔ Bob
3. Établir l'amitié: Bob ↔ Charlie
4. Alice poste une publication
5. Bob commente la publication d'Alice
```

### Scénario 2: Gérer les abonnements
```
1. Charlie s'abonne à Alice
2. Bob s'abonne à Charlie
3. Trouver le chemin Charlie → Alice
```

## 🔍 Détails d'Implémentation

### Pile (Stack)
- Opérations: push, pop, peek, isEmpty
- Complexité: O(1) pour tous les opérations

### File (Queue)
- Opérations: enqueue, dequeue, peek, isEmpty
- Complexité: O(1) pour tous les opérations

### Liste Simplement Chaînée
- Opérations: insert, remove, get, contains
- Complexité: O(n) pour remove, get, contains

### Liste Doublement Chaînée
- Opérations: insert, insertFirst, remove, get, contains
- Complexité: O(n) pour traversal

### Graphe
- Parcours: DFS et BFS pour trouver les chemins
- Opérations: addVertex, addEdge, removeVertex, removeEdge

## ✨ Validations Implémentées

✅ Vérification des noms uniques d'utilisateurs  
✅ Vérification des utilisateurs inexistants  
✅ Pas d'amitié ou d'abonnement à soi-même  
✅ Éviction des doublons dans les relations  
✅ Suppression en cascade (utilisateur supprime ses relations et publications)  

## 📝 Notes Importantes

- **Aucune collection Java prédéfinie** n'est utilisée (pas de ArrayList, HashMap, etc.)
- Toutes les structures sont implémentées from scratch
- Les IDs sont générés automatiquement avec des compteurs statiques
- Les timestamps sont générés avec LocalDateTime
- L'affichage des files de commentaires utilise un système de copie temporaire

## 👨‍💻 Auteur
Projet d'examen - Structure de Données en Java

## 📄 Licence
Projet éducatif
