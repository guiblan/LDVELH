# R-01 — Spécification de la feuille d’aventure de *La Vallée des Os*

**Projet :** Compagnon LDVELH  
**Livre ciblé :** *La Vallée des Os*, tome 1 de *Legendary Kingdoms*  
**Statut :** Brouillon fonctionnel — validation du porteur du projet requise  
**Priorité :** P0  
**Version :** 0.2 — entretien fonctionnel intégré

> **Mise à jour importante :** les sections 1 à 14 constituent la première proposition de cadrage. Les réponses du porteur du projet consignées dans la section 15 et ses sous-sections sont la référence la plus récente et prévalent en cas de contradiction. La spécification reste à valider formellement.

## 1. Objectif

Décrire le contenu et le comportement attendus de la feuille d’aventure pour la première bêta. La feuille remplace les notes papier répétitives pendant la lecture : elle permet de retrouver la section en cours, de consulter l’équipe et de suivre pour chaque personnage ses points de vie et son équipement.

Ce document définit les besoins et les règles métier, pas la technologie ni le format final de persistance. Les valeurs des six personnages sont transcrites dans R-02 depuis les captures fournies par le porteur ; les équipements et autres données qui ne sont pas encore disponibles y restent identifiés.

## 2. Périmètre de la bêta

### Inclus

- Une aventure sur *La Vallée des Os*.
- La sélection initiale de quatre personnages distincts parmi les six personnages du livre.
- Une vue d’équipe présentant les personnages actifs (quatre au départ, potentiellement moins après des décès sans remplaçant) et une fiche consultable pour chaque personnage choisi.
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

Ces documents confirment le périmètre général de la bêta. Les noms, les cinq compétences et la Santé initiale des six personnages ont depuis été transcrits dans R-02 à partir des captures fournies par le porteur ; les rôles, la règle du maximum de Santé, l’absence d’équipement au départ et les règles de mort ont également été confirmés par lui. Les transcriptions restent à relire. Les sorts et le catalogue détaillé des équipements et modificateurs ne sont pas encore complets.

Les termes **Confirmé** et **Proposé** sont employés ainsi :

- **Confirmé** : indiqué explicitement dans le cadrage ou la feuille de route.
- **Proposé** : choix raisonnable pour rendre la spécification exploitable, à accepter ou corriger par le porteur du projet.
- **À confirmer** : le document ne permet pas de trancher sans information supplémentaire.

## 4. Scénarios utilisateur

### S1 — Créer l’aventure et choisir l’équipe (P1)

Le lecteur démarre une aventure sur *La Vallée des Os*, sélectionne exactement quatre personnages distincts parmi les six disponibles, puis consulte la feuille d’équipe et les fiches individuelles. Les personnages non sélectionnés restent disponibles. Cette sélection est l’équipe initiale ; un personnage vivant ne peut pas être remplacé volontairement. Après un décès définitif, le lecteur doit choisir un remplaçant si un personnage admissible est disponible ; l’équipe ne peut compter moins de quatre membres que lorsqu’aucun remplaçant n’est disponible.

**Test indépendant :** à partir des six entrées de personnages fournies par R-02, vérifier que le lecteur peut créer une équipe initiale de quatre, qu’un personnage ne peut pas être sélectionné deux fois et que les quatre fiches sont accessibles. Vérifier qu’un personnage vivant ne peut pas être remplacé volontairement.

### S2 — Reprendre la lecture à la bonne section (P1)

Le lecteur consulte la section actuelle depuis la vue d’équipe, la modifie lorsqu’il avance dans le livre et retrouve cette valeur après fermeture puis réouverture.

**Test indépendant :** enregistrer une nouvelle section valide, quitter l’application, la relancer hors ligne et vérifier que la section actuelle et l’historique horodaté de ses changements sont conservés.

### S3 — Suivre les points de vie et l’équipement d’un personnage (P1)

Le lecteur ouvre une fiche, consulte les points de vie et l’équipement, met à jour une valeur courante ou attribue/retire un équipement autorisé et voit les informations correspondantes dans la vue d’équipe. Si la Santé atteint zéro, le personnage est indiqué comme mort définitivement dans cette aventure et retiré de l’équipe active ; le lecteur choisit quels personnages actifs reçoivent son équipement et ses objets. Si un personnage admissible parmi les six reste disponible, le lecteur doit le choisir comme remplaçant.

