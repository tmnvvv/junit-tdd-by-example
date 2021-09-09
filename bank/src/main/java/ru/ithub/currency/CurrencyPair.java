package ru.ithub.currency;

import java.util.Objects;

public class CurrencyPair {
    private final Currency from;
    private final Currency to;

    private CurrencyPair(Currency from, Currency to) {
        this.from = from;
        this.to = to;
    }

    public static CurrencyPair of(Currency from, Currency to) {
        return new CurrencyPair(from, to);
    }

    public Currency getFrom() {
        return from;
    }

    public Currency getTo() {
        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyPair that = (CurrencyPair) o;
        return from == that.from && to == that.to;
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }
}
