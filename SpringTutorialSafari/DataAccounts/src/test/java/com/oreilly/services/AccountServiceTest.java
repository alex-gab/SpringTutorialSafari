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
@Transactional
@ActiveProfiles("test")
public class AccountServiceTest {

    @Autowired
    private AccountService service;

    @Test
    public void testGetBalance() throws Exception {
        final BigDecimal balance = service.getBalance(1);
        assertThat(balance, is(equalTo(new BigDecimal("100.0"))));
    }

    @Test
    public void testDeposit() throws Exception {
        BigDecimal start = service.getBalance(1L);
        BigDecimal amount = new BigDecimal("50.0");
        service.deposit(1L, amount);
        BigDecimal finish = start.add(amount);

        assertThat(finish, is(closeTo(service.getBalance(1L),
                new BigDecimal("0.01"))));
    }

    @Test
    public void testWithdraw() throws Exception {
        BigDecimal start = service.getBalance(1L);
        BigDecimal amount = new BigDecimal("50.0");
        service.withdraw(1L, amount);
        BigDecimal finish = start.subtract(amount);

        assertThat(finish, is(closeTo(service.getBalance(1L),
                new BigDecimal("0.01"))));
    }

    @Test
    public void testTransfer() throws Exception {
        BigDecimal acct1start = service.getBalance(1L);
        BigDecimal acct2start = service.getBalance(2L);

        BigDecimal amount = new BigDecimal("50.0");
        service.transfer(1L, 2L, amount);

        BigDecimal acct1finish = acct1start.subtract(amount);
        BigDecimal acct2finish = acct2start.add(amount);

        assertThat(acct1finish, is(closeTo(service.getBalance(1L),
                new BigDecimal("0.01"))));
        assertThat(acct2finish, is(closeTo(service.getBalance(2L),
                new BigDecimal("0.01"))));
    }
}