**Test indépendant :** modifier un personnage et vérifier que ses valeurs sont mises à jour sans modifier les autres personnages ni ses valeurs de base ; mettre un membre à zéro et vérifier son retrait de l’équipe active, la conservation de son état mort, la répartition de ses objets aux destinataires choisis par le lecteur et le remplacement obligatoire par un personnage admissible s’il en reste, avec Santé au maximum. Vérifier également que l’équipe continue avec les survivants si aucun remplacement n’est possible.

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
| Personnages participants | Équipe | Liste de références | Initiale et courante | À la création et lors d’un remplacement | À la création, le lecteur choisit lui-même quatre personnages distincts parmi les six. Un personnage mort définitivement est retiré de l’équipe active ; le lecteur choisit quels personnages actifs reçoivent son équipement et ses objets. Si un remplaçant admissible est disponible, le lecteur doit en choisir un ; celui-ci arrive avec sa Santé au maximum et sans équipement de départ. Sans remplaçant disponible, l’aventure continue avec les survivants. |
| Section actuelle | Équipe | Entier | Valeur courante et historique horodaté | Oui | Une seule progression est affichée pour l’équipe dans la bêta — confirmé par le parcours d’équipe. Chaque changement de section est conservé avec sa date et son heure. |
| Statut de l’aventure | Aventure | Valeur parmi les statuts | Valeur courante | Non dans cette bêta | Pause/reprise/terminée sont différés dans la feuille de route. |

### 5.2 Champs de chaque personnage

| Champ | Portée | Type fonctionnel | Catégorie | Éditable en partie | Règle / statut |
|---|---|---|---|---|---|
| Identifiant stable du personnage du livre | Livre | Identifiant | Référence du livre | Non | Référence permettant de distinguer les six personnages, y compris si des noms se ressemblent. Son format relève de R-05. |
| Nom du personnage | Livre/personnage | Texte | Donnée initiale | Non | Repris de la fiche source du livre, à fournir et vérifier en R-02. |
| Nom d’usage ou surnom | Personnage | Texte facultatif | Valeur courante | À confirmer | Aucun besoin n’est confirmé ; ne pas imposer sa présence. |
| Caractéristiques de base | Personnage | Ensemble de caractéristiques nommées et typées | Donnée initiale | Non pendant la partie | Combat, Discrétion, Savoir, Survie et Charisme sont transcrits dans R-02 depuis les captures fournies ; relecture souhaitée. |
| Caractéristiques effectives | Personnage | Ensemble de valeurs correspondantes aux caractéristiques de base | Valeur calculée | Non | Chaque valeur de base est augmentée ou diminuée de la somme des modificateurs entiers des équipements portés ; les valeurs de base restent intactes. |
| Points de vie maximum | Personnage | Entier | Donnée initiale | Non pendant la partie | Égal à la Santé initiale du personnage. |
| Points de vie actuels | Personnage | Entier | Valeur courante | Oui | Initialisés au maximum au démarrage de l’aventure ; restent entre zéro et le maximum inclus. À zéro, le personnage meurt définitivement dans *La Vallée des Os*. |
| Équipement de départ | Personnage | Liste de références d’équipements et quantités | Donnée initiale | Non directement | Aucun équipement ; absence confirmée par le porteur. L’inventaire de partie débute vide. |
| Inventaire courant | Personnage | Liste unique d’entrées individuelles d’objets et d’équipements | Valeur courante | Oui | Objets et équipements partagent une seule liste et les cinq mêmes emplacements ; chaque exemplaire porté figure sur une ligne distincte et occupe un emplacement, même si plusieurs exemplaires sont identiques. Chaque exemplaire conserve uniquement le numéro du paragraphe où il a été obtenu, s’il est connu, sans en recopier le texte. Les limites de possession propres à chaque objet s’appliquent au personnage. À la mort, le lecteur choisit les destinataires des biens portés ; les biens qui ne peuvent être attribués sont perdus. Le lecteur peut déposer dans le coffre tout ou partie de ses biens et en retirer certains exemplaires ; leur provenance est conservée lors des transferts. |
| Modificateurs d’équipement appliqués | Personnage | Ensemble de modificateurs nommés | Valeur calculée | Non | Dérivé de l’équipement porté ; ne modifie jamais les caractéristiques de base. Les règles exactes restent à vérifier. |
| Bourse en pièces d’argent | Équipe | Valeur numérique | Initiale et courante | Oui | Une seule bourse est partagée par l’équipe ; elle commence à zéro. Il n’existe pas de bourse individuelle par personnage. Le total doit rester positif ou nul. |
| Notes personnelles | Personnage | Texte libre avec historique horodaté | Valeur courante et versions antérieures | Oui | Chaque personnage actif dispose de notes personnelles. Une nouvelle version horodatée est créée lorsque le lecteur quitte la fenêtre ou la popup des notes si le texte a été modifié pendant cette session ; sans modification, aucune nouvelle version n’est créée. |
| Sorts | Personnage magicien | Liste de six entrées au maximum, chacune avec un nom et une description | Référence de livre / valeur courante | Oui | La liste initiale est vide et le lecteur peut ajouter les sorts au fil de l’eau. Aucun suivi de recharge n’est requis pour les sorts actuellement utilisés. |

### 5.3 Champs du catalogue d’équipement

