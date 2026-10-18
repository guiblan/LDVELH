# Phase 3 — Sauvegarder, restaurer et distribuer la bêta

Objectif : protéger les données personnelles et rendre la version bêta installable et récupérable.

## R-15 — Définir un format JSON versionné couvrant le catalogue, l'aventure, la feuille des personnages et l'équipement

- Priorité : P1
- Livrable : schéma documenté et exemples de test.
- Validation : un export inclut toutes les données nécessaires à la reconstruction de la feuille ; les données personnelles et le catalogue sont distinguables.
- Dépendances : R-05, R-08
- Statut : À faire

## R-16 — Implémenter l'export JSON et l'enregistrement du fichier via le mécanisme Android prévu

- Priorité : P1
- Livrable : sauvegarde manuelle récupérable par l'utilisateur.
- Validation : le fichier exporté est valide, lisible par l'application et sauvegardé à un emplacement choisi ou clairement accessible ; les erreurs sont signalées.
- Dépendances : R-03, R-15
- Statut : À faire

## R-17 — Implémenter l'import JSON avec validation avant écriture et comportement explicite en cas de fichier invalide

- Priorité : P1
- Livrable : restauration contrôlée.
- Validation : un fichier invalide ne modifie pas les données locales ; un export de l'application peut être réimporté et restituer la feuille ; les conflits ne sont jamais écrasés silencieusement.
- Dépendances : R-15, R-16
- Statut : À faire

## R-18 — Produire, installer et remettre la bêta Android pour test personnel

- Priorité : P1
- Livrable : APK ou paquet Android signé selon la chaîne retenue et procédure associée.
- Validation : installation et lancement sur le téléphone cible confirmés ; les limites connues et la procédure de restauration sont communiquées.
- Dépendances : R-14, R-16, R-17
- Statut : À faire
