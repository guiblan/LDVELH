# Cadrage fonctionnel — Compagnon de livres dont vous êtes le héros

## 1. Vision

Créer une application Android servant de compagnon de jeu aux lecteurs de livres dont vous êtes le héros (LDVELH). Elle permet de gérer une bibliothèque, plusieurs aventures et les personnages qui y participent, tout en conservant l’historique des choix et des embranchements.

Le MVP doit fonctionner intégralement hors ligne. Les données doivent pouvoir être exportées dans un fichier JSON puis réimportées, notamment après une réinstallation.

## 2. Objectifs

- Retrouver les livres, séries et aventures dans une bibliothèque personnelle.
- Suivre plusieurs aventures en parallèle, y compris plusieurs aventures sur un même livre ou une même série.
- Gérer un ou plusieurs personnages dans chaque aventure avec des données adaptables aux règles du livre.
- Enregistrer la progression et son historique sans effacer les parcours passés.
- Créer et gérer des branches de parcours.
- Sauvegarder et restaurer les données de façon compréhensible et contrôlée par le lecteur.

## 3. Périmètre du MVP

### Inclus

- Application prioritairement destinée à Android.
- Fonctionnement hors ligne pour consulter, créer et modifier toutes les données.
- Bibliothèque de livres et de séries, avec ajout manuel.
- Une ou deux fiches de livres préchargées pour l’exemple et les tests. La bibliothèque préchargée pourra être enrichie au fil des mises à jour de l’application.
- Gestion de plusieurs aventures indépendantes.
- Gestion d’un ou plusieurs personnages par aventure.
- Données de personnage personnalisables selon le livre.
- Suivi de la section actuelle et historique des sections, avec ajout direct depuis le résumé et notes facultatives.
- Création, nommage et gestion de branches d’aventure.
- Mise en pause et clôture d’une aventure.
- Export et import de sauvegardes JSON.
- Résolution des conflits d’import au cas par cas, avec comparaison et choix des valeurs champ par champ.

### Hors périmètre du MVP

- Synchronisation en ligne ou entre appareils.
- Stockage de couvertures et import d’images depuis le téléphone.
- Format de sauvegarde compact ou autre que JSON.
- Automatisation des règles propres aux livres (calculs, combats, validations de caractéristiques ou de sorts).

## 4. Concepts et données

### Série

Une série regroupe plusieurs tomes. Elle possède au minimum un nom. Chaque tome reste une fiche de livre distincte.

### Livre

Chaque livre possède :

- un titre ;
- un auteur ;
- un identifiant de type ISBN, lorsqu’il existe ;
- un numéro de tome, si le livre appartient à une série ;
- une série facultative ;
- une note personnelle de 1 à 5 étoiles ;
- un commentaire facultatif associé à la note.

La fiche de livre ne nécessite pas de nombre total de sections pour le MVP. Les données de bibliothèque fournies avec l’application pourront évoluer lors des mises à jour.

### Aventure

Une aventure représente une partie jouée sur un livre ou une série. Elle peut contenir un ou plusieurs personnages et plusieurs branches. Plusieurs aventures peuvent concerner le même livre ou la même série.

Une aventure peut être marquée comme active, en pause ou terminée. Son parcours et ses branches doivent rester consultables après sa clôture.

### Personnage

Chaque personnage appartient à une aventure et comporte au minimum :

- un nom ;
- des caractéristiques personnalisables ;
- un équipement personnalisable ;
- de l’argent ;
- des notes ;
- une liste de sorts personnalisable lorsque le personnage est magicien.

Les caractéristiques et éléments de suivi ne doivent pas être imposés par un modèle universel : le lecteur doit pouvoir définir les champs adaptés au livre. Les champs de type caractéristique doivent au minimum permettre de nommer un champ et d’en enregistrer la valeur. Les autres champs personnalisés doivent également pouvoir représenter les informations propres au livre.

### Progression et historique

- La progression est exprimée par le numéro de section et, pour une série, le tome concerné.
- L’historique des sections est conservé et propre à chaque personnage.
- Dans une équipe, les personnages avancent actuellement ensemble et se trouvent au même endroit. Une progression d’équipe doit donc pouvoir être enregistrée de manière cohérente pour tous les personnages concernés, tout en conservant leur historique individuel. À préciser : la portée exacte de l’entrée (équipe ou personnage).
- Une entrée d’historique comprend le numéro de section, le tome lorsque pertinent, la date et une note facultative.
- Le passé n’est pas modifié lorsqu’on reprend l’aventure depuis une section antérieure : cette reprise crée une branche.

