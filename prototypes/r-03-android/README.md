# Prototype R-03 — JavaFX / Gluon sur Android

Ce prototype isole la validation du socle technique R-03. Il utilise JavaFX, le plugin GluonFX Gradle et Gluon Attach `StorageService`. L’application écrit un marqueur dans le stockage privé de l’application, affiche le chemin obtenu, puis permet de relire ce marqueur après fermeture et redémarrage.

Le fichier est stocké dans le répertoire privé de l’application sous `ldvelh-r03-proof.txt`. Il survit à la fermeture de l’application et au redémarrage du téléphone, mais pas à la désinstallation de l’application. Ce test ne valide pas un sélecteur de fichiers Android ni le partage de documents avec d’autres applications.

## Versions candidates

Les versions sont épinglées pour rendre l’essai reproductible, sans constituer encore la décision finale de R-04 :

- Java 21 (JDK/GraalVM pour le build natif Android) ;
- Gradle 9.1.0 via le wrapper ;
- JavaFX 21.0.7 ;
- GluonFX Gradle plugin 1.0.29 ;
- Gluon Attach 4.0.25 ;
- Android SDK Platform 35 et Build Tools 35.0.0.

Attach 4.0.25 est publié par Gluon. Le dépôt du plugin GluonFX Gradle précise que son implémentation Gradle est maintenue par la communauté ; vérifier ses prérequis natifs et sa compatibilité avec GraalVM et Android SDK avant d’en faire le choix définitif (décision R-04).
Le dépôt Gluon Attach indique une licence GPL-3.0 ; évaluer ses implications de distribution avant de retenir cette dépendance pour l’application finale.

## Préparer le poste de build

Installer GraalVM 21 et Android SDK Platform 35 avec Build Tools 35.0.0. Sur le poste vérifié, GraalVM CE 21.0.2 est installé via SDKMAN et l’Android SDK est présent dans `~/Android/Sdk` avec Platform 37, Build Tools 36.0.0 et `platform-tools/adb`. Les versions candidates 35 ne sont pas installées. Configurer l’environnement Android pour ce chemin :

```sh
export ANDROID_HOME="$HOME/Android/Sdk"
export ANDROID_SDK_ROOT="$ANDROID_HOME"
export PATH="$ANDROID_HOME/platform-tools:$PATH"
```

Configurer également `GRAALVM_HOME` vers l’installation GraalVM 21. Sur le poste vérifié, `~/.bashrc` définit cette variable vers `~/.sdkman/candidates/java/21.0.2-graalce`. Activer le débogage USB sur le téléphone, le connecter et accepter l’empreinte de l’ordinateur.

Vérifier la connexion :

```sh
adb devices -l
```

Depuis ce dossier :

```sh
./gradlew test
./gradlew run
./gradlew nativeBuild
./gradlew nativePackage
./gradlew nativeInstall
```

La dernière commande installe sur un appareil Android connecté. Les tâches `nativeBuild`, `nativePackage` et `nativeInstall` ont été confirmées par `./gradlew tasks --all` avec la version épinglée. Ne pas remplacer ces étapes par une simple compilation JVM.

## Vérification manuelle sur le téléphone

1. Construire, installer et lancer l’application ; confirmer que l’écran s’affiche et que le chemin privé est indiqué.
2. Saisir un marqueur distinctif et appuyer sur **Enregistrer sur l’appareil** ; confirmer le message de succès.
3. Fermer complètement l’application, la relancer, puis appuyer sur **Relire le fichier local** ; confirmer la valeur et son horodatage.
4. Redémarrer le téléphone, relancer l’application et relire le fichier ; confirmer que le marqueur est toujours présent.
5. Confirmer que le chemin affiché correspond au stockage privé et que l’application fonctionne hors ligne.
6. Noter le modèle/version Android, le résultat du build, de l’installation, des deux démarrages et les éventuelles erreurs dans le suivi R-03.

## Limite de validation actuelle

Les tests JVM passent avec le wrapper Gradle 9.1.0. GraalVM CE 21.0.2, l’Android SDK et `adb` sont installés et configurés dans `~/.bashrc`. Les composants Android présents sont Platform 37 et Build Tools 36.0.0, au lieu des versions candidates 35. `adb devices -l` ne détecte aucun appareil. `nativeBuild` atteint GraalVM Native Image, mais échoue avec `Missing CAP cache value for: NativeCodeInfo:PosixDirectives:ConstantInfo:CLOCK_REALTIME`. Le packaging natif, l’installation, le lancement et la persistance après redémarrage restent à valider.
