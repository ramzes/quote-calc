package org.algoteque.model.quote;

import java.util.Map;

public record QuoteRequest(Map<String, Integer> topics) {
}
