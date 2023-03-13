package com.easybank.accounts.controller;


import com.easybank.accounts.config.AccountsConfig;
import com.easybank.accounts.model.Accounts;
import com.easybank.accounts.model.Cards;
import com.easybank.accounts.model.Customer;
import com.easybank.accounts.model.CustomerDetails;
import com.easybank.accounts.model.Loans;
import com.easybank.accounts.model.Properties;
import com.easybank.accounts.repository.AccountsRepository;
import com.easybank.accounts.service.client.CardsFeignClient;
import com.easybank.accounts.service.client.LoansFeignClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {
    @Autowired
    private AccountsRepository accountsRepository;

    @Autowired
    AccountsConfig accountsConfig;

    @Autowired
    CardsFeignClient cardsFeignClient;
    @Autowired
    LoansFeignClient loansFeignClient;

    @PostMapping("/myAccounts")
    public Accounts getAccountDetails(@RequestBody Customer customer){
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId());
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

    @PostMapping("/myCustomerDetails")
    public CustomerDetails myCustomerDetails(@RequestBody Customer customer) {
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId());
        List<Loans> loans = loansFeignClient.getLoanDetails(customer);
        List<Cards> cards = cardsFeignClient.getCardDetails(customer);

        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setAccounts(accounts);
        customerDetails.setLoans(loans);
        customerDetails.setCards(cards);

        return customerDetails;

    }

}
