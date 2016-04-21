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

    public BigDecimal deposit(final long id, final BigDecimal amount) {
        final Account account = repository.getAccount(id);
        final BigDecimal newBalance = account.getBalance().add(amount);
        account.setBalance(newBalance);
        repository.updateAccount(account);
        return newBalance;
    }

    public BigDecimal withdraw(final long id, final BigDecimal amount) {
        return deposit(id, amount.negate());
    }

    public void transfer(long fromId, long toId, BigDecimal amount) {
        withdraw(fromId, amount);
        deposit(toId, amount);
    }
}
