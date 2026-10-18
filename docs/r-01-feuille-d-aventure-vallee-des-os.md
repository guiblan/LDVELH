# R-01 — Spécification de la feuille d’aventure de *La Vallée des Os*

**Projet :** Compagnon LDVELH  
**Livre ciblé :** *La Vallée des Os*, tome 1 de *Legendary Kingdoms*  
**Statut :** Brouillon fonctionnel — validation du porteur du projet requise  
**Priorité :** P0  
**Version :** 0.2 — entretien fonctionnel intégré

> **Mise à jour importante :** les sections 1 à 14 constituent la première proposition de cadrage. Les réponses du porteur du projet consignées dans la section 15 et ses sous-sections sont la référence la plus récente et prévalent en cas de contradiction. La spécification reste à valider formellement.

## 1. Objectif

Décrire le contenu et le comportement attendus de la feuille d’aventure pour la première bêta. La feuille remplace les notes papier répétitives pendant la lecture : elle permet de retrouver la section en cours, de consulter l’équipe et de suivre pour chaque personnage ses points de vie et son équipement.

Ce document définit les besoins et les règles métier, pas la technologie ni le format final de persistance. Les valeurs exactes du livre qui ne figurent pas dans les documents du dépôt restent à fournir et à vérifier dans R-02.

## 2. Périmètre de la bêta

### Inclus

- Une aventure sur *La Vallée des Os*.
- La sélection de quatre personnages distincts parmi les six personnages du livre.
- Une vue d’équipe et une fiche consultable pour chaque personnage choisi.
- La section actuelle de l’aventure.
- Les données initiales des personnages, notamment les caractéristiques de base, le maximum de points de vie et l’équipement de départ, lorsqu’elles auront été vérifiées dans R-02.
- Le suivi des points de vie actuels et de l’équipement porté.
- Un catalogue d’équipement propre au livre, extensible pendant la partie.
- L’affichage des caractéristiques effectives calculées depuis les valeurs de base et les modificateurs d’équipement, lorsque les données et règles de calcul auront été confirmées.
- L’enregistrement local des changements, hors ligne.

### Hors périmètre de cette spécification bêta

- Plusieurs aventures ou plusieurs branches.
- L’historique détaillé des sections et la reprise depuis un point antérieur.
- La gestion de plusieurs tomes dans une même aventure.
- La synchronisation en ligne.
- Les règles automatiques de combat ou la résolution complète des règles du livre.
- Les sorts, ressources particulières et états spéciaux tant que leur nécessité pour les personnages de ce tome n’a pas été confirmée.

## 3. Sources et niveau de confiance

Cette première rédaction s’appuie sur les documents du dépôt :

- `docs/feuille-de-route-compagnon-ldvelh.md`
- `docs/cadrage-fonctionnel-compagnon-ldvelh.md`
- `docs/architecture-ux-compagnon-ldvelh.md`
- `docs/r-01-feuille-d-aventure-compagnon-ldvelh.md`

Ces documents confirment le périmètre général de la bêta, mais ne contiennent pas les fiches sources des six personnages ni le catalogue détaillé du livre. Aucune valeur de caractéristique, de points de vie, d’équipement ou de modificateur n’est donc fixée par cette spécification.

Les termes **Confirmé** et **Proposé** sont employés ainsi :

- **Confirmé** : indiqué explicitement dans le cadrage ou la feuille de route.
- **Proposé** : choix raisonnable pour rendre la spécification exploitable, à accepter ou corriger par le porteur du projet.
- **À confirmer** : le document ne permet pas de trancher sans information supplémentaire.

## 4. Scénarios utilisateur

### S1 — Créer l’aventure et choisir l’équipe (P1)

Le lecteur démarre une aventure sur *La Vallée des Os*, sélectionne exactement quatre personnages distincts parmi les six disponibles, puis consulte la feuille d’équipe et les fiches individuelles.

**Test indépendant :** à partir des six entrées de personnages fournies par R-02, vérifier qu’une équipe de quatre peut être créée, qu’un personnage ne peut pas être sélectionné deux fois et que les quatre fiches sont accessibles.

### S2 — Reprendre la lecture à la bonne section (P1)

Le lecteur consulte la section actuelle depuis la vue d’équipe, la modifie lorsqu’il avance dans le livre et retrouve cette valeur après fermeture puis réouverture.

**Test indépendant :** enregistrer une nouvelle section valide, quitter l’application, la relancer hors ligne et vérifier que la section est toujours affichée.

### S3 — Suivre les points de vie et l’équipement d’un personnage (P1)

Le lecteur ouvre une fiche, consulte les points de vie et l’équipement, met à jour une valeur courante ou attribue/retire un équipement autorisé et voit les informations correspondantes dans la vue d’équipe.

