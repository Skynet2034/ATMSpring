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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;
import java.util.Map;

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


}