### Branche

- Une branche appartient à une aventure entière et concerne l’ensemble de ses personnages, leur progression et leurs données de jeu.
- Chaque branche a un nom.
- Une branche créée à partir d’un point antérieur conserve intacte la branche d’origine et son historique ; elle démarre à partir du point choisi.
- Les branches permettent de conserver des parcours alternatifs sans écraser le passé.
- Une fusion est indivisible : le lecteur conserve la branche choisie dans son ensemble ou ne réalise pas la fusion. Il n’y a pas de fusion champ par champ entre branches.
- Lorsqu’une branche de destination existe, la branche choisie remplace la destination, qui est supprimée. Aucune nouvelle branche de fusion n’est créée.

## 5. Parcours utilisateur principaux

### Constituer la bibliothèque

1. Le lecteur consulte les livres et séries préchargés.
2. Il peut créer ou modifier une série et ses fiches de tomes.
3. Il peut créer une fiche de livre avec titre, auteur, ISBN éventuel, tome éventuel, série éventuelle et note personnelle.

### Démarrer et jouer une aventure

1. Le lecteur démarre une aventure à partir d’un livre ou d’une série.
2. Il ajoute un ou plusieurs personnages.
3. Il configure les champs nécessaires à ce livre et renseigne les valeurs initiales.
4. Il enregistre la section de départ et, pour une série, le tome.
5. Au fil de la lecture, il met à jour la progression et peut ajouter une note à chaque étape.
6. L’application conserve l’historique et le reflète pour chaque personnage de l’équipe.
7. Le lecteur peut mettre l’aventure en pause puis la reprendre, ou la marquer comme terminée.

### Reprendre un ancien parcours

1. Le lecteur choisit un point passé de l’historique.
2. L’application lui propose de créer une nouvelle branche, avec un nom.
3. La branche d’origine reste intacte.
4. La nouvelle branche reprend au point choisi et permet de poursuivre un parcours alternatif.

### Fusionner des branches

1. Le lecteur choisit la branche à conserver et la branche de destination.
2. L’application présente clairement l’effet de l’opération avant confirmation.
3. La branche choisie est conservée dans son intégralité et remplace la destination ; la destination est supprimée.
4. L’application ne propose ni fusion partielle des données ni création automatique d’une nouvelle branche.

### Sauvegarder et restaurer

1. Le lecteur exporte ses données dans un fichier JSON.
2. Après réinstallation, il sélectionne un fichier JSON à importer.
3. Si des éléments en conflit existent, l’application affiche les valeurs comparées et permet de choisir les valeurs à conserver champ par champ.
4. L’import explicite les ajouts, mises à jour et conflits avant validation, sans remplacement silencieux des données.

## 6. Exigences fonctionnelles

| ID | Exigence |
|---|---|
| RF-01 | L’application doit permettre de consulter et modifier les données hors ligne. |
| RF-02 | L’application doit fournir une bibliothèque initiale d’un ou deux livres et permettre son enrichissement par des mises à jour. |
| RF-03 | Le lecteur doit pouvoir gérer des livres et des séries, avec une fiche distincte pour chaque tome. |
| RF-04 | Le lecteur doit pouvoir noter chaque livre de 1 à 5 étoiles et ajouter un commentaire facultatif. |
| RF-05 | Le lecteur doit pouvoir créer plusieurs aventures sur le même livre ou la même série. |
| RF-06 | Une aventure doit accepter un ou plusieurs personnages. |
| RF-07 | Le lecteur doit pouvoir personnaliser les champs de personnage selon le livre, dont les caractéristiques, l’équipement et les sorts éventuels, et suivre l’argent et les notes. |
| RF-08 | La progression doit conserver le numéro de section et le tome si l’aventure porte sur une série. |
| RF-09 | Depuis le résumé, le lecteur doit pouvoir saisir une nouvelle section, ouvrir facultativement une note repliable et sélectionner « Ajouter » ; une seule entrée valide est ajoutée en tête du fil récent, et une valeur vide ou invalide affiche un message d’erreur court sans créer d’entrée. La saisie complète est accessible via « Ajouter une note détaillée ». |
| RF-10 | L’application doit permettre de mettre une aventure en pause ou de la terminer. |
| RF-11 | Une reprise depuis un point passé doit créer une branche nommable sans altérer la branche d’origine. |
| RF-12 | Une branche doit représenter l’aventure complète et l’ensemble de ses personnages. |
| RF-13 | Une fusion de branches doit être tout-ou-rien ; avec une destination, celle-ci est remplacée et supprimée au profit de la branche retenue. |
| RF-14 | L’application doit exporter et importer les données au format JSON. |
| RF-15 | L’import doit gérer les conflits avec une comparaison et un choix champ par champ, sans écrasement silencieux. |

