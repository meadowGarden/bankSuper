package src;

import lt.itakademija.exam.*;

import java.util.ArrayList;
import java.util.List;

public class BankImpl implements Bank {

    private List<Customer> customers;
    private List<Account> accounts;
    private static long customerID;
    private SequenceGenerator accountID;
    private SequenceGenerator paymentID;
    private CurrencyConverter currencyConverter;


    public BankImpl(CurrencyConverter currencyConverter) {
        this.customers = new ArrayList<>();
        this.accounts = new ArrayList<>();
        this.accountID = new SequenceGenerator();
        this.paymentID = new SequenceGenerator();
        customerID = 0;
        this.currencyConverter = currencyConverter;
    }

    @Override
    public Customer createCustomer(PersonCode personCode, PersonName personName) throws CustomerCreateException {
        if ((personCode == null) || (personName == null)) {
            throw new NullPointerException();
        }
        for (Customer c : customers) {
            if (c.getPersonCode().equals(personCode)) {
                throw new CustomerCreateException("x");
            }
        }
        Customer customer = new Customer(++customerID, personCode, personName);
        customers.add(customer);
        return customer;
    }

    @Override
    public Account createAccount(Customer customer, Currency currency) throws AccountCreateException {
        if ((customer == null) || (currency == null)) {
            throw new NullPointerException();
        }
        if (!customers.contains(customer)) {
            throw new AccountCreateException("x");
        }
        Account account = new Account(accountID.getNext(), customer, currency, new Money(0));
        customer.addAccount(account);
        accounts.add(account);
        return account;
    }

    @Override
    public Operation transferMoney(Account debit, Account credit, Money amountToTransfer) {
        if (debit.getBalance().isLessThan(amountToTransfer)) {
            throw new InsufficientFundsException("x");
        }
            Money moneyConverted = currencyConverter.convert(debit.getCurrency(), credit.getCurrency(), amountToTransfer);
            debit.setBalance(debit.getBalance().substract(amountToTransfer));
            credit.setBalance(credit.getBalance().add(moneyConverted));
            return new Operation(paymentID.getNext(), debit, credit, amountToTransfer);
    }

    @Override
    public Money getBalance(Currency currency) {
        Money totalBalance = new Money(0);

        for (Account a : accounts) {
            totalBalance = totalBalance.add(currencyConverter.convert(a.getCurrency(), currency, a.getBalance()));
        }

        return totalBalance;
    }

}
