package com.easybank.accounts.controller;

import com.easybank.accounts.model.Accounts;
import com.easybank.accounts.model.Customer;
import com.easybank.accounts.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("/myAccount")
    public Accounts getAccountDetails(@RequestBody Customer customer){
        Accounts accounts = accountRepository.findByCustomerId(customer.getCustomerId());
        return Optional.ofNullable(accounts).orElse(null);
    }
}