**Test indépendant :** modifier un personnage et vérifier que ses valeurs sont mises à jour sans modifier les autres personnages ni ses valeurs de base.

### S4 — Consulter les caractéristiques de base et effectives (P1)

Le lecteur distingue les caractéristiques initiales des valeurs effectives après application des modificateurs d’équipement.

**Test indépendant :** vérifier qu’une modification d’équipement ne change pas la caractéristique de base et que la valeur effective correspond à la règle de calcul validée.

## 5. Inventaire fonctionnel de la feuille

La portée désigne l’objet auquel appartient la donnée : **Livre**, **Aventure**, **Équipe** ou **Personnage**. Une donnée calculée est affichée en lecture seule. Les champs marqués « à confirmer » ne doivent pas être codés comme des règles définitives avant validation.

### 5.1 Références du livre et de l’aventure

| Champ | Portée | Type fonctionnel | Catégorie | Éditable en partie | Règle / statut |
|---|---|---|---|---|---|
| Titre du livre | Livre | Texte | Référence du livre | Non | *La Vallée des Os* — confirmé. |
| Série | Livre | Texte | Référence du livre | Non | *Legendary Kingdoms* — confirmé. |
| Tome | Livre | Entier | Référence du livre | Non | Tome 1 — confirmé. |
| Nom de l’aventure | Aventure | Texte | Initiale | À confirmer | Nom par défaut à déterminer ; il peut reprendre le titre du livre. |
| Personnages participants | Équipe | Liste de références | Initiale | À la création seulement | Exactement quatre références distinctes parmi les six personnages disponibles — confirmé. |
| Section actuelle | Équipe | Entier | Valeur courante | Oui | Une seule progression est affichée pour l’équipe dans la bêta — confirmé par le parcours d’équipe ; règles de saisie précisées ci-dessous. |
| Statut de l’aventure | Aventure | Valeur parmi les statuts | Valeur courante | Non dans cette bêta | Pause/reprise/terminée sont différés dans la feuille de route. |

### 5.2 Champs de chaque personnage

| Champ | Portée | Type fonctionnel | Catégorie | Éditable en partie | Règle / statut |
|---|---|---|---|---|---|
| Identifiant stable du personnage du livre | Livre | Identifiant | Référence du livre | Non | Référence permettant de distinguer les six personnages, y compris si des noms se ressemblent. Son format relève de R-05. |
| Nom du personnage | Livre/personnage | Texte | Donnée initiale | Non | Repris de la fiche source du livre, à fournir et vérifier en R-02. |
| Nom d’usage ou surnom | Personnage | Texte facultatif | Valeur courante | À confirmer | Aucun besoin n’est confirmé ; ne pas imposer sa présence. |
| Caractéristiques de base | Personnage | Ensemble de caractéristiques nommées et typées | Donnée initiale | Non pendant la partie | Noms, valeurs et types dépendent des données vérifiées du livre. Les caractéristiques exactes sont à fournir en R-02. |
| Caractéristiques effectives | Personnage | Ensemble de valeurs correspondantes aux caractéristiques de base | Valeur calculée | Non | Calcul fondé sur la base et les modificateurs d’équipement validés. Formule, cumul et arrondis restent à confirmer avant R-09. |
| Points de vie maximum | Personnage | Entier | Donnée initiale | Non pendant la partie | Défini par la donnée vérifiée du personnage ; distinct des points de vie actuels. |
| Points de vie actuels | Personnage | Entier | Valeur courante | Oui | Ne peut dépasser le maximum. La borne basse à zéro est proposée, à confirmer avec les règles du livre. |
| Équipement de départ | Personnage | Liste de références d’équipements et quantités | Donnée initiale | Non directement | Provenance et contenu à vérifier en R-02. Sert à initialiser l’inventaire de la partie. |
| Équipement porté / inventaire courant | Personnage | Liste de références d’équipements et quantités | Valeur courante | Oui | Attribution, retrait et quantités doivent respecter la définition de chaque équipement. |
| Modificateurs d’équipement appliqués | Personnage | Ensemble de modificateurs nommés | Valeur calculée | Non | Dérivé de l’équipement porté ; ne modifie jamais les caractéristiques de base. Les règles exactes restent à vérifier. |
| Argent | Personnage | Valeur numérique et unité à définir | Valeur courante | À confirmer | Le cadrage fonctionnel mentionne l’argent comme donnée possible, mais son besoin pour le tome et son unité ne sont pas définis dans les documents consultés. |
| Notes libres | Personnage | Texte | Valeur courante | À confirmer | Les notes sont mentionnées dans le cadrage général ; présence et emplacement dans la bêta restent à décider. |
| Sorts, ressources ou états particuliers | Personnage | Champs définis par le livre | Référence et/ou valeur courante | À confirmer | À inclure seulement si les fiches vérifiées des personnages de ce tome établissent ce besoin. Ne pas créer un champ universel sans source. |

