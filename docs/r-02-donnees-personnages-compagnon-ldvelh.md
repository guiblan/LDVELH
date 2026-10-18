# R-02 — Données de la bibliothèque et des personnages

**Projet :** Compagnon LDVELH  
**Livre de départ :** *La Vallée des Os*, tome 1 de *Legendary Kingdoms*  
**Statut :** Terminé — six fiches relues et confirmées ; catalogue initial vide et extensible
**Livrables associés :** [`docs/data/ldvelh-personnages-base.json`](./data/ldvelh-personnages-base.json)  
**Référence métier :** [R-01 — feuille d’aventure de *La Vallée des Os*](./r-01-feuille-d-aventure-vallee-des-os.md)

## 1. Objectif et périmètre

Ce document prépare les données initiales à intégrer dans la bibliothèque de l’application : la série, son premier livre, les six personnages de référence et le catalogue d’équipement du livre.

Les documents du dépôt confirment le titre du livre, sa série et le nombre de personnages à proposer. Les trois captures d’écran partagées par le porteur apportent les noms, les rôles de jeteur de sorts explicitement légendés, les cinq compétences et la Santé initiale des six personnages. Le porteur a relu et confirmé ces transcriptions ; il a corrigé la Survie d’Akihiro à 5. Il a également confirmé que la Santé initiale est le maximum de Santé et qu’aucun personnage ne commence avec de l’équipement. Les données absentes restent signalées comme telles.

Le jeu de données initial est vérifié et structuré. Le catalogue d’équipement reste vide tant qu’aucune entrée n’est fournie ; son parcours d’ajout à la demande et ses champs obligatoires sont définis ci-dessous.

## 2. Sources et niveau de confiance

