# Prompt de démarrage — R-01 et R-02

## Contexte

On lance la réalisation des tâches prioritaires du projet Compagnon LDVELH :

- R-01 : définir le contenu complet de la feuille d'aventure ;
- R-02 : rassembler les données initiales des six personnages et les premiers éléments du catalogue du livre.

Le but est de poser une base solide et exécutable pour l'IA qui va ensuite piloter le reste du process, sans perdre de temps sur des décisions non tranchées.

## Mission

Réaliser les livrables attendus pour R-01 et R-02, puis documenter les décisions, hypothèses, points à valider et fichiers de sortie dans le dépôt.

La priorité est la clarté de modélisation, la traçabilité des choix, et la préparation du socle métier qui servira à toutes les tâches suivantes.

## Règles de travail pour l'IA

1. Travailler depuis la racine du dépôt.
2. Lire d'abord les documents de cadrage déjà existants dans `docs/` :
   - `feuille-de-route-compagnon-ldvelh.md`
   - `cadrage-fonctionnel-compagnon-ldvelh.md`
   - `architecture-ux-compagnon-ldvelh.md`
   - `phase-0-lever-les-inconnues-compagnon-ldvelh.md`
   - `r-01-feuille-d-aventure-compagnon-ldvelh.md`
3. Ne pas réécrire des éléments déjà validés sans nécessité.
4. Produire des livrables lisibles, structurés, et directement exploitables par le prochain agent de développement.
5. Faire apparaître explicitement :
   - les données initiales ;
   - les valeurs courantes ;
   - les valeurs calculées ;
   - les champs éditables ;
   - les éléments à confirmer.
6. Éviter toute ambiguïté sur la feuille d'aventure : les champs doivent être clairement séparés par rôle et par source de vérité.
7. Pour R-02, ne pas inventer des données de personnage sans provenance claire ; si une valeur manque, utiliser un marqueur explicite : `À confirmer` ou `Non fourni dans la source`.
8. Documenter chaque fichier généré avec un objectif, une source, une méthode, et un statut.

## Livrables attendus

### Livrable R-01

Créer un document Markdown dédié dans `docs/` nommé :

- `r-01-feuille-d-aventure-validee-compagnon-ldvelh.md`

Ce document contient la spécification métier complète de la feuille d'aventure. Il est la référence fonctionnelle pour R-01 : le prompt indique à l'IA quoi faire, mais ne remplace pas les informations détaillées de la feuille. Ne place donc pas la spécification uniquement dans ce fichier de prompt.

Le document doit contenir :

- une synthèse de la feuille d'aventure pour la bêta ;
- une liste exhaustive des champs par personnage ;
- un découpage par catégorie :
  - valeurs initiales ;
  - valeurs courantes ;
  - valeurs calculées ;
  - valeurs éditables ;
  - données de référence du livre ;
- les règles de validation métier ;
- la structure de la vue d'équipe ;
- la liste des champs non décidés ou à confirmer ;
- un tableau “décision / hypothèse / validation” ;
- un point sur les impacts sur Windows/Linux.

### Livrable R-02

Créer :

- un fichier `docs/r-02-donnees-personnages-compagnon-ldvelh.md` ;
- un fichier `docs/data/ldvelh-personnages-base.json` si les données sont structurées avec un format exploitable.

Le document doit contenir :

- la liste des six personnages de base ;
- leur fiche de données initiales ;
- leur maximum de points de vie ;
- leurs équipements de départ ;
- les éventuels modificateurs d'équipement de départ ;
- une colonne “source / vérification / statut” pour chaque donnée ;
- les éléments non fournis ou à confirmer.

Le JSON doit être exploitable pour les prochaines étapes de modélisation métier, et respecter une structure cohérente avec le modèle de données à venir.

## Processus à suivre

### Étape 1 — Analyse du contexte

- lire les documents existants ;
- identifier les hypothèses déjà validées ;
- repérer les points à confirmer.

### Étape 2 — Définition de la feuille d'aventure

- formaliser les sections essentielles de la fiche personnage ;
- distinguer clairement données de référence et état courant ;
- définir le rôle de l'équipement dans le calcul des caractéristiques ;
- définir la logique des points de vie ;
- préparer une proposition de structure JSON.

### Étape 3 — Données du livre

- dresser la liste des six personnages ;
- récupérer ou compléter leurs valeurs de base ;
- formaliser les équipements initiaux ;
- signaler toutes les données manquantes ou non vérifiées.

### Étape 4 — Validation

Vérifier que le livrable satisfait les critères suivants :