### 5.3 Champs du catalogue d’équipement

| Champ | Portée | Type fonctionnel | Catégorie | Éditable en partie | Règle / statut |
|---|---|---|---|---|---|
| Identifiant d’équipement | Livre | Identifiant | Référence du livre | Non | Référence stable définie lors de l’intégration des données. |
| Nom | Livre | Texte | Référence du livre | Non après création, sauf correction explicite | Repris des sources fournies ; provenance conservée. |
| Description courte | Livre | Texte facultatif | Référence du livre | À confirmer | À limiter à une description utile au jeu ; ne pas recopier le texte du livre. |
| Quantité maximale portée | Livre | Entier positif ou règle « sans limite » | Référence du livre | À la création d’un nouvel équipement | La plupart des objets sont limités à un exemplaire, avec des exceptions — confirmé. La limite précise est définie par objet. |
| Modificateurs | Livre | Liste de couples caractéristique / valeur | Référence du livre | À la création d’un nouvel équipement | Les valeurs et les caractéristiques affectées doivent être sourcées. La formule de cumul reste à confirmer. |
| Disponibilité dans le catalogue de partie | Aventure | État ou présence dans le catalogue | Valeur courante | Oui | Le lecteur peut ajouter un équipement au catalogue au fil du jeu — confirmé. |
| Quantité possédée et attribution | Personnage | Entier / relation équipement-personnage | Valeur courante | Oui | Doit respecter la limite de portage et ne pas créer de copies incohérentes de la définition d’un objet. |

## 6. Règles métier et d’édition

### 6.1 Équipe et progression

1. Une aventure de bêta contient exactement quatre personnages distincts, sélectionnés parmi les six personnages du tome.
2. La section actuelle appartient à l’équipe et est présentée une seule fois dans la vue principale. Cette interprétation est confirmée par la priorité donnée à une équipe qui avance ensemble.
3. La valeur saisie est un entier positif. Le nombre maximal de sections et la validation par rapport au contenu du livre ne sont pas imposés, car le nombre total de sections n’est pas requis au MVP.
4. L’historique détaillé de progression est hors périmètre R-01 bêta. La conservation d’un historique individuel est définie dans le cadrage général mais reportée par la feuille de route ; son articulation avec la feuille simplifiée est à revoir lors de la planification.

### 6.2 Valeurs des personnages

1. Les caractéristiques de base sont conservées séparément et ne sont pas modifiables pendant une partie.
2. Les valeurs effectives sont calculées à partir des caractéristiques de base et des modificateurs des équipements portés. Elles sont affichées en lecture seule.
3. La règle exacte de calcul, le cumul de plusieurs équipements affectant la même caractéristique et l’interaction avec d’éventuels effets temporaires restent à confirmer avant implémentation.
4. Les points de vie actuels sont modifiables. Ils ne peuvent en aucun cas dépasser le maximum. Refuser la valeur invalide avec un message explicite, sans correction silencieuse.
5. La valeur minimale des points de vie, l’autorisation d’une valeur nulle ou négative et les conséquences d’un personnage à zéro sont à confirmer avec les règles du livre. La proposition provisoire est une borne basse à zéro.
6. Modifier un personnage ne modifie pas les données des autres personnages.

### 6.3 Équipement

1. Une définition d’équipement appartient au catalogue du livre ; une partie peut y ajouter une entrée sans modifier les données de base fournies avec le livre.
2. Chaque définition indique sa propre limite de quantité portée. La valeur par défaut ne doit pas être fixée globalement à un exemplaire si des exceptions existent.
3. Attribuer ou retirer un objet affecte uniquement le personnage concerné.
4. Les modificateurs sont calculés depuis l’équipement porté. La base reste intacte ; les valeurs effectives sont recalculées selon la formule validée.
5. Si une attribution dépasse une limite, l’opération est refusée et un message explique la limite.

### 6.4 Persistance et édition

- Les valeurs modifiables sont enregistrées automatiquement, conformément au cadrage UX.
- La feuille doit fonctionner hors ligne et son état doit survivre à la fermeture et au redémarrage.
- Les erreurs d’écriture ne doivent pas être présentées comme des enregistrements réussis.
- Cette spécification ne fixe ni technologie de persistance, ni format de stockage local.

## 7. Présentation attendue

### 7.1 Vue d’équipe

Ordre d’information proposé pour permettre une consultation rapide pendant la lecture :