| Information | Valeur retenue | Source | Statut |
|---|---|---|---|
| Nom de la série | *Legendary Kingdoms* | Feuille de route du projet et R-01 | Confirmé dans les documents du dépôt |
| Titre du premier livre | *La Vallée des Os* | Feuille de route du projet et R-01 | Confirmé dans les documents du dépôt |
| Numéro du tome | 1 | Feuille de route du projet et R-01 | Confirmé dans les documents du dépôt |
| Nombre de personnages de référence | 6 | Feuille de route et R-01 | Confirmé dans les documents du dépôt |
| Taille de l’équipe en bêta | 4 personnages distincts | Feuille de route et R-01 | Confirmé dans les documents du dépôt |
| Choix de l’équipe initiale | Le lecteur choisit lui-même quatre personnages distincts parmi les six | Confirmation explicite du porteur dans la conversation | Confirmé |
| Personnages non sélectionnés au départ | Restent disponibles pour remplacer un personnage mort, tant qu’ils sont vivants et n’ont pas encore rejoint l’équipe | Confirmation explicite du porteur dans la conversation | Confirmé |
| Argent de l’aventure | Une seule bourse commune à l’équipe, initialisée à zéro ; aucun personnage ne possède de bourse individuelle et la bourse reste inchangée lorsqu’un personnage meurt | Confirmation explicite du porteur dans la conversation | Confirmé |
| Coffre-fort | Commun à l’équipe ; son contenu reste intact au décès d’un personnage | Confirmation explicite du porteur dans la conversation | Confirmé |
| Contenu initial du coffre-fort | Vide au début de l’aventure | Confirmation explicite du porteur dans la conversation | Confirmé |
| Argent dans le coffre-fort | Distinct de la bourse commune de l’équipe | Confirmation explicite du porteur dans la conversation | Confirmé |
| Limites de possession et coffre-fort | Les limites de possession s’appliquent aux personnages qui portent les objets, pas aux exemplaires rangés dans le coffre-fort | Confirmation explicite du porteur dans la conversation | Confirmé |
| Emplacements et coffre-fort | Les équipements rangés dans le coffre ne consomment pas les emplacements des personnages | Confirmation explicite du porteur dans la conversation | Confirmé |
| Capacité du coffre-fort | Aucune capacité maximale | Confirmation explicite du porteur dans la conversation | Confirmé |
| Accès au coffre-fort | Tous les personnages actifs peuvent y déposer les objets et équipements portés ou en retirer une quantité partielle ; le lecteur choisit les transferts et le porteur lors d’un retrait | Confirmation explicite du porteur dans la conversation | Confirmé |
| Nombre maximal de magiciens dans l’équipe | 2 | Décision consignée dans la section 15.1 de R-01 | Confirmé dans R-01 |
| Noms, valeurs des cinq compétences et Santé initiale | Capturés dans les trois images partagées dans la conversation ; Survie d’Akihiro corrigée à 5 | Captures des six fiches et relecture/correction par le porteur | Vérifiés par le porteur |
| Rôle de magicien / jeteur de sorts | Lord Ti'Quan et Amelia Pass-Dayne | Captures et confirmation explicite du porteur | Confirmé |
| Rôle des quatre autres personnages | Aucun rôle dans ce livre | Confirmation explicite du porteur | Confirmé |
| Maximum de Santé | Égal à la Santé initiale | Confirmation explicite du porteur dans la conversation | Confirmé |
| Santé courante | Toujours entre zéro et le maximum inclus ; à zéro le personnage est mort | Confirmation explicite du porteur dans la conversation | Confirmé |
| Santé au début d’une aventure | Initialisée au maximum de chaque personnage | Confirmation explicite du porteur dans la conversation | Confirmé |
| Mort d’un personnage | Définitive dans *La Vallée des Os* ; la règle peut varier selon les livres | Confirmation explicite du porteur dans la conversation | Confirmé pour ce livre uniquement |
| Remplacement après une mort | S’il reste un personnage encore vivant qui n’a pas déjà rejoint l’équipe, le lecteur doit le choisir comme remplaçant | Confirmation explicite du porteur dans la conversation | Confirmé pour ce livre uniquement |
| Remplacement volontaire d’un personnage vivant | Non autorisé ; un remplacement n’est possible qu’après une mort définitive | Confirmation explicite du porteur dans la conversation | Confirmé pour ce livre uniquement |
| Santé du remplaçant | Un personnage rejoignant l’équipe en remplacement commence avec sa Santé au maximum | Confirmation explicite du porteur dans la conversation | Confirmé pour ce livre uniquement |
| Aucun remplaçant disponible | L’aventure continue avec les personnages survivants, même si l’équipe compte moins de quatre membres | Confirmation explicite du porteur dans la conversation | Confirmé pour ce livre uniquement |
| Équipement au démarrage | Aucun pour les six personnages | Confirmation explicite du porteur dans la conversation | Confirmé |
| Répartition des biens d’un personnage mort | Le lecteur choisit quels personnages actifs reçoivent son équipement et ses objets ; ceux qui ne peuvent être attribués sont perdus | Confirmation explicite du porteur dans la conversation | Confirmé |
| Modificateurs et catalogue d’équipement | Catalogue initial vide ; le lecteur choisit une entrée existante ou ajoute une nouvelle entrée puis la sélectionne | Confirmation du porteur et politique de catalogue décrite dans ce document | Structure confirmée ; entrées à ajouter au fil de l’utilisation |
| Sorts initiaux | Aucun sort préchargé ; ajout au fil de l’eau pour les magiciens | Décision explicite du porteur dans la conversation | Saisie différée |
| Auteur | Olivier Hulme | Confirmation explicite du porteur dans la conversation | Confirmé |
| ISBN | Absent du livre consulté par le porteur ; valeur conservée comme inconnue | Confirmation explicite du porteur dans la conversation | Non fourni |

Les identifiants `character-01` à `character-06` sont des identifiants techniques provisoires et stables. Ils ne préjugent ni des noms ni de l’ordre officiel des personnages dans le livre.

