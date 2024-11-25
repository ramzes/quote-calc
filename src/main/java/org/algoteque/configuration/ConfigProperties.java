package org.algoteque.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.algoteque.model.provider.ProviderConfiguration;
import org.algoteque.model.provider.VendorFileConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Configuration
public class ConfigProperties {

    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
    }

    @Bean
    public ProviderConfiguration providerConfiguration(VendorFileConfiguration vendorFileConfiguration) {
        try {
            return new ProviderConfiguration(objectMapper.readerFor(Map.class)
                    .withRootName("provider_topics")
                    .readValue(new File(vendorFileConfiguration.filePath())));
        } catch (IOException ex) {
            throw new RuntimeException("Unsupported json file root element in provider configuration");
        }
    }
}
