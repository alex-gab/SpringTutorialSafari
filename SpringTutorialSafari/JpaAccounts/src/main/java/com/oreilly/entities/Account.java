package com.oreilly.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public final class Account {
    @Id
    private long id;
    private BigDecimal balance;

    public Account() {}

    public Account(final long id, final BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }

    public final long getId() {
        return id;
    }

    public final void setId(final long id) {
        this.id = id;
    }

    public final BigDecimal getBalance() {
        return balance;
    }

    public final void setBalance(final BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        return id == account.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