## 3. Inventaire initial des personnages

Les six entrées doivent exister dès le premier chargement afin que le futur parcours puisse proposer quatre personnages distincts. Les noms, compétences et Santé initiale ci-dessous sont transcrits des captures puis relus et confirmés par le porteur. Le maximum de Santé reprend la Santé initiale, conformément à la règle confirmée par le porteur. Aucun personnage ne reçoit d’équipement initial.

| Identifiant | Nom | Rôle visible | Combat | Discrétion | Savoir | Survie | Charisme | Santé initiale | Maximum de Santé | Équipement de départ | Statut |
|---|---|---|---:|---:|---:|---:|---:|---:|---|---|---|
| `character-01` | Sar Jessica Dayne | Aucun rôle dans ce livre | 5 | 1 | 3 | 2 | 4 | 8 | 8 | Aucun | Vérifié par le porteur |
| `character-02` | Lord Ti'Quan | Magicien (jeteur de sorts) | 1 | 2 | 5 | 1 | 2 | 6 | 6 | Aucun | Vérifié par le porteur |
| `character-03` | Tasha | Aucun rôle dans ce livre | 3 | 5 | 1 | 3 | 3 | 8 | 8 | Aucun | Vérifié par le porteur |
| `character-04` | Amelia Pass-Dayne | Magicienne (jeteuse de sorts) | 3 | 2 | 2 | 3 | 1 | 6 | 6 | Aucun | Vérifié par le porteur |
| `character-05` | Akihiro | Aucun rôle dans ce livre | 4 | 3 | 2 | 5 | 1 | 8 | 8 | Aucun | Vérifié par le porteur ; Survie corrigée à 5 |
| `character-06` | Joli cœur | Aucun rôle dans ce livre | 2 | 4 | 3 | 1 | 5 | 8 | 8 | Aucun | Vérifié par le porteur |

### 3.1 Données attendues par personnage

Les règles et données consignées pour chacune des six fiches sont :

- nom exact et rôle du personnage ; les rôles sont désormais confirmés : seuls Lord Ti'Quan et Amelia Pass-Dayne sont magiciens, les quatre autres n’ont pas de rôle dans ce livre ;
- valeur initiale de Combat, Discrétion, Savoir, Survie et Charisme ;
- Santé initiale ;
- maximum de Santé égal à la Santé initiale, règle confirmée par le porteur ;
- Santé courante initialisée au maximum au démarrage de chaque aventure ;
- Santé courante comprise entre zéro et le maximum inclus ; à zéro, le personnage est mort ;
- la mort à zéro est définitive pour l’aventure sur *La Vallée des Os* ; cette règle ne doit pas être généralisée aux autres livres ;
- après une mort, le personnage mort est retiré de l’équipe active ; s’il existe un personnage encore vivant qui n’a pas déjà rejoint l’équipe, le lecteur doit choisir lui-même ce remplaçant ;
- À la mort d’un personnage, le lecteur choisit quels personnages actifs reçoivent son équipement et ses objets ;
- les biens qui ne peuvent être attribués lors de cette répartition sont perdus ;
- les personnages non choisis dans l’équipe initiale restent disponibles comme remplaçants ; un personnage est éligible s’il est vivant et n’a pas encore fait partie de l’équipe ;
- un personnage vivant ne peut pas être remplacé volontairement ; un remplacement n’est permis qu’après une mort définitive ;
- un personnage qui rejoint l’équipe comme remplaçant commence avec sa Santé au maximum ;
- si aucun personnage ne peut remplacer le mort, l’aventure continue avec les survivants, même si l’équipe compte moins de quatre membres ;
- aucun équipement de départ ; les objets pourront être ajoutés au catalogue au fil de l’utilisation ;
- modificateurs exacts de chaque équipement, y compris les éventuels modificateurs d’Armure ;
- pour chaque magicien, la liste initiale des sorts est vide et peut être enrichie au fil du jeu ; chaque sort comporte un nom et une description, avec une limite de six sorts par magicien ;
- numéro de page du livre pour les données de personnage, lorsqu’il est disponible.

