package com.checkout.domain.model;


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

  public Money add(Money other) {
    validateCurrency(other);
    return new Money(this.amount.add(other.amount), currency);
  }

  public BigDecimal amount() { return amount; }
  public String currency() { return currency; }

  private void validateCurrency(Money other) {
    if (!Objects.equals(this.currency, other.currency)) {
      throw new IllegalArgumentException("Monedas distintas");
    }
  }

  @Override public String toString() { return amount + " " + currency; }
}

