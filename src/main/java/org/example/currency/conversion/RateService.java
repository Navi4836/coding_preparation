package org.example.currency.conversion;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RateService {

    public static Map<String, Double> exchangeRates = new ConcurrentHashMap<>();

    public RateService() {
        loadRates();
    }

    public Map<String, Double> exchangeRates() {
        return exchangeRates;
    }

    private static void loadRates() {
        exchangeRates.put("GBP/EUR", 1.10);
        exchangeRates.put("GBP/USD", 1.35);
        exchangeRates.put("GBP/INR", 119.25);
    }
}
