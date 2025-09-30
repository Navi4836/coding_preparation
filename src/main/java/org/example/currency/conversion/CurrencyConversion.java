package org.example.currency.conversion;

public class CurrencyConversion {

    public Double convert(String domesticCurrency, String foreignCurrency, double domesticValue) {
        String key = "GBP/USD";
        RateService rateService = new RateService();
        Double exchangeRate = rateService.exchangeRates().get(key);
        Double foreignValue = domesticValue * exchangeRate;
        return foreignValue;
    }

    public static void main(String[] args) {
        CurrencyConversion conversion = new CurrencyConversion();
        Double USD = conversion.convert("GBP", "USD", 1);
        System.out.println(USD);
    }

}
