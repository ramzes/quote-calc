package org.algoteque.model.provider;


import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record ProviderConfiguration(Map<String, String> settings) {

    public static final String MISSING_CONF_EXCEPTION = "Missing providers configuration. Have you not declared provider.settings in your configuration files?";
    public static Map<String, List<String>> providerTopics;

    public ProviderConfiguration(Map<String, String> settings) {
        if (CollectionUtils.isEmpty(settings)) {
            throw new IllegalArgumentException(MISSING_CONF_EXCEPTION);
        }

        this.settings = settings;
        providerTopics = this.settings.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        value -> Arrays.asList(value.getValue().split("\\+"))
                ));
    }
}
