package com.iloveyou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("/accounts")
@RestController
public class AccountController {
    @Autowired
    AccountRepository accountRepository;

    // Get all accounts
    @GetMapping()
    public List<Account> getAllAccounts() {
        return (List<Account>) accountRepository.findAll();
    }

    // Partial search of Accounts based on the given field
    @GetMapping("/search/{field}")
    @ResponseBody
    public String getAccountBySearch(@PathVariable String field, @RequestParam String query) {
        List<Account> accounts = new ArrayList<Account>();

        // Execute SQL code in the repository depending on what field was given
        if (field.equals("name")) {
            // search by name
            accounts = accountRepository.searchByName(query);
        } else if (field.equals("email")) {
            // search by email
            accounts = accountRepository.searchByEmail(query);
        }

        if (accounts.size() == 0) {
            return "No accounts found matching search: " + query;
        }

        return accounts.toString();
    }

    // Partial search of Accounts by name OR email
    @GetMapping("/search")
    @ResponseBody
    public String getAccountByNameOrEmail(@RequestParam String query) {
        List<Account> accounts = accountRepository.searchByNameOrEmail(query);

        if (accounts.size() == 0) {
            return "No accounts found with a name or email matching: " + query;
        }

        return accounts.toString();
    }

    @GetMapping("/{id}")
    Optional<Account> getAccount(@PathVariable long id) {
        return accountRepository.findById(id);
    }

    @PostMapping("/add")
    public Account createAccount(@RequestBody Account account) {
        return accountRepository.save(account);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAccount(@PathVariable("id") Long id) {
        accountRepository.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateAccount(@RequestBody Account account, @PathVariable Long id) {
        Optional<Account> accountOptional = accountRepository.findById(account.getId());
        if (!accountOptional.isPresent())
            return ResponseEntity.notFound().build();
        account.setId(id);
        accountRepository.save(account);
        return ResponseEntity.noContent().build();
    }
}
