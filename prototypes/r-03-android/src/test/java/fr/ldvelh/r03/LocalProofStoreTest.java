package fr.ldvelh.r03;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Path;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LocalProofStoreTest {
    @TempDir
    Path temporaryDirectory;

    @Test
    void savedMarkerCanBeReadAfterCreatingANewStore() throws IOException {
        Path file = temporaryDirectory.resolve("private/ldvelh-r03-proof.txt");
        LocalProofStore firstRun = new LocalProofStore(file);
        firstRun.save("section 42");

        LocalProofStore afterRestart = new LocalProofStore(file);

        assertEquals("section 42", afterRestart.load().orElseThrow().marker());
        assertTrue(afterRestart.load().orElseThrow().savedAt().isBefore(Instant.now().plusSeconds(1)));
    }

    @Test
    void missingProofIsReportedAsEmpty() throws IOException {
        LocalProofStore store = new LocalProofStore(temporaryDirectory.resolve("missing.txt"));

        assertFalse(store.load().isPresent());
    }

    @Test
    void invalidMarkerIsRejectedInsteadOfSilentlyChanged() {
        LocalProofStore store = new LocalProofStore(temporaryDirectory.resolve("proof.txt"));

        assertThrows(IllegalArgumentException.class, () -> store.save(" "));
        assertThrows(IllegalArgumentException.class, () -> store.save("line one\nline two"));
    }
}
