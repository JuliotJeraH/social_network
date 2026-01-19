#!/bin/bash
# Script de démonstration du réseau social

echo "Bienvenue au test du Réseau Social Simplifié!"
echo ""

# Simulation des entrées
(
    # Menu principal -> Gestion des utilisateurs (1)
    echo "1"
    sleep 0.5
    
    # Ajouter un utilisateur (1)
    echo "1"
    echo "Alice"
    sleep 0.5
    
    # Retour à la gestion des utilisateurs (1)
    echo "1"
    echo "1"
    echo "Bob"
    sleep 0.5
    
    # Retour (1)
    echo "1"
    echo "1"
    echo "Charlie"
    sleep 0.5
    
    # Afficher tous les utilisateurs (3)
    echo "3"
    sleep 1
    
    # Retour au menu principal (5)
    echo "5"
    
    # Quitter (5)
    echo "5"
    
) | java App
