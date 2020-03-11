package app.controller;

import app.Store;
import app.model.Account;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class AtmController {

    @GetMapping("/balance")
    public String getBalance(){
        return "index";
    }

    @GetMapping("/users")
    public ResponseEntity<Map<String, Account>> getAccounts(){
        return new ResponseEntity<>(Store.getStore(), HttpStatus.OK);
    }

}
