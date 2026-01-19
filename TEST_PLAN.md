# 🧪 PLAN DE TEST - MiniBook

## 🎯 Objectif
Valider l'implémentation complète du réseau social simplifié en testant toutes les fonctionnalités requises par le sujet d'examen.

## ✅ Tests des Structures de Données

### 1. Stack (Pile)
- [ ] Créer une pile
- [ ] Push plusieurs éléments
- [ ] Pop retourne les éléments en LIFO
- [ ] Peek ne modifie pas la pile
- [ ] isEmpty fonctionne correctement

### 2. Queue (File - Commentaires)
- [ ] Enqueue ajoute correctement
- [ ] Dequeue retourne en FIFO
- [ ] Size augmente et diminue
- [ ] Queue vide après dequeue complet

### 3. SinglyLinkedList (Publications)
- [ ] Insert ajoute à la fin
- [ ] Get accède par index
- [ ] Remove supprime correctement
- [ ] Contains trouve les éléments
- [ ] Size exact après opérations

### 4. DoublyLinkedList (Amis/Abonnés)
- [ ] Insert ajoute à la fin
- [ ] InsertFirst ajoute au début
- [ ] Remove supprime correctement
- [ ] Traversal avant/arrière possible
- [ ] Size exact

### 5. Graph (Abonnements)
- [ ] AddVertex ajoute un sommet
- [ ] AddEdge crée une arête orientée
- [ ] RemoveEdge supprime correctement
- [ ] FindPathBFS trouve le chemin optimal
- [ ] GetAdjacents retourne les voisins

## ✅ Tests de Gestion des Utilisateurs

### Ajouter Utilisateur
```
Test 1: Ajouter un utilisateur normal
- Input: "Alice"
- Expected: ✅ Succès, utilisateur créé
- Verificación: ID unique, timestamp présent

Test 2: Ajouter utilisateur avec nom doublé
- Input 1: "Bob"
- Input 2: "Bob" (même nom)
- Expected: ✅ Succès pour le 1er, ❌ Erreur pour le 2e
- Vérification: Message d'erreur approprié

Test 3: Ajouter utilisateur avec nom vide
- Input: ""
- Expected: ❌ Erreur, pas de création
```

### Supprimer Utilisateur
```
Test 1: Supprimer utilisateur existant
- Setup: Créer Alice
- Input: "Alice"
- Expected: ✅ Suppression réussie
- Vérification: Alice n'existe plus

Test 2: Supprimer utilisateur inexistant
- Input: "NonExistent"
- Expected: ❌ Erreur appropriée

Test 3: Supprimer user avec relations
- Setup: Alice et Bob amis, Charlie abonné à Alice
- Input: "Alice"
- Expected: ✅ Alice supprimée, relations cascadées
- Vérification: Bob n'a plus Alice comme amie
```

### Afficher Utilisateurs
```
Test: Afficher liste utilisateurs
- Setup: Créer 3 utilisateurs
- Expected: ✅ Affichage formaté avec stats
- Vérification: 
  - Tous les utilisateurs affichés
  - IDs corrects
  - Timestamps présents
  - Compteurs exacts (amis, abonnés, publications)
```

### Rechercher Utilisateur
```
Test 1: Recherche exacte
- Setup: Alice existe
- Input: "Alice"
- Expected: ✅ Trouvé avec toutes les infos

Test 2: Recherche insensible casse
- Input: "alice" ou "ALICE"
- Expected: ✅ Trouvé (si implémenté ainsi)

Test 3: Utilisateur inexistant
- Input: "NonExistent"
- Expected: ✅ Message "non trouvé"
```

## ✅ Tests de Gestion des Relations

### Ajouter Amitié
```
Test 1: Ajouter amis valides
- Setup: Créer Alice et Bob
- Input: "Alice", "Bob"
- Expected: ✅ Relation mutuelle établie
- Vérification:
  - alice.friends contient bob
  - bob.friends contient alice

Test 2: Essayer amitié avec soi-même
- Input: "Alice", "Alice"
- Expected: ❌ Erreur appropriée

Test 3: Ajouter amis inexistants
- Input: "Inexistant1", "Inexistant2"
- Expected: ❌ Erreur

Test 4: Dupliquer amitié
- Setup: Alice et Bob déjà amis
- Input: "Alice", "Bob" à nouveau
- Expected: ✅ Pas d'erreur mais pas de doublon
```

### Supprimer Amitié
```
Test 1: Supprimer amitié existante
- Setup: Alice et Bob amis
- Input: "Alice", "Bob"
- Expected: ✅ Relation supprimée
- Vérification: Bidirectionnel supprimé

Test 2: Supprimer amitié inexistante
- Input: Utilisateurs jamais amis
- Expected: ✅ Pas d'erreur mais rien ne se passe
```

### Afficher Amis/Abonnés
```
Test 1: Afficher amis d'utilisateur
- Setup: Alice a 2 amis (Bob, Charlie)
- Expected: ✅ Liste formatée de 2 amis

Test 2: Afficher abonnés (0)
- Setup: Alice a 0 abonnés
- Expected: ✅ Message "Aucun abonné"
```

### Trouver Chemin
```
Test 1: Chemin direct
- Setup: Alice → Bob (abonnement orienté)
- Input: "Alice", "Bob"
- Expected: ✅ Chemin trouvé [Alice, Bob]

Test 2: Chemin multi-sauts
- Setup: Alice → Bob, Bob → Charlie
- Input: "Alice", "Charlie"
- Expected: ✅ Chemin [Alice, Bob, Charlie]

Test 3: Pas de chemin
- Setup: Alice et Bob isolés
- Input: "Alice", "Bob"
- Expected: ✅ Message "Aucun chemin"
```

