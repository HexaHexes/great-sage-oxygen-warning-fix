package org.bitwisemadness.gsoxygenfix;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Keeps the junit CI check non-vacuous. This mod is mixin-only (no @Mod
 * class), so the test validates the shipped metadata instead of a code
 * constant.
 */
class GsOxygenFixTest {
    @Test
    void modsTomlDeclaresStableModId() throws Exception {
        var url = GsOxygenFixTest.class.getResource("/META-INF/neoforge.mods.toml");
        assertNotNull(url, "neoforge.mods.toml must be on the test resources classpath");
        String toml;
        try (var stream = url.openStream()) {
            toml = new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        }
        assertTrue(java.util.regex.Pattern.compile("modId\\s*=\\s*\"gsoxygenfix\"").matcher(toml).find(),
                "mods.toml must declare modId gsoxygenfix");
    }
}
