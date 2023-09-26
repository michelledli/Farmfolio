package com.iloveyou.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.iloveyou.repository.AccountRepository;

import com.iloveyou.entity.Account;

@RequestMapping("/accounts")
@RestController 
@Controller
public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/all")
    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    @GetMapping("/{id}")
    Optional<Account> getAccount(@PathVariable long id) {
        return accountRepository.findById(id);
    } 

    @PostMapping("/add")
    public Account createAccount(@RequestBody Account account){
        return accountRepository.save(account); 
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAccount(@PathVariable("id") Long id){
        accountRepository.deleteById(id);
    } 

    @PutMapping("/update/{id}") 
    public ResponseEntity<Object> updateAccount(@RequestBody Account account, @PathVariable Long id){
        Optional<Account> accountOptional = accountRepository.findById(account.getId());
        if(!accountOptional.isPresent())
            return ResponseEntity.notFound().build();
        account.setId(id);
        accountRepository.save(account);
        return ResponseEntity.noContent().build();
    }
}