| Champ | Portée | Type fonctionnel | Catégorie | Éditable en partie | Règle / statut |
|---|---|---|---|---|---|
| Identifiant d’équipement | Livre | Identifiant | Référence du livre | Non | Référence stable définie lors de l’intégration des données. |
| Nom | Livre | Texte | Référence du livre | Non après création, sauf correction explicite | Repris des sources fournies ; provenance conservée. |
| Description courte | Livre | Texte facultatif | Référence du livre | À confirmer | À limiter à une description utile au jeu ; ne pas recopier le texte du livre. |
| Quantité maximale portée par personnage | Livre | Entier positif ou règle « sans limite » | Référence du livre | À la création d’un nouvel équipement | La plupart des objets sont limités à un exemplaire, avec des exceptions — confirmé. La limite précise est définie par objet et s’applique aux exemplaires portés par un personnage, pas au stock du coffre-fort partagé. |
| Modificateurs | Livre | Liste de couples caractéristique / valeur | Référence du livre | À la création d’un nouvel équipement | Les valeurs et les caractéristiques affectées doivent être sourcées. La formule de cumul reste à confirmer. |
| Disponibilité dans le catalogue de partie | Aventure | État ou présence dans le catalogue | Valeur courante | Oui | Le lecteur peut ajouter un équipement au catalogue au fil du jeu — confirmé. |
| Quantité possédée et attribution | Personnage | Entier / relation équipement-personnage | Valeur courante | Oui | Chaque exemplaire porté occupe un emplacement et l’objet porté par un personnage respecte sa limite de possession. La limite de possession ne s’applique pas aux quantités stockées dans le coffre-fort. |

## 6. Règles métier et d’édition

### 6.1 Équipe et progression

1. Une aventure de bêta commence avec exactement quatre personnages distincts, sélectionnés parmi les six personnages du tome. L’équipe active peut ensuite compter moins de quatre membres si des personnages meurent et qu’aucun remplaçant n’est disponible.
2. La section actuelle appartient à l’équipe et est présentée une seule fois dans la vue principale. Cette interprétation est confirmée par la priorité donnée à une équipe qui avance ensemble.
3. La valeur saisie est un entier positif. Le nombre maximal de sections et la validation par rapport au contenu du livre ne sont pas imposés, car le nombre total de sections n’est pas requis au MVP.
4. L’historique détaillé de progression est hors périmètre R-01 bêta. La conservation d’un historique individuel est définie dans le cadrage général mais reportée par la feuille de route ; son articulation avec la feuille simplifiée est à revoir lors de la planification.
5. Les quatre personnages correspondent à l’effectif initial et sont choisis par le lecteur ; les deux personnages non retenus restent disponibles. Un personnage vivant ne peut pas être remplacé volontairement. Si un personnage meurt, il est conservé dans l’historique de l’aventure comme mort et retiré de l’équipe active ; le lecteur choisit quels personnages actifs reçoivent son équipement porté et ses objets. Si au moins un remplaçant admissible est disponible, le lecteur doit en choisir un parmi les personnages encore vivants qui n’ont pas encore fait partie de l’équipe, y compris parmi ceux non sélectionnés au départ. Celui-ci rejoint l’équipe avec sa Santé au maximum et sans équipement de départ. Le remplacement respecte la limite de deux magiciens. L’aventure continue avec moins de quatre membres seulement si aucun remplaçant admissible n’est disponible.

### 6.2 Valeurs des personnages

1. Les caractéristiques de base sont conservées séparément et ne sont pas modifiables pendant une partie.
2. Les valeurs effectives sont calculées à partir des caractéristiques de base et des modificateurs des équipements portés. Elles sont affichées en lecture seule.
3. Les modificateurs d’équipement s’additionnent sur la valeur de base ; l’interaction avec d’éventuels effets temporaires reste à confirmer avant implémentation.
4. Au démarrage d’une aventure, les points de vie actuels de chaque personnage sont initialisés à son maximum. Ils sont ensuite modifiables et toujours compris entre zéro et le maximum inclus. Refuser toute valeur hors de ces bornes avec un message explicite, sans correction silencieuse.
5. Un personnage dont les points de vie atteignent zéro est mort définitivement pour l’aventure *La Vallée des Os*. Son état doit être identifiable comme mort et ne peut pas être réactivé dans cette aventure.
6. Modifier un personnage ne modifie pas les données des autres personnages.

### 6.3 Équipement

1. Une définition d’équipement appartient au catalogue du livre ; une partie peut y ajouter une entrée sans modifier les données de base fournies avec le livre.
2. Chaque définition indique sa propre limite de quantité portée. La valeur par défaut ne doit pas être fixée globalement à un exemplaire si des exceptions existent.
3. Attribuer ou retirer un objet affecte uniquement le personnage concerné.
4. Les modificateurs sont calculés depuis l’équipement porté. La base reste intacte ; les valeurs effectives sont recalculées selon la formule validée.
5. Chaque personnage dispose d’une seule liste d’inventaire regroupant objets et équipements. Chaque entrée portée occupe un de ses cinq emplacements et respecte la limite de possession propre à l’objet. La limite de possession ne restreint pas les quantités rangées dans le coffre-fort.
6. Lorsqu’un personnage meurt, le lecteur choisit les destinataires des objets et équipements de son inventaire unique. Les attributions issues de cette répartition respectent les emplacements disponibles et les limites de possession ; les biens qui ne peuvent être attribués à aucun survivant sont perdus.

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
3. une fiche synthétique par personnage actif (jusqu’à quatre), avec nom et points de vie actuels / maximum ;
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

