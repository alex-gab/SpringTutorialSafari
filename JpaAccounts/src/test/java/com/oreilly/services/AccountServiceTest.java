package com.oreilly.services;

import com.oreilly.config.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@ActiveProfiles("test")
@Transactional
public class AccountServiceTest {

    @Autowired
    private AccountService service;

    @Test
    public void testGetBalance() {
        final BigDecimal balance = service.getBalance(1);
        assertThat(balance, is(equalTo(new BigDecimal("100.0"))));
    }

    @Test
    public void testDeposit() {
        final BigDecimal currentBalance = service.getBalance(1);
        final BigDecimal amount = new BigDecimal("50.0");
        service.deposit(1, amount);
        final BigDecimal desiredBalance = currentBalance.add(amount);

        assertThat(desiredBalance, is(closeTo(new BigDecimal("150.0"), new BigDecimal("0.001"))));
    }

    @Test
    public void testWithdraw() {
        final BigDecimal start = service.getBalance(1L);
        final BigDecimal amount = new BigDecimal("50.0");
        service.withdraw(1L, amount);
        final BigDecimal finish = start.subtract(amount);

        assertThat(finish, is(closeTo(service.getBalance(1L),
                new BigDecimal("0.001"))));
    }

    @Test
    public void testTransfer() {
        final BigDecimal acct1start = service.getBalance(1L);
        final BigDecimal acct2start = service.getBalance(2L);

        final BigDecimal amount = new BigDecimal("50.0");
        service.transfer(1L, 2L, amount);

        final BigDecimal acct1finish = acct1start.subtract(amount);
        final BigDecimal acct2finish = acct2start.add(amount);

        assertThat(acct1finish, is(closeTo(service.getBalance(1L),
                new BigDecimal("0.001"))));
        assertThat(acct2finish, is(closeTo(service.getBalance(2L),
                new BigDecimal("0.001"))));
    }
}