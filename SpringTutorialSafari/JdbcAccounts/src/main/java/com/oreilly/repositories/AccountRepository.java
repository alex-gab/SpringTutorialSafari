package com.oreilly.repositories;

import com.oreilly.entities.Account;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AccountRepository {
    List<Account> getAccounts();

    Account getAccount(Long id);

    int getNumberOfAccounts();

    Long createAccount(BigDecimal innitialBalance);

    void updateAccount(Account account);

    int deleteAccount(Long id);
}
