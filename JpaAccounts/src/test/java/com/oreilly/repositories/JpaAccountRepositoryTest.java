package com.oreilly.repositories;

import com.oreilly.config.AppConfig;
import com.oreilly.entities.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.number.BigDecimalCloseTo.closeTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@Transactional
@ActiveProfiles("test")
public class JpaAccountRepositoryTest {
    @Autowired
    private AccountRepository repository;

    @Test
    public final void testGetAccounts() {
        final List<Account> accounts = repository.getAccounts();
        assertThat(accounts.size(), is(3));
    }

    @Test
    public final void testGetAccount() {
        final Account account = repository.getAccount(1L);
        assertThat(account.getId(), is(1L));
        assertThat(account.getBalance(),
                is(closeTo(new BigDecimal("100.0"), new BigDecimal("0.01"))));
    }

    @Test
    public final void testGetNumberOfAccounts() {
        assertThat(repository.getNumberOfAccounts(), is(3));
    }

    @Test
    @SuppressWarnings("Duplicates")
    public final void testCreateAccount() {
        final long id = repository.createAccount(new BigDecimal("250.00"));
        assertThat(id, is(notNullValue()));

        final Account account = repository.getAccount(id);
        assertThat(account.getId(), is(id));
        assertThat(account.getBalance(),
                is(closeTo(new BigDecimal("250.00"), new BigDecimal("0.01"))));
    }

    @Test
    @SuppressWarnings("Duplicates")
    public final void testUpdateAccount() {
        final Account account = repository.getAccount(1L);
        final BigDecimal current = account.getBalance();
        final BigDecimal amount = new BigDecimal("50.0");
        account.setBalance(current.add(amount));
        repository.updateAccount(account);

        final Account again = repository.getAccount(1L);
        assertThat(again.getBalance(),
                is(closeTo(current.add(amount), new BigDecimal("0.01"))));
    }

    @Test
    public final void testDeleteAccount() {
        for (final Account account : repository.getAccounts()) {
            repository.deleteAccount(account.getId());
        }
        assertThat(repository.getNumberOfAccounts(), is(0));
    }
}