package org.algoteque.model.provider;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@ConfigurationProperties("vendor")
public record VendorFileConfiguration(String filePath, String fileName) {

    @ConstructorBinding
    public VendorFileConfiguration {
        if (filePath.isBlank()) {
            filePath = this.getClass().getClassLoader().getResource(fileName).getFile();
        }
    }
}