1. titre ou nom de l’aventure et tome ;
2. section actuelle, avec accès rapide à sa modification ;
3. quatre fiches synthétiques, une par personnage, avec nom et points de vie actuels / maximum ;
4. résumé d’équipement et caractéristiques effectives pertinentes lorsque leur sélection aura été validée ;
5. accès direct à la fiche individuelle de chaque personnage.

Le choix des caractéristiques et objets précis mis en avant est à confirmer après réception des fiches sources du tome et essai sur téléphone. Aucun statut tel que « blessé » ou « hors combat » n’est ajouté sans règle confirmée.

### 7.2 Fiche individuelle

La fiche présente dans cet ordre :

1. identité et nom de référence du personnage ;
2. caractéristiques de base, clairement identifiées comme initiales ;
3. caractéristiques effectives, visuellement distinctes et en lecture seule ;
4. points de vie actuels et maximum ;
5. équipement de départ et inventaire courant, avec quantités et modificateurs vérifiés ;
6. données optionnelles (argent, notes, ressources ou sorts) uniquement après décision explicite.

Les valeurs calculées ne doivent pas ressembler à des champs saisissables. Les données initiales doivent demeurer consultables après les modifications de partie.

## 8. Scénarios limites à couvrir

- Une sélection contient moins ou plus de quatre personnages : l’aventure ne peut pas être créée tant que le nombre requis n’est pas atteint.
- Le même personnage est sélectionné deux fois : la sélection est refusée.
- Une caractéristique de base est modifiée par erreur via l’interface : elle reste non éditable pendant la partie.
- Les points de vie dépassent leur maximum ou sont sous la borne minimale validée : la modification est refusée avec une explication.
- Une quantité d’équipement dépasse la limite propre à l’objet : l’attribution est refusée.
- Un équipement référencé n’est plus disponible dans le catalogue de la partie : l’incohérence doit être signalée, pas remplacée silencieusement.
- Un modificateur ou une valeur nécessaire au calcul est absent : ne pas afficher un résultat présenté comme exact ; signaler que la donnée est incomplète.
- La sauvegarde échoue : l’utilisateur ne doit pas recevoir une confirmation de réussite.

## 9. Exigences fonctionnelles traçables

- **REQ-001** : Le lecteur doit pouvoir démarrer une aventure sur *La Vallée des Os*.
- **REQ-002** : Le lecteur doit pouvoir sélectionner exactement quatre personnages distincts parmi les six personnages du livre.
- **REQ-003** : La feuille doit présenter une seule section actuelle partagée par l’équipe dans la bêta.
- **REQ-004** : La feuille doit distinguer les caractéristiques de base des caractéristiques effectives calculées.
- **REQ-005** : Les caractéristiques de base doivent être consultables mais non modifiables pendant la partie.
- **REQ-006** : Les points de vie actuels doivent être modifiables sans dépasser le maximum du personnage.
- **REQ-007** : La feuille doit afficher, pour chaque personnage, ses points de vie actuels et maximum.
- **REQ-008** : Le lecteur doit pouvoir consulter l’équipement de départ et gérer l’équipement courant de chaque personnage.
- **REQ-009** : Chaque équipement doit respecter sa propre limite de quantité portée.
- **REQ-010** : Les modificateurs d’équipement ne doivent pas altérer les caractéristiques de base.
- **REQ-011** : La vue d’équipe doit donner accès aux quatre fiches individuelles et aux informations essentielles de progression et de santé.
- **REQ-012** : Les modifications valides doivent persister localement et rester consultables hors ligne après redémarrage.
- **REQ-013** : Toute saisie refusée doit donner un retour explicite et ne pas modifier silencieusement l’état enregistré.
- **REQ-014** : Le catalogue de la partie doit pouvoir être enrichi sans altérer la définition initiale du livre.

## 10. Entités métier identifiées

- **Livre** : référence de *La Vallée des Os*, tome 1 de *Legendary Kingdoms*, et modèle de feuille associé.
- **Aventure** : partie jouée sur le livre ; contient l’équipe et sa section actuelle.
- **Équipe** : groupe de quatre personnages retenus parmi les six ; porte la progression partagée de la bêta.
- **Personnage du livre** : définition initiale d’un personnage, ses caractéristiques de base, son maximum de vie et ses équipements initiaux.
- **Personnage dans l’aventure** : état courant d’un personnage retenu, notamment ses points de vie et son inventaire.
- **Équipement du livre** : définition cataloguée, avec limite de quantité et éventuels modificateurs sourcés.
- **Équipement porté** : quantité d’un équipement attribuée à un personnage dans l’aventure.

## 11. Critères de réussite

