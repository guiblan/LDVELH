# Feuille de route — Compagnon LDVELH

## Objectif

Livrer rapidement une bêta Android utilisable pour remplacer la feuille papier d'une aventure de *Legendary Kingdoms*, puis enrichir l'application sans remettre en cause les données ni dupliquer inutilement le code lors de l'ajout de Windows et Linux.

La première bêta est destinée à un usage personnel et à des tests sur le téléphone Android du porteur du projet. Elle doit fonctionner hors ligne. L'objectif indicatif est une disponibilité dans quelques semaines ; aucune date précise ni estimation de durée par tâche n'a été confirmée.

## Priorisation

Critère confirmé : obtenir une application fonctionnelle le plus rapidement possible, en privilégiant les fonctions qui remplacent les réécritures répétées sur la feuille papier. La section, les points de vie et l'équipement sont donc prioritaires. Les fonctionnalités plus larges viennent après la bêta, sans sacrifier la persistance des données ni la possibilité d'étendre l'application.

- **P0 — Décision ou risque bloquant** : à résoudre avant de s'engager dans l'implémentation concernée.
- **P1 — Indispensable à la bêta** : nécessaire à la feuille d'aventure utilisable.
- **P2 — Après bêta** : amélioration importante, mais explicitement différée.
- **P3 — À réévaluer** : valeur ou priorité à confirmer après les premiers essais.

## Périmètre confirmé de la bêta

- Une application Android installable et testée sur le téléphone du porteur du projet.
- Une bibliothèque de base contenant *La Vallée des Os*, tome 1 de la série *Legendary Kingdoms*.
- Une seule aventure suffit pour la bêta. À sa création, choisir exactement quatre personnages parmi les six personnages définis pour le livre.
- Une feuille d'aventure complète pour l'usage prévu, avec au minimum la section actuelle, les données des personnages, les points de vie et l'équipement.
- Les valeurs de base des caractéristiques restent inchangées. L'équipement peut porter des modificateurs ; les caractéristiques effectives sont calculées en conséquence.
- Les points de vie actuels peuvent être modifiés et ne peuvent pas dépasser leur maximum.
- Les équipements sont définis dans le catalogue du livre, peuvent être ajoutés au fil de l'utilisation et appliqués aux personnages. La quantité portée dépend de l'équipement ; la plupart sont limités à un exemplaire.
- Persistance locale après fermeture et redémarrage ; utilisation hors ligne ; export et import JSON.
- Architecture et règles métier conçues pour réutiliser le maximum de code lors des cibles Windows et Linux.

## Phases et tâches

### Phase 0 — Lever les inconnues qui menacent le délai

Objectif : préciser le minimum nécessaire pour construire la feuille et vérifier immédiatement la faisabilité de la cible Android.

| ID | Priorité | Tâche et livrable | Critère de validation | Dépendances | Statut |
|---|---|---|---|---|---|
| R-01 | P0 | Définir le contenu complet de la feuille d'aventure : champs requis pour chaque personnage, règles d'édition et présentation de l'équipe. Livrable : liste de champs validée. | La liste distingue clairement données initiales, valeurs courantes, valeurs calculées et informations éditables. Les champs non décidés restent identifiés. | — | Brouillon détaillé après entretien — validation finale requise |
| R-02 | P0 | Rassembler les données initiales des six personnages et définir le catalogue de données du livre, à partir des éléments fournis par le porteur du projet. Livrable : jeu de données structuré et vérifiable, sans dépendance à une saisie manuelle répétée en jeu. | Les six personnages sont représentés ; leurs valeurs de base, maximums de vie et équipements de départ sont vérifiés par le porteur. Le catalogue peut être enrichi à la demande avec les propriétés obligatoires définies dans R-02. | R-01 | Terminé — six fiches relues et confirmées ; catalogue extensible défini |
| R-03 | P0 | Prototyper la chaîne JavaFX/Gluon sur le téléphone Android réel, avec une persistance locale et accès aux fichiers nécessaires au JSON. Livrable : décision de faisabilité et prototype exécutable. | Le prototype se construit, s'installe et démarre sur le téléphone ; une donnée locale survit à un redémarrage ; la voie d'accès aux fichiers est validée. | — | À faire |
| R-04 | P0 | Arrêter les versions et outils compatibles pour Java, JavaFX/Gluon, Android SDK, build et stockage local, en visant un domaine partagé et des adaptateurs plateforme isolés. Livrable : choix technique consigné. | Build Android reproductible ; risques, éventuels coûts/licences et option de repli documentés. Ne pas engager les écrans complets avant le résultat de R-03. | R-03 | À faire |

