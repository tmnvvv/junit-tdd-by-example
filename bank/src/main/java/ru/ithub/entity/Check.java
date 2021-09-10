package ru.ithub.entity;

import ru.ithub.currency.Currency;

import java.util.Objects;
import java.util.UUID;

public class Check {
    protected UUID id;
    protected double amount;
    protected Currency currency;

    private Check() { }

    public static Builder newBuilder() {
        return new Check().new Builder();
    }

    public double getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public UUID getId() {
        return id;
    }

//    public Check setId(UUID id) {
//        this.id = id;
//
//        return this;
//    }
//
//    public Check setAmount(double amount) {
//        this.amount = amount;
//
//        return this;
//    }
//
//    public Check setCurrency(Currency currency) {
//        this.currency = currency;
//
//        return this;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Check check = (Check) o;
        return Double.compare(check.amount, amount) == 0 && Objects.equals(id, check.id) && currency == check.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, currency);
    }

    @Override
    public String toString() {
        return "Check{" +
                "id=" + id +
                ", amount=" + amount +
                ", currency=" + currency +
                '}';
    }

    public Check times(int multiplier) {
        return  Check.newBuilder()
                .setId(this.id)
                .setAmount(this.amount * multiplier)
                .setCurrency(this.currency)
                .build();
    }

    public Check plus(double amount) {
        return Check.newBuilder()
                .setId(this.id)
                .setAmount(this.amount + amount)
                .setCurrency(this.currency)
                .build();
    }

    public Check minus(double amount) {
        return Check.newBuilder()
                .setId(this.id)
                .setAmount(this.amount - amount)
                .setCurrency(this.currency)
                .build();
    }

    public class Builder {
        private Builder() { }

        public Builder setId(UUID id) {
            Check.this.id = id;

            return this;
        }

        public Builder setAmount(double amount) {
            Check.this.amount = amount;

            return this;
        }

        public Builder setCurrency(Currency currency) {
            Check.this.currency = currency;

            return this;
        }

        public Check build() {
            if (Check.this.id == null) Check.this.id = UUID.randomUUID();

            return Check.this;
        }
    }
}
