package src;

import lt.itakademija.exam.*;

public class CurrencyConverterImpl implements CurrencyConverter {


    private CurrencyRatesProvider ratesProvider;

    public CurrencyConverterImpl() {

    }

    @Override
    public Money convert(Currency currencyFrom, Currency currencyTo, Money money) {

        Money rate = ratesProvider.getRate(currencyFrom, currencyTo);
        return money.multiply(rate);
//        return money;
    }
}