- Lors de la création, une sélection contient moins ou plus de quatre personnages : l’aventure ne peut pas être créée tant que le nombre requis n’est pas atteint.
- Le même personnage est sélectionné deux fois : la sélection est refusée.
- Une caractéristique de base est modifiée par erreur via l’interface : elle reste non éditable pendant la partie.
- Les points de vie dépassent leur maximum ou passent sous zéro : la modification est refusée avec une explication.
- Les points de vie atteignent zéro : le personnage est indiqué comme mort définitivement, y compris dans le résumé d’équipe ; il ne peut pas être rétabli à un état vivant dans cette aventure.
- Un personnage meurt alors qu’un candidat au remplacement reste disponible : le personnage mort ne peut pas être resélectionné et le lecteur doit choisir un personnage disponible comme remplaçant sans dépasser deux magiciens.
- Aucun personnage n’est disponible pour remplacer un personnage mort : l’aventure continue avec les survivants, même si l’équipe active compte moins de quatre membres.
- Un personnage rejoint l’équipe en remplacement : ses points de vie actuels sont initialisés à son maximum et il ne reçoit pas d’équipement de départ.
- Un personnage meurt avec de l’équipement ou des objets : le lecteur choisit quels personnages actifs les reçoivent ; les limites de capacité applicables doivent être respectées et tout bien restant impossible à attribuer est perdu.
- Une quantité d’équipement dépasse la limite propre à l’objet : l’attribution est refusée.
- Un équipement référencé n’est plus disponible dans le catalogue de la partie : l’incohérence doit être signalée, pas remplacée silencieusement.
- Un modificateur ou une valeur nécessaire au calcul est absent : ne pas afficher un résultat présenté comme exact ; signaler que la donnée est incomplète.
- La sauvegarde échoue : l’utilisateur ne doit pas recevoir une confirmation de réussite.

## 9. Exigences fonctionnelles traçables

- **REQ-001** : Le lecteur doit pouvoir démarrer une aventure sur *La Vallée des Os*.
- **REQ-002** : Le lecteur doit pouvoir sélectionner exactement quatre personnages distincts parmi les six personnages du livre au démarrage de l’aventure.
- **REQ-003** : La feuille doit présenter une seule section actuelle partagée par l’équipe dans la bêta.
- **REQ-004** : La feuille doit distinguer les caractéristiques de base des caractéristiques effectives calculées.
- **REQ-005** : Les caractéristiques de base doivent être consultables mais non modifiables pendant la partie.
- **REQ-006** : Les points de vie actuels doivent être modifiables et rester entre zéro et le maximum inclus ; à zéro, le personnage meurt définitivement pour cette aventure.
- **REQ-007** : La feuille doit afficher, pour chaque personnage, ses points de vie actuels et maximum.
- **REQ-008** : Le lecteur doit pouvoir consulter l’équipement de départ et gérer l’équipement courant de chaque personnage.
- **REQ-009** : Chaque équipement doit respecter sa propre limite de quantité portée.
- **REQ-010** : Les modificateurs d’équipement ne doivent pas altérer les caractéristiques de base.
- **REQ-011** : La vue d’équipe doit donner accès aux fiches des personnages actifs, jusqu’à quatre, et aux informations essentielles de progression et de santé.
- **REQ-012** : Les modifications valides doivent persister localement et rester consultables hors ligne après redémarrage.
- **REQ-013** : Toute saisie refusée doit donner un retour explicite et ne pas modifier silencieusement l’état enregistré.
- **REQ-014** : Le catalogue de la partie doit pouvoir être enrichi sans altérer la définition initiale du livre.
- **REQ-015** : Lorsqu’un personnage meurt définitivement, l’application doit le retirer de l’équipe active tout en conservant son état et permettre au lecteur de choisir quels personnages actifs reçoivent son équipement et ses objets ; si un remplaçant admissible est disponible, le lecteur doit en choisir un parmi les personnages encore vivants qui n’ont pas encore fait partie de l’équipe. Le remplaçant commence avec sa Santé au maximum et sans équipement de départ, et le choix respecte la limite de deux magiciens. Si aucun remplaçant n’est disponible, l’aventure continue avec les survivants, même si l’équipe compte moins de quatre membres.

## 10. Entités métier identifiées

- **Livre** : référence de *La Vallée des Os*, tome 1 de *Legendary Kingdoms*, et modèle de feuille associé.
- **Aventure** : partie jouée sur le livre ; contient l’équipe et sa section actuelle.
- **Équipe** : groupe de quatre personnages initialement retenus parmi les six ; porte la progression partagée de la bêta et peut compter moins de quatre membres actifs après des décès sans remplaçant.
- **Personnage du livre** : définition initiale d’un personnage, ses caractéristiques de base, son maximum de vie et ses équipements initiaux.
- **Personnage dans l’aventure** : état courant d’un personnage retenu, notamment ses points de vie et son inventaire.
- **Équipement du livre** : définition cataloguée, avec limite de quantité et éventuels modificateurs sourcés.
- **Équipement porté** : quantité d’un équipement attribuée à un personnage dans l’aventure.

