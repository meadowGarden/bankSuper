package test;

import lt.itakademija.exam.Bank;
import lt.itakademija.exam.CurrencyConverter;
import lt.itakademija.exam.CurrencyRatesProvider;
import lt.itakademija.exam.test.BaseTest;
import src.BankImpl;
import src.CurrencyConverterImpl;

public class TestImpl extends BaseTest {

    @Override
    protected Bank createBank(CurrencyConverter currencyConverter) {
        return new BankImpl();
    }

    @Override
    protected CurrencyConverter createCurrencyConverter(CurrencyRatesProvider currencyRatesProvider) {
        return new CurrencyConverterImpl();
    }
}
