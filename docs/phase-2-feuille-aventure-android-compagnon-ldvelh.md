# Phase 2 — Livrer la feuille d'aventure Android

Objectif : permettre de créer la partie, jouer et retrouver l'état complet sans devoir effacer et réécrire la feuille papier.

## R-10 — Réaliser les écrans Android nécessaires

- Priorité : P1
- Livrable : parcours jouable sur téléphone, avec bibliothèque initiale, création de l'aventure, sélection des quatre personnages, feuille d'équipe et fiches individuelles.
- Validation : depuis une installation vierge, le porteur du projet peut créer l'aventure et consulter les quatre fiches ainsi que l'équipement de départ.
- Dépendances : R-06, R-07, R-08
- Statut : À faire

## R-11 — Ajouter la mise à jour rapide de la section actuelle et des points de vie

- Priorité : P1
- Livrable : édition simple des données fréquemment réécrites sur papier.
- Validation : les nouvelles valeurs valides sont visibles immédiatement ; les points de vie ne dépassent pas le maximum ; les caractéristiques de base restent protégées.
- Dépendances : R-07, R-10
- Statut : À faire

## R-12 — Permettre d'ajouter, retirer et attribuer l'équipement

- Priorité : P1
- Livrable : gestion de l'équipement en partie.
- Validation : les restrictions propres à chaque équipement sont respectées ; les caractéristiques effectives sont recalculées ; les autres personnages ne sont pas modifiés par erreur.
- Dépendances : R-08, R-09, R-10
- Statut : À faire

## R-13 — Enregistrer localement les changements de la feuille et de la section

- Priorité : P1
- Livrable : reprise après fermeture.
- Validation : après fermeture forcée ou normale puis relance, la section, les personnages, les points de vie et l'équipement correspondent au dernier état effectivement enregistré ; une erreur n'est pas présentée comme un succès.
- Dépendances : R-04, R-05, R-10, R-11, R-12
- Statut : À faire

## R-14 — Valider les principaux parcours et erreurs sur le téléphone cible

- Priorité : P1
- Livrable : compte-rendu de test bêta.
- Validation : installation, création, changements de section, édition des fiches, règles de points de vie, équipement, fermeture/réouverture et fonctionnement hors ligne sont vérifiés sur l'appareil.
- Dépendances : R-13
- Statut : À faire
