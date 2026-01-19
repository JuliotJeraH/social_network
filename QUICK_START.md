# 🚀 GUIDE DE DÉMARRAGE RAPIDE - MiniBook

## ⚡ Démarrage Rapide

### 1️⃣ Compilation

Ouvrez un terminal PowerShell et exécutez:

```powershell
cd c:\Users\HP\Desktop\Exam_structure\social_network
javac -d . src/structures/*.java src/models/*.java src/network/*.java src/App.java
```

### 2️⃣ Exécution

```powershell
java App
```

## 🎮 Guide d'Utilisation Interactive

### Exemple Complet: Créer un Petit Réseau

#### Étape 1: Ajouter des Utilisateurs
```
Menu: 1 (Gestion des utilisateurs)
  → 1 (Ajouter un utilisateur)
  → Nom: Alice
  → Retour (5)

Menu: 1
  → 1
  → Nom: Bob
  → Retour (5)

Menu: 1
  → 1
  → Nom: Charlie
  → Retour (5)

Menu: 1
  → 3 (Afficher tous les utilisateurs)
  → Retour (5)
```

#### Étape 2: Établir des Relations d'Amitié
```
Menu: 2 (Gestion des relations)
  → 1 (Ajouter un ami)
  → Premier utilisateur: Alice
  → Second utilisateur: Bob
  → Retour (8)

Menu: 2
  → 1
  → Premier utilisateur: Bob
  → Second utilisateur: Charlie
```

#### Étape 3: Poster des Publications
```
Menu: 3 (Gestion des publications)
  → 1 (Poster une publication)
  → Votre nom: Alice
  → Contenu: Bonjour, c'est mon premier post!
  → Retour (5)

Menu: 3
  → 1
  → Votre nom: Bob
  → Contenu: J'aime beaucoup ce réseau social!
```

#### Étape 4: Commenter les Publications
```
Menu: 3
  → 2 (Commenter une publication)
  → Votre nom: Bob
  → Nom de l'auteur: Alice
  → ID de la publication: 1
  → Commentaire: Super post Alice!
```

#### Étape 5: Afficher les Publications
```
Menu: 3
  → 3 (Afficher mes publications)
  → Votre nom: Alice
```

#### Étape 6: Consulter les Statistiques
```
Menu: 4 (Recherche et statistiques)
  → 1 (Afficher les statistiques globales)
```

## 📊 Structure des Menus

```
╔════════════════════════════════════════════════════════════╗
║                      MENU PRINCIPAL                        ║
╚════════════════════════════════════════════════════════════╝
1. 👤 Gestion des utilisateurs
   ├─ 1. Ajouter un utilisateur
   ├─ 2. Supprimer un utilisateur
   ├─ 3. Afficher tous les utilisateurs
   ├─ 4. Rechercher un utilisateur
   └─ 5. Retour

2. 👥 Gestion des relations
   ├─ 1. Ajouter un ami
   ├─ 2. Supprimer un ami
   ├─ 3. Ajouter un abonnement
   ├─ 4. Supprimer un abonnement
   ├─ 5. Afficher les amis
   ├─ 6. Afficher les abonnés
   ├─ 7. Trouver un chemin
   └─ 8. Retour

3. 📝 Gestion des publications
   ├─ 1. Poster une publication
   ├─ 2. Commenter une publication
   ├─ 3. Afficher mes publications
   ├─ 4. Afficher publications des amis
   └─ 5. Retour

4. 🔍 Recherche et statistiques
   ├─ 1. Afficher les statistiques
   ├─ 2. Compter les amis d'un utilisateur
   ├─ 3. Compter les abonnés d'un utilisateur
   └─ 4. Retour

5. 🚪 Quitter l'application
```

## 💡 Points Importants

### Noms d'Utilisateurs
- Les noms doivent être **uniques**
- La recherche est **insensible à la casse**
- Les noms ne peuvent pas être vides

### Relations
- **Amis** = Relation **mutuelle** bidirectionnelle (Alice amie Bob ↔ Bob amie Alice)
- **Abonnés** = Relation **orientée** (Alice s'abonne à Bob → Alice voit les posts de Bob)

### Publications et Commentaires
- Chaque publication a un **ID unique** automatique
- Les commentaires sont stockés dans une **file** (FIFO)
- Afficher une publication affiche aussi ses commentaires

### Recherche de Chemin
- Utilise **BFS** (Breadth-First Search)
- Cherche un chemin dans le graphe d'abonnement
- Retourne la route la plus courte entre deux utilisateurs

## 🛠️ Dépannage

### Q: Je reçois une erreur lors de la compilation
**R:** Vérifiez que vous êtes dans le bon répertoire et que tous les fichiers source existent.

### Q: L'application se ferme immédiatement
**R:** C'est normal si vous choisissez "5. Quitter" au menu principal.

### Q: Impossible de trouver un utilisateur
**R:** Vérifiez l'orthographe du nom. La recherche est sensible à la casse pour certaines opérations.

## 📝 Exemple de Sortie

```
═══════════════════════════════════════════════════════════
📋 LISTE DE TOUS LES UTILISATEURS
═══════════════════════════════════════════════════════════
1. 👤 [ID: 1] Alice | Inscrit le: 2025-01-20 14:30:45 | Amis: 1 | Abonnés: 0 | Publications: 1
2. 👤 [ID: 2] Bob | Inscrit le: 2025-01-20 14:30:46 | Amis: 1 | Abonnés: 0 | Publications: 1
3. 👤 [ID: 3] Charlie | Inscrit le: 2025-01-20 14:30:47 | Amis: 0 | Abonnés: 0 | Publications: 0
═══════════════════════════════════════════════════════════
```

## 🎓 Structures de Données Utilisées dans ce Projet

- 📚 **Stack**: Structure LIFO (Last In First Out)
- 🎯 **Queue**: Structure FIFO (First In First Out) - Pour les commentaires
- 🔗 **SinglyLinkedList**: Liste chaînée simple - Pour les publications
- 🔄 **DoublyLinkedList**: Liste chaînée double - Pour les amis et abonnés
- 🗺️ **Graph**: Graphe orienté - Pour les abonnements

Bon amusement avec MiniBook! 🎉
