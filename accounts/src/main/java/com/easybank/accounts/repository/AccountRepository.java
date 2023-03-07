package com.easybank.accounts.repository;

import com.easybank.accounts.model.Accounts;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Accounts, Long> {

    Accounts findByCustomerId(int customerId);
}
