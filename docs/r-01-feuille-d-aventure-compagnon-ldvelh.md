# R-01 — Définition du contenu de la feuille d'aventure

> Document de travail initial, à lire avec la spécification spécifique du premier livre : [R-01 — feuille de *La Vallée des Os*](./r-01-feuille-d-aventure-vallee-des-os.md). La spécification dédiée est la référence pour la bêta ; ce brouillon antérieur contient des propositions générales qui ne sont pas toutes confirmées pour le tome 1.

## 1. Objectif

Définir le contenu minimal et complet de la feuille d'aventure pour la bêta Android du compagnon LDVELH. Le livrable doit permettre d'implémenter sans ambiguïté :

- les données de chaque personnage ;
- les règles d'édition et de validation ;
- la présentation de l'équipe dans la vue principale ;
- le découpage entre données de référence, valeurs courantes et valeurs calculées.

La feuille doit rester compatible avec le périmètre de la bêta : une seule aventure, quatre personnages sélectionnés parmi six, sans duplication inutile de logique, tout en gardant la structure extensible pour les cibles Windows et Linux.

## 2. Principes de modélisation

### 2.1 Séparation des données

Chaque donnée est classée dans une catégorie de responsabilité claire :

- Données initiales : valeur définie au départ de l'aventure ou au moment de la création du personnage ;
- Valeurs courantes : état actuel modifiable pendant le jeu ;
- Valeurs calculées : dérivées des données initiales et de l'équipement porté ;
- Informations éditables : valeurs ou champs modifiables par le joueur au fil de la partie.

### 2.2 Règles de base

- Les valeurs de base ne sont pas modifiées par un équipement ou par une mise à jour de l'état de jeu.
- Les points de vie actuels peuvent être modifiés, mais ne peuvent pas dépasser leur maximum.
- Les modificateurs d'équipement ne doivent jamais modifier la valeur de base d'une caractéristique.
- L'équipe est vue comme un ensemble cohérent ; les fiches individuelles restent accessibles.
- Les données sont persistées localement et peuvent être exportées/importées en JSON.

## 3. Structure de la feuille d'aventure

### 3.1 Vue d'équipe

La vue principale affiche l'état actuel de l'équipe et donne accès aux fiches détaillées. Elle contient :

- nom de l'aventure ;
- numéro de section actuelle ;
- liste des quatre personnages ;
- statut général de l'équipe ;
- accès rapide aux actions principales : avancer, consulter les fiches, gérer l'équipement.

Pour chaque personnage dans la vue d'équipe, afficher au minimum :

- nom ;
- points de vie actuels / maximum ;
- statut bref (normal, blessé, hors combat, etc. si applicable) ;
- caractéristiques prioritaires ou visibles au premier niveau ;
- équipement porté ou nombre d'objets importants ;
- lien vers la fiche détaillée.

### 3.2 Fiche individuelle du personnage

La fiche détaillée du personnage contient les sections suivantes.

## 4. Champs du personnage

### 4.1 Identité et contexte de jeu

| Catégorie | Champ | Type | Modifiable | Règle |
|---|---|---:|---|---|
| Initiale | Nom du personnage | texte | oui | Obligatoire ; unique dans l'aventure. |
| Initiale | Genre / rôle / classe | texte ou enum | oui | À confirmer selon le livre et le modèle de personnage. |
| Initiale | Nom du personnage de base (si variante de livre) | texte | non ou optionnel | Conserve l'identité définie par le livre. |
| Courante | Section actuelle | entier | oui | Représente la progression actuelle du personnage ou de l'équipe. |
| Courante | Troupe / équipe | booléen ou sélection | oui | Indique si le personnage suit le groupe principal. |
| Courante | Notes de session | texte libre | oui | Utilisable pour l'annotation rapide. |

### 4.2 Caractéristiques

Les caractéristiques sont structurées en deux niveaux : base et effective.

