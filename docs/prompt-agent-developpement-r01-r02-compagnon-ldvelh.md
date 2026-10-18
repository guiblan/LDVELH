# Prompt d'agent de développement — R-01 et R-02

## Mission

Tu es l’agent de développement chargé de produire les livrables de R-01 et R-02 du projet Compagnon LDVELH.

Tu dois travailler exclusivement dans le cadre de cette mission, sans disperser le travail sur d’autres tâches du backlog. Tu ne dois pas te contenter d’une synthèse : tu dois produire des fichiers exploitables, structurés, et prêts à être utilisés par les étapes suivantes du projet.

## Contexte du projet

Le projet vise à créer une application Android de compagnon de jeu pour les livres dont vous êtes le héros, en partant d’une bêta utile, hors ligne, avec persistance locale et export JSON.

La priorité immédiate est la feuille d’aventure et la donnée métier, avant toute interface Android complète.

## Jeux de documents à lire avant toute action

Lit d’abord les documents suivants, dans l’ordre indiqué :

1. `docs/feuille-de-route-compagnon-ldvelh.md`
2. `docs/cadrage-fonctionnel-compagnon-ldvelh.md`
3. `docs/architecture-ux-compagnon-ldvelh.md`
4. `docs/phase-0-lever-les-inconnues-compagnon-ldvelh.md`
5. `docs/r-01-feuille-d-aventure-compagnon-ldvelh.md`

Tu dois utiliser ces documents comme sources de vérité. Si certaines informations sont contradictoires ou incomplètes, tu dois signaler explicitement l’incohérence et proposer une décision de travail, sans improviser au hasard.

## Objectif fonctionnel

### R-01 — Feuille d’aventure

Réaliser une spécification métier de la feuille d’aventure pour la bêta, avec une distinction explicite entre :

- données initiales ;
- valeurs courantes ;
- valeurs calculées ;
- champs éditables ;
- données de référence du livre ;
- zones non décidées ou à confirmer.

Le document final doit permettre de développer le domaine sans ambiguïté, et doit être directement exploitable pour la phase suivante du projet.

### R-02 — Données de base du livre

Rassembler les informations de base des six personnages et des premiers éléments du catalogue du livre, avec un format structuré et vérifiable.

Le travail doit inclure :

- les six personnages du livre ;
- leurs caractéristiques de base ;
- leurs max de points de vie ;
- leurs équipements de départ ;
- leur statut de données : validé / à confirmer / non fourni ;
- les éléments manquants clairement identifiés.

## Règles strictes

1. Travailler depuis la racine du dépôt.
2. Créer les livrables dans `docs/` uniquement.
3. Ne pas modifier des documents existants sans nécessité objective.
4. Ne pas supposer des données non fournies : si un élément manque, le marquer comme `À confirmer` ou `Non fourni dans la source`.
5. Garder une logique claire de provenance : chaque donnée doit pouvoir être reliée à une source de référence, même si elle est incomplète.
6. Écrire en français.
7. Produire des fichiers Markdown structurés et lisibles, avec titre, sections, tableaux si nécessaire, et sections de validation.
8. Si tu produis un JSON, il doit être cohérent, lisible, et exploitable pour les étapes de développement suivantes.
9. L’IA doit rendre le travail en gardant toujours le bon niveau : pas trop vague, pas trop technique pour l’instant, mais suffisamment précis pour que le prochain agent puisse développer.

## Fichiers attendus

Tu dois créer au minimum les fichiers suivants :

- `docs/r-01-feuille-d-aventure-validee-compagnon-ldvelh.md`
- `docs/r-02-donnees-personnages-compagnon-ldvelh.md`
- `docs/data/ldvelh-personnages-base.json`

Si un autre document est utile pour clarifier les décisions, tu peux le créer, mais sans surcharger inutilement le dépôt.

## Contenu attendu pour R-01

Le fichier `r-01-feuille-d-aventure-validee-compagnon-ldvelh.md` doit contenir :

1. un résumé de la feuille d’aventure pour la bêta ;
2. une liste exhaustive des champs requis par personnage ;
3. une ségrégation claire entre :
   - valeurs initiales ;
   - valeurs courantes ;
   - valeurs calculées ;
   - informations éditables ;
4. les règles de validation métier ;
5. la présentation attendue de la vue d’équipe ;
6. la structure d’une fiche individuelle détaillée ;
7. les éléments restant à confirmer ;
8. un tableau de décisions, hypothèses et validation ;
9. un point sur l’impact pour les cibles Windows et Linux.

## Contenu attendu pour R-02

Le fichier `r-02-donnees-personnages-compagnon-ldvelh.md` doit contenir :

1. la liste des six personnages de base du livre ;
2. leurs données initiales structurées ;
3. leurs max de points de vie ;
4. leurs équipements de départ ;
5. leurs caractéristiques de base ;
6. une colonne de statut : validé / à confirmer / non fourni ;
7. les différences entre données du livre et données de jeu ;
8. les conséquences pour le calcul métier à venir.

Le fichier JSON `docs/data/ldvelh-personnages-base.json` doit contenir une structure exploitable, par exemple :

- `book`
- `characters[]`
- `baseStats`
- `maxHp`
- `startingEquipment`
- `notes`
- `status`

Tu peux ajouter des champs utiles, tant que la structure reste logique et cohérente.

## Validation de la mission

La mission est réussie si :

- les documents sont créés dans `docs/` ;
- les données sont structurées et exploitables ;
- la feuille d’aventure est explicitement séparée entre base, état courant et calcul ;
- les six personnages sont traités, avec des données incomplètes clairement marquées ;
- les points à confirmer ne sont pas noyés dans le reste ;
- le travail est assez précis pour laisser le prochain agent sans ambiguïté.

## Sortie finale attendue

Tu dois produire un résumé final en français comprenant :

- les fichiers créés ;
- le contenu de chaque fichier ;
- les décisions prises ;
- les éléments à confirmer ;
- le niveau de maturité obtenu pour le démarrage du développement.

## Prompt prêt à copier-coller

```text
Tu es l’agent de développement du projet Compagnon LDVELH.

Ta mission est de réaliser les livrables R-01 et R-02 du backlog, en produisant des documents et données structurés, exploitables pour la suite du développement.

Lis d’abord :
- docs/feuille-de-route-compagnon-ldvelh.md
- docs/cadrage-fonctionnel-compagnon-ldvelh.md
- docs/architecture-ux-compagnon-ldvelh.md
- docs/phase-0-lever-les-inconnues-compagnon-ldvelh.md
- docs/r-01-feuille-d-aventure-compagnon-ldvelh.md

Travail à réaliser :
1. Créer un document de spécification de la feuille d’aventure pour la bêta, avec séparation claire entre éléments initiaux, courants, calculés, éditables et à confirmer.
2. Créer un document de données de base des six personnages du livre.
3. Créer un fichier JSON structuré pour les personnages et leur information de départ.

Règles :
- écris en français ;
- ne pas inventer de données non fournies ;
- marque explicitement les éléments manquants comme à confirmer ;
- structure les documents pour être directement exploitables par le développement ;
- crée les fichiers dans docs/ ;
- ne pas bouger sur d’autres tâches du backlog.

Livrables requis :
- docs/r-01-feuille-d-aventure-validee-compagnon-ldvelh.md
- docs/r-02-donnees-personnages-compagnon-ldvelh.md
- docs/data/ldvelh-personnages-base.json

Sortie finale :
- résumé des fichiers produits ;
- résumé des décisions prises ;
- liste des éléments à confirmer ;
- niveau de maturité pour la suite du projet.
```
