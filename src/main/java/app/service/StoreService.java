package app.service;

import app.model.Account;

import java.util.List;

public interface StoreService {
    public Account get(String id);
    public List<Account> get();
    public void delete(String id);
    public void insert(Account account);
    public void update(Account account);
}
