package com.oreilly.repositories;

import com.oreilly.entities.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountRepository {
    List<Account> getAccounts();

    Account getAccount(long id);

    int getNumberOfAccounts();

    long createAccount(BigDecimal initialBalance);

    int deleteAccount(long id);

    void updateAccount(Account account);
}
