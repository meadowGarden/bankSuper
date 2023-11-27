package src;

import lt.itakademija.exam.*;

public class CurrencyConverterImpl implements CurrencyConverter {

    CurrencyRatesProvider ratesProvider;

    public CurrencyConverterImpl(CurrencyRatesProvider ratesProvider) {
        this.ratesProvider = ratesProvider;
    }

    @Override
    public Money convert(Currency currencyFrom, Currency currencyTo, Money money) {
            Money rate = ratesProvider.getRate(currencyFrom, currencyTo);
            if (rate == null) {
                throw new CurrencyConversionException("x");
            }
            return money.multiply(rate);
    }
}
