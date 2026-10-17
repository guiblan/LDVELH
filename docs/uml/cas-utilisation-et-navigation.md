# Cas d'utilisation et navigation — Compagnon LDVELH

## Statut du document

Ces diagrammes décrivent les parcours cibles issus du cadrage et des décisions UX. L'application n'étant pas encore implémentée, ils sont des spécifications et non une description du comportement actuel. Les cas d'utilisation, la navigation et le parcours d'activité en flowchart sont des approximations Mermaid, pas des notations UML natives. Aucun diagramme de classes ou d'entités n'est inclus.

## Acteurs et cas d'utilisation

Le lecteur est le seul acteur métier prévu pour le MVP. Les opérations de fichiers utilisent le système d'exploitation comme acteur secondaire.

```mermaid
flowchart LR
    Reader["Lecteur"]
    OS["Systeme de fichiers"]
    Home["Consulter accueil"]
    Resume["Consulter resume aventure"]
    Progress["Ajouter une section depuis le résumé"]
    Team["Consulter equipe"]
    Character["Modifier equipement"]
    History["Consulter historique"]
    Branch["Creer ou changer branche"]
    Library["Gerer livres et series"]
    CreateAdventure["Creer aventure"]
    Lifecycle["Mettre en pause ou terminer"]
    Export["Exporter sauvegarde JSON"]
    Import["Importer sauvegarde JSON"]
    Conflicts["Comparer et resoudre conflits"]

    Reader --> Home
    Reader --> Resume
    Reader --> Progress
    Summary --> Progress
    Reader --> Team
    Reader --> Character
    Reader --> History
    Reader --> Branch
    Reader --> Library
    Reader --> CreateAdventure
    Reader --> Lifecycle
    Reader --> Export
    Reader --> Import
    Import --> Conflicts
    Export --> OS
    Import --> OS
```

## Navigation mobile proposée

La barre basse est une recommandation à confirmer. La sélection d'une aventure ouvre son résumé ; l'ajout d'une section depuis le résumé est l'action principale, avec note facultative repliable et fil récent ; les écrans d'historique, de branches et de fiches sont accessibles depuis celui-ci. La saisie complète est un accès secondaire via « Ajouter une note détaillée ».

```mermaid
flowchart TD
    Start["Lancement"]
    Home["Accueil"]
    Adventures["Liste des aventures"]
    Library["Bibliotheque"]
    More["Plus"]
    Backup["Sauvegardes"]
    Settings["Parametres"]
    NewAdventure["Nouvelle aventure"]
    BookChoice["Choisir livre de base ou personnel"]
    CharacterChoice["Choisir personnages du livre"]
    StartSection["Section de depart proposee et modifiable"]
    Summary["Resume aventure"]
    CharacterSheet["Fiche personnage"]
    History["Fil récent en premier"]
    Branches["Liste des branches"]
    CreateBranch["Nommer branche et confirmer"]

    Start --> Home
    Home --> Adventures
    Home --> NewAdventure
    Home --> Library
    Home --> More
    More --> Backup
    More --> Settings
    Adventures --> Summary
    NewAdventure --> BookChoice
    BookChoice --> CharacterChoice
    CharacterChoice --> StartSection
    StartSection --> Summary
    Summary --> CharacterSheet
    Summary --> History
    Summary --> Branches
    History --> CreateBranch
    CreateBranch --> Summary
    Branches --> Summary
```

## Parcours de création d'aventure

Un formulaire unique suit l'ordre du choix du livre, de l'équipe et des valeurs initiales. Les personnages créés sont des instances propres à la nouvelle aventure.

```mermaid
flowchart TD
    Begin["Nouvelle aventure"]
    ChooseSource{"Choisir une liste"}
    BaseBooks["Livres de base"]
    PersonalBooks["Livres personnels"]
    CreateBook["Creer fiche personnelle"]
    SelectBook["Selectionner livre"]
    ShowModels["Afficher feuille et personnages du livre"]
    SelectCharacters["Choisir un ou plusieurs personnages"]
    Name["Proposer nom modifiable"]
    Section["Proposer section initiale modifiable"]
    Validate{"Informations valides"}
    Save["Creer aventure et instances vierges"]
    Summary["Ouvrir resume de la branche initiale"]
    Correct["Signaler les champs a corriger"]

    Begin --> ChooseSource
    ChooseSource --> BaseBooks
    ChooseSource --> PersonalBooks
    BaseBooks --> SelectBook
    PersonalBooks --> SelectBook
    ChooseSource --> CreateBook
    CreateBook --> PersonalBooks
    SelectBook --> ShowModels
    ShowModels --> SelectCharacters
    SelectCharacters --> Name
    Name --> Section
    Section --> Validate
    Validate -->|"oui"| Save
    Validate -->|"non"| Correct
    Correct --> Section
    Save --> Summary
```
