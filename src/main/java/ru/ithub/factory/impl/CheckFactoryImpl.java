package ru.ithub.factory.impl;

import ru.ithub.currency.Currency;
import ru.ithub.entity.Check;
import ru.ithub.factory.CheckFactory;
import ru.ithub.factory.LoggerFactory;
import ru.ithub.util.logging.AutoLoggedComponent;

import java.util.logging.Logger;

public class CheckFactoryImpl implements CheckFactory, AutoLoggedComponent {
    private final Logger logger = LoggerFactory.getLogger(CheckFactory.class.getName());

    public CheckFactoryImpl() { }

    @Override
    public Logger getLogger() {
        return logger;
    }

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