## 11. Critères de réussite

- **SC-001** : Sur les six personnages référencés, une aventure accepte exactement quatre sélections initiales uniques et rend accessibles les quatre fiches.
- **SC-002** : Depuis la vue d’équipe, le lecteur peut identifier la section en cours et les points de vie actuels / maximum de chacun des membres actifs sans ouvrir leurs fiches.
- **SC-003** : Une caractéristique de base reste inchangée après toute modification d’équipement ; la valeur effective correspond à la formule validée par le porteur.
- **SC-004** : Toute valeur de points de vie hors de l’intervalle zéro–maximum inclus est refusée, sans altérer la valeur précédemment enregistrée ; une valeur de zéro indique un personnage mort définitivement pour cette aventure.
- **SC-005** : Une quantité d’équipement supérieure à la limite définie pour cet objet est refusée, sans altérer les autres personnages.
- **SC-006** : Après modification valide, fermeture et redémarrage hors ligne, la section, les points de vie et l’équipement affichés correspondent au dernier état enregistré.
- **SC-007** : Chaque donnée initiale intégrée pour les six personnages et le catalogue est accompagnée d’une provenance vérifiable dans le livrable R-02.
- **SC-008** : Lorsqu’un personnage meurt, il n’est plus compté dans l’équipe active et reste conservé comme mort ; le lecteur peut attribuer son équipement et ses objets aux personnages actifs de son choix. Si un remplaçant est disponible, le lecteur doit en choisir un et celui-ci arrive avec sa Santé au maximum et sans équipement de départ, sans dépasser deux magiciens. L’aventure continue avec les survivants si aucun remplacement n’est possible.

## 12. Décisions et questions ouvertes

| Sujet | État actuel | Décision ou action requise |
|---|---|---|
| Livre ciblé | Confirmé | *La Vallée des Os*, tome 1 de *Legendary Kingdoms*. |
| Nombre de personnages dans la bêta | Confirmé | Choisir exactement quatre personnages distincts parmi six. |
| Progression d’équipe | Confirmé dans le cadrage du jeu en équipe ; détail à harmoniser avec l’historique | La feuille bêta affiche une section partagée. Confirmer que tous les personnages sont toujours solidaires au tome 1. |
| Noms, rôles, compétences et Santé initiale des six personnages | Transcrits à partir des captures fournies par le porteur dans R-02 ; rôles confirmés ensuite par le porteur | Relire les valeurs transcrites ; ajouter des références paginées si disponibles. |
| Maximum de Santé | Confirmé égal à la Santé initiale | Règle confirmée par le porteur ; valeurs initiales consignées dans R-02. |
| Équipement de départ | Aucun personnage n’en porte au début | Règle confirmée par le porteur ; les équipements pourront être ajoutés au catalogue au fil de l’aventure. |
| Liste du catalogue et modificateurs | Catalogue initial non fourni | Ajouter les équipements au fil de leur découverte et renseigner leurs limites et modificateurs vérifiés. |
| Formule des caractéristiques effectives | Base + somme des modificateurs d’équipement portés ; modificateurs cumulatifs | Confirmé dans les décisions d’entretien de la section 15.2. |
| Santé de départ de l’aventure et d’un remplaçant | Initialisée au maximum de chaque personnage | Confirmation explicite du porteur ; s’applique aussi à un personnage rejoignant l’équipe en remplacement. |
| Santé courante et état à zéro | Entre zéro et le maximum inclus ; à zéro le personnage meurt définitivement dans ce livre | Règle confirmée par le porteur. La résurrection dépendra du livre et n’est pas permise dans *La Vallée des Os*. |
| Remplacement d’un personnage mort | Un personnage encore disponible parmi les six peut le remplacer ; il rejoint l’équipe avec sa Santé au maximum. Si aucun remplaçant n’est disponible, l’aventure continue avec les survivants | Règle confirmée par le porteur pour *La Vallée des Os*. |
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
- les autres éléments encore ouverts de la feuille auront été confirmés ; les bornes de Santé (zéro à maximum) et l’état mort à zéro sont déjà confirmés ;
- les champs optionnels restant à définir (notes, sorts, ressources) auront été tranchés pour la bêta ; la bourse commune en pièces d’argent est confirmée ;
- les règles de modificateurs nécessaires à l’affichage des caractéristiques effectives auront été précisées ;
- la matrice de champs et les critères de réussite auront été acceptés.

**État au moment de la rédaction :** le périmètre fonctionnel de base est spécifié ; les données factuelles du livre et plusieurs règles restent à confirmer. Ce document est donc un brouillon de travail, pas une validation du porteur.

## 15. Décisions issues de l’entretien fonctionnel

Les points ci-dessous ont été établis par les réponses du porteur du projet lors de l’entretien du 18 octobre 2026. Ils précisent et, si nécessaire, remplacent les propositions antérieures de ce document.

### 15.1 Vue de l’équipe

