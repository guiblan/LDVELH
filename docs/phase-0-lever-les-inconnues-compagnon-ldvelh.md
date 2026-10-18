# Phase 0 — Lever les inconnues qui menacent le délai

Objectif : préciser le minimum nécessaire pour construire la feuille et vérifier immédiatement la faisabilité de la cible Android.

## R-01 — Définir le contenu complet de la feuille d'aventure

- Priorité : P0
- Livrable : liste de champs validée pour chaque personnage, règles d'édition et présentation de l'équipe.
- Validation : la liste distingue clairement données initiales, valeurs courantes, valeurs calculées et informations éditables ; les champs non décidés restent identifiés.
- Dépendances : —
- Statut : Brouillon détaillé après entretien — validation finale requise

## R-02 — Rassembler les données initiales des six personnages et les premiers éléments du catalogue

- Priorité : P0
- Livrable : jeu de données structuré et vérifiable pour le livre, sans dépendance à une saisie manuelle répétée en jeu.
- Validation : les six personnages sont représentés ; leurs valeurs de base, maximums de vie et équipements de départ peuvent être vérifiés par le porteur du projet.
- Dépendances : R-01
- Statut : En cours — six fiches transcrites ; relecture et compléments requis

## R-03 — Prototyper la chaîne JavaFX/Gluon sur le téléphone Android réel

- Priorité : P0
- Livrable : décision de faisabilité et prototype exécutable.
- Validation : le prototype se construit, s'installe et démarre sur le téléphone ; une donnée locale survit à un redémarrage ; la voie d'accès aux fichiers est validée.
- Dépendances : —
- Statut : À faire

## R-04 — Arrêter les versions et outils compatibles pour Java, JavaFX/Gluon, Android SDK, build et stockage local

- Priorité : P0
- Livrable : choix technique consigné.
- Validation : build Android reproductible ; risques, éventuels coûts/licences et option de repli documentés ; ne pas engager les écrans complets avant le résultat de R-03.
- Dépendances : R-03
- Statut : À faire