- **SC-001** : Sur les six personnages référencés, une aventure accepte exactement quatre sélections uniques et rend accessibles les quatre fiches.
- **SC-002** : Depuis la vue d’équipe, le lecteur peut identifier la section en cours et les points de vie actuels / maximum de chacun des quatre personnages sans ouvrir leurs fiches.
- **SC-003** : Une caractéristique de base reste inchangée après toute modification d’équipement ; la valeur effective correspond à la formule validée par le porteur.
- **SC-004** : Toute valeur de points de vie supérieure au maximum est refusée, sans altérer la valeur précédemment enregistrée.
- **SC-005** : Une quantité d’équipement supérieure à la limite définie pour cet objet est refusée, sans altérer les autres personnages.
- **SC-006** : Après modification valide, fermeture et redémarrage hors ligne, la section, les points de vie et l’équipement affichés correspondent au dernier état enregistré.
- **SC-007** : Chaque donnée initiale intégrée pour les six personnages et le catalogue est accompagnée d’une provenance vérifiable dans le livrable R-02.

## 12. Décisions et questions ouvertes

| Sujet | État actuel | Décision ou action requise |
|---|---|---|
| Livre ciblé | Confirmé | *La Vallée des Os*, tome 1 de *Legendary Kingdoms*. |
| Nombre de personnages dans la bêta | Confirmé | Choisir exactement quatre personnages distincts parmi six. |
| Progression d’équipe | Confirmé dans le cadrage du jeu en équipe ; détail à harmoniser avec l’historique | La feuille bêta affiche une section partagée. Confirmer que tous les personnages sont toujours solidaires au tome 1. |
| Noms et caractéristiques exactes des six personnages | Non fournis dans les documents consultés | Le porteur doit fournir ou indiquer les pages/sources de référence pour R-02. |
| Maximum de PV et équipement de départ | Non fournis dans les documents consultés | Fournir les données vérifiables pour chaque personnage en R-02. |
| Liste complète du catalogue et modificateurs | Non fournis | Fournir les entrées utiles et leur provenance. |
| Formule des caractéristiques effectives | À confirmer | Préciser si les modificateurs s’additionnent, se remplacent ou suivent une règle particulière, et s’ils se cumulent. |
| Borne basse des PV | Proposition : zéro | Confirmer les règles applicables aux valeurs nulles/négatives et l’état d’un personnage à zéro PV. |
| Argent, notes, sorts et ressources | Mentionnés dans le cadrage général, pas confirmés pour ce tome dans les sources consultées | Confirmer les champs nécessaires à la bêta pour chacun des six personnages. |
| Nom d’aventure et surnom de personnage | À confirmer | Confirmer si le lecteur peut nommer l’aventure et/ou donner un nom d’usage aux personnages. |
| Affichage détaillé de l’équipe | Partiellement défini | Choisir les caractéristiques et équipements à mettre en avant après vérification du contenu du livre et essai UX. |
| Historique par personnage | Contradiction de périmètre entre cadrage général et feuille de route bêta | La feuille bêta ne définit que la section partagée ; confirmer que l’historique détaillé reste différé. |
| Statuts spéciaux | Non définis | N’en ajouter que si les règles ou fiches du livre en démontrent le besoin. |

## 13. Compatibilité multiplateforme

Les règles métier de la feuille — sélection des quatre personnages, bornes des PV, immutabilité des caractéristiques de base, limites d’équipement et calcul des valeurs effectives — doivent être indépendantes de l’appareil utilisé. Android est la cible initiale ; Windows et Linux sont des cibles ultérieures qui doivent réutiliser ces règles et présenter les mêmes données.

La présentation pourra s’adapter aux tailles d’écran : vue compacte et fiches navigables sur téléphone, largeur contenue sur ordinateur. Les détails de stockage et d’accès aux fichiers restent des choix techniques séparés, à valider dans les tâches de faisabilité et de modélisation.

## 14. Validation R-01

R-01 pourra être déclaré validé lorsque :

- le porteur aura confirmé ou corrigé les champs nécessaires à la feuille du tome 1 ;
- la progression partagée et le traitement des données individuelles seront cohérents avec l’usage réel ;
- les règles et bornes de PV auront été confirmées ;
- les champs optionnels (argent, notes, sorts, ressources) auront été tranchés pour la bêta ;
- les règles de modificateurs nécessaires à l’affichage des caractéristiques effectives auront été précisées ;
- la matrice de champs et les critères de réussite auront été acceptés.

**État au moment de la rédaction :** le périmètre fonctionnel de base est spécifié ; les données factuelles du livre et plusieurs règles restent à confirmer. Ce document est donc un brouillon de travail, pas une validation du porteur.

