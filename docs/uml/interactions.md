# Interactions — Compagnon LDVELH

## Statut du document

Les séquences décrivent les interactions prévues entre le lecteur, l'interface JavaFX/Gluon, les services applicatifs et le stockage local. Ce sont des spécifications de l'architecture cible ; aucun code n'est encore disponible pour les confirmer. Les classes et entités métier ne sont pas détaillées ici.

## Enregistrer la progression d'une équipe

L’action « Ajouter » crée une seule étape cohérente pour tous les personnages de l’équipe. La note est facultative et repliable ; la saisie complète est accessible via « Ajouter une note détaillée ».

```mermaid
sequenceDiagram
    actor Reader as Lecteur
    participant Summary as Resume JavaFX
    participant Progress as Service progression
    participant Rules as Regles metier
    participant Repository as Repository local
    participant Store as SQLite

    Reader->>Summary: Saisit « Nouvelle section »
    opt Note facultative
        Reader->>Summary: Ouvre la note repliable et la renseigne
    end
    Reader->>Summary: Actionne « Ajouter »
    Summary->>Progress: Vérifier puis ajouter la progression
    alt Valeur vide ou invalide
        Progress-->>Summary: Message d’erreur court
        Summary-->>Reader: Afficher le message sans écriture
    else Valeur valide
        Progress->>Rules: Vérifier équipe et section
        Rules-->>Progress: Validation
        Progress->>Repository: Démarrer transaction
        Repository->>Store: Ajouter une entrée pour l’équipe
        Store-->>Repository: Écriture réussie
        Repository-->>Progress: Transaction validée
        Progress-->>Summary: Confirmer et placer en tête du fil récent
        Summary-->>Reader: Afficher l’entrée et vider le champ
    end
```

En cas d'échec de validation ou d'écriture, l'interface conserve la saisie, affiche un message d'erreur court et ne présente pas l'étape comme enregistrée. À préciser : la portée de l'entrée (équipe ou personnage) et l'ajout sur une aventure terminée.

## Créer une branche depuis l'historique

```mermaid
sequenceDiagram
    actor Reader as Lecteur
    participant History as Ecran historique
    participant Branches as Service branches
    participant Repository as Repository local
    participant Store as SQLite

    Reader->>History: Selectionne une ancienne etape
    History->>Branches: Demander creation depuis etape
    Branches->>Repository: Charger historique et etat correspondant
    Repository->>Store: Lire branche et snapshots
    Store-->>Repository: Parcours et etat de equipe
    Repository-->>Branches: Point de reprise
    Branches-->>History: Proposer nom aventure_index
    Reader->>History: Modifie nom et confirme
    History->>Branches: Creer branche
    Branches->>Repository: Enregistrer branche et etat initial
    Repository->>Store: Transaction de creation
    Store-->>Repository: Branche creee
    Repository-->>Branches: Confirmation
    Branches-->>History: Activer nouvelle branche
    History-->>Reader: Ouvrir resume de la nouvelle branche
```

L'annulation avant confirmation ne modifie ni la branche source ni l'historique existant.

## Importer une sauvegarde JSON

```mermaid
sequenceDiagram
    actor Reader as Lecteur
    participant BackupView as Ecran sauvegarde
    participant FilePicker as Selecteur de fichiers
    participant Backup as Service import
    participant Validator as Validateur JSON
    participant Repository as Repository local
    participant Store as SQLite

    Reader->>BackupView: Choisit importer
    BackupView->>FilePicker: Ouvrir selecteur
    Reader->>FilePicker: Selectionne fichier JSON
    FilePicker-->>BackupView: Fournir fichier
    BackupView->>Backup: Analyser fichier
    Backup->>Validator: Verifier format et references
    Validator-->>Backup: Donnees valides ou erreurs
    alt Fichier invalide
        Backup-->>BackupView: Retourner erreurs precises
        BackupView-->>Reader: Afficher erreurs sans modifier donnees
    else Fichier valide
        Backup->>Repository: Comparer avec donnees locales
        Repository->>Store: Lire objets existants
        Store-->>Repository: Valeurs locales
        Repository-->>Backup: Ajouts et conflits
        Backup-->>BackupView: Presenter apercu
        Reader->>BackupView: Choisir valeurs conflit par conflit
        Reader->>BackupView: Confirmer import
        BackupView->>Backup: Appliquer choix
        Backup->>Repository: Ecrire transaction atomique
        Repository->>Store: Inserer ou mettre a jour
        Store-->>Repository: Resultat transaction
        Repository-->>Backup: Import confirme
        Backup-->>BackupView: Afficher bilan
    end
```

Si le lecteur annule l'aperçu, aucune donnée locale n'est modifiée. Une erreur transactionnelle laisse l'état antérieur intact et doit être signalée.

## Fusionner deux branches

La politique de fusion doit encore être confirmée. La séquence représente la recommandation actuelle : conserver intégralement la branche choisie et remplacer/supprimer la destination sans mélange champ par champ ni création d'une branche supplémentaire.

```mermaid
sequenceDiagram
    actor Reader as Lecteur
    participant BranchView as Ecran branches
    participant Branches as Service branches
    participant Repository as Repository local
    participant Store as SQLite

    Reader->>BranchView: Choisit source et destination
    BranchView->>Branches: Demander apercu fusion
    Branches->>Repository: Charger les deux parcours
    Repository->>Store: Lire source et destination
    Store-->>Repository: Parcours complets
    Repository-->>Branches: Resume des consequences
    Branches-->>BranchView: Presenter destination supprimee
    Reader->>BranchView: Confirmer operation
    BranchView->>Branches: Appliquer fusion
    Branches->>Repository: Remplacer destination atomiquement
    Repository->>Store: Transaction de fusion
    Store-->>Repository: Fusion terminee
    Repository-->>Branches: Confirmation
    Branches-->>BranchView: Rafraichir branche active
    BranchView-->>Reader: Afficher resultat
```