## 4. Catalogue d’équipement extensible

Le catalogue est associé au livre. Il distingue :

1. les définitions provenant du livre et sourcées ;
2. les équipements ajoutés par le lecteur au fil de l’aventure.

Une définition d’équipement est enregistrée une fois dans le catalogue du livre puis peut être attribuée aux personnages concernés. Chaque exemplaire porté est une entrée distincte de l’inventaire, même si plusieurs exemplaires utilisent la même définition ; il ne crée pas une nouvelle définition de l’objet. Pour chaque exemplaire acquis, l’état de l’aventure conserve uniquement le numéro du paragraphe où il a été obtenu, s’il est connu, sans en recopier le texte. Cette provenance d’acquisition reste attachée à l’exemplaire lors de son dépôt dans le coffre, de son retrait ou de son transfert à un autre personnage. Le numéro de page associé à la définition du catalogue est distinct du numéro de paragraphe d’obtention. L’ajout d’une entrée au catalogue doit rester disponible lors des utilisations ultérieures et ne doit pas modifier silencieusement les données de référence initiales du livre.

Le parcours prévu permet de choisir un équipement déjà présent dans le catalogue ou, s’il manque, de créer sa définition puis de la sélectionner immédiatement. Les nouvelles définitions sont conservées dans le catalogue du livre pour les utilisations ultérieures.

À la création d’une entrée, le nom, les modificateurs (y compris des valeurs nulles explicites pour indiquer l’absence d’effet) et la limite de possession doivent être renseignés. La valeur `-1` signifie explicitement « sans limite » pour la possession par un personnage. La description et la page du livre peuvent rester inconnues ; une entrée qui ne provient pas du livre est identifiée comme ajoutée par le lecteur. Une donnée obligatoire ne peut pas être laissée inconnue.

Pour chaque équipement, le modèle prévoit :

- un identifiant stable et un nom obligatoire ;
- une description facultative ;
- une provenance (`book` ou `user_added`), une référence de source et le numéro de page du livre lorsque ces informations sont disponibles ;
- des modificateurs entiers, positifs, nuls ou négatifs, pour les compétences et/ou l’Armure ;
- une limite de possession propre à l’objet, qui s’applique aux exemplaires portés par un personnage, et non aux quantités stockées dans le coffre-fort ;
- un statut de vérification ;
- des références vers l’objet depuis l’inventaire courant des personnages ; l’inventaire de départ est vide.

Chaque personnage dispose d’une seule liste d’inventaire réunissant objets et équipements, avec cinq emplacements au total. Chaque exemplaire porté apparaît sur une ligne distincte, occupe un emplacement et reste soumis à la limite de possession propre à l’objet. Une limite inconnue ne doit pas être interprétée comme une limite illimitée ni comme un exemplaire unique.

Le coffre-fort partagé n’a pas de capacité maximale : les limites de possession des objets ne réduisent pas les exemplaires qui y sont stockés. Chaque objet ou équipement, y compris les exemplaires identiques, y apparaît sur une ligne distincte. Le lecteur peut y déposer tout ou partie des objets et équipements présents dans l’inventaire d’un personnage ; ces biens libèrent les emplacements correspondants. Tous les personnages actifs peuvent consulter le coffre. Le lecteur peut en retirer certains exemplaires et les attribuer à un personnage ; chaque exemplaire rejoint une ligne distincte de sa liste d’inventaire, utilise un emplacement et respecte les limites de possession et d’emplacements de ce personnage.

Le catalogue est initialement vide dans le JSON, car aucune entrée vérifiable n’est fournie dans les documents consultés. Son schéma permet d’y ajouter des éléments sans modifier le format général de la bibliothèque.

