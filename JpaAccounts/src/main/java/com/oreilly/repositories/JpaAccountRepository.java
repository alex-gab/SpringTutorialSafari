package com.oreilly.repositories;

import com.oreilly.entities.Account;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

@Repository
public final class JpaAccountRepository implements AccountRepository {
    private long nextId = 4;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public final List<Account> getAccounts() {
        return entityManager.createQuery("select a from Account a", Account.class).getResultList();
    }

    @Override
    public final Account getAccount(final long id) {
        return entityManager.find(Account.class, id);
    }

    @Override
    public final int getNumberOfAccounts() {
        final String jpaTxt = "select count(a.id) from Account a";
        return entityManager.createQuery(jpaTxt, Long.class).getSingleResult().intValue();
    }

    @Override
    public final long createAccount(final BigDecimal initialBalance) {
        final long id = nextId++;
        entityManager.persist(new Account(id, initialBalance));
        return id;
    }

    @Override
    public final int deleteAccount(final long id) {
        final Account account = getAccount(id);
        if (account != null) {
            entityManager.remove(account);
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public final void updateAccount(final Account account) {
        entityManager.merge(account);
    }
}