## 7. Exigences de qualité et contraintes

- **Priorité plateforme :** Android.
- **Mode de fonctionnement :** hors ligne dès la première version ; aucune connexion ne doit être requise pour les fonctions du MVP.
- **Persistance :** les données locales doivent survivre à la fermeture et au redémarrage de l’application.
- **Récupération :** l’export JSON constitue le mécanisme de transfert et de restauration après réinstallation.
- **Intégrité :** l’historique passé et les branches non ciblées par une opération doivent être préservés. Les opérations de fusion doivent être présentées avant confirmation compte tenu de la suppression de la destination.
- **Évolutivité :** la structure des champs personnalisés et du JSON doit permettre d’ajouter ultérieurement des données et des livres préchargés sans perdre les sauvegardes compatibles.

## 8. Critères d’acceptation du MVP

1. Après un premier lancement sans réseau, le lecteur peut consulter la bibliothèque initiale, créer un livre et enregistrer une note.
2. Le lecteur peut créer deux aventures distinctes sur le même livre et leur associer des personnages différents.
3. Une aventure peut réunir plusieurs personnages, avec des caractéristiques, équipements, argent, notes et sorts propres aux besoins du livre.
4. Depuis le résumé, le lecteur peut saisir une nouvelle section, ouvrir facultativement la note repliée et sélectionner « Ajouter » ; une entrée valide apparaît en tête du fil récent et le champ est vidé, tandis qu’une valeur vide ou invalide affiche un message d’erreur court sans créer d’entrée. Le lien « Ajouter une note détaillée » ouvre la saisie complète. La progression d’une équipe est cohérente pour tous ses personnages ; l’historique est consultable pour chacun et accepte des notes.
5. La progression peut identifier le tome lorsque l’aventure suit une série.
6. Une aventure peut être mise en pause, reprise et marquée comme terminée.
7. La création d’une branche à partir d’un point historique préserve intégralement la branche d’origine et reprend le parcours au point indiqué.
8. Une fusion ne mélange pas les données : elle conserve une branche dans son ensemble et remplace la destination spécifiée, après confirmation.
9. Un export JSON peut être réimporté après suppression/réinstallation de l’application et restitue les livres, séries, aventures, personnages, historiques et branches.
10. En cas de conflit lors d’un import, les valeurs en conflit sont comparées et le lecteur peut choisir les valeurs à conserver champ par champ.
11. Les fonctions de consultation et de modification restent utilisables en mode avion.

## 9. Hypothèses et points à préciser avant ou pendant la conception

- Les titres des un ou deux livres préchargés restent à choisir. Leur contenu doit être suffisamment simple pour servir d’exemple et de support de test.
- La forme exacte des champs personnalisés (types de valeurs, listes, valeurs numériques, équipement et sorts) est à préciser lors de la conception de l’interface et du modèle de données.
- Le comportement exact d’une fusion doit préciser si la branche source est conservée comme branche distincte après remplacement de la destination, et comment sont traités les événements postérieurs au point de divergence. La règle certaine est que la destination est supprimée et qu’aucune branche de fusion supplémentaire n’est créée.
- La correspondance entre éléments importés et existants doit reposer sur des identifiants stables plutôt que sur le seul nom, afin que les homonymes ne soient pas confondus. La stratégie de génération et de versionnement de ces identifiants reste à définir.
- Le partage d’un champ personnalisé commun à plusieurs personnages, ainsi que les exceptions où les personnages d’une équipe progressent séparément, ne sont pas définis pour le MVP.
- Le choix du stockage local, du sélecteur de fichiers Android, de la compatibilité minimale Android et des politiques de validation des fichiers JSON sera fait lors de la conception technique.
- À préciser : l’ajout concerne-t-il l’équipe ou chaque personnage, et est-il autorisé sur une aventure terminée ?
