package com.iloveyou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.iloveyou.repository.AccountRepository;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;

import com.iloveyou.entity.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("/accounts")
@Controller
public class AccountController {
    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/{id}")
    public ResponseEntity<?> getAccount(@PathVariable Long id) {
        Account a = accountRepository.findById(id).orElse(null);

        if (a == null)
            return new ResponseEntity<>(
                    "A user with the requested id was not found",
                    HttpStatus.BAD_REQUEST);

        a.setPassword(null);

        return new ResponseEntity<>(a, HttpStatus.OK);
    }

    @GetMapping("/me")
    @ResponseBody
    public Account getMe(HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("claims");
        Long id = Long.valueOf(claims.getId());
        return accountRepository.findById(id).orElse(null);
    }

    // Partial search of Accounts based on the given field
    @GetMapping("/search/{field}")
    public ResponseEntity<?> getAccountBySearch(@PathVariable String field, @RequestParam String query) {
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
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(accounts);
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

    @PostMapping("/")
    public Account createAccount(@RequestBody Account account) {
        return accountRepository.save(account);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable("id") Long id) {
        accountRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAccount(@RequestBody Account account, @PathVariable Long id) {
        // find the target account using the path variable id
        Optional<Account> targetAccount = accountRepository.findById(id);

        // check if the account is empty or not
        if (!targetAccount.isPresent())
            // if empty, respond with not found
            return ResponseEntity.notFound().build();

        // the target account exists, update the account
        // first create an Account type object
        Account temp = targetAccount.get();

        // update all fields, some potentially being unchanged
        temp.setFname(account.getFname());
        temp.setLname(account.getLname());
        temp.setEmail(account.getEmail());

        accountRepository.save(temp);
        // return success
        return ResponseEntity.ok().build();
    }

}
