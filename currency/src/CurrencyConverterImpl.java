package src;

import lt.itakademija.exam.*;

public class CurrencyConverterImpl implements CurrencyConverter {


    private CurrencyRatesProvider ratesProvider;




    @Override
    public Money convert(Currency currencyFrom, Currency currencyTo, Money money) {


        return money;
    }
}
