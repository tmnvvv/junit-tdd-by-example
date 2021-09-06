package ru.ithub.entity;

import ru.ithub.currency.Currency;

import java.util.Objects;

public class Check implements Expression {
    protected double amount;
    protected Currency currency;

    public Check(double amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }


    public double getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Check check = (Check) o;
        return Double.compare(check.amount, amount) == 0 && currency == check.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }

    @Override
    public String toString() {
        return "Check{" +
                "amount=" + amount +
                ", currency='" + currency.name() + '\'' +
                '}';
    }


    public Check times(int multiplier) {
        return new Check(this.amount * multiplier, this.currency);
    }

    public Expression plus(double amount) {
        return new Check(this.amount + amount, this.currency);
    }

    public Expression minus(double amount) {
        return new Check(this.amount - amount, this.currency);
    }
}