| Catégorie | Champ | Type | Modifiable | Règle |
|---|---|---:|---|---|
| Initiale | Caractéristiques de base | map de valeurs | non | Définies par le personnage et par le livre. |
| Courante | Caractéristiques effectives | map de valeurs | calculée | Résultat de base + modificateurs d'équipement. |
| Courante | Modificateurs temporaires | map de valeurs | oui | À réserver aux effets de jeu, non à l'équipement. |
| Courante | État de caractéristique | texte / enum | oui | Exemple : blessé, stressé, neutralisé si nécessaire. |

Exemples de champs génériques :

- Force ;
- Agilité ;
- Chance ;
- Sagesse ;
- Habileté ;
- Magie.

L'implémentation doit permettre un modèle flexible : les noms et le nombre de caractéristiques sont définis par le livre, sans imposer un schéma universel fixé dans le code.

### 4.3 Points de vie

| Catégorie | Champ | Type | Modifiable | Règle |
|---|---|---:|---|---|
| Initiale | Maximum de points de vie | entier | non | Défini au départ du personnage. |
| Courante | Points de vie actuels | entier | oui | Doit rester entre 0 et max. |
| Courante | Coefficient de régénération ou d'état | optionnel | oui | À confirmer selon les règles du livre ou l'évolution du jeu. |

Règles :

- PV actuels = valeur courante ;
- PV maximum = référence de conception ;
- si l'utilisateur tente de dépasser le max, l'application doit bloquer la saisie avec un message explicite ;
- la mise à jour des PV ne doit pas modifier le maximum.

### 4.4 Équipement

| Catégorie | Champ | Type | Modifiable | Règle |
|---|---|---:|---|---|
| Initiale | Équipement de départ | liste d'objets | non ou limité | Équipements donnés au personnage au démarrage. |
| Courante | Équipement porté | liste d'objets | oui | L'utilisateur ajoute, retire ou associe des objets. |
| Courante | Quantité portée | int ou compteur | oui | Selon la règle de chaque équipement. |
| Courante | Modificateurs d'équipement | map | calculée | Déduit de l'équipement porté. |

Les objets doivent être modélisés avec au minimum :

- identifiant de l'objet ;
- nom ;
- type ;
- quantité maximale portée ;
- modificateur(s) associé(s) ;
- disponibilité de l'objet dans le livre ou en jeu ;
- option de portabilité (portable, équipé, consumable, unique).

Les règles d'utilisation :

- la plupart des objets sont limités à un exemplaire ;
- certains objets peuvent être portés plusieurs fois ou en quantité ;
- les objets sont ajoutés au catalogue global du livre et réutilisables sur plusieurs personnages ;
- un même objet ne doit pas être dupliqué artificiellement dans le profil d'un personnage.

### 4.5 Argent, objets et ressources

| Catégorie | Champ | Type | Modifiable | Règle |
|---|---|---:|---|---|
| Courante | Argent / trésor | entier | oui | Saisi et mis à jour librement. |
| Courante | Objets personnels | liste | oui | À considérer comme inventaire libre. |
| Courante | Ressources spécifiques | map | oui | Ex. potions, clés, etc. |
| Courante | Notes d'inventaire | texte | oui | Pour l’usage personnel ou l’annotation. |

### 4.6 Historique de progression

| Catégorie | Champ | Type | Modifiable | Règle |
|---|---|---:|---|---|
| Courante | Section de départ | entier | non | Donnée de référence initiale. |
| Courante | Section actuelle | entier | oui | État de progression en cours. |
| Courante | Historique des sections | liste d'entrées | oui | Conservé en mémoire. |
| Courante | Dernière mise à jour | datetime | automatique | À enregistrer pour traçabilité. |

## 5. Règles d'édition et de validation

### 5.1 Validation métier

L'application doit appliquer les règles suivantes :

- les caractéristiques de base sont immuables pendant la partie ;
- les points de vie actuels sont bornés par leur maximum ;
- les valeurs calculées ne doivent pas être modifiables directement dans le formulaire ;
- les données non prises en charge doivent être explicitement identifiées comme "à préciser" dans le modèle ;
- un équipement ne doit être attribué qu'au personnage concerné et au catalogue du livre ;
- l'ajout d'un équipement modifie seulement le modèle de jeu et non le livre de base.