### Phase 1 — Construire le socle métier et les données du MVP

Objectif : représenter sans perte l'aventure, la feuille des quatre personnages et le catalogue, indépendamment de l'interface Android.

| ID | Priorité | Tâche et livrable | Critère de validation | Dépendances | Statut |
|---|---|---|---|---|---|
| R-05 | P1 | Définir le modèle de données versionnable pour le livre, l'aventure unique, les quatre personnages choisis, leurs valeurs initiales/courantes et l'équipement. Livrable : modèle métier et stratégie de persistance. | Les valeurs initiales ne sont pas écrasées par les valeurs courantes ; l'état de la feuille et les données de catalogue restent cohérents après lecture/écriture. | R-01, R-04 | À faire |
| R-06 | P1 | Intégrer le catalogue de base du tome 1, les six personnages et leurs valeurs de départ. Livrable : données initiales disponibles dès la première ouverture. | La bibliothèque présente le livre et le choix de personnages permet de sélectionner quatre personnages distincts parmi six. | R-02, R-05 | À faire |
| R-07 | P1 | Définir puis implémenter la validation de l'état des personnages : points de vie actuels bornés par leur maximum et caractéristiques de base immuables en jeu. Livrable : règles métier testées. | Les valeurs invalides sont refusées avec un retour explicite ; une modification d'une valeur courante ne modifie pas sa valeur initiale. | R-01, R-05 | À faire |
| R-08 | P1 | Modéliser les équipements du livre, leurs modificateurs et leurs limites de quantité portée ; permettre d'ajouter un équipement au catalogue du livre au fil du jeu. Livrable : catalogue extensible et règles associées. | Un équipement peut être défini une fois puis attribué ; sa règle de quantité est contrôlée ; un ajout au catalogue reste disponible ensuite. Les valeurs de modificateurs sont vérifiables. | R-02, R-05 | À faire |
| R-09 | P1 | Calculer les caractéristiques effectives à partir des valeurs de base et des modificateurs des équipements portés. Livrable : calcul métier centralisé, utilisé par l'interface. | L'ajout, le retrait ou le changement d'équipement met à jour les valeurs effectives sans altérer les valeurs de base. | R-07, R-08 | À faire |

### Phase 2 — Livrer la feuille d'aventure Android

Objectif : permettre de créer la partie, jouer et retrouver l'état complet sans devoir effacer et réécrire la feuille papier.

