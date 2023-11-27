package src;

import lt.itakademija.exam.*;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

//        CurrencyRatesProvider.CurrencyPair pair = new CurrencyRatesProvider.CurrencyPair(new Currency("USD"), new Currency("EUR"));
        CurrencyRatesProvider.CurrencyPair pair = new CurrencyRatesProvider.CurrencyPair(new Currency("EUR"), new Currency("USD"));

        Money rate = new Money(1.1);
        Map<CurrencyRatesProvider.CurrencyPair, Money> map = new HashMap<>();
        map.put(pair, rate);

        CurrencyRatesProvider ratesProvider = new CurrencyRatesProvider(map);

        CurrencyConverter converter = new CurrencyConverterImpl(ratesProvider);

        BankImpl bank = new BankImpl(converter);
        Customer customer01 = bank.createCustomer(new PersonCode("iiii"), new PersonName("john", "doe"));
        System.out.println(customer01);
        Account account01 = bank.createAccount(customer01, new Currency("USD"));
        System.out.println(account01);
        account01.setBalance(new Money(1000));
        System.out.println(account01);

        Customer customer02 = bank.createCustomer(new PersonCode("iiiii"), new PersonName("jane", "doe"));
        System.out.println(customer02);
        Account account02 = bank.createAccount(customer02, new Currency("EUR"));
        System.out.println(account02);
        account02.setBalance(new Money(5000));
        System.out.println(account02);

        bank.transferMoney(account01, account02, new Money(600));
        System.out.println(account01);
        System.out.println(account02);
        System.out.println(bank.transferMoney(account01, account02, new Money(100)));


        System.out.println("-------");

    }
}
