# Phase 4 — Enrichir après retour de bêta

Objectif : étendre les usages sans bloquer la livraison initiale ni compromettre les données déjà créées.

## R-19 — Recueillir les retours de bêta et ajuster les irritants prioritaires

- Priorité : P2
- Livrable : liste de changements triée selon l'usage réel.
- Validation : chaque changement retenu correspond à un problème observé ou à un besoin confirmé ; le socle de données est préservé.
- Dépendances : R-18
- Statut : À faire

## R-20 — Ajouter plusieurs aventures et leur accueil de reprise

- Priorité : P2
- Livrable : gestion de plusieurs aventures en parallèle.
- Validation : deux aventures peuvent coexister sans partager accidentellement leurs personnages, états ou équipements.
- Dépendances : R-05, R-13
- Statut : À faire

## R-21 — Ajouter historique détaillé des sections, notes et création/reprise de branches

- Priorité : P2
- Livrable : gestion des branches sans altérer les parcours passés.
- Validation : une reprise depuis un point passé conserve la branche d'origine et l'état des personnages au point choisi.
- Dépendances : R-05, R-20
- Statut : À faire

## R-22 — Compléter le cycle de vie (pause/reprise/terminée)

- Priorité : P2
- Livrable : règles de vie de l'aventure et accès associé.
- Validation : les transitions sont cohérentes ; une aventure terminée reste consultable.
- Dépendances : R-20
- Statut : À faire

## R-23 — Étendre bibliothèque et gestion des livres/personnages/équipements

- Priorité : P2
- Livrable : catalogue enrichi et personnalisation possible.
- Validation : les ajouts et personnalisations ne modifient pas silencieusement le modèle de base ni les parties existantes.
- Dépendances : R-05, R-08
- Statut : À faire

## R-24 — Compléter les conflits d'import par comparaison et choix explicite champ par champ

- Priorité : P2
- Livrable : gestion avancée des importations.
- Validation : annuler ne modifie rien ; confirmer applique les choix de façon atomique ; les branches restent des parcours cohérents.
- Dépendances : R-17, R-21
- Statut : À faire

## R-25 — Porter l'application sur Windows puis Linux

- Priorité : P2
- Livrable : adaptation multi-plateforme avec partage du domaine et des règles métier.
- Validation : parcours et données équivalents ; persistance et import/export vérifiés sur chaque cible ; aucune régression Android.
- Dépendances : R-04, R-19
- Statut : À faire