La feuille comprend une section d’équipe présentant les personnages actifs : quatre au départ, éventuellement moins si aucun remplaçant n’est disponible après un décès. L’équipe ne peut pas contenir plus de deux magiciens. Le rôle est une propriété du personnage défini par le livre : il n’est pas choisi séparément lors de la création de l’aventure.

À la création de l’aventure, le lecteur choisit lui-même quatre personnages distincts parmi les six du livre ; les deux personnages non sélectionnés restent disponibles. Seuls Lord Ti'Quan et Amelia Pass-Dayne sont magiciens (jeteurs de sorts) ; les quatre autres personnages n’ont pas de rôle dans ce livre. La contrainte de deux magiciens maximum s’applique à la sélection. Un personnage vivant ne peut pas être remplacé volontairement. Si un membre meurt définitivement, il est retiré de l’équipe active et le lecteur choisit quels personnages actifs reçoivent son équipement et ses objets. S’il existe un personnage encore vivant qui n’a pas encore fait partie de l’équipe, le lecteur doit en choisir un comme remplaçant, y compris parmi les personnages non sélectionnés initialement. Le remplaçant rejoint l’équipe avec sa Santé au maximum et sans équipement de départ. Si aucun remplaçant n’est disponible, l’aventure continue avec les personnages survivants, même si l’équipe compte moins de quatre membres.

### 15.2 Fiche d’un personnage

Chaque personnage comporte les éléments suivants :

| Donnée | Catégorie | Éditabilité / règle convenue |
|---|---|---|
| Nom | Donnée initiale du personnage | Fourni par la définition du personnage dans le livre. |
| Rôle, notamment magicien | Donnée initiale du personnage | Fourni par la définition du personnage dans le livre. Seuls Lord Ti'Quan et Amelia Pass-Dayne sont magiciens ; les quatre autres n’ont pas de rôle dans ce livre. Le rôle n’est pas sélectionné par le joueur. |
| Combat | Valeur de base et valeur modifiée | La base vient du personnage défini dans le livre ; la valeur modifiée tient compte de l’équipement. |
| Discrétion | Valeur de base et valeur modifiée | La base vient du personnage défini dans le livre ; la valeur modifiée tient compte de l’équipement. |
| Savoir | Valeur de base et valeur modifiée | La base vient du personnage défini dans le livre ; la valeur modifiée tient compte de l’équipement. |
| Survie | Valeur de base et valeur modifiée | La base vient du personnage défini dans le livre ; la valeur modifiée tient compte de l’équipement. |
| Charisme | Valeur de base et valeur modifiée | La base vient du personnage défini dans le livre ; la valeur modifiée tient compte de l’équipement. |
| Santé | Valeur initiale, maximum et valeur courante | La valeur initiale vient du personnage défini dans le livre et évolue pendant l’aventure. Le maximum est égal à la Santé initiale, conformément à la confirmation du porteur du projet. |
| Armure | Valeur calculée | Somme des modificateurs d’Armure des équipements portés. |
| Équipement | Cinq emplacements | Les cinq emplacements sont vides au début de l’aventure. Le joueur peut ajouter des objets au fil du jeu ; un emplacement peut rester vide et un objet peut être présent plusieurs fois si sa limite de possession le permet. |
| Notes du personnage | Texte | Sous-section propre au personnage. Le format et la conservation d’un historique des notes du personnage ne sont pas précisés dans l’entretien. |
| Sorts | Jusqu’à six entrées pour chaque magicien | Aucun sort n’est préchargé dans la bibliothèque initiale ; le lecteur pourra les ajouter au fil de l’eau. Chaque entrée comporte un nom, une description conforme à la source du livre et une information de recharge si applicable. Les sorts actuellement prévus n’ont pas de recharge à suivre. |

Pour les compétences Combat, Discrétion, Savoir, Survie et Charisme, la valeur modifiée est la valeur de base augmentée ou diminuée par les modificateurs des équipements attribués. Les modificateurs applicables se cumulent par addition. La valeur de base demeure distincte et ne change pas.

Les modificateurs d’équipement sur les compétences et l’Armure sont des nombres entiers, positifs ou négatifs. La répartition exacte des modificateurs de chaque objet proviendra des données de catalogue vérifiées en R-02.

### 15.3 Équipement des personnages

- Chaque personnage dispose d’une seule liste d’inventaire de cinq emplacements, qui regroupe les objets et les équipements ; les emplacements vides sont autorisés.
- La liste est vide au début de l’aventure ; aucun personnage ne reçoit d’objet ni d’équipement de départ.
- Les objets et équipements peuvent être ajoutés au catalogue du livre et attribués aux personnages au fil de l’aventure.
- Chaque exemplaire porté d’un objet ou d’un équipement occupe un emplacement.
- Un même objet peut occuper plusieurs emplacements, jusqu’à cinq exemplaires si le personnage en possède suffisamment et si la limite individuelle de possession le permet.
- La limite de possession est définie pour chaque objet du catalogue et s’applique au personnage ; elle prévaut sur le nombre d’emplacements disponibles.
- La fiche d’un objet comporte au minimum son nom, une description facultative et vide par défaut, sa limite de possession et ses modificateurs entiers (éventuellement négatifs ou positifs) sur les compétences et/ou l’Armure.
- Les équipements portés sont la source des valeurs modifiées des cinq compétences ainsi que de la valeur d’Armure.