## ✅ Tests de Gestion des Publications

### Poster Publication
```
Test 1: Poster publication valide
- Input: "Alice", "Mon premier post!"
- Expected: ✅ Publication créée
- Vérification:
  - ID unique
  - Timestamp présent
  - Contenu exact
  - Ajoutée à la liste

Test 2: Poster avec utilisateur inexistant
- Input: "NonExistent", "Contenu"
- Expected: ❌ Erreur

Test 3: Contenu vide
- Input: "Alice", ""
- Expected: ❌ Erreur (si validé) ou ✅ Publication vide
```

### Commenter Publication
```
Test 1: Commenter publication existante
- Setup: Alice a 1 publication (ID 1)
- Input: Bob commente
- Expected: ✅ Commentaire ajouté à la file
- Vérification:
  - Commentaire dans Queue
  - FIFO order maintenu
  - Timestamp correct

Test 2: Commenter publication inexistante
- Input: ID invalide
- Expected: ❌ Erreur

Test 3: Plusieurs commentaires
- Setup: Ajouter 3 commentaires
- Expected: ✅ Tous présents dans Queue
- Vérification: Order FIFO respecté
```

### Afficher Publications Personnelles
```
Test 1: Afficher publications avec commentaires
- Setup: Alice a 2 publications avec commentaires
- Expected: ✅ Format:
  - Publication avec metadata
  - Liste des commentaires en indentation
  - Compteur exact

Test 2: Utilisateur sans publications
- Expected: ✅ Message "Aucune publication"
```

### Afficher Publications des Amis
```
Test 1: Afficher publications d'amis
- Setup: Alice amie de Bob et Charlie
  - Bob a 2 publications
  - Charlie a 1 publication
- Expected: ✅ Toutes les publications affichées
- Vérification: Groupées par auteur

Test 2: Utilisateur sans amis
- Expected: ✅ Message "Aucun ami"
```

## ✅ Tests de Recherche et Statistiques

### Statistiques Globales
```
Test 1: Afficher stats
- Setup: 3 utilisateurs, 5 publications, 8 commentaires
- Expected: ✅ Affichage de:
  - Total d'utilisateurs
  - Utilisateur avec plus d'amis
  - Utilisateur avec plus d'abonnés
  - Utilisateur avec plus de publications
  - Publication avec plus de commentaires
```

### Compter Amis/Abonnés
```
Test 1: Compte correct
- Setup: Alice a 3 amis
- Expected: ✅ Retourne 3

Test 2: Compte 0
- Setup: Charlie n'a pas d'amis
- Expected: ✅ Retourne 0
```

## 🧪 Scénario d'Intégration Complet

### Créer un Petit Réseau Social
```
Phase 1: Initialisation
├── Ajouter 5 utilisateurs: Alice, Bob, Charlie, Diana, Eve
├── Afficher la liste (vérifier IDs et timestamps)
└── Rechercher chaque utilisateur

Phase 2: Relations
├── Alice ↔ Bob (amis)
├── Bob ↔ Charlie (amis)
├── Charlie ↔ Diana (amis)
├── Alice → Eve (abonnement)
├── Bob → Diana (abonnement)
├── Afficher amis de Alice, Bob, etc.
└── Trouver chemin Alice → Diana

Phase 3: Publications
├── Alice poste 2 publications
├── Bob poste 1 publication
├── Charlie poste 3 publications
└── Afficher publications personnelles

Phase 4: Commentaires
├── Bob commente les posts d'Alice (2 commentaires)
├── Charlie commente le post de Bob (1 commentaire)
├── Alice commente les posts de Charlie (3 commentaires)
└── Afficher publications avec commentaires

Phase 5: Statistiques
├── Afficher stats globales
├── Compter amis de chaque utilisateur
├── Identifier l'utilisateur avec plus de publications
└── Identifier la publication avec plus de commentaires

Phase 6: Opérations de Suppression
├── Supprimer Charlie
├── Vérifier les cascades:
│   - Bob n'a plus Charlie comme ami
│   - Diana n'a plus Charlie
│   - Les publications de Charlie supprimées
│   - Les commentaires de Charlie supprimés
└── Afficher stats mises à jour
```

### Résultats Attendus
```
Total utilisateurs: 4 (Charlie supprimé)
Utilisateur avec plus d'amis: Alice (1) ou autre
Publication avec plus de commentaires: Post d'Alice (2)
```

## 📋 Checklist de Validation

- [ ] Toutes les structures de données implémentées
- [ ] Aucune collection Java prédéfinie utilisée
- [ ] Gestion des erreurs appropriée
- [ ] Interface console fonctionnelle
- [ ] Tous les menus accessibles
- [ ] Affichage formaté et lisible
- [ ] IDs générés correctement
- [ ] Timestamps présents
- [ ] Suppression en cascade
- [ ] Relations bidirectionnelles pour amis
- [ ] Queue FIFO pour commentaires
- [ ] Graphe dirigé pour abonnements

## 🐛 Bugs Potentiels à Tester

1. Vérifier que dequeue ne supprime pas
2. Vérifier l'ordre FIFO des commentaires
3. Vérifier les amitié mutuelles
4. Vérifier la suppression en cascade complète
5. Vérifier l'unicité des IDs
6. Vérifier la recherche insensible à la casse
