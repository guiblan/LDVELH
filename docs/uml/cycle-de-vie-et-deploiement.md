# Cycle de vie et déploiement — Compagnon LDVELH

## Statut du document

Ces vues décrivent les états et environnements cibles du MVP. JavaFX/Gluon, le packaging et SQLite restent à valider par prototype. Le diagramme d'état utilise la syntaxe Mermaid dédiée ; les vues d'activité et de déploiement sont des approximations en flowchart, pas une notation UML native. Ce document n'inclut ni diagramme de classes ni diagramme d'entités.

## Cycle de vie d'une aventure

Une aventure terminée reste consultable. Le cadrage ne prévoit pas de transition de retour depuis l'état terminé ; le diagramme la traite donc comme terminale, sous réserve de confirmation des règles métier.

```mermaid
stateDiagram-v2
    [*] --> Active: creer aventure
    Active --> Paused: mettre en pause
    Paused --> Active: reprendre
    Active --> Completed: terminer
    Paused --> Completed: terminer
    Completed --> [*]
```

La branche active est un état du parcours de l'aventure, indépendant du statut actif/en pause/terminé : changer de branche ne réactive pas une aventure terminée.

## Activité : reprendre depuis un point passé

```mermaid
flowchart TD
    Start["Ouvrir historique"]
    Select["Selectionner ancienne etape"]
    Propose["Proposer nom aventure_index"]
    Edit{"Nom a modifier"}
    Confirm{"Confirmer creation"}
    Snapshot["Restaurer etat personnages au point choisi"]
    Create["Creer branche independante"]
    Activate["Activer nouvelle branche"]
    Resume["Afficher resume de la branche"]
    Cancel["Annuler sans modifier les donnees"]

    Start --> Select
    Select --> Propose
    Propose --> Edit
    Edit -->|"oui"| Propose
    Edit -->|"non"| Confirm
    Confirm -->|"oui"| Snapshot
    Confirm -->|"non"| Cancel
    Snapshot --> Create
    Create --> Activate
    Activate --> Resume
```

## Déploiement logique cible

Ce diagramme montre les paquets applicatifs et leurs données locales, sans infrastructure serveur. Les builds Android, Windows et Linux sont distincts ; les données ne sont pas automatiquement partagées entre eux.

```mermaid
flowchart LR
    subgraph AndroidDevice["Android device"]
        AndroidApp["Gluon packaged application"]
        AndroidData[("Private local SQLite")]
        AndroidFiles["OS file picker and JSON files"]
        AndroidApp --> AndroidData
        AndroidApp --> AndroidFiles
    end
    subgraph WindowsDevice["Windows device"]
        WindowsApp["JavaFX desktop application"]
        WindowsData[("Local SQLite")]
        WindowsFiles["File system and JSON files"]
        WindowsApp --> WindowsData
        WindowsApp --> WindowsFiles
    end
    subgraph LinuxDevice["Linux device"]
        LinuxApp["JavaFX desktop application"]
        LinuxData[("Local SQLite")]
        LinuxFiles["File system and JSON files"]
        LinuxApp --> LinuxData
        LinuxApp --> LinuxFiles
    end
    Build["Build and signing tools"]
    Build --> AndroidApp
    Build --> WindowsApp
    Build --> LinuxApp
```

Le fichier JSON constitue le mécanisme manuel de transfert entre appareils. Tout partage via OneDrive ou Google Drive est réalisé par le lecteur au moyen du système de fichiers ; aucune synchronisation applicative n'est prévue au MVP.
