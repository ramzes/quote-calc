package org.algoteque.model.provider;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProviderConfigurationUnitTests {

    private static final Map<String, String> TEST_MAP = Map.of(
            "provider_a", "math+science",
            "provider_b", "reading+science",
            "provider_c", "history+math"
    );

    @Test
    public void shouldThrowIllegalArgumentExceptionForMissingConfig() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new ProviderConfiguration(null));

        assertEquals(ProviderConfiguration.MISSING_CONF_EXCEPTION, exception.getMessage());
    }

    @Test
    public void shouldSplitVendorConfiguration() {
        ProviderConfiguration providerConfiguration = new ProviderConfiguration(TEST_MAP);

        assertEquals(3, providerConfiguration.settings().size());
        assertEquals(3, ProviderConfiguration.providerTopics.size());
        assertEquals(ProviderConfiguration.providerTopics.get("provider_a"), List.of("math", "science"));
        assertEquals(ProviderConfiguration.providerTopics.get("provider_b"), List.of("reading", "science"));
        assertEquals(ProviderConfiguration.providerTopics.get("provider_c"), List.of("history", "math"));
    }

}
