package ru.ithub.factory.impl;

import ru.ithub.entity.Check;
import ru.ithub.currency.Currency;
import ru.ithub.factory.CheckFactory;

public class CheckFactoryImpl implements CheckFactory {
    @Override
    public Check dollar(double amount) {
        return new Check(amount, Currency.USD);
    }

    @Override
    public Check euro(double amount) {
        return new Check(amount, Currency.EUR);
    }

    @Override
    public Check rubble(double amount) {
        return new Check(amount, Currency.RUB);
    }

    @Override
    public Check franc(double amount) {
        return new Check(amount, Currency.CHF);
    }
}
