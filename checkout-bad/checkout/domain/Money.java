package com.checkout.domain;

import java.math.BigDecimal;
import java.util.Objects;

public final class Money {
    private final BigDecimal amount;
    private final String currency;

    public Money(BigDecimal amount, String currency) {
        if (amount == null || currency == null) throw new IllegalArgumentException("Money inválido");
        this.amount = amount;
        this.currency = currency;
    }

    public static Money of(double amount, String currency) {
        return new Money(BigDecimal.valueOf(amount), currency);
    }

    public BigDecimal amount() { return amount; }
    public String currency() { return currency; }

    @Override public String toString() { return amount + " " + currency; }
}