Les listes de sorts des personnages magiciens sont également initialement vides. Cette liste vide signifie « aucun sort saisi dans la bibliothèque à ce stade », et non « le personnage ne possède aucun sort ». Le lecteur peut ajouter jusqu’à six sorts par magicien au fil de l’eau ; chaque entrée comporte un nom et une description. Pour cette description, le lecteur réécrit avec ses propres mots celle du livre ; le texte du livre n’est pas recopié mot pour mot. La référence de source est conservée lorsqu’elle est connue. Aucun suivi de recharge n’est requis pour les sorts actuellement utilisés.

## 5. Organisation des données : référence et partie

Le fichier JSON décrit la bibliothèque de référence : série, livre, personnages de base et catalogue d’équipement. Il ne contient pas l’état courant d’une partie.

| Donnée | Portée | Source de vérité |
|---|---|---|
| Nom, rôle, caractéristiques de base, Santé initiale et équipement initial | Définition du personnage du livre | Données de bibliothèque transcrites et règles initiales confirmées |
| Santé actuelle, équipement attribué, notes et progression | Personnage ou équipe dans une aventure | État de l’aventure, distinct du catalogue |
| Valeurs modifiées des compétences et Armure | Calcul à partir des valeurs de base et de l’équipement porté | Calcul métier prévu par R-01 |
| Définitions d’équipement fournies avec le livre | Catalogue initial du livre | Données sourcées du livre |
| Définitions ajoutées en cours d’utilisation | Catalogue enrichi du livre | Lecteur, avec provenance « ajout utilisateur » |

R-01 fixe que les valeurs modifiées des cinq compétences correspondent à la valeur de base plus la somme des modificateurs des équipements portés ; l’Armure correspond à la somme de ses modificateurs d’équipement. Les nombres eux-mêmes et les définitions d’équipement nécessaires à ces calculs restent à fournir et à vérifier.

## 6. Enrichissements différés

1. **Pages des six fiches :** les renseigner lorsqu’elles sont disponibles ; elles ne bloquent pas la vérification des valeurs confirmées par le porteur.
2. **Équipements ajoutés en cours de jeu :** saisir chaque objet dans le catalogue à mesure qu’il apparaît, en renseignant son nom, ses modificateurs et sa limite de possession (`-1` si elle est illimitée).
3. **Sorts :** les ajouter au fil de l’eau pour Lord Ti'Quan et Amelia Pass-Dayne ; aucune liste de départ n’est requise.
4. **Sources précises :** numéro de page pour les données de personnage et les définitions d’équipement lorsque disponible ; numéro du paragraphe d’obtention pour chaque exemplaire acquis pendant l’aventure, sans recopier son texte.
5. **Métadonnées bibliographiques :** l’auteur Olivier Hulme est confirmé par le porteur ; l’ISBN est absent du livre et reste inconnu.

Ces enrichissements ne bloquent pas la clôture de R-02 : les informations encore indisponibles ne sont pas inventées et les entrées du catalogue seront ajoutées lorsqu’elles seront connues.

## 7. Critères de validation R-02

