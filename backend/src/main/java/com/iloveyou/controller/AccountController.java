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


import com.iloveyou.entity.Account;
import com.iloveyou.repository.AccountRepository;

import java.util.ArrayList;
import java.util.List; 

@RequestMapping("accounts")
@RestController
public class AccountController {
    @Autowired
    AccountRepository userRepository;

    // Get all accounts
    @GetMapping()
    public List<Account> getAllAccounts() {
        return (List<Account>) userRepository.findAll();
    }

    // Partial search of Accounts based on the given field
    @GetMapping("/search/{field}")
    @ResponseBody
    public String getAccountBySearch(@PathVariable String field, @RequestParam String query) {
        List<Account> accounts = new ArrayList<Account>();

        // Execute SQL code in the repository depending on what field was given
        if (field.equals("name")) {
            // search by name
            accounts = userRepository.searchByName(query);
        } else if (field.equals("email")) {
            // search by email
             accounts = userRepository.searchByEmail(query);
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
        List<Account> accounts = userRepository.searchByNameOrEmail(query);

        if (accounts.size() == 0) {
            return "No accounts found with a name or email matching: " + query;
        }

        return accounts.toString();
    }
}
