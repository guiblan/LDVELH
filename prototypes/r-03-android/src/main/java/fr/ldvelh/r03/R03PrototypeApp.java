package fr.ldvelh.r03;

import com.gluonhq.attach.storage.StorageService;
import com.gluonhq.attach.util.Services;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

public final class R03PrototypeApp extends Application {
    private final Label storageStatus = new Label();
    private final Label result = new Label("Aucune lecture effectuée.");
    private final TextField markerInput = new TextField("R-03 persistance validée");
    private LocalProofStore store;

    @Override
    public void start(Stage stage) {
        Optional<Path> storagePath = Services.get(StorageService.class)
                .flatMap(StorageService::getPrivateStorage)
                .map(File::toPath);

        if (storagePath.isPresent()) {
            store = new LocalProofStore(storagePath.get().resolve("ldvelh-r03-proof.txt"));
            storageStatus.setText("Stockage privé accessible : " + store.file());
            storageStatus.setStyle("-fx-text-fill: #176b36;");
        } else {
            storageStatus.setText("ERREUR : le service de stockage privé est indisponible.");
            storageStatus.setStyle("-fx-text-fill: #b42318;");
            System.err.println(storageStatus.getText());
        }

        markerInput.setPromptText("Texte à conserver après redémarrage");
        markerInput.setMaxWidth(Double.MAX_VALUE);

        Button saveButton = new Button("Enregistrer sur l’appareil");
        saveButton.setMaxWidth(Double.MAX_VALUE);
        saveButton.setDisable(store == null);
        saveButton.setOnAction(event -> saveProof());

        Button reloadButton = new Button("Relire le fichier local");
        reloadButton.setMaxWidth(Double.MAX_VALUE);
        reloadButton.setDisable(store == null);
        reloadButton.setOnAction(event -> reloadProof());

        VBox content = new VBox(
                12,
                new Label("Prototype R-03 — JavaFX / Gluon"),
                new Label("Écris une valeur, enregistre-la, ferme l’application puis relis-la."),
                storageStatus,
                markerInput,
                saveButton,
                reloadButton,
                result);
        content.setPadding(new Insets(24));

        stage.setTitle("LDVELH — R-03");
        stage.setScene(new Scene(content, 420, 520));
        stage.show();
    }

    private void saveProof() {
        try {
            store.save(markerInput.getText());
            result.setText("Écriture réussie. Ferme l’application puis utilise « Relire ».");
        } catch (IOException | IllegalArgumentException exception) {
            showError("Écriture impossible", exception);
        }
    }

    private void reloadProof() {
        try {
            Optional<LocalProofStore.SavedProof> proof = store.load();
            if (proof.isEmpty()) {
                result.setText("Aucun fichier de preuve trouvé. Enregistre d’abord une valeur.");
                return;
            }

            LocalProofStore.SavedProof saved = proof.get();
            markerInput.setText(saved.marker());
            result.setText("Relu depuis le disque à " + saved.savedAt() + " : " + saved.marker());
        } catch (IOException exception) {
            showError("Lecture impossible", exception);
        }
    }

    private void showError(String action, Exception exception) {
        result.setText("ERREUR — " + action + " : " + exception.getMessage());
        System.err.println(action + " : " + exception);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
