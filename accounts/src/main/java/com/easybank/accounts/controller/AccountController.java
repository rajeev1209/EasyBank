package com.easybank.accounts.controller;


import com.easybank.accounts.config.AccountsConfig;
import com.easybank.accounts.model.Accounts;
import com.easybank.accounts.model.Customer;
import com.easybank.accounts.model.Properties;
import com.easybank.accounts.repository.AccountRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    AccountsConfig accountsConfig;

    @PostMapping("/myAccounts")
    public Accounts getAccountDetails(@RequestBody Customer customer){
        Accounts accounts = accountRepository.findByCustomerId(customer.getCustomerId());
        return Optional.ofNullable(accounts).orElse(null);
    }

    @GetMapping("/accounts/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(accountsConfig.getMsg(), accountsConfig.getBuildVersion(),
                accountsConfig.getMailDetails(), accountsConfig.getActiveBranches());
        String jsonStr = ow.writeValueAsString(properties);
        return jsonStr;
    }

}