- Le livre est rattaché à *Legendary Kingdoms*, tome 1.
- Six entrées de personnage stables sont présentes ; une aventure peut ensuite en sélectionner quatre distinctes, avec au plus deux magiciens.
- Les noms, les cinq compétences et la Santé initiale des six personnages ont été relus et confirmés par le porteur ; les valeurs confirmées sont enregistrées avec leur provenance.
- Le catalogue initial peut être vide ; une entrée créée à la demande est conservée et sélectionnable immédiatement. Son nom, ses modificateurs et sa limite de possession sont obligatoires ; `-1` représente une possession sans limite.
- Chaque valeur factuelle de personnage et chaque objet du catalogue porte un statut et une provenance permettant sa vérification ; les pages sont consignées lorsqu’elles sont disponibles.
- Chaque sort ajouté à un magicien comporte un nom et une description réécrite par le lecteur à partir de celle du livre, sans reprise mot pour mot ; la liste ne dépasse pas six sorts par magicien et aucun suivi de recharge n’est requis.
- Chaque exemplaire d’objet ou d’équipement acquis en cours d’aventure conserve uniquement son numéro de paragraphe d’obtention lorsqu’il est connu, sans copier le texte du paragraphe, y compris après dépôt, retrait ou transfert.
- Une donnée inconnue est représentée comme inconnue ; une liste vide n’est utilisée que lorsque l’absence est confirmée, comme pour l’équipement de départ.
- Le maximum de Santé est égal à la Santé initiale pour chacun des six personnages, conformément à la confirmation du porteur.
- La Santé courante est bornée entre zéro et le maximum inclus ; une Santé égale à zéro signifie que le personnage est mort.
- Au démarrage d’une aventure, chaque personnage commence avec sa Santé au maximum.
- Dans *La Vallée des Os*, la mort à zéro est définitive pour l’aventure ; cette règle reste propre au livre.
- Lorsqu’un personnage meurt, le lecteur doit choisir lui-même un remplaçant s’il reste un personnage encore vivant qui n’a pas déjà rejoint l’équipe ; le remplacement respecte la limite de deux magiciens.
- Un personnage vivant ne peut pas être remplacé volontairement ; le remplacement est permis uniquement après une mort définitive.
- Un personnage qui remplace un membre mort rejoint l’équipe avec sa Santé au maximum.
- À la mort d’un personnage, le lecteur choisit quels personnages actifs reçoivent son équipement et ses objets ; tout bien impossible à attribuer est perdu. Le remplaçant ne reçoit pas d’équipement de départ.
- L’argent est suivi dans une bourse commune à l’équipe, pas dans les fiches individuelles ; le décès d’un personnage ne modifie pas le solde commun.
- La bourse commune commence à zéro au début de l’aventure.
- Le coffre-fort partagé est vide au début de l’aventure et conserve ensuite son contenu lorsqu’un personnage meurt.
- L’argent éventuellement rangé dans le coffre-fort est distinct du solde de la bourse commune.
- Les équipements rangés dans le coffre-fort ne consomment pas les emplacements d’équipement des personnages.
- Les limites de possession ne s’appliquent pas aux objets rangés dans le coffre-fort ; elles s’appliquent quand un personnage les porte.
- Le coffre-fort partagé n’a pas de capacité maximale.
- Tous les personnages actifs peuvent accéder aux objets du coffre-fort ; le lecteur choisit la quantité qu’il retire, éventuellement une partie seulement, et leur attribue un porteur. La quantité non retirée demeure dans le coffre ; les limites d’inventaire s’appliquent à la quantité attribuée au porteur.
- Le lecteur peut déposer dans le coffre tout ou partie des objets et équipements présents dans l’inventaire d’un personnage ; les biens déposés libèrent les emplacements correspondants.
- Si aucun personnage disponible ne peut remplacer un personnage mort, l’aventure continue avec les survivants, même si l’équipe compte moins de quatre membres.
- L’équipement de départ est vide pour les six personnages ; le catalogue peut être enrichi ultérieurement.
- Le catalogue accepte des équipements ajoutés par le lecteur sans confusion avec les données initiales vérifiées du livre.
- Les données de bibliothèque restent distinctes des valeurs courantes et calculées d’une aventure.

**Maturité actuelle :** les six fiches ont été relues et confirmées par le porteur ; la Survie d’Akihiro est corrigée à 5. Le maximum égal à la Santé initiale, le démarrage de chaque aventure au maximum de Santé, les bornes de Santé courante, la mort définitive à zéro et le remplacement possible depuis les personnages encore disponibles, l’absence d’équipement au départ et les rôles des six personnages sont confirmés par le porteur. Le catalogue initial vide et son fonctionnement extensible sont définis ; ses entrées seront ajoutées au fil du jeu.