- R-01 distingue bien les quatre catégories de données ;
- R-02 couvre tous les six personnages ;
- les données sont vérifiables et exploitable en code ;
- les zones incertaines sont explicitement identifiées ;
- l'ensemble est préparé pour la suite du backlog sans ambiguïté.

## Sortie attendue

L'IA doit remettre un ensemble de documents proprement nommés et structurés dans `docs/`, avec un niveau de détail suffisant pour permettre à un autre agent de développer le cœur métier sans demander à reconstruire les bases.

## Prompt prêt à copier-coller

```text
Tu es l’agent autonome chargé de préparer puis de réaliser les tâches R-01 et R-02 du projet Compagnon LDVELH.

## Objectif

Produire une spécification validable de la feuille d’aventure (R-01) et un jeu de données initiales structuré et vérifiable pour les six personnages et le premier catalogue du livre (R-02).

Ne commence pas à développer l’application ni à traiter d’autres tâches du backlog. Prépare des livrables qui permettront à un agent ultérieur d’implémenter le modèle métier sans devoir deviner les règles.

## Sources à lire

Depuis la racine du dépôt, lis dans cet ordre :

1. `docs/feuille-de-route-compagnon-ldvelh.md`
2. `docs/cadrage-fonctionnel-compagnon-ldvelh.md`
3. `docs/architecture-ux-compagnon-ldvelh.md`
4. `docs/phase-0-lever-les-inconnues-compagnon-ldvelh.md`
5. `docs/r-01-feuille-d-aventure-compagnon-ldvelh.md`
6. `docs/prompt-r01-r02-compagnon-ldvelh.md`
7. `docs/prompt-agent-developpement-r01-r02-compagnon-ldvelh.md`, s’il existe.

Vérifie l’état réel du dépôt avant de modifier quoi que ce soit. Respecte les changements présents. Si un fichier de sortie existe déjà, lis-le et préserve son contenu utile.

## Règles de conduite

- Travaille en français et depuis la racine du dépôt.
- Limite les modifications aux livrables R-01, R-02 et aux mises à jour strictement nécessaires de leur statut.
- Ne développe pas de code applicatif et ne traite aucune autre tâche du backlog.
- N’invente aucune donnée du livre. Pour chaque donnée, indique sa provenance et son statut : `vérifiée`, `à confirmer` ou `non fournie dans les sources consultées`.
- Utilise les sources fournies par le porteur du projet ou présentes dans le dépôt. Si une donnée manque, marque-la comme manquante et demande la source dans le rapport final ; ne la remplace pas par une valeur plausible.
- Ne reproduis pas de longs passages du livre. Enregistre uniquement les données structurées nécessaires et leur référence de provenance.
- En cas de contradiction, consigne les sources concernées et propose une décision provisoire explicitement marquée. Ne modifie pas silencieusement une décision existante.
- Distingue les statuts `rédigé`, `à valider par le porteur du projet` et `validé`. Ne déclare rien validé sans satisfaire les critères et obtenir la validation requise.
- Si une source ou décision est indispensable, produis les parties vérifiables, identifie le blocage et pose des questions précises dans le rapport final.
- N’installe aucune dépendance et ne supprime aucun fichier.

## Fichiers livrables

Créer ou mettre à jour :

- `docs/r-01-feuille-d-aventure-validee-compagnon-ldvelh.md`
- `docs/r-02-donnees-personnages-compagnon-ldvelh.md`
- `docs/data/ldvelh-personnages-base.json`

**La spécification complète de la feuille d’aventure doit se trouver dans le fichier Markdown R-01.** Le présent prompt n’est qu’une instruction de travail. Le JSON de R-02 décrit les données de référence initiales ; il ne remplace pas la spécification fonctionnelle ni les valeurs courantes d’une aventure.

Le dépôt contient déjà `docs/r-01-feuille-d-aventure-compagnon-ldvelh.md`. Lis-le, récupère ses éléments utiles et évite de maintenir deux spécifications concurrentes. Le livrable `...-validee...` devient la référence R-01 ; indique dans l’ancien document s’il est conservé comme brouillon ou référence historique, sans supprimer son contenu utile.

## R-01 — Spécification de la feuille d’aventure

Le document doit permettre au prochain agent de développer le domaine sans ambiguïté. Il contient :

1. l’objectif et le périmètre de la feuille de bêta ;
2. les champs de l’aventure et de la progression partagée ;
3. la liste exhaustive des champs de chaque personnage ;
4. une matrice des champs précisant pour chacun : nom, sens, type, portée (livre, aventure, équipe ou personnage), catégorie, éditabilité, règles de validation et statut (décidé, hypothèse ou à confirmer) ;
5. la séparation entre données initiales, valeurs courantes, valeurs calculées, données de référence du livre et champs éditables ;
6. les règles confirmées, notamment l’immutabilité des caractéristiques de base, le calcul des caractéristiques effectives, les points de vie et les limites de quantité d’équipement ;
7. la présentation de la vue d’équipe et de la fiche individuelle ;
8. les éléments hors périmètre de la bêta ;
9. un registre des décisions, hypothèses, contradictions et questions ouvertes ;
10. les critères de validation de R-01 et les implications pour Android, Windows et Linux, sans imposer prématurément une implémentation technique.

Ne présente pas comme décisions les choix encore ouverts. En particulier, distingue progression partagée et données propres à chaque personnage si le contexte documentaire ne tranche pas.

## R-02 — Données initiales des personnages et du catalogue

Le document Markdown doit :

1. présenter le livre et les sources consultées ;
2. contenir une entrée pour chacun des six personnages, même si des informations manquent ;
3. séparer caractéristiques de base, maximum de points de vie, équipement de départ et autres données pertinentes ;
4. donner pour chaque donnée une provenance précise et un statut ;
5. distinguer les données de référence du livre des états propres à une partie ;
6. lister les éléments incertains, absents et à faire valider par le porteur du projet.

Le JSON doit être valide et représenter les six personnages sans omettre les données inconnues. Utilise `null` pour une valeur indisponible, avec statut et provenance. Maintiens une cohérence exacte entre les données présentées dans le Markdown et dans le JSON. La structure peut contenir `schemaVersion`, `book`, `characters`, `baseStats`, `maxHp`, `startingEquipment`, `status`, `provenance`, `notes`, `catalogue` et `openQuestions`. Signale les décisions de schéma à réexaminer pour R-05.

## Processus obligatoire

### 1. Examiner le dépôt

Lire les sources ci-dessus, repérer les éventuelles sources des données du livre, noter les décisions, contradictions et absences, puis vérifier si les livrables existent déjà.

### 2. Réaliser R-01

Consolider la spécification complète de la feuille dans le Markdown R-01 dédié. Vérifier que chaque champ et règle est associé à une catégorie et à un statut, et que les points ouverts sont visibles.

### 3. Réaliser R-02

Établir les fiches des six personnages et le catalogue initial uniquement à partir de sources disponibles. Reporter les mêmes données, statuts et provenances dans le Markdown et le JSON.

### 4. Vérifier les livrables

- vérifier la présence des six personnages dans les deux formats ;
- valider la syntaxe JSON avec un outil disponible, sans installer de dépendance ;
- comparer les données répétées entre Markdown et JSON ;
- s’assurer qu’aucune valeur manquante n’a été inventée ;
- vérifier que chaque donnée non triviale a une provenance ou est identifiée comme non fournie ;
- vérifier que R-01 sépare clairement les catégories de données et les champs éditables ;
- signaler toute vérification impossible.

### 5. Mettre à jour le suivi

Dans `docs/phase-0-lever-les-inconnues-compagnon-ldvelh.md`, mettre à jour uniquement les statuts R-01 et R-02 si les livrables correspondants ont effectivement été produits. Utiliser un statut précis comme `Livrable rédigé — validation du porteur requise`. Ne pas déclarer ces tâches validées si des données ou décisions restent à confirmer.

## Rapport final obligatoire

Réponds en français et utilise ces rubriques :

### Résultat

Indique séparément si R-01 et R-02 sont `rédigés`, `validés` ou `bloqués`.

### Fichiers

Liste chaque fichier créé ou modifié et résume son contenu.

### Décisions et hypothèses

Sépare les décisions établies des hypothèses provisoires.

### À confirmer

Formule les questions précises adressées au porteur, en priorisant les demandes de sources manquantes pour les personnages et le catalogue.

### Vérifications

Indique les contrôles exécutés, leurs résultats et les contrôles non effectués.

### Prochaine étape

Précise ce qui manque pour clôturer R-01/R-02 et si les livrables sont suffisamment prêts pour démarrer R-05. N’affirme pas que le développement peut démarrer sans réserve si des données fondamentales restent inconnues.
```

## Ce que cela permet

Ce prompt prépare une IA pour qu’elle :

- formalise la feuille sans ambiguïté ;
- sécurise les données initiales du livre ;
- produit des artefacts exploitables immédiatement ;
- garde une trace explicite des décisions et questions ouvertes.

C’est la bonne base pour démarrer avec R-01 puis R-02 et faire évoluer le process ensuite sans repartir de zéro.