| ID | Priorité | Tâche et livrable | Critère de validation | Dépendances | Statut |
|---|---|---|---|---|---|
| R-10 | P1 | Réaliser les écrans Android nécessaires : bibliothèque initiale, création de l'aventure, sélection des quatre personnages, feuille d'équipe et fiches individuelles. Livrable : parcours jouable sur téléphone. | Depuis une installation vierge, le porteur du projet peut créer l'aventure et consulter les quatre fiches ainsi que l'équipement de départ. | R-06, R-07, R-08 | À faire |
| R-11 | P1 | Ajouter la mise à jour rapide de la section actuelle et des points de vie depuis la feuille d'aventure. Livrable : édition simple des données fréquemment réécrites sur papier. | Les nouvelles valeurs valides sont visibles immédiatement ; les points de vie ne dépassent pas le maximum ; les caractéristiques de base restent protégées. | R-07, R-10 | À faire |
| R-12 | P1 | Permettre d'ajouter, retirer et attribuer l'équipement, avec indication claire des quantités et des modificateurs appliqués. Livrable : gestion de l'équipement en partie. | Les restrictions propres à chaque équipement sont respectées ; les caractéristiques effectives sont recalculées ; les autres personnages ne sont pas modifiés par erreur. | R-08, R-09, R-10 | À faire |
| R-13 | P1 | Enregistrer localement les changements de la feuille et de la section avec confirmation d'écriture fiable. Livrable : reprise après fermeture. | Après fermeture forcée ou normale puis relance, la section, les personnages, les points de vie et l'équipement correspondent au dernier état effectivement enregistré ; une erreur n'est pas présentée comme un succès. | R-04, R-05, R-10, R-11, R-12 | À faire |
| R-14 | P1 | Valider les principaux parcours et erreurs sur le téléphone cible, en mode avion. Livrable : compte-rendu de test bêta. | Installation, création, changements de section, édition des fiches, règles de points de vie, équipement, fermeture/réouverture et fonctionnement hors ligne sont vérifiés sur l'appareil. | R-13 | À faire |

### Phase 3 — Sauvegarder, restaurer et distribuer la bêta

Objectif : protéger les données personnelles et rendre la version bêta installable et récupérable.

| ID | Priorité | Tâche et livrable | Critère de validation | Dépendances | Statut |
|---|---|---|---|---|---|
| R-15 | P1 | Définir un format JSON versionné couvrant le catalogue, l'aventure, la feuille des personnages et l'équipement. Livrable : schéma documenté et exemples de test. | Un export inclut toutes les données nécessaires à la reconstruction de la feuille ; les données personnelles et le catalogue sont distinguables. | R-05, R-08 | À faire |
| R-16 | P1 | Implémenter l'export JSON et l'enregistrement du fichier via le mécanisme Android prévu. Livrable : sauvegarde manuelle récupérable par l'utilisateur. | Le fichier exporté est valide, lisible par l'application et sauvegardé à un emplacement choisi ou clairement accessible. Les erreurs sont signalées. | R-03, R-15 | À faire |
| R-17 | P1 | Implémenter l'import JSON avec validation avant écriture et comportement explicite en cas de fichier invalide ou de données existantes. Livrable : restauration contrôlée. | Un fichier invalide ne modifie pas les données locales ; un export de l'application peut être réimporté et restituer la feuille ; les conflits ne sont jamais écrasés silencieusement. | R-15, R-16 | À faire |
| R-18 | P1 | Produire, installer et remettre la bêta Android pour test personnel, avec instructions de sauvegarde et de mise à jour. Livrable : APK ou paquet Android signé selon la chaîne retenue et procédure associée. | Installation et lancement sur le téléphone cible confirmés ; les limites connues et la procédure de restauration sont communiquées. | R-14, R-16, R-17 | À faire |

### Phase 4 — Enrichir après retour de bêta

Objectif : étendre les usages sans bloquer la livraison initiale ni compromettre les données déjà créées.

| ID | Priorité | Tâche et livrable | Critère de validation | Dépendances | Statut |
|---|---|---|---|---|---|
| R-19 | P2 | Recueillir les retours de bêta et ajuster les irritants prioritaires de la feuille d'aventure. Livrable : liste de changements triée selon l'usage réel. | Chaque changement retenu correspond à un problème observé ou à un besoin confirmé ; le socle de données est préservé. | R-18 | À faire |
| R-20 | P2 | Ajouter plusieurs aventures et leur liste/accueil de reprise. | Deux aventures peuvent coexister sans partager accidentellement leurs personnages, états ou équipements. | R-05, R-13 | À faire |
| R-21 | P2 | Ajouter historique détaillé des sections, notes et création/reprise de branches sans altérer les parcours passés. | Une reprise depuis un point passé conserve la branche d'origine et l'état des personnages au point choisi. | R-05, R-20 | À faire |
| R-22 | P2 | Compléter le cycle de vie (pause/reprise/terminée) et les règles d'accès associées. | Les transitions sont cohérentes ; une aventure terminée reste consultable. | R-20 | À faire |
| R-23 | P2 | Étendre bibliothèque et gestion des livres/personnages/équipements au-delà du catalogue initial. | Les ajouts et personnalisations ne modifient pas silencieusement le modèle de base ni les parties existantes. | R-05, R-08 | À faire |
| R-24 | P2 | Compléter les conflits d'import par comparaison et choix explicite champ par champ, si nécessaire au-delà du besoin de restauration bêta. | Annuler ne modifie rien ; confirmer applique les choix de façon atomique ; les branches restent des parcours cohérents. | R-17, R-21 | À faire |
| R-25 | P2 | Porter l'application sur Windows puis Linux en partageant le domaine et les règles métier et en isolant uniquement les adaptations de plateforme. | Parcours et données équivalents ; persistance et import/export vérifiés sur chaque cible ; aucune régression Android. | R-04, R-19 | À faire |