## 15. Décisions issues de l’entretien fonctionnel

Les points ci-dessous ont été établis par les réponses du porteur du projet lors de l’entretien du 18 octobre 2026. Ils précisent et, si nécessaire, remplacent les propositions antérieures de ce document.

### 15.1 Vue de l’équipe

La feuille comprend une section d’équipe présentant les quatre personnages de l’aventure. L’équipe ne peut pas contenir plus de deux magiciens. Le rôle est une propriété du personnage défini par le livre : il n’est pas choisi séparément lors de la création de l’aventure.

La sélection initiale demeure celle du périmètre de bêta : quatre personnages distincts parmi les six du livre. La contrainte de deux magiciens maximum s’applique en plus de cette sélection.

### 15.2 Fiche d’un personnage

Chaque personnage comporte les éléments suivants :

| Donnée | Catégorie | Éditabilité / règle convenue |
|---|---|---|
| Nom | Donnée initiale du personnage | Fourni par la définition du personnage dans le livre. |
| Rôle, notamment magicien | Donnée initiale du personnage | Fourni par la définition du personnage dans le livre. Le rôle n’est pas sélectionné par le joueur. |
| Combat | Valeur de base et valeur modifiée | La base vient du personnage défini dans le livre ; la valeur modifiée tient compte de l’équipement. |
| Discrétion | Valeur de base et valeur modifiée | La base vient du personnage défini dans le livre ; la valeur modifiée tient compte de l’équipement. |
| Savoir | Valeur de base et valeur modifiée | La base vient du personnage défini dans le livre ; la valeur modifiée tient compte de l’équipement. |
| Survie | Valeur de base et valeur modifiée | La base vient du personnage défini dans le livre ; la valeur modifiée tient compte de l’équipement. |
| Charisme | Valeur de base et valeur modifiée | La base vient du personnage défini dans le livre ; la valeur modifiée tient compte de l’équipement. |
| Santé | Valeur unique et courante | La valeur initiale vient du personnage défini dans le livre et évolue pendant l’aventure. L’idée que la valeur initiale soit également le maximum est une hypothèse du porteur, pas encore une règle validée. |
| Armure | Valeur calculée | Somme des modificateurs d’Armure des équipements portés. |
| Équipement | Cinq emplacements | Cinq objets librement choisis ; chaque emplacement peut rester vide. Un objet peut être présent plusieurs fois si sa limite de possession le permet. |
| Notes du personnage | Texte | Sous-section propre au personnage. Le format et la conservation d’un historique des notes du personnage ne sont pas précisés dans l’entretien. |
| Sorts | Jusqu’à six entrées pour chaque magicien | Chaque sort comporte un nom, une description conforme à la source du livre et une information de recharge si applicable. Les sorts actuellement prévus n’ont pas de recharge à suivre. |

Pour les compétences Combat, Discrétion, Savoir, Survie et Charisme, la valeur modifiée est la valeur de base augmentée ou diminuée par les modificateurs des équipements attribués. Les modificateurs applicables se cumulent par addition. La valeur de base demeure distincte et ne change pas.

Les modificateurs d’équipement sur les compétences et l’Armure sont des nombres entiers, positifs ou négatifs. La répartition exacte des modificateurs de chaque objet proviendra des données de catalogue vérifiées en R-02.

### 15.3 Équipement des personnages

- Chaque personnage dispose exactement de cinq emplacements d’équipement ; les emplacements vides sont autorisés.
- Chaque exemplaire d’un objet occupe un emplacement.
- Un même objet peut occuper plusieurs emplacements, jusqu’à cinq exemplaires si le joueur en possède suffisamment et si la limite individuelle de l’objet le permet.
- La limite de possession est définie pour chaque objet du catalogue ; elle prévaut sur le nombre d’emplacements disponibles.
- La fiche d’un objet comporte au minimum son nom, une description facultative et vide par défaut, sa limite de possession et ses modificateurs entiers (éventuellement négatifs ou positifs) sur les compétences et/ou l’Armure.
- Les équipements portés sont la source des valeurs modifiées des cinq compétences ainsi que de la valeur d’Armure.

### 15.4 Sorts

- Seuls les personnages dont le rôle est magicien disposent de cette sous-section.
- Un magicien peut avoir au maximum six sorts.
- Chaque sort contient son nom, sa description et son information de recharge lorsqu’elle existe.
- La description doit être fondée sur le contenu du livre. Sa source doit être identifiable ; ce document ne contient pas de transcription de la description.
- Les sorts actuellement utilisés par le porteur ne disposent pas de recharge. Aucun compteur, délai ou état « disponible/en recharge » n’est donc requis pour la première feuille, sauf besoin ultérieur confirmé.

