package org.algoteque.controller.quota;

import lombok.RequiredArgsConstructor;
import org.algoteque.model.quote.QuoteRequest;
import org.algoteque.service.QuoteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("quote")
@RequiredArgsConstructor
public class QuoteController {

    private final QuoteService quoteService;

    @GetMapping
    public Map<String, Double> calculateQuote(@RequestBody QuoteRequest quoteRequest) {
        return quoteService.generateQuotes(quoteRequest.topics());
    }
}
