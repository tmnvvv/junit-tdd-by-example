package ru.ithub.factory.impl;

import ru.ithub.entity.Check;
import ru.ithub.currency.Currency;
import ru.ithub.factory.CheckFactory;

public class CheckFactoryImpl implements CheckFactory {
    @Override
    public Check dollar(double amount) {
        return Check.newBuilder()
                .setAmount(amount)
                .setCurrency(Currency.USD)
                .build();
    }

    @Override
    public Check euro(double amount) {
        return Check.newBuilder()
                .setAmount(amount)
                .setCurrency(Currency.EUR)
                .build();
    }

    @Override
    public Check rubble(double amount) {
        return Check.newBuilder()
                .setAmount(amount)
                .setCurrency(Currency.RUB)
                .build();
    }

    @Override
    public Check franc(double amount) {
        return Check.newBuilder()
                .setAmount(amount)
                .setCurrency(Currency.CHF)
                .build();
    }
}