### 15.5 Butin du groupe

Une section commune à l’équipe suit le total du butin en pièces d’argent. Le total est modifiable en ajoutant ou retirant une quantité et doit rester positif ou nul. Une opération qui conduirait à un montant négatif est refusée avec un retour explicite.

### 15.6 Codes

Une section présente tous les codes de A1 à A100. Chaque code est représenté par un état obtenu / non obtenu. Aucun autre champ par code n’a été demandé.

### 15.7 Coffre-fort

Le coffre-fort est partagé par l’équipe. Il contient des entrées de type objet, argent ou équipement. Chaque entrée comporte un nom et une quantité. Les unités et les règles particulières de quantité pour les différentes catégories restent à définir si elles diffèrent.

### 15.8 Flotte navale

La flotte contient au maximum cinq bateaux. Pour chaque bateau, la feuille suit :

- le nom du bateau ;
- sa valeur de combat ;
- sa valeur de vie ;
- la taille de sa soute ;
- son port de mouillage actuel ;
- sa cargaison.

Le mouillage désigne un port et doit être suivi comme une donnée du bateau. La cargaison est une liste de marchandises avec, au minimum, un nom et une quantité. Les bornes, unités et modalités de mise à jour des valeurs de combat, vie, soute et quantité de cargaison ne sont pas encore établies.

### 15.9 Armées

La feuille peut suivre au maximum 18 armées. Chaque armée comporte :

- une unité choisie parmi les unités définies dans le livre ; une unité absente du livre ne peut pas être ajoutée ;
- une force entière ;
- un moral entier ;
- une garnison.

Chaque changement de garnison doit conserver le nouvel emplacement et l’ancien emplacement. La date, l’auteur du changement et la conservation éventuelle de l’historique complet au-delà de la dernière transition ne sont pas encore précisés.

### 15.10 Champ de bataille

Le champ de bataille présente deux côtés, les forces du joueur et les forces ennemies. Chaque côté comporte les six emplacements suivants :

1. Réserve — flanc gauche ;
2. Réserve — centre ;
3. Réserve — flanc droit ;
4. Ligne de front — gauche ;
5. Ligne de front — centre ;
6. Ligne de front — droite.

Chaque emplacement contient au plus une armée référencée, choisie dans la liste des armées, plutôt qu’une nouvelle copie de ses données. Il reste à confirmer si les armées ennemies font partie de la même liste des 18 armées ou si une liste distincte leur est nécessaire, et si une même armée peut être déplacée d’un emplacement à un autre sans historique particulier.

### 15.11 Notes de mission

La feuille comporte une seule zone de notes de mission, partagée par toute l’aventure. Elle contient du texte libre. L’historique des modifications est conservé et chaque version est horodatée automatiquement (date et heure). La granularité de création d’une version — à chaque frappe, à l’enregistrement ou après validation explicite — reste à définir lors de la conception UX.

## 16. Organisation complète de la feuille

Ordre fonctionnel proposé, à valider pour l’affichage :

1. **Équipe** : quatre personnages au maximum, rôles, deux magiciens au maximum et accès aux fiches.
2. **Fiche de chaque personnage** : nom, rôle, valeurs de base et modifiées des cinq compétences, Santé, Armure calculée, cinq emplacements d’équipement, notes et, pour un magicien, jusqu’à six sorts.
3. **Butin du groupe** : total commun de pièces d’argent.
4. **Codes** : A1 à A100, obtenus ou non obtenus.
5. **Coffre-fort** : objets, argent et équipement avec quantités, partagé par l’équipe.
6. **Votre flotte navale** : jusqu’à cinq bateaux, leurs valeurs, mouillage et cargaison.
7. **Vos armées** : jusqu’à 18 armées avec unité du livre, force, moral et garnison.
8. **Le champ de bataille** : six emplacements pour les forces du joueur et six pour l’ennemi, chacun pouvant référencer au plus une armée.
9. **Notes de mission** : texte libre commun avec historique horodaté.
10. **Section actuelle** : position de progression de l’aventure, conformément au périmètre initial du MVP.

Les sections constituent les rubriques fonctionnelles de la feuille. Leur ordre visuel final et leur mode de navigation sur téléphone restent des choix UX à confirmer.

## 17. Matrice synthétique des données et règles d’édition

