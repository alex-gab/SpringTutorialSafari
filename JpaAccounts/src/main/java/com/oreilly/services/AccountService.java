package com.oreilly.services;

import com.oreilly.entities.Account;
import com.oreilly.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class AccountService {
    @Autowired
    private AccountRepository repository;

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public BigDecimal getBalance(final long id) {
        return repository.getAccount(id).getBalance();
    }

    @SuppressWarnings("Duplicates")
    public BigDecimal deposit(final long id, final BigDecimal amount) {
        Account account = repository.getAccount(id);
        BigDecimal newBalance = account.getBalance().add(amount);
        account.setBalance(newBalance);
        repository.updateAccount(account);
        return newBalance;
    }

    public BigDecimal withdraw(final long id, final BigDecimal amount) {
        return deposit(id, amount.negate());
    }

    public void transfer(final long fromId, final long toId, final BigDecimal amount) {
        withdraw(fromId, amount);
        deposit(toId, amount);
    }
}