### 5.2 Règles d'écriture

- Les changements sont enregistrés automatiquement après modification.
- L'application doit empêcher les valeurs invalides plutôt que de corriger silencieusement.
- Chaque erreur de saisie doit fournir un message précis : "PV au-dessus du maximum", "caractéristique de base non modifiable", etc.
- Les formulaires doivent distinguer les zones de saisie accessibles et les champs calculés en lecture seule.

### 5.3 Règles de cohérence entre caractères et équipe

- Une équipe de quatre personnages partage la même section actuelle ou une progression cohérente,
- les personnages peuvent être consultés individuellement ;
- le changement d'une donnée de progression comme la section doit rester cohérent avec la vue d'équipe ;
- les effets liés à un équipement de personnage ne doivent pas modifier l'équipe dans son ensemble sans action explicite.

## 6. Présentation de l'équipe

### 6.1 Vue compacte

La vue principale de l'équipe doit mettre en avant les informations utiles pendant la lecture. Elle doit permettre de lire rapidement :

- la section actuelle ;
- l'état de santé des personnages ;
- les caractéristiques les plus visibles du groupe ;
- l'équipement principal ou les objets clés ;
- la possibilité de passer directement à une fiche personnage.

### 6.2 Vue détaillée par personnage

La fiche détaillée présente les informations suivantes dans l'ordre logique :

1. identité du personnage ;
2. section actuelle et progression ;
3. caractéristiques de base ;
4. caractéristiques effectives ;
5. points de vie ;
6. équipement porté ;
7. argent et ressources ;
8. notes et historique de jeu.

La fiche doit respecter la logique :

- les données de base restent lisibles ;
- les valeurs calculées sont affichées avec une marque claire (par exemple, "effective") ;
- les champs modifiables apparaissent dans un formulaire dédié ;
- les champs calculés ne doivent pas être éditables directement.

## 7. Champs à confirmer avant mise en œuvre

Ces éléments restent identifiés comme non décidés ou à confirmer lors de la phase de spécification détaillée :

- nom exact des caractéristiques de base du livre *La Vallée des Os* ;
- liste complète des objets de départ pour les six personnages ;
- stratégie exacte d'édition des points de vie et des modificateurs temporaires ;
- nature exacte de la section d'équipe : unique ou four sections indépendantes ;
- périmètre des notes, objets personnels et ressources non standard ;
- éventuels statuts spéciaux (blessé, inconscient, etc.) à modéliser par enum ou texte libre.

## 8. Résultat attendu pour R-01

Le livrable R-01 est validé si :

- la feuille d'aventure distingue clairement données initiales, courantes, calculées et éditables ;
- chaque personnage contient les informations nécessaires au jeu de la bêta ;
- les points de vie, l'équipement et les caractéristiques sont modélisés sans ambiguïté ;
- la vue d'équipe présente suffisamment d'information pour jouer sans feuille papier ;
- les champs non décidés sont explicitement identifiés pour éviter une implémentation partielle ou contradictoire.

## 9. Proposition de structure de données (exemple)

```json
{
  "adventure": {
    "id": "aventure-01",
    "name": "La Vallée des Os",
    "currentSection": 12,
    "party": ["hero-01", "hero-02", "hero-03", "hero-04"]
  },
  "characters": [
    {
      "id": "hero-01",
      "name": "Nom du personnage",
      "baseStats": {
        "force": 14,
        "agilite": 11,
        "chance": 10
      },
      "effectiveStats": {
        "force": 14,
        "agilite": 11,
        "chance": 10
      },
      "maxHp": 12,
      "currentHp": 8,
      "equipment": ["epee", "armure"],
      "gold": 15,
      "notes": ""
    }
  ]
}
```

Cette structure sert de base fonctionnelle, sans imposer le format final de stockage. Elle montre la séparation attendue entre données de base, valeurs calculées et état courant.