| Portée | Donnée | Nature | Règle confirmée |
|---|---|---|---|
| Personnage du livre | Nom, rôle, bases des six compétences, Santé initiale | Initiale / référence du livre | Importée depuis les données vérifiées du personnage ; les valeurs détaillées sont à établir en R-02. |
| Personnage d’aventure | Combat, Discrétion, Savoir, Survie, Charisme modifiés | Calculée et affichée | Base correspondante + somme des modificateurs d’équipement. |
| Personnage d’aventure | Santé | Courante | Évolue ; maximum égal à la base seulement à titre d’hypothèse, à confirmer. |
| Personnage d’aventure | Armure | Calculée et affichée | Somme des modificateurs d’Armure des équipements. |
| Personnage d’aventure | Cinq emplacements d’équipement | Courante / éditable | Peuvent être vides ; les exemplaires utilisent chacun un emplacement et respectent la limite de l’objet. |
| Personnage d’aventure | Notes | Courante / éditable | Sous-section personnelle ; historique non décidé. |
| Personnage magicien | Sorts, jusqu’à six | Référence de livre / liste de personnage | Nom et description sourcée ; aucune gestion de recharge pour les sorts actuels. |
| Équipe | Section actuelle | Courante / éditable | Progression commune de l’aventure. |
| Équipe | Butin en pièces d’argent | Courante / éditable | Total commun supérieur ou égal à zéro. |
| Équipe | Codes A1–A100 | Courante / éditable | Chaque entrée est obtenue ou non obtenue. |
| Équipe | Coffre-fort | Courante / éditable | Entrées typées objet, argent ou équipement, avec nom et quantité. |
| Équipe | Flotte navale | Courante / éditable | Maximum cinq bateaux ; attributs définis à la section 15.8. |
| Équipe | Armées | Courante / éditable | Maximum 18 ; unité de la liste du livre, force et moral entiers, suivi des changements de garnison avec ancien et nouvel emplacement. |
| Équipe | Champ de bataille | Courante / éditable | Six emplacements par camp ; au plus une référence d’armée par emplacement. |
| Aventure | Notes de mission | Courante / éditable | Texte libre partagé ; versions conservées avec date et heure. |

## 18. Points restant à décider

Les réponses à l’entretien ont défini le contenu principal de la feuille. Les questions suivantes sont encore ouvertes et ne doivent pas être transformées en règles implicites :

1. **Santé maximale :** la valeur de départ est-elle bien aussi le maximum ? C’est l’hypothèse proposée, pas une certitude.
2. **Bornes de Santé :** peut-elle descendre sous zéro ? Quel comportement adopter à zéro ?
3. **Données exactes des personnages et du livre :** noms, rôles, caractéristiques de base, Santé initiale, sorts et unités autorisées doivent être extraits de sources fournies/vérifiables en R-02.
4. **Unités et conventions :** préciser les unités de taille de soute, cargaison, force, moral, combat et vie des bateaux, ainsi que les bornes permises.
5. **Coffre-fort :** préciser si les quantités sont entières pour toutes les entrées et si l’argent y suit la même règle non négative que le butin du groupe.
6. **Armées ennemies :** préciser si elles appartiennent à la liste des 18 armées ou à un registre distinct.
7. **Champ de bataille :** préciser si une armée peut occuper plusieurs emplacements simultanément et si les déplacements doivent être historisés.
8. **Garnison :** le nouvel et l’ancien emplacement sont conservés ; préciser si chaque transition est conservée dans un historique complet et si elle est horodatée.
9. **Notes personnelles :** préciser si les notes d’un personnage doivent également conserver un historique horodaté comme les notes de mission.
10. **Notes de mission :** préciser quand une nouvelle version historique est créée (à chaque frappe, après délai d’inactivité, à l’enregistrement ou sur confirmation).
11. **Sorts :** confirmer si une description exacte du livre est nécessaire au MVP ou si une référence/description résumée par le porteur suffit ; les règles de droit et les sources fournies doivent être respectées.
12. **Historique de progression :** confirmer si l’historique détaillé reste différé malgré les autres historiques de la feuille et si la section actuelle reste uniquement au niveau de l’aventure.
13. **Ordre et navigation :** valider l’ordre des sections et les informations à afficher sans ouvrir une fiche sur téléphone.

## 19. Critères de validation mis à jour

R-01 pourra être déclaré validé lorsque le porteur du projet aura confirmé :

- les champs par personnage et toutes les sections d’équipe décrites dans les sections 15 à 17 ;
- l’hypothèse de maximum de Santé et les bornes de cette valeur ;
- le fonctionnement des armées ennemies, du champ de bataille et du suivi des garnisons ;
- les unités et règles quantitatives requises pour les bateaux, armées, cargaisons et coffre-fort ;
- le comportement d’historique des notes de mission et des notes personnelles ;
- la source et le mode de représentation des sorts ;
- l’organisation UX générale de la feuille.

La validation de R-01 ne valide pas à elle seule les valeurs initiales du livre : celles-ci doivent être sourcées, structurées et vérifiées séparément au titre de R-02.
