package org.algoteque.controller.provider;


import lombok.RequiredArgsConstructor;
import org.algoteque.model.provider.ProviderConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("provider")
@RequiredArgsConstructor
public class ProviderController {

    private final ProviderConfiguration providerConfiguration;

    @GetMapping("list")
    public ProviderResponse listProvidersConfiguration() {
        return new ProviderResponse(providerConfiguration.settings());
    }
}

record ProviderResponse(Map<String, String> provider_list) {
}