### 15.4 Sorts

- Seuls les personnages dont le rôle est magicien disposent de cette sous-section.
- Un magicien peut avoir au maximum six sorts.
- La liste initiale des sorts est vide ; les sorts sont ajoutés au fil de l’eau par le lecteur.
- Chaque sort contient un nom et une description ; le lecteur les ajoute au fil de l’eau.
- Le lecteur réécrit la description du sort à partir de celle du livre, avec ses propres mots ; le texte du livre n’est pas recopié mot pour mot. La référence de source est conservée lorsqu’elle est connue.
- Les sorts actuellement utilisés par le porteur ne disposent pas de recharge. Aucun compteur, délai ou état « disponible/en recharge » n’est donc requis pour la première feuille, sauf besoin ultérieur confirmé.

### 15.5 Butin du groupe

Une bourse commune à l’équipe suit le total des pièces d’argent. Elle commence à zéro au début de l’aventure. Il n’existe pas de bourse individuelle par personnage. Le total est modifiable en ajoutant ou retirant une quantité et doit rester positif ou nul. La mort d’un personnage n’entraîne aucun transfert de pièces : l’argent reste dans la bourse commune. Une opération qui conduirait à un montant négatif est refusée avec un retour explicite.

### 15.6 Codes

Une section présente tous les codes de A1 à A100. Chaque code est représenté par un état obtenu / non obtenu. Aucun autre champ par code n’a été demandé.

### 15.7 Coffre-fort

Le coffre-fort est partagé par l’équipe, vide au début de l’aventure et sans capacité maximale. Tous les personnages actifs peuvent accéder à son contenu ; chaque objet ou équipement y figure sur une ligne distincte, y compris les exemplaires identiques. Le lecteur choisit les exemplaires qu’il en retire et leur attribue un porteur. Le lecteur peut également y déposer tout ou partie des objets et équipements de l’inventaire d’un personnage ; ces biens libèrent alors les emplacements correspondants et deviennent accessibles à toute l’équipe. Le numéro du paragraphe où chaque exemplaire a été obtenu est conservé, s’il est connu, lors des dépôts, retraits et transferts ; le texte du paragraphe n’est pas recopié. L’argent éventuellement rangé dans le coffre-fort est distinct de la bourse commune et peut être suivi comme un montant. Chaque personnage n’a qu’une liste d’inventaire, où objets et équipements partagent les cinq emplacements ; chaque exemplaire porté figure sur une ligne distincte et occupe un emplacement. Les objets et équipements stockés dans le coffre n’occupent pas ces emplacements et ne sont pas soumis aux limites de possession tant qu’ils y sont rangés ; après retrait, les limites du personnage s’appliquent. Le contenu du coffre reste inchangé lorsqu’un personnage meurt ; seuls les biens présents dans l’inventaire individuel du mort sont concernés par la répartition prévue à la section 15.1. Les unités et les règles particulières de quantité pour les différentes catégories restent à définir si elles diffèrent.

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
2. **Fiche de chaque personnage** : nom, rôle, valeurs de base et modifiées des cinq compétences, Santé, Armure calculée, une liste unique de cinq emplacements pour les objets et équipements (chaque exemplaire occupe un emplacement), notes et, pour un magicien, jusqu’à six sorts.
3. **Butin du groupe** : total commun de pièces d’argent.
4. **Codes** : A1 à A100, obtenus ou non obtenus.
5. **Coffre-fort** : objets, argent et équipement avec quantités, partagé par l’équipe.
6. **Votre flotte navale** : jusqu’à cinq bateaux, leurs valeurs, mouillage et cargaison.
7. **Vos armées** : jusqu’à 18 armées avec unité du livre, force, moral et garnison.
8. **Le champ de bataille** : six emplacements pour les forces du joueur et six pour l’ennemi, chacun pouvant référencer au plus une armée.
9. **Notes de mission** : texte libre commun avec historique horodaté.
10. **Section actuelle** : position de progression de l’aventure, conformément au périmètre initial du MVP ; chaque changement est conservé dans un historique horodaté.

Les sections constituent les rubriques fonctionnelles de la feuille. Leur ordre visuel final et leur mode de navigation sur téléphone restent des choix UX à confirmer.

## 17. Matrice synthétique des données et règles d’édition

