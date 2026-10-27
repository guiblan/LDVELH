package fr.ldvelh.r03;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Optional;

final class LocalProofStore {
    private final Path file;

    LocalProofStore(Path file) {
        this.file = Objects.requireNonNull(file, "file");
    }

    Path file() {
        return file;
    }

    void save(String marker) throws IOException {
        Objects.requireNonNull(marker, "marker");
        if (marker.isBlank() || marker.contains("\n") || marker.contains("\r")) {
            throw new IllegalArgumentException("Le marqueur doit être non vide et tenir sur une ligne.");
        }

        Files.createDirectories(file.getParent());
        Files.writeString(
                file,
                Instant.now() + "\n" + marker,
                StandardCharsets.UTF_8);
    }

    Optional<SavedProof> load() throws IOException {
        if (Files.notExists(file)) {
            return Optional.empty();
        }

        String[] lines = Files.readString(file, StandardCharsets.UTF_8).split("\\R", 2);
        if (lines.length != 2 || lines[1].isBlank()) {
            throw new IOException("Le fichier de preuve existe mais son contenu est invalide.");
        }

        try {
            return Optional.of(new SavedProof(Instant.parse(lines[0]), lines[1]));
        } catch (DateTimeParseException exception) {
            throw new IOException("La date enregistrée dans le fichier de preuve est invalide.", exception);
        }
    }

    record SavedProof(Instant savedAt, String marker) {
    }
}
