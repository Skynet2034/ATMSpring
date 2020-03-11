package app.controller;

import app.Store;
import app.model.Account;
import app.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class AtmController {

    @Autowired
    @Qualifier("StoreServiceImpl")
    private StoreService service;

//    @GetMapping("/balance")
//    public String getBalance(){
//        return "index";
//    }
//
//    @GetMapping("/users")
//    public ResponseEntity<Map<String, Account>> getAccounts(){
//        return new ResponseEntity<>(Store.getStore(), HttpStatus.OK);
//    }

    // https://github.com/greepz/spring-mvc-hackerU

    @GetMapping("/account")
    public ResponseEntity<Account> getAccount(@RequestParam("id") String id){
        Account acc = service.get(id);
        return new ResponseEntity<>(acc, HttpStatus.OK);
    }

    @GetMapping("/accounts")
    public String getAccounts(Model model){
        Collection<Account> accounts = service.get();
        model.addAttribute("list", accounts);
        return "index";
    }

    //http://localhost:9011/account/delete/2ddf929f-8d96-410a-99b5-377f8a6749e6
    @GetMapping("/account/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String id){
        service.delete(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    //http://localhost:9011/account/add

    /**
     * {
     *     "holder": "artem111",
     *     "date": "2020-01-01",
     *     "amount": 1000.0
     * }
     *
     */
    @PostMapping("/account/add")
    public ResponseEntity<Account> add(@RequestBody Account account){
        account.setId(UUID.randomUUID().toString());
        account.setPin((int)(Math.random()*9999));
        service.insert(account);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PutMapping("/account/update")
    public ResponseEntity<Account> update(@RequestBody Account account){
        Account oldAccount = service.get(account.getId());
        oldAccount.setHolder(account.getHolder());
        oldAccount.setDate(account.getDate());
        oldAccount.setAmount(account.getAmount());
        service.update(oldAccount);
        return new ResponseEntity<>(oldAccount, HttpStatus.OK);
    }


}
