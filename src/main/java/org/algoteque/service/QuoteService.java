package org.algoteque.service;

import org.algoteque.model.provider.ProviderConfiguration;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuoteService {

    public Map<String, Double> generateQuotes(Map<String, Integer> topics) {
        List<Map.Entry<String, Integer>> topTopics = topics.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .limit(3)
                .toList();

        Map<String, Integer> ranks = new HashMap<>();
        for (int i = 0; i < topTopics.size(); i++) {
            ranks.put(topTopics.get(i).getKey(), i + 1);
        }

        Map<String, Double> quotes = new HashMap<>();
        ProviderConfiguration.providerTopics.forEach((provider, offeredTopics) -> {
            List<String> matches = offeredTopics.stream()
                    .filter(ranks::containsKey)
                    .toList();

            double quote = 0;
            if (matches.size() == 2) {
                quote = 0.1 * matches.stream().mapToInt(topics::get).sum();
            } else if (matches.size() == 1) {
                String topic = matches.get(0);
                int rank = ranks.get(topic);
                double factor = rank == 1 ? 0.20 : (rank == 2 ? 0.25 : 0.3);
                if (topics.get(topic) > 0) {
                    quote = factor * topics.get(topic);
                }
            }

            if (quote > 0) {
                quotes.put(provider, quote);
            }
        });

        return quotes;
    }
}