| Portée | Donnée | Nature | Règle confirmée |
|---|---|---|---|
| Personnage du livre | Nom, rôle, bases des cinq compétences, Santé initiale | Initiale / référence du livre | Transcrites dans R-02 depuis les captures fournies par le porteur ; relecture souhaitée. |
| Personnage d’aventure | Combat, Discrétion, Savoir, Survie, Charisme modifiés | Calculée et affichée | Base correspondante + somme des modificateurs d’équipement. |
| Personnage d’aventure | Santé | Courante | Évolue ; maximum égal à la Santé initiale, règle confirmée par le porteur. |
| Personnage d’aventure | Armure | Calculée et affichée | Somme des modificateurs d’Armure des équipements. |
| Personnage d’aventure | Liste d’inventaire unique, cinq emplacements | Courante / éditable | Objets et équipements sont réunis ; chaque exemplaire est une ligne individuelle qui occupe un emplacement, respecte sa limite de possession et conserve, si connu, le numéro du paragraphe d’obtention, sans copie de son texte. |
| Personnage d’aventure | Notes personnelles | Courante / éditable | Sous-section personnelle ; une nouvelle version horodatée est créée lorsque le lecteur quitte la fenêtre ou la popup des notes. |
| Personnage magicien | Sorts, jusqu’à six | Liste courante / éditable | Chaque sort contient un nom et une description ; aucune gestion de recharge pour les sorts actuels. |
| Équipe | Section actuelle | Courante / éditable | Progression commune de l’aventure ; chaque changement est conservé dans un historique horodaté. |
| Équipe | Bourse en pièces d’argent | Initiale puis courante / éditable | Bourse unique et partagée, initialisée à zéro ; total supérieur ou égal à zéro et indépendant du décès d’un personnage. |
| Équipe | Codes A1–A100 | Courante / éditable | Chaque entrée est obtenue ou non obtenue. |
| Équipe | Coffre-fort partagé | Initiale puis courante / éditable | Vide et sans capacité maximale au début de l’aventure. Tous les personnages actifs peuvent y accéder ; chaque objet ou équipement figure sur une ligne distincte, y compris les exemplaires identiques. Le lecteur peut déposer des exemplaires portés ou en retirer certains et choisir le porteur. Le numéro du paragraphe d’obtention, s’il est connu, suit chaque exemplaire lors des transferts ; le texte du paragraphe n’est pas copié. Les dépôts libèrent des emplacements ; ni les emplacements ni les limites de possession des personnages ne restreignent le stockage. Au retrait, objets et équipements vont dans la liste d’inventaire unique du personnage et en respectent les limites. L’argent du coffre est distinct de la bourse commune et son contenu reste indépendant du décès d’un personnage. |
| Équipe | Flotte navale | Courante / éditable | Maximum cinq bateaux ; attributs définis à la section 15.8. |
| Équipe | Armées | Courante / éditable | Maximum 18 ; unité de la liste du livre, force et moral entiers, suivi des changements de garnison avec ancien et nouvel emplacement. |
| Équipe | Champ de bataille | Courante / éditable | Six emplacements par camp ; au plus une référence d’armée par emplacement. |
| Aventure | Notes de mission | Courante / éditable | Texte libre partagé ; une nouvelle version horodatée est créée lorsque le lecteur quitte la fenêtre ou la popup si le texte a été modifié ; sans modification, aucune nouvelle version n’est créée. |

## 18. Points restant à décider

Les réponses à l’entretien ont défini le contenu principal de la feuille. La Santé (zéro à maximum, mort à zéro) est une règle confirmée ; les autres points ci-dessous restent à décider et ne doivent pas être transformés en règles implicites :

1. **Données des personnages et du livre :** les six rôles, noms, cinq compétences et Santé initiale sont consignés dans R-02 ; les sorts et les unités autorisées restent à préciser depuis les informations disponibles. Les règles de mort définitive et de remplacement consignées ici sont propres à *La Vallée des Os*.
2. **Unités et conventions :** préciser les unités de taille de soute, cargaison, force, moral, combat et vie des bateaux, ainsi que les bornes permises.
3. **Coffre-fort :** préciser si les quantités sont entières pour toutes les entrées et si l’argent y suit la même règle non négative que le butin du groupe.
4. **Armées ennemies :** préciser si elles appartiennent à la liste des 18 armées ou à un registre distinct.
5. **Champ de bataille :** préciser si une armée peut occuper plusieurs emplacements simultanément et si les déplacements doivent être historisés.
6. **Garnison :** le nouvel et l’ancien emplacement sont conservés ; préciser si chaque transition est conservée dans un historique complet et si elle est horodatée.
7. **Ordre et navigation :** valider l’ordre des sections et les informations à afficher sans ouvrir une fiche sur téléphone.
## 19. Critères de validation mis à jour

R-01 pourra être déclaré validé lorsque le porteur du projet aura confirmé :

- les champs par personnage et toutes les sections d’équipe décrites dans les sections 15 à 17 ;
- les champs et règles de la feuille encore ouverts ; les bornes de Santé et la mort définitive à zéro pour *La Vallée des Os* sont déjà confirmées ;
- le fonctionnement des armées ennemies, du champ de bataille et du suivi des garnisons ;
- les unités et règles quantitatives requises pour les bateaux, armées, cargaisons et coffre-fort ;
- le comportement des historiques des notes personnelles, des notes de mission et des changements de section, désormais défini ;
- la source et le mode de représentation des sorts ;
- l’organisation UX générale de la feuille.

La validation de R-01 ne valide pas à elle seule les valeurs initiales du livre : celles-ci doivent être sourcées, structurées et vérifiées séparément au titre de R-02.
