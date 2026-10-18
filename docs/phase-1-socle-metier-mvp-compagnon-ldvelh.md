# Phase 1 — Construire le socle métier et les données du MVP

Objectif : représenter sans perte l'aventure, la feuille des quatre personnages et le catalogue, indépendamment de l'interface Android.

## R-05 — Définir le modèle de données versionnable pour le livre, l'aventure, les personnages et l'équipement

- Priorité : P1
- Livrable : modèle métier et stratégie de persistance.
- Validation : les valeurs initiales ne sont pas écrasées par les valeurs courantes ; l'état de la feuille et les données de catalogue restent cohérents après lecture/écriture.
- Dépendances : R-01, R-04
- Statut : À faire

## R-06 — Intégrer le catalogue de base du tome 1, les six personnages et leurs valeurs de départ

- Priorité : P1
- Livrable : données initiales disponibles dès la première ouverture.
- Validation : la bibliothèque présente le livre et le choix de personnages permet de sélectionner quatre personnages distincts parmi six.
- Dépendances : R-02, R-05
- Statut : À faire

## R-07 — Définir et implémenter la validation de l'état des personnages

- Priorité : P1
- Livrable : règles métier testées.
- Validation : les valeurs invalides sont refusées avec un retour explicite ; une modification d'une valeur courante ne modifie pas sa valeur initiale.
- Dépendances : R-01, R-05
- Statut : À faire

## R-08 — Modéliser les équipements du livre, leurs modificateurs et leurs limites de quantité portée

- Priorité : P1
- Livrable : catalogue extensible et règles associées.
- Validation : un équipement peut être défini une fois puis attribué ; sa règle de quantité est contrôlée ; un ajout au catalogue reste disponible ensuite ; les valeurs de modificateurs sont vérifiables.
- Dépendances : R-02, R-05
- Statut : À faire

## R-09 — Calculer les caractéristiques effectives à partir des valeurs de base et des modificateurs d'équipement

- Priorité : P1
- Livrable : calcul métier centralisé, utilisé par l'interface.
- Validation : l'ajout, le retrait ou le changement d'équipement met à jour les valeurs effectives sans altérer les valeurs de base.
- Dépendances : R-07, R-08
- Statut : À faire
