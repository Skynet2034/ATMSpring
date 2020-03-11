package app.service.impl;

import app.model.Account;
import app.service.AccountService;
import app.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class AccountServiceImpl implements AccountService {

    @Autowired
    @Qualifier("StoreServiceImpl")
    private StoreService service;

    @Override
    public double balance(Account account) {
        return account.getAmount();
    }

    @Override
    public void deposit(Account account, double amount) {
        double sum = account.getAmount() + amount;
        account.setAmount(sum);
        service.update(account);
    }

    @Override
    public void withdraw(Account account, double amount) {
        double sum = account.getAmount() - amount;
        if(sum < 0){
            throw new RuntimeException("Not enough money");
        }
        service.update(account);
    }

    @Override
    public void transfer(Account from, Account to, double amount) {

    }
}
