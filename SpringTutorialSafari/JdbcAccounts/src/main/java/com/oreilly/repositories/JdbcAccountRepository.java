package com.oreilly.repositories;

import com.oreilly.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public final class JdbcAccountRepository implements AccountRepository {
    private final JdbcTemplate template;
    private long nextId = 4;

    @Autowired
    public JdbcAccountRepository(final DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    @Override
    public final List<Account> getAccounts() {
        final String statement = "select * from account";
        return template.query(statement, new AccountMapper());
    }

    @Override
    public final Account getAccount(final long id) {
        final String statement = "select * from account where id=?";
        return template.queryForObject(statement, new AccountMapper(), id);
    }

    @Override
    public final int getNumberOfAccounts() {
        final String statement = "select count(*) from account";
        return template.queryForObject(statement, Integer.class);
    }

    @Override
    public final long createAccount(final BigDecimal initialBalance) {
        final String statement = "insert into account(id,balance) values(?,?)";
        final long id = nextId++;
        template.update(statement, id, initialBalance);
        return id;
    }

    @Override
    public final void updateAccount(final Account account) {
        final String statement = "update account set balance=? where id=?";
        template.update(statement, account.getBalance(), account.getId());
    }

    @Override
    public final int deleteAccount(final long id) {
        final String statement = "delete from account where id=?";
        return template.update(statement, id);
    }

    private static final class AccountMapper implements RowMapper<Account> {
        @Override
        public final Account mapRow(final ResultSet rs, final int rowNum) throws SQLException {
            return new Account(rs.getLong("id"), rs.getBigDecimal("balance"));
        }
    }
}
