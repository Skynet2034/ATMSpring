package app.service.impl;

import app.model.Account;
import app.service.AccountService;
import app.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("AccountServiceImpl")
public class AccountServiceImpl implements AccountService {

    @Autowired
    @Qualifier("Base")
    private StoreService service;


    private Object obj = new Object();

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
        account.setAmount(sum);
        service.update(account);
    }

    @Override
    public void transfer(Account from, Account to, double amount) {

        double fromSum = from.getAmount() - amount;
        double toSum = to.getAmount() + amount;
        if(fromSum < 0 ){
            throw new RuntimeException("Not enough money");
        }
        from.setAmount(fromSum);
        service.update(from);
        to.setAmount(toSum);
        service.update(to);

    }

}