## Dépendances et ordre recommandé

1. Valider la faisabilité JavaFX/Gluon et du stockage sur l'appareil Android avant d'investir dans l'interface complète (R-03, R-04).
2. Définir les champs de la feuille et disposer des données initiales fournies par le porteur du projet avant de figer le modèle (R-01, R-02, R-05).
3. Implémenter les règles de personnages et d'équipement dans le domaine avant de les présenter dans l'interface (R-07 à R-09).
4. Construire le parcours Android, garantir la persistance, puis valider les parcours réels avant la remise de la bêta (R-10 à R-14, R-18).
5. Versionner le format JSON et tester l'aller-retour avant distribution (R-15 à R-18).

## Risques, hypothèses et décisions à prendre

- **Risque technique principal observé dans les documents :** la compatibilité JavaFX/Gluon, du stockage local et du sélecteur de fichiers sur Android n'est pas encore démontrée. Le prototype constitue un jalon bloquant, avec une option de repli à décider si une exigence échoue.
- **Données du livre :** les six personnages sont définis dans le livre, mais le contenu détaillé à intégrer et les premières définitions d'équipement restent à rassembler par le porteur du projet. La feuille de route ne présume pas de valeurs non fournies.
- **Feuille complète :** les documents mentionnent aussi argent, notes et sorts parmi les données possibles ; la portée exacte de « complète » pour cette aventure, ainsi que les champs à inclure ou non dans la bêta, reste à confirmer par l'inventaire R-01.
- **Modificateurs d'équipement :** leur formule, leur cumul et les caractéristiques affectées ne sont pas encore précisés ; à fixer avant R-09.
- **Limites d'équipement :** la plupart des objets portés sont uniques, mais il existe des exceptions. La limite doit être définie pour chaque objet ajouté.
- **Import JSON :** import/export sont confirmés pour la bêta ; le traitement avancé des conflits n'a pas été confirmé comme indispensable au premier test et reste une amélioration post-bêta. Aucun remplacement silencieux n'est acceptable.
- **Périmètre volontairement différé :** historique détaillé, branches, pause/fin, plusieurs aventures, synchronisation et cibles Windows/Linux ne doivent pas retarder la première bêta. Le socle doit néanmoins permettre leur ajout sans migration destructive.
- **Échéance :** « d'ici quelques semaines » est un objectif indicatif confirmé ; aucune date ferme, estimation ou ordre de grandeur par tâche n'est disponible.

## État d'avancement

Les documents du projet décrivent l'architecture, les parcours, les exigences et les maquettes, mais présentent l'application comme non implémentée. Cette feuille de route traduit les échanges du 17 octobre 2026 en tâches priorisées ; aucun avancement de développement n'est présumé.

## Journal des mises à jour

| Date | Mise à jour |
|---|---|
| 2026-10-17 | Création de la feuille de route à partir du cadrage fonctionnel, de l'architecture UX, des documents UML, de la maquette HTML et des réponses du porteur du projet. Priorité donnée à une bêta Android rapide avec feuille d'aventure complète et section actuelle ; historique, branches et plateformes desktop reportés. |
