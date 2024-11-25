package org.algoteque.service;

import org.algoteque.model.provider.ProviderConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class QuoteServiceTest {

    private static final Map<String, String> PROVIDERS_TEST_MAP = Map.of(
            "provider_a", "math+science",
            "provider_b", "reading+science",
            "provider_c", "history+math"
    );

    private static final Map<String, Integer> VALID_TEST_DATA = Map.of(
            "reading", 20,
            "math", 50,
            "science", 30,
            "history", 15,
            "art", 10
    );

    private static QuoteService quoteService;

    @BeforeAll
    public static void setup() {
        quoteService = new QuoteService();
    }

    @Test
    public void shouldReturnQuotesForProvidedExample() {
        ProviderConfiguration staticInitialize = new ProviderConfiguration(PROVIDERS_TEST_MAP);
        Map<String, Double> result = quoteService.generateQuotes(VALID_TEST_DATA);

        assertEquals(8, result.get("provider_a"));
        assertEquals(5, result.get("provider_b"));
        assertEquals(10, result.get("provider_c"));
    }

    @Test
    public void shouldNotReturnZeroQuotaForMissingProviderMatch() {
        ProviderConfiguration staticInitialize = new ProviderConfiguration(PROVIDERS_TEST_MAP);
        Map<String, Double> result = quoteService.generateQuotes(Map.of(
                "geography", 20,
                "polish", 50,
                "german", 30,
                "history", 15,
                "art", 10));

        assertNull(result.get("provider_a"));
        assertNull(result.get("provider_b"));
        assertNull(result.get("provider_c"));
    }

    @Test
    public void shouldNotReturnZeroQuotaBecauseOfInvalidConfigFile() {
        ProviderConfiguration staticInitialize = new ProviderConfiguration(Map.of(
                "provider_x", "math-science",
                "provider_y", "reading/science",
                "provider_z", "history+math"
        ));
        Map<String, Double> result = quoteService.generateQuotes(VALID_TEST_DATA);

        assertNull(result.get("provider_x"));
        assertNull(result.get("provider_y"));
        assertEquals(10, result.get("provider_z"));
    }

    @Test
    public void shouldReturnQuoteForSingleMatchesAndTooShortRequest() {
        ProviderConfiguration staticInitialize = new ProviderConfiguration(Map.of(
                "provider_a", "math+art",
                "provider_b", "history+math",
                "provider_c", "history+art"
        ));
        Map<String, Double> result = quoteService.generateQuotes(Map.of(
                "history", 15,
                "art", 10));

        assertEquals(2.5, result.get("provider_a"));
        assertEquals(3, result.get("provider_b"));
        assertEquals(2.5, result.get("provider_c"));
    }

    @Test
    public void shouldAvoidMultiplicationByZero() {
        ProviderConfiguration staticInitialize = new ProviderConfiguration(Map.of(
                "provider_a", "math+art",
                "provider_b", "history+math",
                "provider_c", "math+science"
        ));
        Map<String, Double> result = quoteService.generateQuotes(Map.of(
                "history", 15,
                "art", 10,
                "math", 0));

        assertEquals(1, result.get("provider_a"));
        assertEquals(1.5, result.get("provider_b"));
        assertEquals(null, result.get("provider_c"));
    }

}
