package ch11;

import java.util.Currency;
import java.util.Locale;
import java.util.Set;

public class UsageCurrency {
    public static void main(String[] args) {
        Set<Currency> currencies = Currency.getAvailableCurrencies();

        for (Currency currency : currencies) {
            System.out.printf("%s - %s - %s%n", currency.getDisplayName(), currency.getDisplayName(Locale.GERMAN), currency.getNumericCode());
        }
    }
}
